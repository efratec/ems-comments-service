package com.algaworks.algacomments.device.moderation.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ModerationOutput {

    private UUID commentId;
    private Boolean approved;
    private String reason;

}
