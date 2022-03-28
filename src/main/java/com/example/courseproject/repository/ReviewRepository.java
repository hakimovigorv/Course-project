package com.example.courseproject.repository;

import com.example.courseproject.models.Review;
import com.example.courseproject.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByTagsContains(Tag tag);
}
