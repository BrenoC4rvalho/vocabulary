package com.breno.vocabulary.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAiChatService {

    private final ChatClient chatClient;

    public OpenAiChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getVocabulary() {
        return this.chatClient.prompt()
                .user("me fale tres palavras")
                .call()
                .content();
    }


}
