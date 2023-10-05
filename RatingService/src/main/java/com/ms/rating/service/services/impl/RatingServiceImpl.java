package com.ms.rating.service.services.impl;

import com.ms.rating.service.entities.Rating;
import com.ms.rating.service.repositories.RatingRepository;
import com.ms.rating.service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating create(Rating rating) {

        return this.ratingRepository
                .save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return this.ratingRepository
                .findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {

        return this.ratingRepository
                .findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return this.ratingRepository
                .findByHotelId(hotelId);
    }
}
