package com.example.courseproject.mappers;

import com.example.courseproject.dto.LikeDto;
import com.example.courseproject.dto.ScoreDto;
import com.example.courseproject.models.Like;
import com.example.courseproject.models.Score;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "review.id", source = "reviewId")
    Like likeDtoToLike(LikeDto likeDto);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "review.id", source = "reviewId")
    Score scoreDtoToScore (ScoreDto scoreDto);
}
