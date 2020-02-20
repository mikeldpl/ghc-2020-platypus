package com.jsonknights.gdg2019.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Library {
    public int numberOfBooks;
    public int timeToSignUpDays;
    public int numberOfBooksShippedPerDay;
    public double value;

    public List<Book> books;
}
