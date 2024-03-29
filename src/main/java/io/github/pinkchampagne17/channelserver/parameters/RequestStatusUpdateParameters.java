package io.github.pinkchampagne17.channelserver.parameters;

import io.github.pinkchampagne17.channelserver.entity.RequestStatus;
import lombok.Data;

@Data
public class RequestStatusUpdateParameters {
    private Long applicantGid;
    private Long targetGid;
    private RequestStatus status;
}
