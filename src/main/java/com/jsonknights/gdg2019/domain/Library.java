package com.jsonknights.gdg2019.domain;

import lombok.Value;

import java.util.List;

@Value
public class Library {
    public int numberOfBooks;
    public int timeToSignUpDays;
    public int numberOfBooksShippedPerDay;

    public List<Book> books;
}
