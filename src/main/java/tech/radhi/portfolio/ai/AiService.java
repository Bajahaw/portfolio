package tech.radhi.portfolio.ai;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    private final ChatClient chatClient;

    public AiService(ChatClient chatClient) {
        this.chatClient = chatClient.mutate()
                .defaultSystem("""
                     You are a helpful assistant. You are being used in a personal
                     portfolio website to answer questions about the site content and its owner.
                     You should be concise, informative, and helpful in your responses.
                    """)
                .defaultOptions(
                        ChatOptions.builder()
                                .model("meta-llama/llama-4-scout-17b-16e-instruct")
                                .maxTokens(1024)
                                .temperature(0.7)
                                .build()
                ).build();
    }
    public JsonNode chat(String prompt) {
        return chatClient
                .prompt(prompt)
                .call()
                .entity(JsonNode.class);
    }
}
