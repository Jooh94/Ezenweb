package com.Ezenweb.service;


import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.dto.NboardDto;
import com.Ezenweb.domain.dto.NcateogryDto;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.bcategory.BcategoryRepository;
import com.Ezenweb.domain.entity.bcategory.NcategoryEntity;
import com.Ezenweb.domain.entity.bcategory.NcategoryRepository;
import com.Ezenweb.domain.entity.board.BoardEntity;
import com.Ezenweb.domain.entity.board.BoardRepository;
import com.Ezenweb.domain.entity.board.NboardEntity;
import com.Ezenweb.domain.entity.board.NboardRepository;
import com.Ezenweb.domain.entity.member.MemberEntity;
import com.Ezenweb.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.function.Consumer;

@Service // 컴포넌트
public class BoardService {
    //--------------------1.전역변수-----------------//
    @Autowired
    private HttpServletRequest request; // 요청 객체 선언
    @Autowired
    private HttpServletResponse response; // 응답 객체 선언
    @Autowired
    private MemberRepository memberRepository; // 회원 리포지토리 객체 선언
    @Autowired
    private MemberService memberService; //
    @Autowired //
    private BoardRepository boardRepository; // 게시물 리포지토리 객체 선언
    @Autowired
    private BcategoryRepository bcategoryRepository;

    //첨부파일 경로
    String path = "C:\\Users\\504\\Desktop\\Ezenweb\\Ezenweb\\src\\main\\resources\\static\\bupload";

    @Autowired
    private NboardRepository nboardRepository;
    @Autowired
    private NcategoryRepository ncategoryRepository;





    //--------------------2.서비스-----------------//

