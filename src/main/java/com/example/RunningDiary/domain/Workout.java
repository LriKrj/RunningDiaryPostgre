package com.example.RunningDiary.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class Workout {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	private String title;
	private double distance;
	private double duration;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "goalid")
	private RunningGoal runninggoal;

	public Workout() {
		super();
	}

	public Workout(String title, double distance, double duration, LocalDate date, RunningGoal runninggoal) {
		super();
		this.title = title;
		this.distance = distance;
		this.duration = duration;
		this.date = date;
		this.runninggoal = runninggoal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public RunningGoal getRunninggoal() {
		return runninggoal;
	}

	public void setRunninggoal(RunningGoal runninggoal) {
		this.runninggoal = runninggoal;
	}

	@Override
	public String toString() {
		return "Workout [id=" + id + ", title=" + title + ", distance=" + distance + ", duration=" + duration
				+ ", date=" + date + ", runninggoal=" + runninggoal + "]";
	}

	

	

}
