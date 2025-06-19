package com.algaworks.algacomments.device.moderation.domain.service;

import com.algaworks.algacomments.device.moderation.api.client.MonitorationClient;
import com.algaworks.algacomments.device.moderation.api.config.web.CommentNotFoundException;
import com.algaworks.algacomments.device.moderation.api.model.CommentInput;
import com.algaworks.algacomments.device.moderation.api.model.CommentOutput;
import com.algaworks.algacomments.device.moderation.api.model.ModerationOutput;
import com.algaworks.algacomments.device.moderation.common.mappers.CommentMapper;
import com.algaworks.algacomments.device.moderation.domain.model.Comment;
import com.algaworks.algacomments.device.moderation.domain.model.CommentId;
import com.algaworks.algacomments.device.moderation.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private static final CommentMapper commentMapper = CommentMapper.INSTANCE;

    private final MonitorationClient monitorationClient;
    private final CommentRepository commentRepository;

    public CommentOutput create(@RequestBody CommentInput input) {
        var moderateComment = getModerateComment(input);

        var comment = Comment.builder()
                .id(CommentId.of(UUID.randomUUID()))
                .text(input.text())
                .author(input.author())
                .createdAt(OffsetDateTime.now())
                .build();

        commentRepository.saveAndFlush(comment);
        return getCommentOutput(comment, moderateComment);

    }

    public CommentOutput findById(CommentId id) {
        var comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        return commentMapper.toModel(comment);
    }

    public Page<CommentOutput> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable).map(commentMapper::toModel);
    }

    private ModerationOutput getModerateComment(CommentInput input) {
        var moderateOutput = monitorationClient.moderate(new CommentInput(input.text(), input.author()));
        if (Boolean.FALSE.equals(moderateOutput.getApproved())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Comentario nao aprovado");
        }
        return moderateOutput;
    }

    private static CommentOutput getCommentOutput(Comment comment, ModerationOutput moderateComment) {
        var commentOutput = commentMapper.toModel(comment);
        commentOutput.setReason(moderateComment.getReason());
        commentOutput.setApproved(moderateComment.getApproved());
        return commentOutput;
    }

}
