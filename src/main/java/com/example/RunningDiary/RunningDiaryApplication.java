package com.example.RunningDiary;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.RunningDiary.domain.RunningGoal;
import com.example.RunningDiary.domain.RunningGoalRepository;
import com.example.RunningDiary.domain.User;
import com.example.RunningDiary.domain.UserRepository;
import com.example.RunningDiary.domain.Workout;
import com.example.RunningDiary.domain.WorkoutRepository;

@SpringBootApplication
public class RunningDiaryApplication {
	private static final Logger log = LoggerFactory.getLogger(RunningDiaryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RunningDiaryApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(WorkoutRepository wrepository, RunningGoalRepository rgrepository,
			UserRepository urepository) {
		return (args) -> {
			log.info("save workouts");
			
			// running goals
			rgrepository.save(new RunningGoal("Speed"));
			rgrepository.save(new RunningGoal("Endurance"));
			rgrepository.save(new RunningGoal("Basic"));
			
			//mock up workouts
			wrepository.save(new Workout("Peruskunto", 5, 45, LocalDate.of(2022, 3, 14),
					rgrepository.findByName("Speed").get(0)));
			wrepository.save(new Workout("Nopeus", 2, 12, LocalDate.of(2022, 3, 15),
					rgrepository.findByName("Endurance").get(0)));

			// Two users user/user admin/admin
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all workouts");
			for (Workout workout : wrepository.findAll()) {
				log.info(workout.toString());
			}
		};

	}
}
