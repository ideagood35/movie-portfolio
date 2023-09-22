package com.example.movieportfolio.mapper;

import com.example.movieportfolio.vo.BbsVO;
import com.example.movieportfolio.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BbsVO> getBoardList(Integer bbsID); // 목록
    BbsVO getBoard(Integer bbsID); // 상세
    void insertBoard(BbsVO bbsVO); // 등록
    void updateBoard(BbsVO bbsVO); // 수정
    void deleteBoard(Integer bbsID); // 삭제
}