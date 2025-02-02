package ru.job4j.bmb.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.job4j.bmb.repository.UserRepository;

@Service
public class RemindService implements BeanNameAware {

    private String beanName;
    private final TgRemoteService tgRemoteService;
    private final UserRepository userRepository;

    public RemindService(TgRemoteService tgRemoteService, UserRepository userRepository) {
        this.tgRemoteService = tgRemoteService;
        this.userRepository = userRepository;
    }

    @Scheduled(fixedRateString = "${remind.period}")
    public void ping() {
        for (var user : userRepository.findAll()) {
            var message = new SendMessage();
            message.setChatId(user.getChatId());
            message.setText("Ping");
            tgRemoteService.send(message);
        }
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

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
