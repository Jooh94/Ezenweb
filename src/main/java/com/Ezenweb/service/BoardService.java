package com.Ezenweb.service;


import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.board.BoardEntity;
import com.Ezenweb.domain.entity.board.BoardRepository;
import com.Ezenweb.domain.entity.member.MemberEntity;
import com.Ezenweb.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // 컴포넌트
public class BoardService {
    //--------------------1.전역변수-----------------//
    @Autowired
    private HttpServletRequest request; // 요청객체 선언
    @Autowired
    private MemberRepository memberRepository; // 회원 리포지토리 객체 선언
    @Autowired //
    private BoardRepository boardRepository; // 게시물 리포지토리 객체 선언
            //@Transactional : 엔티티 DML 적용 할때 사용되는 어노테이션
            //1.메소드
    //--------------------2.서비스-----------------//
    //1.게시물 쓰기
    @Transactional
    public boolean setboard(BoardDto boardDto){

        // 로그인 정보 확인 [ 세션 = loginMno]
        Object object= request.getSession().getAttribute("loginMno");
        if(object==null){return false;}
        //2.회원 정보 호출
        int mno = (Integer)object;
        //3. 회원번호 --> 회원정보호출
        Optional<MemberEntity> optional = memberRepository.findById(mno);
        if(!optional.isPresent()){return false;}
        //4.로그인된 회원의 엔티티
        MemberEntity memberEntity = optional.get();

      BoardEntity boardEntity = boardRepository.save(boardDto.toEntity());
        if( boardEntity.getBno() !=0) { //2. 생성된 entity 의 게시물번호가 0 이 아니면 성공
            //5. ****!!!! 5.fk 대입 중요하다는뜻이지 않을까 별이  저렇게많은데
            boardEntity.setMemberEntity(memberEntity);
            // *** 양방향[pk필드에 fk연결]
            memberEntity.getBoardEntityList().add(boardEntity);
            return true;
        }
        else{return false;} // 2. 0이면 entity 생성 실패
    }
    //2.게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(){
        List<BoardEntity> elist =  boardRepository.findAll(); //1. 모든 엔티티 호출한다
        List<BoardDto> dlist = new ArrayList<>(); //2. 컨트롤에게 전달할때 형변환[ entity -> dto ] : 이유 역할이 달라서
        for( BoardEntity entity : elist){// 3. 변환
            dlist.add(entity.toDto());
        }
        return  dlist;//4. 변환된 리스트 dlist 반환
    }
    //3.게시물 개별 조회
    @Transactional
    public BoardDto getboard(int bno){
        Optional<BoardEntity> optional = boardRepository.findById(bno);//1. 입력받은 게시물번호로 엔티티 검색 [ Optional]
        if(optional.isPresent()){//2. Optional 안에 있는 내용물 확인
            BoardEntity boardEntity = optional.get();//3. 엔티티 꺼내기 .get();
            return boardEntity.toDto(); //4형변환
        }else{
            return  null; // 4. 없으면 null
        }

    }
    //4. 게시물 삭제
    @Transactional
    public boolean delboard(int bno){
        Optional<BoardEntity> optional =  boardRepository.findById(bno);
       if( optional.isPresent()){
           BoardEntity entity = optional.get();
            boardRepository.delete(entity);// 잦은 엔티티를 삭제한다
            return true;
       }else{return false;}
    }
    //5. 게시물 수정 [ 첨부파일]
    @Transactional
    public boolean upboard(BoardDto boardDto){
        // 1.DTO에서 수정할 PK 번호 이용해서 엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno());
        //2.
        if(optional.isPresent()){
            BoardEntity entity = optional.get();
            // 수정처리 [ 메소드가 별도로 존재x / 엔티티가<----> 레코드/엔티티 객체 자체를 수정]
            entity.setBtitle(boardDto.getBtitle());
            entity.setBcontent(boardDto.getBcontent());
            entity.setBfile(boardDto.getBfile());
            return true;
        } else{return   false;}
    }

}
