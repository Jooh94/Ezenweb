package com.Ezenweb.service;


import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.bcategory.BcategoryRepository;
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
import java.util.function.Consumer;

@Service // 컴포넌트
public class BoardService {
    //--------------------1.전역변수-----------------//
    @Autowired
    private HttpServletRequest request; // 요청객체 선언
    @Autowired
    private MemberRepository memberRepository; // 회원 리포지토리 객체 선언
    @Autowired
    private MemberService memberService; //
    @Autowired //
    private BoardRepository boardRepository; // 게시물 리포지토리 객체 선언
            //@Transactional : 엔티티 DML 적용 할때 사용되는 어노테이션
            //1.메소드
    @Autowired
    private BcategoryRepository bcategoryRepository;
    //--------------------2.서비스-----------------//
    //1.게시물 쓰기
    @Transactional
    public boolean setboard(BoardDto boardDto){

        MemberEntity memberEntity = memberService.getEntity();
        if(memberEntity == null){return false;}

        Optional<BcategoryEntity> optional = bcategoryRepository.findById(boardDto.getBcno());
        if(!optional.isPresent()){return false;}
        BcategoryEntity bcategoryEntity = optional.get();

      BoardEntity boardEntity = boardRepository.save(boardDto.toEntity());
        if( boardEntity.getBno() !=0) { //2. 생성된 entity 의 게시물번호가 0 이 아니면 성공
           //1.회원 <---> 게시물 연관관게
            boardEntity.setMemberEntity(memberEntity);
            memberEntity.getBoardEntityList().add(boardEntity);
            //2. 카테고리 <---> 게시물 연관관계 대입
            boardEntity.setBcategoryEntity(bcategoryEntity);
            bcategoryEntity.getBoardEntityList().add(boardEntity);
            return true;
        }
        else{return false;} // 2. 0이면 entity 생성 실패
    }
    //2.게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(int bcno){
        List<BoardEntity> elist = null;
        if( bcno == 0){ elist = boardRepository.findAll(); }
        else{// 카테고리번호가 0이 아니면 선택된 카테고리별 보기
             BcategoryEntity bcEntity = bcategoryRepository.findById(bcno).get();
            elist = bcEntity.getBoardEntityList(); // 해당 엔티티의 게시물목록
        }
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

    //6.카테고리 등록
    public boolean setbcategory(BcategoryDto bcategoryDto){
        BcategoryEntity entity = bcategoryRepository.save(bcategoryDto.toEntity());
        if(entity.getBcno() != 0){return  true;}
        else{return false;}
    }

    //7.카테고리 출력
    public  List<BcategoryDto> bcategorlist(){
        List<BcategoryEntity> entityList= bcategoryRepository.findAll();
        List<BcategoryDto> dtolist = new ArrayList<>();
        entityList.forEach(e->dtolist.add(e.toDto()));
        return dtolist;
    }
}
 /*
       //1. 리스트를 순회하는 방법3가지
        for ( int i = 0 ; i< entityList.size(); i++){
            BcategoryEntity e=  entityList.get(i);
            System.out.println(e.toString());
        }
        for(BcategoryEntity e : entityList){
            System.out.println(e.toString());
        }
        entityList.forEach(e->{e.toString(); });
            // 화살표함수[람다식표현] js: (인수) =>{실행코드}  java:인수->{실행코드}
        entityList.forEach(e -> dtolist.add(e.toDto()));
        return dtolist;
    */
