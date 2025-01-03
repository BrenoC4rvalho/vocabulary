package com.breno.vocabulary.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TwilioService {

    @Value("${twilio.account.sid}")
    private String accountSid;
    @Value("${twilio.auth.token}")
    private String authToken;

    private final WebClient.Builder webClientBuilder;

    public TwilioService(WebClient.Builder webClienteBuilder) {
        String url = "https://api.twilio.com/2010-04-01/Accounts/" + accountSid + "/Messages.json";
        this.webClientBuilder = webClienteBuilder
                .baseUrl(url);
    }

    public String sendWhatsappMessage(String message) {
        try {
            WebClient webClient = webClientBuilder.build();


        } catch (Exception e) {
            throw new RuntimeException("Error sending message", e);
        }

    }
}
