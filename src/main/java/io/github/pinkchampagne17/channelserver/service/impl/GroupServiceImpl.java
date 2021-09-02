package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.Group;
import io.github.pinkchampagne17.channelserver.entity.GroupMember;
import io.github.pinkchampagne17.channelserver.entity.GroupMemberType;
import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.GidCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.GroupAddMemberParameters;
import io.github.pinkchampagne17.channelserver.parameters.GroupCreateParameters;
import io.github.pinkchampagne17.channelserver.repository.GidRepository;
import io.github.pinkchampagne17.channelserver.repository.GroupRepository;
import io.github.pinkchampagne17.channelserver.repository.RequestRepository;
import io.github.pinkchampagne17.channelserver.service.GroupService;
import io.github.pinkchampagne17.channelserver.util.HashId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private GidRepository gidRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public Group createGroup(User owner, GroupCreateParameters parameters) {
        var txStatus = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        var gidCreateParameters = new GidCreateParameters() {{
            setLink(parameters.getLink());
        }};
        try {
            this.gidRepository.createGid(gidCreateParameters);

            var gid = gidCreateParameters.getGid();
            var group = parameters.asGroup();
            group.setGid(gid);
            group.setHashId(HashId.encodeOne(gid));

            this.groupRepository.createGroup(group);
            this.addMember(new GroupAddMemberParameters() {{
                setGroupGid(gid);
                setUserGid(owner.getGid());
                setType(GroupMemberType.OWNER);
            }});

            this.transactionManager.commit(txStatus);

        } catch (Exception e) {
            this.transactionManager.rollback(txStatus);

            if (e instanceof DuplicateKeyException) {
                throw new ParameterInvalidException("This link already exists.");
            }
            throw e;
        }
        return this.groupRepository.queryGroupByGid(gidCreateParameters.getGid());
    }

    @Override
    public Group queryGroupByGid(Long gid) {
        return this.groupRepository.queryGroupByGid(gid);
    }

    @Override
    public Group queryGroupByHashId(String hashID) {
        var gid = HashId.decodeOne(hashID);
        return this.queryGroupByGid(gid);
    }

    @Override
    public List<Group> queryGroupByUserGid(Long gid) {
        return this.groupRepository.queryGroupByUserGid(gid);
    }

    @Override
    public List<GroupMember> queryMembersOfGroup(Long gid) {
        return this.groupRepository.queryMembersOfGroup(gid);
    }

    @Override
    public boolean isUserInGroup(Long groupGid, Long userGid) {
        return this.groupRepository.isUserInGroup(groupGid, userGid);
    }

    @Override
    public boolean isUserOwnerOrAdmin(Long groupGid, Long userGid) {
        return this.groupRepository.isOwnerOrAdmin(groupGid, userGid);
    }

    @Override
    public void addMember(GroupAddMemberParameters parameters) {
        this.groupRepository.addMember(parameters);
    }

    @Override
    public void removeMember(Long groupGid, Long userGid) {
        var txStatus = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            this.requestRepository.removeRequest(userGid, groupGid);
            this.groupRepository.removeMember(groupGid, userGid);

            this.transactionManager.commit(txStatus);
        } catch (Exception e) {
            this.transactionManager.rollback(txStatus);
            throw e;
        }
    }
}
