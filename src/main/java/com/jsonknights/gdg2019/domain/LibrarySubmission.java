package com.jsonknights.gdg2019.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class LibrarySubmission {
    public int libraryIndex;
    public int countOfBooksSentAfterScanning;

    public Set<Integer> indexesOfSentBooks;
}
