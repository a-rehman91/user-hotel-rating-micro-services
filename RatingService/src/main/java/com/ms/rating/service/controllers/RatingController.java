package com.ms.rating.service.controllers;

import com.ms.rating.service.configurations.AppsConstant;
import com.ms.rating.service.entities.Rating;
import com.ms.rating.service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppsConstant.API + "/" + AppsConstant.APP_VERSION + "/" + AppsConstant.RATING_BASE_URL)
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.ratingService.create(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating(){

        return ResponseEntity.ok(this.ratingService.getRatings());
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping(AppsConstant.USER_BASE_URL + "/{user_id}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("user_id") String userId){

        return ResponseEntity.ok(this.ratingService
                .getRatingByUserId(userId));
    }

    @GetMapping(AppsConstant.HOTEL_BASE_URL + "/{hotel_id}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable("hotel_id") String hotelId){

        return ResponseEntity.ok(this.ratingService
                .getRatingByHotelId(hotelId));
    }
}
