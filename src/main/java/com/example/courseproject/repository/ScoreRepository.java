package com.example.courseproject.repository;

import com.example.courseproject.models.Review;
import com.example.courseproject.models.Score;
import com.example.courseproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findScoreByUserAndReview(User user, Review review);

    Optional<Score> findScoreByUserIdAndReviewId(Long userId, Long reviewId);

    Optional<Score> findByUserUsernameAndReviewId (String username, Long reviewId);

    @Query(value = "SELECT avg(score) FROM scores where review_id=?1", nativeQuery = true)
    Double avgScoreByReviewId(Long reviewId);

    List<Score> findAllByReview(Review review);
}
