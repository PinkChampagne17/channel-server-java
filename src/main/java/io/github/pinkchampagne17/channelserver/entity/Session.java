package io.github.pinkchampagne17.channelserver.entity;

import io.github.pinkchampagne17.channelserver.dto.SessionDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
public class Session extends SessionBase{
    private LocalDateTime expires;

    public SessionDTO asSessionDTO() {
        var dto = new SessionDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
