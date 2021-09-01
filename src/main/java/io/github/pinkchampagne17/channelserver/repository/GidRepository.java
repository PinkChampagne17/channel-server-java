package io.github.pinkchampagne17.channelserver.repository;

import io.github.pinkchampagne17.channelserver.parameters.GidCreateParameters;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GidRepository {
    int createGid(GidCreateParameters parameters);
}
