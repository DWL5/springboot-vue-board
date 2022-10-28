package com.example.server.representation;

import com.example.server.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardResponse {
    private Long id;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime createdAt;


    private BoardResponse(Long id, String title, String contents, String author, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.createdAt = createdAt;
    }

    public static BoardResponse from(Board board) {
        return new BoardResponse(board.getId(),
                board.getTitle(), board.getContents(), board.getAuthor(),
                board.getCreatedAt());
    }

    public static List<BoardResponse> toList(List<Board> boards) {
        return boards.stream()
                .map(BoardResponse::from)
                .collect(Collectors.toList());
    }
}
