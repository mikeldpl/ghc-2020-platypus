package com.jsonknights.gdg2019.domain;

import lombok.Value;

@Value
public class LibrarySubmission {
    public int libraryIndex;
    public int countOfBooksSentAfterScanning;

    public int[] indexesOfSentBooks;
}
