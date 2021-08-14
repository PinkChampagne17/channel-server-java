package io.github.pinkchampagne17.channelserver.parameters;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class CreateRequestParameters {

    @Null
    private Long applicantGid;

    @Null
    private Long targetGid;

    @NotNull
    @JsonAlias("targetId")
    private String targetHashId;

    @Size(max = 128)
    private String reason;
}
