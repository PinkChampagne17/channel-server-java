package io.github.pinkchampagne17.channelserver.repository;

import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.parameters.CreateRequestParameters;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RequestRepository {
    List<Request> getRequestsByGid(Long gid);
    int createOrUpdateRequest(CreateRequestParameters parameters);
}
