package com.algaworks.algacomments.device.moderation.api.model;

public record CommentInput(String text, String author) {

    public CommentInput {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Texto não pode ser vazio");
        }

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Autor não pode ser vazio");
        }
    }

}
