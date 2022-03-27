package com.example.courseproject.repository;

import com.example.courseproject.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from images where review_id=?1", nativeQuery = true)
    void deleteAllByReviewId(Long reviewId);
}
