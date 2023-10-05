package com.ms.user.service.controller;

import com.ms.user.service.configuration.AppsConstant;
import com.ms.user.service.entities.User;
import com.ms.user.service.payload.ApiResponse;
import com.ms.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppsConstant.API + "/" + AppsConstant.APP_VERSION + "/" + AppsConstant.USER_BASE_URL)
public class UserController {

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
    public ResponseEntity<User> getUser(@PathVariable("user_id") String userId){

        return ResponseEntity.ok(this.userService
                .getUser(userId));
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
