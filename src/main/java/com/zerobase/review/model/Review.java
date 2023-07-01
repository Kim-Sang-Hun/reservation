package com.zerobase.review.model;

import com.zerobase.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
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
}