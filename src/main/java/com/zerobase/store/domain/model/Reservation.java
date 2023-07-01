package com.zerobase.store.domain.model;

import com.zerobase.domain.BaseEntity;
import com.zerobase.member.domain.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private LocalDateTime reservationDateTime;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Store store;
}