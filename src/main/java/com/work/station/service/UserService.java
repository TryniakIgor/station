package com.work.station.service;

import com.work.station.dto.UserDTO;
import com.work.station.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDTO saveUser(User user);

    UserDTO getUser(String userName);

    void deleteUser(String userName);

    UserDTO updateUser(String userName, User user);

    List<UserDTO> getUsers(Pageable pageable);
}
