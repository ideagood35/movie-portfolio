package com.example.movieportfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ModalDTO
{
    public static final String SUCCESS="btn btn-primary";
    public static final String ERROR="btn btn-danger";
    private String modalTitle;
    private String modalBody;
    private String modalColor;
}