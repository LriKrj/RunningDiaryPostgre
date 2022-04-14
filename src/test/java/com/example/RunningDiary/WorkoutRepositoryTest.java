package com.example.RunningDiary;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.RunningDiary.domain.RunningGoal;
import com.example.RunningDiary.domain.Workout;
import com.example.RunningDiary.domain.WorkoutRepository;




@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WorkoutRepositoryTest {
	
	@Autowired
	private WorkoutRepository wrepository;
	
	//Check that test returns a single workout with title
	@Test
	public void findByTitleShouldReturnWorkout() {
		List<Workout> books = wrepository.findByTitle("Peruskunto");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getDistance()).isEqualTo(5);

	}
	//create a mock up workout and test that it exists
	@Test
	public void createWorkout() {
		Workout workout = new Workout("Palauttava lenkki", 2, 12, LocalDate.of(2022, 2, 14),
				new RunningGoal("Recovery"));
		wrepository.save(workout);
		assertThat(workout.getId()).isNotNull();
	}
	//Tests that mock up workout gets deleted
	@Test
	public void deleteNewWorkout() {
		List<Workout> workouts = wrepository.findByTitle("Peruskunto");
		Workout workout = workouts.get(0);
		wrepository.delete(workout);
		List<Workout> newWorkouts = wrepository.findByTitle("Peruskunto");
		assertThat(newWorkouts).hasSize(0);
	}


}
