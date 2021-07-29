package io.github.pinkchampagne17.channelserver;

import io.github.pinkchampagne17.channelserver.exception.SocketMessageDataIllegalException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ChannelServerApplication {

	public static void main(String[] args) throws IOException, SocketMessageDataIllegalException {
		SpringApplication.run(ChannelServerApplication.class, args);
	}

}
