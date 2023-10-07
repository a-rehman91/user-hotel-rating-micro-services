package com.ms.user.service;

import com.ms.user.service.entities.Rating;
import com.ms.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

/*
	@Autowired
	private RatingService ratingService;

	@Test
	void createRating(){

		Rating rating = Rating.builder()
				.userId("fe2bec0b-c2e2-41f7-8949-16cce1d90115")
				.hotelId("fe2bec0b-c2e2-41f7-8949-16cce1d90115")
				.rating(2)
				.remarks("Very bad experience.")
				.build();

		ratingService.create(rating);

		System.out.println("New Rating Created....");
	}
*/


}
