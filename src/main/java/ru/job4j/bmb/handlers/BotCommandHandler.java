package ru.job4j.bmb.handlers;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class BotCommandHandler {

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
