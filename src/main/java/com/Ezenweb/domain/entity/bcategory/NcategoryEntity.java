package com.Ezenweb.domain.entity.bcategory;


import com.Ezenweb.domain.dto.NboardDto;
import com.Ezenweb.domain.dto.NcateogryDto;
import com.Ezenweb.domain.entity.board.NboardEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ncatagory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NcategoryEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int ncno; //카테고리 pk
    private  String ncname; //카테고리 이름
    @OneToMany(mappedBy = "ncategoryEntity")
    @Builder.Default
    private List<NboardEntity>nboardEntityList
            =new ArrayList<>();

    public NcateogryDto toDto(){
        return NcateogryDto .builder()
                .ncno(this.ncno)
                .ncname(this.ncname)
                .build();
    }





}
