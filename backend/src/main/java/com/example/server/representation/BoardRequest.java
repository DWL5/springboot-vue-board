package com.example.server.representation;

import com.example.server.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardRequest {
    private Long id;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Board toEntity() {
        return new Board(id, title, contents, author, createdAt);
    }
}