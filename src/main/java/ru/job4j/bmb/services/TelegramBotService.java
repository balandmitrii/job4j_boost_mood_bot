package ru.job4j.bmb.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import ru.job4j.bmb.content.Content;

@Service
public class TelegramBotService {
    private final BotCommandHandler handler;

    public TelegramBotService(BotCommandHandler handler) {
        this.handler = handler;
    }

    public void receive(Content content) {
        handler.receive(content);
    }

    @PostConstruct
    public void init() {
        System.out.println("Class " + getClass() + " is going through "
                + "@PostConstruct init.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Class " + getClass() + "  will be destroyed via @PreDestroy.");
    }
}