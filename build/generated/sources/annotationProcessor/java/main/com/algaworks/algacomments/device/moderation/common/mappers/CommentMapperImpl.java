package com.algaworks.algacomments.device.moderation.common.mappers;

import com.algaworks.algacomments.device.moderation.api.model.CommentOutput;
import com.algaworks.algacomments.device.moderation.domain.model.Comment;
import com.algaworks.algacomments.device.moderation.domain.model.CommentId;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-19T00:57:34-0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.jar, environment: Java 21.0.7 (Ubuntu)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentOutput toModel(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentOutput.CommentOutputBuilder commentOutput = CommentOutput.builder();

        commentOutput.id( commentIdValue( comment ) );
        commentOutput.text( comment.getText() );
        commentOutput.author( comment.getAuthor() );
        commentOutput.createdAt( comment.getCreatedAt() );

        return commentOutput.build();
    }

    private UUID commentIdValue(Comment comment) {
        CommentId id = comment.getId();
        if ( id == null ) {
            return null;
        }
        return id.getValue();
    }
}
