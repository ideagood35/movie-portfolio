package com.example.movieportfolio.mapper;

import com.example.movieportfolio.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    public void register(UserVO userVO);

    List<UserVO> login(@Param("userID") String userID);

    void updateUserPwd(@Param("userID") String userID, @Param("newPassword") String newPassword);

    boolean existsUserID(String userID);

    boolean existsUserSSN(String userSSN);

    void updateUser(UserVO user);

    void deleteUser(String userID);
}
