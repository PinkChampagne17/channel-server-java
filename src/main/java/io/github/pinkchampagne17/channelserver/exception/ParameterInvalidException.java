package io.github.pinkchampagne17.channelserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class ParameterInvalidException extends HttpException {
    private final List<String> allErrorMessages;

    public ParameterInvalidException(List<String> messages) {
        super(HttpStatus.BAD_REQUEST, ErrorCode.PARAMETER_INVALID, messages);
        this.allErrorMessages = messages;
    }

    public ParameterInvalidException(BindingResult bindingResult) {
        this(bindingResult
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList()));
    }

    public ParameterInvalidException(String message) {
        this(List.of(message));
    }

    public List<String> getAllErrorMessages() {
        return this.allErrorMessages;
    }
}
