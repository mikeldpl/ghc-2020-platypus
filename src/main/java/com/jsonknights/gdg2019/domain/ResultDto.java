package com.jsonknights.gdg2019.domain;

import lombok.Value;

import java.util.List;

@Value
public class ResultDto {
    public int countOfLibrariesToSignUp;

    public List<LibrarySubmission> librarySubmissions;
}
