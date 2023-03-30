package com.assignment.board.repository;

import com.assignment.board.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BoardRepository {


    /**
     * @j1yeon
     * DB 역할을 대신할 map 선언
     * */
    private static final Map<Long, Board> boardStore = new ConcurrentHashMap<>();

    private static String username = "";
    private static long sequence = 0L;

    private static Map<Long, Board> findList = new ConcurrentHashMap<>();

    public Board saveBoard(Board board) {
        board.setBoardNo(++sequence);
        boardStore.put(board.getBoardNo(), board);
        username = board.getWriter();
        return board;
    }

    public Board findById(Long boardKey){
        return boardStore.get(boardKey);
    }

    public List<Board> findByTitle(String title){
        findList.clear();

        boardStore.forEach((key, value) -> {
            if(boardStore.get(key).getTitle().contains(title)){
                        findList.put(key, boardStore.get(key));
                    }
        });
        return new ArrayList<>(findList.values());
    }

    public List<Board> findByWriter(String writer){
        findList.clear();

        boardStore.forEach((key, value) -> {
            if(boardStore.get(key).getWriter().equals(writer)){
                findList.put(key, boardStore.get(key));
            }
        });
        return new ArrayList<>(findList.values());
    }

    public boolean deleteBoard(Long boardKey, Board board){
        return boardStore.remove(boardKey, board);
    }


    public int updateBoard(Long boardKey, Board updateBoard){
        Board findBoard = findById(boardKey);

        findBoard.setContents(updateBoard.getContents());
        findBoard.setTitle(updateBoard.getTitle());
        findBoard.setChecked(updateBoard.getChecked());

        return 1;
    }

    public List<Board> boardList() {
        return new ArrayList<>(boardStore.values());
    }

    public String getWriterName(){
        return username;
    }
}
