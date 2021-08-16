package io.github.pinkchampagne17.channelserver.repository;

import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.parameters.UserCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.UserQueryParameters;
import io.github.pinkchampagne17.channelserver.parameters.UserUpdateParameters;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRepository {
    User getUserById(Long gid);
    List<User> getUsers(UserQueryParameters parameters);
    int createGidAndUsername(UserCreateParameters parameters);
    int createUserByGid(UserCreateParameters parameters);
    int updateUser(UserUpdateParameters parameters);
}
