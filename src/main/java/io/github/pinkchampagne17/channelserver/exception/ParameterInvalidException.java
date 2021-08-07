package io.github.pinkchampagne17.channelserver.exception;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class ParameterInvalidException extends RuntimeException {
    private final List<String> allErrorMessages;

    public ParameterInvalidException(List<String> messages) {
        super(String.join(", ", messages));
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
