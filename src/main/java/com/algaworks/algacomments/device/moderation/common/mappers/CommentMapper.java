package com.algaworks.algacomments.device.moderation.common.mappers;

import com.algaworks.algacomments.device.moderation.api.model.CommentOutput;
import com.algaworks.algacomments.device.moderation.domain.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "id", source = "id.value")
    CommentOutput toModel(Comment comment);

    default Page<CommentOutput> toModel(Page<Comment> comments) {
        return comments.map(this::toModel);
    }

}
