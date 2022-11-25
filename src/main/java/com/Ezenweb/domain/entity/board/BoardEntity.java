package com.Ezenweb.domain.entity.board;


import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.member.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="board")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BoardEntity extends BaseEntity {

    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // quto
    private  int bno; //게시물 번호
    @Column( nullable = false) //not null
    private  String btitle; //게시물 제목
    @Column( nullable = false , columnDefinition = "TEXT") // not null , 저장할수 있는길이
    private  String bcontent; //게시물 내용
    @Column( nullable = false) // not null
    @ColumnDefault("0")      //JPA insert 할 경ㅇ default
    private  int bview; //조회수
    @Column
    private  String bfile; //파일명


    //연관관계 [회원번호[pk]] <--양방향--> 게시물번호[fk]
    @ManyToOne // [1:n] FK에 해당 어노테이션
    @JoinColumn(name="mno") // 테이블에서 사용할 fk의 필드명 정의
    @ToString.Exclude // 해당 필드는 ToString 사용하지 않는다
    private MemberEntity memberEntity;

    // 연관관계2 [ 카테고리번호[pk] <-양방향-> 게시물번호[fk]
    @ManyToOne // [1:n] FK 에 해당 어노테이션
    @JoinColumn(name="bcno")
    @ToString.Exclude
    private BcategoryEntity bcategoryEntity;

    //1.형변환
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .memail(this.memberEntity.getMemail())
                .bfilename(this.bfile)
                .build();
    }

}
/*

  자바 ------------------->DB 자료형
  int                       int
  double float              float
  String                    varchar
         columnDefinition = "DB자료형"



 */
/*
    연관관게
    @oneToone   1:1 회원이하나의 게시물만 작성 가능할때
    @ManyToOne  n:1 회원이 여러개의 게시물을 작성가능
    @OneTomany  1:n
    @ManyTOMany n:n

 */