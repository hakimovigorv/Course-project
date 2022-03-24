package com.example.courseproject.repository;

import com.example.courseproject.models.Review;
import com.example.courseproject.models.Tag;
import com.example.courseproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Set<Review>> findAllByUser(User user);

    List<Review> findAllByTagsContains(Tag tag);
}
