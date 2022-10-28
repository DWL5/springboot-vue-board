package com.example.server.service;

import com.example.server.domain.Board;
import com.example.server.domain.BoardRepository;
import com.example.server.representation.BoardRequest;
import com.example.server.representation.BoardResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long create(BoardRequest boardRequest) {
        Board board = boardRepository.save(boardRequest.toEntity());
        return board.getId();
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> read() {
        List<Board> boards = boardRepository.findAll();
        return BoardResponse.toList(boards);
    }

    @Transactional(readOnly = true)
    public BoardResponse read(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                RuntimeException::new);
        return BoardResponse.from(board);
    }

    @Transactional
    public Long update(BoardRequest boardRequest) {
        Board board = boardRepository.save(boardRequest.toEntity());
        return board.getId();
    }

    @Transactional
    public void delete(Long id) {
         Board board = boardRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException(""));
         boardRepository.delete(board);
    }
}
