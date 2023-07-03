package com.zerobase.store.domain.model;

import com.zerobase.domain.BaseEntity;
import com.zerobase.member.domain.model.Member;
import com.zerobase.reservation.domain.model.Reservation;
import com.zerobase.store.domain.UploadStoreForm;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;
    private String storeName;
    private String storeLocation;
    private String storeDescription;
    @OneToMany(mappedBy = "store")
    private List<Reservation> reservation;
    @OneToOne(mappedBy = "store")
    private Member member;

    public static Store from(UploadStoreForm form) {
        return Store.builder()
                .storeName(form.getStoreName())
                .storeLocation(form.getStoreLocation())
                .storeDescription(form.getStoreDescription())
                .build();
    }
}