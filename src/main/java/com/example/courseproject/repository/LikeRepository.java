package com.example.courseproject.repository;

import com.example.courseproject.models.Like;
import com.example.courseproject.models.Review;
import com.example.courseproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findLikeByUserAndReview(User user, Review review);

    Integer countAllByReviewId(Long reviewId);

    Optional<Like> findLikeByReviewIdAndUserId(Long reviewId, Long userId);
}
