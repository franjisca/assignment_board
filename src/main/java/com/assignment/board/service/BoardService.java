package com.assignment.board.service;


import com.assignment.board.domain.Board;
import com.assignment.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board saveBoard(Board board) {
        return boardRepository.saveBoard(board);
    }

    public Board findById(Long boardKey){
        return boardRepository.findById(boardKey);
    }

    public List<Board> findByTitle(String title){
        return boardRepository.findByTitle(title);
    }

    public List<Board> findByWriter(String writer){
      return boardRepository.findByWriter(writer);
    }

    public boolean deleteBoard(Long boardKey, Board board){
        return boardRepository.deleteBoard(boardKey, board);
    }

    public int updateBoard(Long boardKey, Board updateBoard){
        return boardRepository.updateBoard(boardKey, updateBoard);
    }

    public List<Board> boardList() {
        if(!boardRepository.boardList().isEmpty()) return boardRepository.boardList();
        return null;
    }

    public String getWriterName(){
        return boardRepository.getWriterName();
    }


}
