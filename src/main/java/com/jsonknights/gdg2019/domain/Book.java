package com.jsonknights.gdg2019.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Book {
    int index;
    int score;
    double value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return index == book.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
