package io.github.pinkchampagne17.channelserver.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Request {
    private Long applicantGid;
    private Long targetGid;
    private String reason;
    private RequestStatus status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
