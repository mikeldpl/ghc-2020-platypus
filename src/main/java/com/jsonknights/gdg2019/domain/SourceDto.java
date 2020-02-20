package com.jsonknights.gdg2019.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SourceDto {
    public int numberOfBooks;
    public int numberOfLibraries;
    public int numberOfDaysForScanning;
    public List<Book> books;

    public List<Library> libraries;
}
