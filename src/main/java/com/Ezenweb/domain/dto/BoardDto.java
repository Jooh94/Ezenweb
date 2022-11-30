package com.Ezenweb.domain.dto;


import com.Ezenweb.domain.entity.board.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


// 룸북: 생성자,GET/SET .TOString, 빌더패턴
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder

public class BoardDto {
        private int bno;            // 게시물번호
        private String btitle;      // 게시물제목
        private String bcontent;    // 게시물 내용
        private int bview;          // 조회수
        private MultipartFile bfile;// 첨부파일 객체[업로드용]
        // spring : MultipartFile 인터페이스
        // jsp : cos 라이브러리
        private String bfilename;   //첨부파일 [ 출력용 ]

        private int bcno;           // 카테고리[ 카테고리-fk ]
        private String memail;      //  회원아이디

        private  int startbtn;
        private  int endbtn;
        //1.형변환
        public BoardEntity toEntity() {
                return BoardEntity.builder()
                        .bno(this.bno)
                        .btitle(this.btitle)
                        .bcontent(this.bcontent)
                        .bview(this.bview)
                        .build();
        }
}
