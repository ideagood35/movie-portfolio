package com.example.movieportfolio.mapper;

import com.example.movieportfolio.dto.PageRequestDTO;
import com.example.movieportfolio.vo.BbsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BbsVO> getBoardList(PageRequestDTO pageRequestDTO); // 목록
    BbsVO getBoard(Integer bbsID); // 상세
    void insertBoard(BbsVO bbsVO); // 등록
    void updateBoard(BbsVO bbsVO); // 수정
    void deleteBoard(Integer bbsID); // 삭제

    int getTotalCount();
}