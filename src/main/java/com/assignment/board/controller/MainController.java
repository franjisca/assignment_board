package com.assignment.board.controller;


import com.assignment.board.domain.Board;
import com.assignment.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BoardService boardService;

    @GetMapping("/")
    public String mainPage(Model model){

       model.addAttribute("writer", boardService.getWriterName());

        return "main";
    }

}
