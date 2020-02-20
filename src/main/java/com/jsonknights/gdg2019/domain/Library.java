package com.jsonknights.gdg2019.domain;

import lombok.Value;

@Value
public class Library {
    public int numberOfBooks;
    public int timeToSignUpDays;
    public int numberOfBooksShippedPerDay;

    public int[] booksIndexes;
}
