package com.jsonknights.gdg2019.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    int index;
    int score;
    double value;
}
