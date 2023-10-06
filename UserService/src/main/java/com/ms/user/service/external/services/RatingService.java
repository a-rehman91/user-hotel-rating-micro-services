package com.ms.user.service.external.services;

import com.ms.user.service.configuration.AppsConstant;
import com.ms.user.service.entities.Rating;
import com.ms.user.service.payload.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name = AppsConstant.RATING_SERVICE)
public interface RatingService {

    // Create Rating external service
    @PostMapping(AppsConstant.API + "/" + AppsConstant.APP_VERSION + "/" + AppsConstant.RATING_BASE_URL)
    ResponseEntity<Rating> create(Rating rating);

    // Update Rating external service
    @PutMapping(AppsConstant.API + "/" + AppsConstant.APP_VERSION + "/" + AppsConstant.RATING_BASE_URL + "/{rating_id}")
    ResponseEntity<Rating> update(@PathVariable("rating_id") String ratingId, Rating rating);

    // Delete Rating external service
    @DeleteMapping(AppsConstant.API + "/" + AppsConstant.APP_VERSION + "/" + AppsConstant.RATING_BASE_URL + "/{rating_id}")
    ResponseEntity<ApiResponse> delete(@PathVariable("rating_id") String ratingId);

}
