package com.example.courseproject.dto;

import com.example.courseproject.constants.Constants;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Data
public class ScoreDto {
    private Long userId;
    private Long reviewId;
    @DecimalMax(value = Constants.MAX_SCORE_VALUE, message = "{score.interval}")
    @DecimalMin(value = Constants.MIN_SCORE_VALUE, message = "{score.interval}")
    private Double score;

    public ScoreDto(Long userId, Long reviewId, Double score) {
        this.userId = userId;
        this.reviewId = reviewId;
        this.score = score;
    }
}
