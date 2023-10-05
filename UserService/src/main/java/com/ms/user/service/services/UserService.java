package com.ms.user.service.services;

import com.ms.user.service.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUser();
    User getUser(String userId);
    void deleteUser(String userId);
    User updateUser(User user, String userId);
}
