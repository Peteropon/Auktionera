package se.iths.auktionera.business.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequest {
    private double rating;
    private String reviewText;
}
