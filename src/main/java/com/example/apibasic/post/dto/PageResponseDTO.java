package com.example.apibasic.post.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

//클라이언트에게 응답할 페이지 정보
/*
    pageInfo:{
                "startPage" : 1,
                "endPage" : 10,
                "currentPage" : 3,
                "prev" : false,
                "next" : true,
                "totalCount" : 500
             }
 */
@ToString@Setter@Getter
public class PageResponseDTO<T> {

    private int startPage;
    private int endPage;
    private int currentPage;
    private boolean prev;
    private boolean next;
    private int totalCount;

    //페이지 개수
    private static final int PAGE_COUNT=10; //10페이지씩

    public PageResponseDTO(Page<T> pageDate){
        this.totalCount= (int) pageDate.getTotalElements();
        this.currentPage = pageDate.getPageable().getPageNumber() + 1;
        this.endPage= (int) (Math.ceil((double)currentPage/PAGE_COUNT)*PAGE_COUNT); // double형이라 int로 다운캐스팅
        this.startPage=endPage-PAGE_COUNT+1;

        //페이지 마지막 구간에서 endPage값 보정
        //실제 끝페이지 숫자를 구함
        //int realEnd= (int)Math.ceil((double)totalCount/pageDate.getSize());
        int realEnd=pageDate.getTotalPages();

        //언제 보정해야돼?? 마지막 구간에서만
        if(realEnd<endPage)this.endPage=realEnd;

        this.prev=startPage>1;
        this.next=endPage<realEnd;


    }

}//class
