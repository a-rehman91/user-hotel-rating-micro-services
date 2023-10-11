package com.ms.user.service.controller;

import com.ms.user.service.configuration.AppsConstant;
import com.ms.user.service.entities.User;
import com.ms.user.service.payload.ApiResponse;
import com.ms.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppsConstant.API + "/" + AppsConstant.APP_VERSION + "/" + AppsConstant.USER_BASE_URL)
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.userService
                        .saveUser(user));
    }

    @GetMapping("/{user_id}")
//    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getUser(@PathVariable("user_id") String userId){

        logger.info("Getting the user of id : {}", userId);
        return ResponseEntity.ok(this.userService
                .getUser(userId));
    }

    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex){

        logger.info("Fallback method is executed because rating/hotel service is down : {}", ex.getMessage());

        ex.printStackTrace();
        User user = User.builder()
                .userId("Dummy")
                .email("Dummy@gmail.com")
                .about("Dummy user because service is down.")
                .build();

        return new ResponseEntity<>(user, HttpStatus.OK);

     }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){

        return ResponseEntity.ok(this.userService
                .getAllUser());
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("user_id") String userId){

        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppsConstant.USER_DELETED, true, HttpStatus.OK), HttpStatus.OK);
    }
}
