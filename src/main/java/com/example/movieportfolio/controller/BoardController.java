package com.example.movieportfolio.controller;

import com.example.movieportfolio.dto.PageRequestDTO;
import com.example.movieportfolio.service.BoardService;
import com.example.movieportfolio.vo.BbsVO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {
    BoardService boardService;

    @GetMapping("/boardListView.reservation")
    public String boardListView(@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {
//        System.out.println("pageRequestDTO: " + pageRequestDTO);

        List<BbsVO> bbsVOList = boardService.getBoardList(pageRequestDTO);
        int totalCount = boardService.getTotalCount();
        int totalPage = totalCount / pageRequestDTO.getSize();

        model.addAttribute("list", bbsVOList);
        model.addAttribute("totalPage", totalPage);

        return "boardListView";
    }

    @GetMapping("/boardWriteView.reservation")
    public String boardWriteView(Model model) {
        return "boardWriteView";
    }

    @PostMapping("/boardWriteAction.reservation")
    public String boardWriteAction(@ModelAttribute BbsVO bbsVO, HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        bbsVO.setUserID(userID);
        boardService.insertBoard(bbsVO);
        return "redirect:/boardListView.reservation";
    }

    @GetMapping("/boardInfoView.reservation")
//    public String boardInfoView(@ModelAttribute BbsVO bbsVO, Model model) {
    public String boardInfoView(@RequestParam Integer bbsID, Model model) {
        BbsVO bbsVO = boardService.getBoard(bbsID);
        model.addAttribute("bbs", bbsVO);
        return "boardInfoView";
    }

    @GetMapping("/boardEditView.reservation")
//    public String boardEditView(@ModelAttribute BbsVO bbsVO, Model model) {
    public String boardEditView(@RequestParam Integer bbsID, Model model) {
        BbsVO bbsVO = boardService.getBoard(bbsID);
        model.addAttribute("bbs", bbsVO);
        return "boardEditView";
    }

    @PostMapping("/boardEditAction.reservation")
    public String boardEditAction(@ModelAttribute BbsVO bbsVO, HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        bbsVO.setUserID(userID);
        boardService.updateBoard(bbsVO);
        return "redirect:/boardListView.reservation";
    }

    @GetMapping("/boardDeleteAction.reservation")
//    public String boardDeleteAction(@ModelAttribute BbsVO bbsVO, Model model) {
    public String boardDeleteAction(@RequestParam Integer bbsID, Model model) {
        boardService.deleteBoard(bbsID);
        return "boardDeleteView";
    }

}