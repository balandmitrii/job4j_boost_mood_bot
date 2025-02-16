package ru.job4j.bmb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.bmb.model.Achievment;

import java.util.List;

@Repository
public interface AchievementRepository extends CrudRepository<Achievment, Long> {
    List<Achievment> findAll();
}
