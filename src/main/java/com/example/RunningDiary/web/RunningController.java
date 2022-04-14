package com.example.RunningDiary.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.RunningDiary.domain.RunningGoalRepository;
import com.example.RunningDiary.domain.Workout;
import com.example.RunningDiary.domain.WorkoutRepository;

@Controller
public class RunningController {
	@Autowired
	private WorkoutRepository wrepository;

	@Autowired
	private RunningGoalRepository rgrepository;
	//login page
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// show all workouts
	@RequestMapping(value = { "/", "/workoutlist" })
	public String viewWorkouts(Model model) {
		model.addAttribute("workouts", wrepository.findAll());
		return "workoutlist";
		
	}

	// restful service for showing all workouts
	@RequestMapping(value = "/workouts", method = RequestMethod.GET)
	public @ResponseBody List<Workout> bookListRest() {
		return (List<Workout>) wrepository.findAll();
	}

	// restful service for searching by id
	@RequestMapping(value = "/workout/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Workout> findWorkoutRest(@PathVariable("id") Long workoutId) {
		return wrepository.findById(workoutId);
	}

	// add workout
	@RequestMapping(value = "/add")
	public String addWorkout(Model model) {
		model.addAttribute("workout", new Workout());
		model.addAttribute("runninggoals", rgrepository.findAll());
		return "addworkout";
	}

	// save workout
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Workout workout) {
		wrepository.save(workout);
		return "redirect:workoutlist";
	}

	// delete workout
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteWorkout(@PathVariable("id") Long workoutId, Model model) {
		wrepository.deleteById(workoutId);
		return "redirect:../workoutlist";
	}

	// edit workout
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editWorkout(@PathVariable("id") Long workoutId, Model model) {
		model.addAttribute("workout", wrepository.findById(workoutId));
		model.addAttribute("runninggoals", rgrepository.findAll());
		return "editworkout";
	}

}
