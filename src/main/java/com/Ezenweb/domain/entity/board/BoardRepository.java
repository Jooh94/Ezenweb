package com.Ezenweb.domain.entity.board;

import com.Ezenweb.domain.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {

    //1. 기본 메소드 외 메소드 추가
        //1.  .findById(pk값) : 해당 pk의 엔티티만 하나 호출
        //2.  .findAll() : 모든 엔티티를 호출
        //3. [직접 find 만들기] : .findby필드명(조건) [Optional<엔티티명>]
        //3.                    .findby필드명(조건)   [엔티티명]
        //3.                    .findby필드명(조건) [List<엔티티명>]
        //3.                    .findby필드명(조건,pageable pageable) [Page<엔티티명>]
            //1. @Quert( value = "쿼리문작성",nativeQuery=true)
            //@Query : JPQl : sql 사용자정의
                    //@Param("번수명") 생략가능 [jdk 8 이상]
//        @Query(value="select * from board where bcno = :bcno", nativeQuery = true)
//        Page<BoardEntity> findBybcno(@Param("bcno") int bcno, Pageable pageable);
            // page 사용하는 이유 페이징처리 할려고
//    @Query(value="select p from board p where p.bcno = ?1")
//    Page<BoardEntity> findBybcno(int bcno,Pageable pageable);

    //1. 제목 검색

    @Query(value = "select * from board where bcno = :bcno and btitle like %:keyword%",nativeQuery = true)
    Page<BoardEntity> findbtitle(int bcno, String keyword , Pageable pageable);

    //2. 내용 검색
    @Query(value = "select * from board where bcno = :bcno and bcontent like %:keyword%",nativeQuery = true)
    Page<BoardEntity> findbybcontent( int bcno , String keyword , Pageable pageable);
    @Query(value="select * from board where bcno = :bcno", nativeQuery = true)
      Page<BoardEntity> findBybcno(@Param("bcno") int bcno, Pageable pageable);


}
