package com.example.movieportfolio.vo;

import lombok.Data;

@Data
public class BbsVO {
    private Integer bbsID;
    private String bbsTitle;
    private String userID;
    private String bbsDate;
    private String bbsContent;
    private Integer bbsAvailable;
}