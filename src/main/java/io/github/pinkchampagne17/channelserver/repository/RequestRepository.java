package io.github.pinkchampagne17.channelserver.repository;

import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.parameters.RequestCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.RequestStatusUpdateParameters;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RequestRepository {
    List<Request> getRequestsByGid(Long gid);
    Request getRequestByGid(Long applicantGid, Long targetGid);
    int createOrUpdateRequest(Request request);
    int updateStatus(RequestStatusUpdateParameters parameters);
    int removeRequest(Long gid, Long anotherGid);
}
