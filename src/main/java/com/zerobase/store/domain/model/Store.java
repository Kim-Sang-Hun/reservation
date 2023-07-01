package com.zerobase.store.domain.model;

import com.zerobase.member.domain.model.Member;
import com.zerobase.store.domain.UploadForm;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;
    @Column(nullable = false)
    private String storeName;
    @Column(nullable = false)
    private String storeLocation;
    private String storeDescription;
    @OneToOne(mappedBy = "store")
    private Member member;

    public static Store from(UploadForm form) {
        return Store.builder()
                .storeName(form.getStoreName())
                .storeLocation(form.getStoreLocation())
                .storeDescription(form.getStoreDescription())
                .build();
    }
}