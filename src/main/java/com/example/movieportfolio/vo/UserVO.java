package com.example.movieportfolio.vo;

import lombok.Data;

@Data
public class UserVO {
   private String userID;
   private String userPassword;
   private String userPasswordConfirm;
   private String userResidentID ;
   private String userName ;
   private String userPhone ;
   private String userAddress ;
   private String userEmail ;
   private Integer userType ;
}
