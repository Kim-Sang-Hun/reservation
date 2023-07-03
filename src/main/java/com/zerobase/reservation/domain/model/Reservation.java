package com.zerobase.reservation.domain.model;

import com.zerobase.domain.BaseEntity;
import com.zerobase.member.domain.model.Member;
import com.zerobase.review.domain.model.Review;
import com.zerobase.store.domain.model.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private LocalDateTime reservationDateTime;
    private String status;
    private boolean validation;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Store store;
    @OneToOne
    private Review review;
}