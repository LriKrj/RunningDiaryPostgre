package com.example.RunningDiary.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class RunningGoal {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long goalid;
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "runninggoal")
	private List<Workout> workouts;

	public RunningGoal() {
		
	}

	public RunningGoal(String name) {
		super();
		this.name = name;
	}

	public RunningGoal(Long goalid, String name, List<Workout> workouts) {
		super();
		this.goalid = goalid;
		this.name = name;
		this.workouts = workouts;
	}

	public Long getGoalid() {
		return goalid;
	}

	public void setGoalid(Long goalid) {
		this.goalid = goalid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Workout> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}

	@Override
	public String toString() {
		return "RunningGoal [goalid=" + goalid + ", name=" + name + "]";
	}

	
	

}
