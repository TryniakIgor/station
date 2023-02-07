package com.work.station.mapper;


import com.work.station.dto.UserDTO;
import com.work.station.model.User;

public class UserMapper {
    public static UserDTO toDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

}
