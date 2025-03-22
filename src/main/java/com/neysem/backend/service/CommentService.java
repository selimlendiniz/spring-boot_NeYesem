package com.neysem.backend.service;

import com.neysem.backend.dto.SaveCommentRequest;
import com.neysem.backend.dto.SaveCommentResponse;
import com.neysem.backend.dto.SaveRestaurantRequest;
import com.neysem.backend.mapper.CommentMapper;
import com.neysem.backend.model.Comment;
import com.neysem.backend.model.Customer;
import com.neysem.backend.model.Manager;
import com.neysem.backend.repo.CommentRepository;
import com.neysem.backend.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantService restaurantService;
    private final CommentMapper commentMapper;

    public SaveCommentResponse saveComment(SaveCommentRequest saveCommentRequest) {
        Customer customer = customerRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new RuntimeException("Customer not found!"));

        Comment comment = Comment.builder()
                .customer(customer)
                .restaurant(restaurantService.getRestaurant(saveCommentRequest.restaurantId()))
                .comment(saveCommentRequest.comment())
                .build();

        comment = commentRepository.save(comment);

        return commentMapper.toDto(comment);

    }
}
