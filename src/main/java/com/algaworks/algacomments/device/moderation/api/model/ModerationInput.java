package com.algaworks.algacomments.device.moderation.api.model;

import jakarta.validation.Valid;

import java.util.UUID;

public record ModerationInput(@Valid UUID commentId, @Valid String text) {
}
