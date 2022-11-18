package com.Ezenweb.domain.dto;


import com.Ezenweb.domain.entity.board.BoardEntity;
import lombok.*;


// 룸북: 생성자,GET/SET .TOString, 빌더패턴
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder

public class BoardDto {
        private  int bno; //게시물 번호
        private  String btitle; //게시물 제목
        private  String bcontent; //게시물 내용
        private  int bview; //조회수
        private  String bfile; //첨부파일
        private  int mno;   // 작성자
        private  int bcno;   // 카테고리 [카테고리fk]
        private  String memail; // 회원 아이디

        //1.형변환
        public BoardEntity toEntity() {
                return BoardEntity.builder()
                        .bno(this.bno)
                        .btitle(this.btitle)
                        .bcontent(this.bcontent)
                        .bview(this.bview)
                        .bfile(this.bfile)
                        .build();
        }
}
