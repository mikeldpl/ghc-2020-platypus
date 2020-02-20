package com.jsonknights.gdg2019.domain;

import lombok.Value;

import java.util.Set;

@Value
public class LibrarySubmission {
    public int libraryIndex;
    public int countOfBooksSentAfterScanning;

    public Set<Integer> indexesOfSentBooks;
}
