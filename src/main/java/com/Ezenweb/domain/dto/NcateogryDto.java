package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.bcategory.NcategoryEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter@Setter
@ToString
@Builder
public class NcateogryDto {

    private  int ncno;
    private  String ncname;

    public NcategoryEntity toEntity(){
        return  NcategoryEntity
                .builder()
                .ncno(this.ncno)
                .ncname(this.ncname)
                .build();
    }


}
