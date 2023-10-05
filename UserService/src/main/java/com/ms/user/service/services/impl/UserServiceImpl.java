package com.ms.user.service.services.impl;

import com.ms.user.service.configuration.AppsConstant;
import com.ms.user.service.entities.Hotel;
import com.ms.user.service.entities.Rating;
import com.ms.user.service.entities.User;
import com.ms.user.service.exceptions.ResourceNotFoundException;
import com.ms.user.service.external.services.HotelService;
import com.ms.user.service.repositories.UserRepository;
import com.ms.user.service.services.UserService;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HotelService hotelService;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {

        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppsConstant.USER_NOT_FOUND + " : " + userId));

        Rating[] ratingsOfUser = restTemplate.getForObject("http://"+ AppsConstant.RATING_SERVICE + "/" + AppsConstant.API + "/" + AppsConstant.APP_VERSION + "/" + AppsConstant.RATING_BASE_URL + "/" + AppsConstant.USER_BASE_URL + "/" + userId, Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {

            // api call to hotel service to get the hotel by using rest template.

            // ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://" + AppsConstant.HOTEL_SERVICE + "/" + AppsConstant.API + "/" + AppsConstant.APP_VERSION + "/" + AppsConstant.HOTEL_BASE_URL + "/" + rating.getHotelId(), Hotel.class);
            // Hotel hotel = hotelEntity.getBody();
            // logger.info("response status code: {}", hotelEntity.getStatusCode());

            // api call to hotel service to get the hotel by using feign client.
            Hotel hotel = this.hotelService.getHotel(rating.getHotelId());

            // set the hotel to rating
            rating.setHotel(hotel);

            // return the rating
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }

    @Override
    public void deleteUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppsConstant.USER_NOT_FOUND + " : " + userId));
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User user, String userId) {
        return null;
    }
}
