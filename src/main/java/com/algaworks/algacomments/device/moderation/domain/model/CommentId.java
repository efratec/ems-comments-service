package com.algaworks.algacomments.device.moderation.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CommentId implements Serializable {

    @Column(name = "id", nullable = false, updatable = false)
    private UUID value;

}
