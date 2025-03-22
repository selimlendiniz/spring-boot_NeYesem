package com.neysem.backend.controller;

import com.neysem.backend.dto.SaveCommentRequest;
import com.neysem.backend.dto.SaveCommentResponse;
import com.neysem.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public SaveCommentResponse saveComment(@RequestBody SaveCommentRequest saveCommentRequest) {
        return commentService.saveComment(saveCommentRequest);
    }

}
