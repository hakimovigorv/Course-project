package com.example.courseproject.controllers;

import com.example.courseproject.dto.CommentDto;
import com.example.courseproject.mappers.CommentMapper;
import com.example.courseproject.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/{reviewId}/all")
    public List<CommentDto> getReviewComments(@PathVariable("reviewId") Long reviewId) {
        return commentMapper.commentsToCommentDtos(commentService.findCommentsByReviewId(reviewId));
    }

    @MessageMapping("/comment")
    public void addComment(@Valid @RequestBody CommentDto commentDto) {
        commentDto = commentMapper.commentToCommentDto(commentService.saveComment(commentMapper.commentDtoToComment(commentDto)));
        messagingTemplate.convertAndSend("/topic/comment/" + commentDto.getReviewId(), commentDto);
    }
}
