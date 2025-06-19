package com.algaworks.algacomments.device.moderation.api.client;

import com.algaworks.algacomments.device.moderation.api.model.CommentInput;
import com.algaworks.algacomments.device.moderation.api.model.ModerationInput;
import com.algaworks.algacomments.device.moderation.api.model.ModerationOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/moderate")
public interface MonitorationClient {

    @PostExchange
    ModerationOutput moderate(@RequestBody CommentInput commentInput);

}
