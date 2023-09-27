package com.example.movieportfolio.mapper;

import com.example.movieportfolio.dto.UserPageRequestDTO;
import com.example.movieportfolio.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    public void register(UserVO userVO);

    List<UserVO> login(@Param("userID") String userID);

    List<UserVO> getUserList(UserPageRequestDTO userPageRequestDTO); //목록

    int getUserTotalCount();

    void updateUserPwd(@Param("userID") String userID, @Param("newPassword") String newPassword);

    boolean existsUserID(String userID);//비번

    boolean existsUserSSN(String userSSN);//비번 확인

    void updateUser(UserVO user);//수정

    void deleteUser(String userID); //삭제

    List<UserVO> getUserById(String userId); //관리자 수정의 id가져옴
}
