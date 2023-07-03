package com.zerobase.review.domain.model;

import com.zerobase.domain.BaseEntity;
import com.zerobase.reservation.domain.model.Reservation;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Review extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private double starRating;
    @Column(nullable = false)
    private String reviewDescription;
    @OneToOne
    private Reservation reservation;
}