    // 0 첨부파일 다운로드
    public  void filedownload(String filename){
        String realfilename = "";
        String [] split =filename.split("_"); //1. _ 기준으로 자르기 [ 파일명만 가져오기]
        for(int i = 1; i < split.length; i++){ //2. uuid 제외한 반복문 돌리기
            realfilename += split[i];           //3. 뒤자리 문자열 추가
            if(split.length-1 !=i){  // 마지막 인덱스 아니면
                realfilename += "_";    //문자열[1] _ 문자열[2] _문자열[3].확장자명

            }

        }
        //uuid 제거
        String realfile = filename.split("_")[1]; // _ 기준으로 자르기 해서 뒤부분
        //1. 경로 찾기
        String filepath = path+filename;
        //2.헤더 구성 [HTTp 해서 지원하는 다운로드형식 메소드 [response]
        try {
            response.setHeader(
                    "Content-Disposition", // 다운로드 형식 [브라우저 마다 다름]
                    "attachmen;filename=" + URLEncoder.encode(realfile, "UTF-8"));
        File file = new File(filepath);
    //3. 다운로드 스트림 [ ]
        //1. 다운로드 할 파일의 바이트 읽어오기
            BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
        //2. 읽어온 바이트 저장
            byte[] bytes = new byte[(int)file.length()];
            //3.파일의 길이 만큼 읽어와서 바이트를 배열에 저장
            fin.read(bytes);    // * 스트림 읽기
            //4.출력 스트림 객체선언
            BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());
            //5.응답하고 [내보내기]
            fout.write(bytes); //* 스트림 내보내기
            //6.버퍼 초기화
            fout.flush(); fout.close(); fin.close();
        }catch (Exception e){System.out.println(e);}
    }
    // * 첨부파일 업로드 [ 1. 쓰기메소드 2. 수정메소드 ] 사용
    @Transactional              //  boardDto : 쓰기,수정 대상     BoardEntity:원본
    public boolean fileupload( BoardDto boardDto , BoardEntity boardEntity ){
        if( boardDto.getBfile() != null ) { // ** 첨부파일 있을때
            // * 업로드 된 파일의 이름 [ 문제점 : 파일명 중복 ]
            String uuid = UUID.randomUUID().toString(); // 1. 난수생성
            String filename = uuid + "_" + boardDto.getBfile().getOriginalFilename(); // 2. 난수+파일명
            // * 첨부파일명 db 에 등록
            boardEntity.setBfile(filename); // 해당 파일명 엔티티에 저장 // 3. 난수+파일명 엔티티 에 저장
            // * 첨부파일 업로드 // 3. 저장할 경로 [ 전역변수 ]
            try {
                File uploadfile = new File(path + filename);  // 4. 경로+파일명 [ 객체화 ]
                boardDto.getBfile().transferTo(uploadfile);   // 5. 해당 객체 경로 로 업로드
            } catch (Exception e) {
                System.out.println("첨부파일 업로드 실패 ");
            }
            return  true;
        }else{ return  false;}
    }

    // 1. 게시물 쓰기 [ 업로드 ]
    @Transactional
    public boolean setboard( BoardDto boardDto ){
        // ---------- 로그인 회원 찾기 메소드 실행 --> 회원엔티티 검색 --------------  //
        MemberEntity memberEntity = memberService.getEntity();
        if( memberEntity == null ){ return false; }
        // ---------------------------- //
        // ------------ 선택한 카테고리 번호 --> 카테고리 엔티티 검색 --------------  //
        Optional<BcategoryEntity> optional = bcategoryRepository.findById( boardDto.getBcno() );
        if ( !optional.isPresent()) { return false;}
        BcategoryEntity bcategoryEntity = optional.get();
        // --------------------------  //
        BoardEntity boardEntity  = boardRepository.save( boardDto.toEntity() );  // 1. dto --> entity [ INSERT ] 저장된 entity 반환
        if( boardEntity.getBno() != 0 ){   // 2. 생성된 entity의 게시물번호가 0 이 아니면  성공

            fileupload( boardDto , boardEntity ); // 업로드 함수 실행

            // 1. 회원 <---> 게시물 연관관계 대입
            boardEntity.setMemberEntity( memberEntity ); // ***!!!! 5. fk 대입
            memberEntity.getBoardEntityList().add( boardEntity); // *** 양방향 [ pk필드에 fk 연결 ]
            // 2. 카테고리 <---> 게시물 연관관계 대입
            boardEntity.setBcategoryEntity( bcategoryEntity );
            bcategoryEntity.getBoardEntityList().add( boardEntity );
            return true;
        }
        else{ return false; } // 2. 0 이면 entity 생성 실패
    }
    // 2. 게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist( int page , int bcno , String key, String keyword ){
        Page<BoardEntity> elist = null; //1. 페이징 처리된 엔티티 리스트 객체선언
        Pageable pageable = PageRequest.of(page-1,3 ,Sort.by(Sort.Direction.DESC,"bno"));
                                //PageRequest.of(현재 페이지번호,표시할레코드수,정렬)
        //3. 검색여부 판단 /카테고리 판단
        if(key.equals("btitle")){ // 검색필드가 제목이면
            //1. 조건: 제목검색 2.조건:카테고리
            elist = boardRepository.findbtitle(bcno,keyword,pageable);
        }else if(key.equals("bcontent")){ // 검색필드가 제목이면
            //1. 조건: 제목검색 2.조건:카테고리
            elist = boardRepository.findbybcontent(bcno,keyword,pageable);
        }else{ // 검색이 없으면
            //1.조건:카테고리
            if (bcno ==0) elist = boardRepository.findAll(pageable);
            else elist = boardRepository.findBybcno(bcno,pageable);
        }
        //프론트엔드에 표시할 페이징번호 버튼 수
        int btncount =5;                            //1. 페이지에 표시할 총 페이지 버튼 개수
        int startbtn =(page/btncount)* btncount +1; //2. 시작번호버튼
        int endbtn = startbtn + btncount-1;          //3. 마지막 번호버튼
        if( endbtn > elist.getTotalPages()) endbtn = elist.getTotalPages();

        List<BoardDto> dlist = new ArrayList<>(); // 2. 컨트롤에게 전달할때 형변환[ entity->dto ] : 역할이 달라서
        for( BoardEntity entity : elist ){ // 3. 변환
            dlist.add( entity.toDto() );
        }

        dlist.get(0).setStartbtn(startbtn);
        dlist.get(0).setEndbtn(endbtn);

        return dlist;  // 4. 변환된 리스트 dist 반환
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
    // 4. 게시물 삭제
    @Transactional
    public boolean delboard( int bno ){
        Optional<BoardEntity> optional = boardRepository.findById( bno);
        if( optional.isPresent() ){
            BoardEntity boardEntity =  optional.get();

            // 첨부파일 같이 삭제
            if( boardEntity.getBfile() != null ) {   // 기존 첨부파일 있을때
                File file = new File(path + boardEntity.getBfile()); // 기존 첨부파일 객체화
                if (file.exists()) {   file.delete(); }           // 존재하면  /// 파일 삭제
            }

            boardRepository.delete( boardEntity ); // 찾은 엔티티를 삭제한다.
            return true;
        }else{ return false; }
    }


    // 5. 게시물 수정 [ 첨부파일  1.첨부파일 있을때->첨부파일변경  , 2.첨부파일 없을때 -> 첨부파일 추가 ]
    @Transactional
    public boolean upboard( BoardDto boardDto){
        // 1. DTO에서 수정할 PK번호 이용해서 엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById( boardDto.getBno() );
        if( optional.isPresent() ){  // 2.
            BoardEntity boardEntity = optional.get();

            // 1. 수정할 첨부파일이 있을때    ----> 새로운 첨부파일 업로드 , db 수정한다.
            if( boardDto.getBfile() != null ){      // boardDto : 수정할정보   boardEntity : 원본[db테이블]
                if( boardEntity.getBfile() != null ) {   // 기존 첨부파일 있을때
                    File file = new File(path + boardEntity.getBfile()); // 기존 첨부파일 객체화
                    if (file.exists()) {   file.delete(); }           // 존재하면  /// 파일 삭제
                }
                fileupload( boardDto , boardEntity ); // 업로드 함수 실행
            }

            // * 수정처리 [ 메소드 별도 존재x /  엔티티 객체 <--매핑--> 레코드 / 엔티티 객체 필드를 수정 : @Transactional ]
            boardEntity.setBtitle( boardDto.getBtitle() );
            boardEntity.setBcontent( boardDto.getBcontent()) ;
            return true;
        }else{  return false;  }
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

    //비회원 글쓰기
    @Transactional
    public boolean nsetboard(NboardDto nboardDto){

        Optional<NcategoryEntity> optional = ncategoryRepository.findById(nboardDto.getNcno());
        if(!optional.isPresent()){return  false;}
        NcategoryEntity ncategoryEntity = optional.get();

        NboardEntity nboardEntity = nboardRepository.save(nboardDto.toEntity());
        if(nboardEntity.getNno() != 0){

           //카테고리 - 게시물 연관관계 대입
           nboardEntity.setNcategoryEntity(ncategoryEntity);
           ncategoryEntity.getNboardEntityList().add(nboardEntity);
            return true;
        }
         else{return false;}
    }
    //비회원 글목록
    @Transactional
    public List<NboardDto> nlist(int ncno){
        List<NboardEntity> elist = null;
        if( ncno ==0){elist=nboardRepository.findAll();//카테고리 번호가  0이면 전체보기
        }
        else{ // 카테고리 번호가 0 이니면 선택된 카테고리별 보기
   NcategoryEntity ncEntity =    ncategoryRepository.findById(ncno).get();
            elist = ncEntity.getNboardEntityList(); // 해당 엔티티 게시물 목록

        } //카테고리 0이아니면 선택된 카테고리보기
        List<NboardDto> nlist = new ArrayList<>();
        for( NboardEntity entity : elist){
            nlist.add(entity.toDto());
        }
        return  nlist;
    }


    //6.비회원 카테고리 등록
    public boolean nsetncategory(NcateogryDto ncateogryDto) {
        NcategoryEntity nce = ncategoryRepository.save(ncateogryDto.toEntity());
        if (nce.getNcno() != 0) {return true;}
        else {return false;}

    }

    //7. 비회원모든카테고리 출력
    public  List<NcateogryDto> ncategorylist(){
        List<NcategoryEntity> entityList = ncategoryRepository.findAll();
        List<NcateogryDto> ndtolist = new ArrayList<>();
        entityList.forEach(e ->ndtolist.add(e.toDto()));
        return ndtolist;
    }

    //비회원 게시물 삭제
    @Transactional
    public boolean ndelboard(int nno){
        Optional<NboardEntity> optional =  nboardRepository.findById(nno);
        if( optional.isPresent()){
            NboardEntity entity = optional.get();
            nboardRepository.delete(entity);// 찾은 엔티티를 삭제한다
            return true;
        }else{return false;}
    }

    //5. 비회원 수정
    @Transactional
    public boolean nupboard(NboardDto nboardDto){
        // 1.DTO에서 수정할 PK 번호 이용해서 엔티티 찾기
        Optional<NboardEntity> optional = nboardRepository.findById(nboardDto.getNno());
        //2.
        if(optional.isPresent()){
            NboardEntity entity = optional.get();

            entity.setNtitle(nboardDto.getNtitle());
            entity.setNcontent(nboardDto.getNcontent());

            return true;
        } else{return   false;}
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
