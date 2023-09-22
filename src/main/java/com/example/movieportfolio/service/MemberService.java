package com.example.movieportfolio.service;

import com.example.movieportfolio.mapper.MemberMapper;
import com.example.movieportfolio.vo.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    // 사용자 등록 메소드입니다.
    public void register(UserVO userVO) throws Exception {
        if (memberMapper.existsUserID(userVO.getUserID())) {
            throw new Exception("아이디가 이미 존재합니다.");
        }

        if (memberMapper.existsUserSSN(userVO.getUserResidentID())) {
            throw new Exception("주민등록번호가 이미 존재합니다.");
        }

        memberMapper.register(userVO);
    }

    public UserVO validateMember(String userID, String password) {
        List<UserVO> users = memberMapper.login(userID);

        if (users != null && !users.isEmpty()) {
            UserVO user = users.get(0);
            String hashedPassword = hashPassword(password);

            if (hashedPassword.equals(user.getUserPassword())) {
                return user;
            }
        }

        return null;
    }

    public String findAndSendNewPassword(String userID, String userEmail) throws Exception {
        List<UserVO> users = memberMapper.login(userID);

        if (users != null && !users.isEmpty()) {
            UserVO user = users.get(0);

            // 사용자의 이메일이 입력한 이메일과 일치하는지 확인합니다.
            if (userEmail.equals(user.getUserEmail())) {

                // 새 비밀번호를 생성합니다.
                String newPassword = generateNewPassword();

                System.out.println("새로 생성된 비밀번호: " + newPassword);

                // 데이터베이스에 새 비밀번호를 저장합니다.
                updateUserPassword(userID, newPassword);  // <- 여기에서 DB에 저장

                return newPassword;  // <- 여기에서 새 비밀번호 반환
            } else {
                throw new Exception("입력된 이메일이 등록된 계정의 이메일과 일치하지 않습니다.");
            }
        } else {
            throw new Exception("입력된 아이디에 해당하는 사용자가 없습니다.");
        }
    }

    private void updateUserPassword(String userID, String newPassword) {  // <- 추가됨
        System.out.println("비밀번호 변경 완료");
        memberMapper.updateUserPwd(userID,newPassword);
    }

    /* 회원 정보 수정 메소드 추가 */
    public void updateUser(UserVO updatedUser) throws Exception {
        /* 회원 정보 수정을 위해 매퍼의 updateUser 호출 */
        System.out.println(updatedUser);
        memberMapper.updateUser(updatedUser);
    }

    private String generateNewPassword() {
        Random random = new Random();
        return Integer.toString(random.nextInt(10000));
    }

    private String hashPassword(String originalPassword) {
        return originalPassword;
    }

    public void updateUserPassword(UserVO user, String newPassword) throws Exception {
        user.setUserPassword(newPassword);
        memberMapper.updateUser(user);
    }


    public void edit(UserVO userVO) {
        System.out.println(userVO);
        memberMapper.updateUser(userVO);
    }
}
