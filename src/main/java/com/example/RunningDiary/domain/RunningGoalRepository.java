package com.example.RunningDiary.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface RunningGoalRepository extends CrudRepository<RunningGoal, Long> {
	List<RunningGoal> findByName(String name);
}
