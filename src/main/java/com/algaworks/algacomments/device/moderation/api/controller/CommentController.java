package com.algaworks.algacomments.device.moderation.api.controller;

import com.algaworks.algacomments.device.moderation.api.model.CommentInput;
import com.algaworks.algacomments.device.moderation.api.model.CommentOutput;
import com.algaworks.algacomments.device.moderation.domain.model.CommentId;
import com.algaworks.algacomments.device.moderation.domain.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService service;

    @PostMapping
    public ResponseEntity<CommentOutput> create(@RequestBody CommentInput input) {
        log.info("Creating a Comment");
        var commentOutput = service.create(input);
        log.info("Comment Registred: {}", commentOutput.getId());
        var location = URI.create("/api/comments/" + commentOutput.getId());
        return ResponseEntity.created(location).body(commentOutput);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentOutput>  findCommentById(@PathVariable String id) {
        log.info("Find by Id a Comment: {}", id);
        return ResponseEntity.ok(service.findById(CommentId.of(UUID.fromString(id))));
    }

    @GetMapping
    public ResponseEntity<Page<CommentOutput>> getAllComments(@PageableDefault Pageable pageable) {
        log.info("Find All Comments");
        return ResponseEntity.ok(service.findAll(pageable));
    }

}
