package com.Ezenweb.domain.dto;


import com.Ezenweb.domain.entity.board.NboardEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class NboardDto {

    private  int nno;
    private  String ntitle;
    private  String ncontent;
    private  int ncno; //fk

    public NboardEntity toEntity(){
        return  NboardEntity.builder()
                .nno(this.nno)
                .ntitle(this.ntitle)
                .ncontent(this.ncontent)
                .build();
    }


}
