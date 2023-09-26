package com.example.movieportfolio.controller;

import com.example.movieportfolio.dto.UserPageRequestDTO;
import com.example.movieportfolio.service.MemberService;
import com.example.movieportfolio.dto.ModalDTO;
import com.example.movieportfolio.vo.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MemberController {
    MemberService memberService;

    @GetMapping("/userConfirmView.reservation")
    public String userConfirmView() {
        return "userConfirmView";
    }

    // 실명인증 후, 회원가입페이지로 이동
    @PostMapping("/userConfirmAction.reservation")
    public String userConfirmAction(@ModelAttribute UserVO userVO, HttpSession session) {

        String userName = userVO.getUserName(); // 이름
        String userResidentID = userVO.getUserResidentID(); // 주민번호

        if (userName == null || userName.trim().isEmpty() ||    //null 이거나 trim()공뱅이 있으면 자동으로 지주고 isEmpty() 이면 오류확인
                userResidentID == null || !userResidentID.matches("\\d{13}")) {// 주민들록13자매칭이 아니면
            session.setAttribute("modal", new ModalDTO("오류 메시지", "모든 내용은 빈 칸일 수 없습니다.", "btn btn-danger"));
            return "redirect:/userConfirmView.reservation";
        } else {
            session.setAttribute("modal", new ModalDTO("성공 메시지", "회원 정보를 기입하여 회원가입 해주세요.", "btn btn-primary"));
            return "redirect:/userJoinView.reservation";
        }
    }

    // 회원가입 페이지로 이동
    @GetMapping("/userJoinView.reservation")
    public String userJoinView() {
        return "userJoinView";
    }

    //회원 등록
    @PostMapping("/userJoinAction.reservation")
    public String insertUser(@ModelAttribute UserVO userVO, HttpSession session) throws Exception {
        // 필드 값 검사
        if (userVO.getUserID() == null || userVO.getUserID().isEmpty() ||
                userVO.getUserPassword() == null || userVO.getUserPassword().isEmpty() ||
                userVO.getUserPasswordConfirm() == null || userVO.getUserPasswordConfirm().isEmpty()
            // ... 위 내용은 null 이거나 또는 빈 문자열 이면 아래 로직 실행 ...
        ) {
            session.setAttribute("modal", new ModalDTO("오류 메세지", "모든 내용은 빈 칸일 수 없습니다.", ModalDTO.ERROR));
            return "redirect:/userJoinView.reservation";  // 회원 가입 페이지로 리다이렉트.
        }

        if (userVO.getUserPassword().equals(userVO.getUserPasswordConfirm())) {
            try {
                // 비밀번호와 '비밀번호 확인'이 일치하는 경우
                memberService.register(userVO);  // 바로 register 메소드에 전달하여 사용자 정보를 등록합니다.
                session.setAttribute("modal", new ModalDTO("성공 메세지", "축하합니다! 로그인해주세요.", ModalDTO.SUCCESS));
                return "redirect:/userLoginView.reservation";  // 로그인 페이지로 리다이렉트
            } catch (Exception e) {
                if (e.getMessage().equals("아이디가 이미 존재합니다.")) {
                    session.setAttribute("modal", new ModalDTO("오류 메세지", e.getMessage(), ModalDTO.ERROR));
                    return "redirect:/userJoinView.reservation";  // 회원 가입 페이지로 리다이렉트.
                }

                throw e;   // 알 수 없는 예외는 다시 던진다.
            }

        } else {
            session.setAttribute("modal", new ModalDTO("오류 메세지", "비밀번호가 일치하지 않습니다.", ModalDTO.ERROR));
            return "redirect:/userJoinView.reservation";  // 회원 가입 페이지로 리다이렉트.
        }
    }

    /////회원 등록후 다시 로그인페이지로 가기/////
    @GetMapping("/userLoginView.reservation")
    public String userLoginView() {
        return "userLoginView";
    }


    //로그인
    @PostMapping("/userLoginAction.reservation")
    public String userLogin(@RequestParam("userID") String userID, @RequestParam("userPassword") String password, Model model, HttpSession session) {
        // 여기서 memberService는 MemberService 객체입니다.
        UserVO user = memberService.validateMember(userID, password);

        if (user != null) {
            // 로그인 성공유지(sesssion이 로그인 유지하는 코드이다): 메인 페이지로 리다이렉트
            session.setAttribute("userID", user.getUserID());  // 로그인한 사용자 정보를 세션에 저장
            session.setAttribute("loggedInUser", user);  // 로그인한 사용자 정보를 세션에 저장
            session.setAttribute("userType", user.getUserType()); //유저 타입-->운영자 유저 구별 하기 위한 코드

            session.setAttribute("modal", new ModalDTO("성공 메세지", "로그인 됐습니다", ModalDTO.SUCCESS));
            return "redirect:/mainView.reservation";
        } else {
            // 로그인 실패: 에러 메시지와 함께 다시 로그인 페이지로 리다이렉트
            model.addAttribute("loginError", "Invalid username or password.");
            session.setAttribute("modal", new ModalDTO("오류 메세지", "아이디 및 비밀번호를 다시 한번 확인해 주십시오.", ModalDTO.ERROR));
            return "userLoginView";
        }
    }

    ////관리자에서 회원 관리
    @GetMapping("/userListView.reservation")
    public String userListView(@ModelAttribute UserVO userVO, UserPageRequestDTO userPageRequestDTO, Model model) {
        System.out.println("유저리스트: " + userPageRequestDTO);

        List<UserVO> userList = memberService.getUserList(userPageRequestDTO);
        System.out.println("User list size: " + userList.size());  // 이 부분 추가



        int totalCount = memberService.getUserTotalCount();
        int totalPage = totalCount / userPageRequestDTO.getSize();
        System.out.println("토탈카운트: " + totalCount);

        model.addAttribute("userList", userList);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("user", userVO);
        return "userListView";
    }


    @GetMapping("/userLogoutAction.reservation")
    public String logout(HttpSession session) {
        session.invalidate(); //세션무효화
        return "redirect:/mainView.reservation";
    }

    //////////////비번찾기
    @GetMapping("/userFindView.reservation")
    public String userFindView() {
        return "userFindView";
    }

    @PostMapping("/userFindAction.reservation")
    public String findPassword(@RequestParam("userID") String userID,
                               @RequestParam("userEmail") String userEmail,
                               HttpSession session) {
        try {
            String newPassword = memberService.findAndSendNewPassword(userID, userEmail);
            session.setAttribute("newPassword", newPassword);
            System.out.println("작동");
            return "redirect:/userFindResultView";
        } catch (Exception e) {
            session.setAttribute("modal", new ModalDTO("오류 메세지", e.getMessage(), ModalDTO.ERROR));
            return "redirect:/userFindView.reservation";
        }
    }

    @GetMapping("/userEditView")
    public String userFindResultView() {
        return "userFindResultView";
    }


    @GetMapping("/userEditView.reservation")
    public String userEditView(Model model, HttpSession session) {
        // 세션에서 로그인한 사용자의 정보 가져오기
        UserVO loggedInUser = (UserVO) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // 사용자 정보에서 전화번호를 가져와서 모델에 추가
            model.addAttribute("userID", loggedInUser.getUserID());
            model.addAttribute("userPhone", loggedInUser.getUserPhone());
            model.addAttribute("userAddress", loggedInUser.getUserAddress());
            model.addAttribute("userEmail", loggedInUser.getUserEmail());
        }

        return "userEditView";
    }

    //회원수정
    @PostMapping("/userEditAction.reservation")
    public String editUser(@ModelAttribute UserVO userVO, HttpSession session) throws Exception {
        // 필드 값 검사
        if (userVO.getUserID() == null || userVO.getUserID().isEmpty() ||
                userVO.getUserPassword() == null || userVO.getUserPassword().isEmpty() ||
                userVO.getUserPasswordConfirm() == null || userVO.getUserPasswordConfirm().isEmpty()
            // ... 위 내용은 null 이거나 또는 빈 문자열 이면 아래 로직 실행 ...
        ) {
            session.setAttribute("modal", new ModalDTO("오류 메세지", "모든 내용은 빈 칸일 수 없습니다.", ModalDTO.ERROR));
            return "redirect:/userEditAction.reservation";  // 회원 가입 페이지로 리다이렉트.
        }

        if (userVO.getUserPassword().equals(userVO.getUserPasswordConfirm())) {
            try {
                // 비밀번호와 '비밀번호 확인'이 일치하는 경우
                memberService.edit(userVO);  // 바로 register 메소드에 전달하여 사용자 정보를 등록합니다.
                session.setAttribute("modal", new ModalDTO("성공 메세지", "수정되었습니다! 로그인해주세요.", ModalDTO.SUCCESS));
                return "redirect:/userEditView.reservation";  // 로그인 페이지로 리다이렉트
            } catch (Exception e) {
                session.setAttribute("modal", new ModalDTO("오류 메세지", "모든 내용은 빈 칸일 수 없습니다.", ModalDTO.ERROR));
                return "redirect:/userEditView.reservation";  // 회원 가입 페이지로 리다이렌트
            }

        } else {
            session.setAttribute("modal", new ModalDTO("오류 메세지", "비밀번호가 일치하지 않습니다.", ModalDTO.ERROR));
            return "redirect:/userEditView.reservation";  // 회원 가입 페이지로 리다이렉트.
        }

    }

    //회워탈퇴
    @GetMapping("/userDeleteView.reservation")
    public String userDeleteView(Model model, HttpSession session) {

        return "userDeleteView";
    }

    //회원 탈퇴액션
    @GetMapping("/userDeleteAction.reservation")
    public String userDeleteView(HttpSession session) {
        UserVO loggedInUser = (UserVO) session.getAttribute("loggedInUser");
        memberService.delete(loggedInUser.getUserID());
        session.invalidate();//세션 무효화
        return "userDeleteResultView";
    }

}


