package com.jsonknights.gdg2019.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResultDto {
    public int countOfLibrariesToSignUp;

    public List<LibrarySubmission> librarySubmissions;
}
