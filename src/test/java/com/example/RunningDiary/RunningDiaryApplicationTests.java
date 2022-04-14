package com.example.RunningDiary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.RunningDiary.web.RunningController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RunningDiaryApplicationTests {
	
	@Autowired
	private RunningController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
