package com.jsonknights.gdg2019.domain;

import lombok.Value;

import java.util.List;

@Value
public class SourceDto {
    public int numberOfBooks;
    public int numberOfLibraries;
    public int numberOfDaysForScanning;
    public int[] scoresOfTheBook;

    public List<Library> libraries;
}
