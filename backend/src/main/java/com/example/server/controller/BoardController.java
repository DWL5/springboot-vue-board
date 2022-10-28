package com.example.server.controller;

import com.example.server.representation.BoardRequest;
import com.example.server.representation.BoardResponse;
import com.example.server.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> boards() {
        return ResponseEntity.ok(boardService.read());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> boards(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.read(id));
    }

    @PostMapping
    public ResponseEntity<Void> write(@RequestBody BoardRequest boardRequest) {
        Long id = boardService.create(boardRequest);
        return ResponseEntity
                .created(URI.create("boards/" + id))
                .build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody BoardRequest boardRequest) {
        boardService.update(boardRequest);
        return ResponseEntity
                .created(URI.create("boards/" + 1L))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boardService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
