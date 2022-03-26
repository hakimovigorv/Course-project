package com.example.courseproject.controllers;

import com.example.courseproject.dto.ReviewAddDto;
import com.example.courseproject.dto.ReviewDetailsDto;
import com.example.courseproject.dto.ReviewEditDto;
import com.example.courseproject.mappers.ReviewMapper;
import com.example.courseproject.models.Review;
import com.example.courseproject.payload.response.MessageResponse;
import com.example.courseproject.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewMapper reviewMapper;

    @GetMapping("/all")
    public List<ReviewDetailsDto> getAllReviews() {
        return reviewService.findAll().stream()
                .map(review -> (reviewMapper.reviewToReviewDetailsDto(review))).collect(Collectors.toList());
    }

    @PostMapping("/add/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addReview(@RequestBody ReviewAddDto reviewAddDto, @PathVariable String username, Principal principal) {
        Review review = reviewMapper.reviewAddDtoToReview(reviewAddDto);
        return ResponseEntity.ok(new MessageResponse(
                reviewService.addReview(review, username, principal.getName())));
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> editReview(@RequestBody ReviewEditDto reviewEditDto, Principal principal) throws Exception {
        Review review = reviewMapper.reviewEditDtoToReview(reviewEditDto);
        return ResponseEntity.ok(new MessageResponse(
                reviewService.editReview(review, principal.getName())));
    }

    @GetMapping("/details/{id}")
    public ReviewDetailsDto getReview(@PathVariable("id") long id) {
        return reviewMapper.reviewToReviewDetailsDto(reviewService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String deleteReview(@PathVariable("id") long id, Principal principal) throws Exception {
        return reviewService.deleteReview(id, principal.getName());
    }

    @GetMapping("/tag/{tagName}")
    public List<ReviewDetailsDto> getReview(@PathVariable("tagName") String tagName) {
        return reviewService.findAllByTag(tagName).stream()
                .map(review -> (reviewMapper.reviewToReviewDetailsDto(review))).collect(Collectors.toList());
    }

    @GetMapping("/search/{text}")
    public List<ReviewDetailsDto> searchReview(@PathVariable("text") String text) {
        return reviewService.searchReview(text).stream()
                .map(review -> (reviewMapper.reviewToReviewDetailsDto(review))).collect(Collectors.toList());
    }

    @GetMapping("/user/{user}")
    public Set<ReviewDetailsDto> getUserReviews(@PathVariable("user") String username) {
        return reviewService.getUserReviews(username).stream()
                .map(review -> (reviewMapper.reviewToReviewDetailsDto(review))).collect(Collectors.toSet());
    }
}