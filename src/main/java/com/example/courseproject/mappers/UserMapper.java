package com.example.courseproject.mappers;

import com.example.courseproject.dto.UserControlDto;
import com.example.courseproject.dto.UserProfileDto;
import com.example.courseproject.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserProfileDto UserToUserProfileDto(User user);

    UserControlDto UserToUserControlDto(User user);
}
