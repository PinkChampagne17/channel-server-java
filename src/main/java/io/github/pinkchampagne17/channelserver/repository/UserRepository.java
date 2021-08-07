package io.github.pinkchampagne17.channelserver.repository;

import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.parameters.CreateUserParameters;
import io.github.pinkchampagne17.channelserver.parameters.GetUsersParameters;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRepository {
    User getUserById(Long id);
    List<User> getUsers(GetUsersParameters parameters);
    int createGidAndUsername(CreateUserParameters parameters);
    int createUserByGid(CreateUserParameters parameters);
}
