package com.zerobase.review.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReviewForm {
    private double starRating;
    private String reviewDescription;
}