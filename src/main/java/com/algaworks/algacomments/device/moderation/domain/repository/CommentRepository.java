package com.algaworks.algacomments.device.moderation.domain.repository;

import com.algaworks.algacomments.device.moderation.domain.model.Comment;
import com.algaworks.algacomments.device.moderation.domain.model.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {

}
