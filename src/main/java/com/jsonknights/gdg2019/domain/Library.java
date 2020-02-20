package com.jsonknights.gdg2019.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Builder
public class Library {
    public int index;
    public int numberOfBooks;
    public int timeToSignUpDays;
    public int numberOfBooksShippedPerDay;
    public double value;

    public List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return index == library.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
