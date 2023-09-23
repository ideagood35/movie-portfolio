package com.example.movieportfolio.dto;

import lombok.Data;

@Data
public class PageRequestDTO {
    private Integer page;
    private Integer size;
    private Integer startIndex; // 자동계산됨
    private Integer endIndex; // 자동계산됨

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
        calculateIndex();
    }

    public PageRequestDTO(Integer page, Integer size) {
        this.page = page;
        this.size = size;
        calculateIndex();
    }

    public PageRequestDTO(Integer page) {
        this.page = page;
        this.size = 10;
        calculateIndex();
    }

    // 이거를 쓰는 거임
    public void setPage(Integer page) {
        this.page = page;
        calculateIndex();
    }

    private void calculateIndex() {
        this.startIndex = (this.page - 1) * this.size ; // 0
        this.endIndex = this.startIndex + this.size;       // 10, 20, 30, 40, 50, 60, 70, 80, 90, 100
    }
}