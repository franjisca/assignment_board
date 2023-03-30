package com.assignment.board.controller;


import com.assignment.board.domain.Board;
import com.assignment.board.dto.SearchDto;
import com.assignment.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String boardList(Model model){
        List<Board> boardList = boardService.boardList();
        try {
        model.addAttribute("boardList", boardList);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "board/list";
    }

    @GetMapping ("/write")
    public String writePage(Model model) {
        model.addAttribute("writerName", boardService.getWriterName());
        return "board/write";
    }

    @PostMapping("/write")
    public String saveBoard(@Validated @ModelAttribute("board") Board formData) {


            Board board = new Board();
            board.setTitle(formData.getTitle());
            board.setWriter(formData.getWriter());
            board.setContents(formData.getContents());
            board.setWriteDate(LocalDateTime.now());
            board.setReadCount(0);
            if(formData.getChecked() != null){
            board.setChecked("y");
            }else{
                board.setChecked("n");
            };
            boardService.saveBoard(board);

        return "redirect:/board/list";
    }

    @GetMapping("/view/{boardNo}")
    public String view(@PathVariable long boardNo, Model model){
        Board board = boardService.findById(boardNo);
        board.setReadCount(board.getReadCount()+1);
        model.addAttribute("board", board);

        return "board/view";
    }

    @GetMapping("/delete/{boardNo}")
    public String deleteBoard(@PathVariable long boardNo){
        Board deleteBoard = boardService.findById(boardNo);

        if(deleteBoard == null){
            return "redirect:/board/view/"+boardNo;
        }

        boardService.deleteBoard(boardNo, deleteBoard);
        return "redirect:/board/list";
    }

    @GetMapping("/update/{boardNo}")
    public String updateView(@PathVariable long boardNo, Model model){
        Board updateView = boardService.findById(boardNo);

        if(updateView == null){
            return "redirect:/board/view/"+boardNo;
        }

        model.addAttribute("board", updateView);

        return "board/update";

    }

    @PostMapping("/update/{boardNo}")
    public String updateBoard(@PathVariable long boardNo, @Validated @ModelAttribute("board") Board updateData){

        if(boardService.findById(boardNo) == null) return "redirect:/board/view"+boardNo;

        if(updateData.getChecked() == null) updateData.setChecked("n");

        boardService.updateBoard(boardNo, updateData);
        return "redirect:/board/list";
    }

    @PostMapping("/search")
    public String findByCategory(@Validated @ModelAttribute("searchData") SearchDto searchData, Model model){

        if(searchData.getCategory().equals("writer")){
             List<Board> byWriter = boardService.findByWriter(searchData.getSearchText());

            if(byWriter.size() == 0) {
                model.addAttribute("notFount", "검색결과가 없습니다.");
            }

            model.addAttribute("boardList", byWriter);
        }
        if(searchData.getCategory().equals("title")){
            List<Board> byTitle = boardService.findByTitle(searchData.getSearchText());

            if(byTitle.size() == 0) {
                model.addAttribute("notFount", "검색결과가 없습니다.");
            }

            model.addAttribute("boardList", byTitle);
        }

        return "board/list";
    }


}
