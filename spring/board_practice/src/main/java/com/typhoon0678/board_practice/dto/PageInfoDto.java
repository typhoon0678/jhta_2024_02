package com.typhoon0678.board_practice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfoDto {

    private final int totalPages;
    private final int currentPage;

    private final int startPage;
    private final int endPage;

    private boolean isPrevious;
    private boolean isNext;


    public PageInfoDto(int totalPages, int currentPage) {
        int paginationSize = 5;

        this.totalPages = totalPages;
        this.currentPage = currentPage + 1;

        this.startPage = paginationSize * (currentPage / paginationSize) + 1;
        this.endPage = Math.min(startPage + paginationSize - 1, totalPages);

        this.isPrevious = startPage > paginationSize;
        this.isNext = endPage < totalPages;
    }
}
