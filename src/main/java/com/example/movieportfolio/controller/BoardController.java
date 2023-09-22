package com.example.movieportfolio.controller;

import com.example.movieportfolio.service.BoardService;
import com.example.movieportfolio.vo.BbsVO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {
    BoardService boardService;

    @GetMapping("/boardListView.reservation")
    public String boardListView(Model model) {
        List<BbsVO> bbsVOList = boardService.getBoardList(10);
        System.out.println(bbsVOList);
        model.addAttribute("list", bbsVOList);
        return "boardListView";
    }
}