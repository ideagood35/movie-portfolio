package com.example.movieportfolio.service;

import com.example.movieportfolio.dto.PageRequestDTO;
import com.example.movieportfolio.mapper.BoardMapper;
import com.example.movieportfolio.vo.BbsVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {
    BoardMapper boardMapper;

    public List<BbsVO> getBoardList(PageRequestDTO pageRequestDTO) {
        return boardMapper.getBoardList(pageRequestDTO);
    }

    public BbsVO getBoard(Integer bbsID) {
        return boardMapper.getBoard(bbsID);
    }

    public void insertBoard(BbsVO bbsVO) {
        boardMapper.insertBoard(bbsVO);
    }

    public void updateBoard(BbsVO bbsVO) {
        boardMapper.updateBoard(bbsVO);
    }

    public void deleteBoard(Integer bbsID) {
        boardMapper.deleteBoard(bbsID);
    }

    public int getTotalCount() {
        return boardMapper.getTotalCount();
    }
}