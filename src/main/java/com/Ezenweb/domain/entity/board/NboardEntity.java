package com.Ezenweb.domain.entity.board;


import com.Ezenweb.domain.dto.NboardDto;
import com.Ezenweb.domain.dto.NcateogryDto;
import com.Ezenweb.domain.entity.bcategory.NcategoryEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="nbaord")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class NboardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // quto
    private  int nno;
    private  String ntitle;
    private  String ncontent;


    //연관관계
    @ManyToOne
    @JoinColumn(name="ncno")
    @ToString.Exclude
    private NcategoryEntity ncategoryEntity;

    public NboardDto toDto() {
        return NboardDto .builder()
                .nno(this.nno)
                .ntitle(this.ntitle)
                .ncontent(this.ncontent)

                .build();
    }




}
