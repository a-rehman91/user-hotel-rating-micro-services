package com.ms.rating.service.services;

import com.ms.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);
    List<Rating> getRatings();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
