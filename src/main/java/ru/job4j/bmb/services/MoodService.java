package ru.job4j.bmb.services;

import org.springframework.stereotype.Service;
import ru.job4j.bmb.content.Content;
import ru.job4j.bmb.model.MoodLog;
import ru.job4j.bmb.model.User;
import ru.job4j.bmb.repository.AchievementRepository;
import ru.job4j.bmb.repository.MoodLogRepository;
import ru.job4j.bmb.repository.UserRepository;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с настроениями пользователей.
 */
@Service
public class MoodService {
    /**
     * Репозиторий для работы с записями настроений (MoodLogs).
     */
    private final MoodLogRepository moodLogRepository;

    /**
     * Система рекомендаций по настроению.
     */
    private final RecommendationEngine recommendationEngine;

    /**
     * Репозиторий для работы с пользователями.
     */
    private final UserRepository userRepository;

    /**
     * Репозиторий для работы с достижениями (Awards).
     */
    private final AchievementRepository achievementRepository;

    /**
     * Формат даты и времени.
     */
    private final String dateTimeFormatter = "dd-MM-yyyy HH:mm";

    /**
     * Конструктор сервиса.
     *
     * @param moodLogRepository Репозиторий для работы с записями настроений (MoodLogs)
     * @param recommendationEngine Система рекомендаций по настроению
     * @param userRepository Репозиторий для работы с пользователями
     * @param achievementRepository Репозиторий для работы с достижениями (Awards)
     */
    public MoodService(MoodLogRepository moodLogRepository,
                       RecommendationEngine recommendationEngine,
                       UserRepository userRepository,
                       AchievementRepository achievementRepository) {
        this.moodLogRepository = moodLogRepository;
        this.recommendationEngine = recommendationEngine;
        this.userRepository = userRepository;
        this.achievementRepository = achievementRepository;
    }

    /**
     * Выбирает настроение для пользователя в зависимости от рекомендации системы.
     *
     * @param user   Пользователь, чей запрос обрабатывается
     * @param moodId Идентификатор настроения, которое необходимо выбрать
     * @return Содержание выбранного настроения (Content)
     */
    public Content chooseMood(User user, Long moodId) {
        return recommendationEngine.recommendFor(user.getChatId(), moodId);
    }

    /**
     * Возвращает содержимое для команды недельной отчетности о настроении.
     *
     * @param chatId Идентификатор чата пользователя
     * @param clientId Идентификатор клиента (пользователя)
     * @return Содержание для вывода в чат (Content) или пустая опциональная ячейка, если данные недоступны
     */
    public Optional<Content> weekMoodLogCommand(long chatId, Long clientId) {
        var content = new Content(chatId);
        return Optional.of(content);
    }

    /**
     * Возвращает содержимое для команды месячной отчетности о настроении.
     *
     * @param chatId Идентификатор чата пользователя
     * @param clientId Идентификатор клиента (пользователя)
     * @return Содержание для вывода в чат (Content) или пустая опциональная ячейка, если данные недоступны
     */
    public Optional<Content> monthMoodLogCommand(long chatId, Long clientId) {
        var content = new Content(chatId);
        return Optional.of(content);
    }

    /**
     * Формирует строку с отформатированными записями настроений.
     *
     * @param logs   Список записей настроений (MoodLog)
     * @param title  Заголовок для результата
     * @return Отформатированная строка с записями настроений или сообщение о их отсутствии, если список пуст
     */
    private String formatMoodLogs(List<MoodLog> logs, String title) {
        if (logs.isEmpty()) {
            return String.format("%s:\nNo mood logs found.", title);
        }
        StringBuilder sb = new StringBuilder(String.format("%s:\n", title));

        for (MoodLog log : logs) {
            String formattedDate = Instant.ofEpochSecond(log.getCreatedAt()).atZone(ZoneId.systemDefault())
                    .format(java.time.format.DateTimeFormatter.ofPattern(dateTimeFormatter));
            sb.append(String.format("%s: %s\n", formattedDate, log.getMood().getText()));
        }
        return sb.toString();
    }

    /**
     * Возвращает содержимое для команды поощрений (awards).
     *
     * @param chatId Идентификатор чата пользователя
     * @param clientId Идентификатор клиента (пользователя)
     * @return Содержание для вывода в чат (Content) или пустая опциональная ячейка, если данные недоступны
     */
    public Optional<Content> awards(long chatId, Long clientId) {
        var content = new Content(chatId);
        return Optional.of(content);
    }
}
