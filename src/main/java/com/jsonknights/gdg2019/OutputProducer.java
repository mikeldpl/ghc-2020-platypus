package com.jsonknights.gdg2019;

import com.jsonknights.gdg2019.domain.Book;
import com.jsonknights.gdg2019.domain.Library;
import com.jsonknights.gdg2019.domain.LibrarySubmission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutputProducer {
    List<Library> libraries;
    int numDays;

    public OutputProducer(List<Library> libraries, int numDays) {
        this.libraries = libraries;
        this.numDays = numDays;
    }

    public List<LibrarySubmission> produce() {
        Set<Book> usedBooks = new HashSet<>();
        Map<Integer, LibrarySubmission> submissions = new HashMap<>();
        for (int i = 0; i < numDays; i++) {
            for (int j = 0; j < libraries.size(); j++) {
                Library currentLibrary = libraries.get(j);
                if (!currentLibrary.books.isEmpty()) {
                    int currentSignupPeriod = 0;
                    for (int k = 0; k <= j; k++) {
                        currentSignupPeriod += libraries.get(k).timeToSignUpDays;
                    }
                    if (i < currentSignupPeriod) {
                        break;
                    }
                    LibrarySubmission currentSubmission = submissions.computeIfAbsent(currentLibrary.index, k -> new LibrarySubmission(currentLibrary.index, 0, new HashSet<>()));
                    int k = 0;
                    while ( k < currentLibrary.numberOfBooksShippedPerDay && !currentLibrary.books.isEmpty()) {
                        Book currentBook = currentLibrary.books.get(0);
                        if (!usedBooks.contains(currentBook)) {
                            currentSubmission.indexesOfSentBooks.add(currentBook.getIndex());
                            usedBooks.add(currentBook);
                            k++;
                        }
                        currentLibrary.books.remove(0);
                    }
                    if (currentLibrary.books.isEmpty()) {
                        currentSubmission.countOfBooksSentAfterScanning = currentSubmission.indexesOfSentBooks.size();
                    }
                }
            }
        }
        return new ArrayList<>(submissions.values());
    }
}
