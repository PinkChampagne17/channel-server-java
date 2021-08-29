package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.ChatMessage;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.ChatMessageCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.ChatMessagePMQueryParameters;
import io.github.pinkchampagne17.channelserver.parameters.ChatMessageQueryParameters;
import io.github.pinkchampagne17.channelserver.repository.ChatMessageRepository;
import io.github.pinkchampagne17.channelserver.service.ChatMessageService;
import io.github.pinkchampagne17.channelserver.service.FriendshipService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipService friendshipService;

    @Override
    public void createMessage(Long senderGid, ChatMessageCreateParameters parameters) {

        var receiver = this.userService.getUserByHashId(parameters.getReceiverHashId());
        if (receiver == null) {
            throw new ParameterInvalidException("This receiverId not available.");
        }

        var areTheyFriend = this.friendshipService.AreTheyFriend(senderGid, receiver.getGid());
        if (!areTheyFriend) {
            throw new ParameterInvalidException("This receiver is not your friend.");
        }

        var chatId = this.GetChatIdByUserGId(senderGid, receiver.getGid());
        var message = ChatMessage.builder()
                .ChatId(chatId)
                .senderGid(senderGid)
                .type(parameters.getType())
                .content(parameters.getContent())
                .build();

        this.chatMessageRepository.createMessage(message);
    }

    @Override
    public List<ChatMessage> queryPrivateMessages(ChatMessagePMQueryParameters parameters) {
        var chatId = this.GetChatIdByUserGId(parameters.getUserGid(), parameters.getAnotherUserGid());
        var queryParams = new ChatMessageQueryParameters() {{
            setChatId(chatId);
        }};
        return this.chatMessageRepository.queryChatMessages(queryParams);
    }

    // The code below that generate a ChatId with the GID of sender and receiver
    // Lesser GID at the left, bigger GID at the right, '-' is between two GID
    // e.g. "114514-1919810"
    public String GetChatIdByUserGId(Long userGid, Long anotherUserGid) {
        var gid1 = userGid;
        var gid2 = anotherUserGid;

        if (anotherUserGid < userGid) {
            gid1 = anotherUserGid;
            gid2 = userGid;
        }

        return String.format("%d-%d", gid1, gid2);
    }

}
