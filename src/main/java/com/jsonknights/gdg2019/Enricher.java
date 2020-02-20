package com.jsonknights.gdg2019;

import com.jsonknights.gdg2019.domain.Book;
import com.jsonknights.gdg2019.domain.Library;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enricher {
    Map<Book, Integer> occurrencesOfBooks;
    List<Library> libraries;
    double sumOfBooksValues;

    public Enricher(List<Library> libraries) {
        this.libraries = libraries;
        this.occurrencesOfBooks = new HashMap<>();
        for (Library library : libraries) {
            library.books.stream()
                    .distinct()
                    .forEach(this::incrementOcccurenceOfTheBook);
        }
        sumOfBooksValues = computeSumOfBooksValues();
    }

    public void enrichBooks() {
        occurrencesOfBooks.keySet().forEach(book -> book.setValue(computeValueOfTheBook(book)));
    }

    public void enrichLibraries() {
        for (Library library : libraries) {
            int sumOfBooksInLibrary = library.books.size();
            double value = (sumOfBooksValues * library.numberOfBooksShippedPerDay) / sumOfBooksInLibrary;
            library.setValue(value);
            library.books.sort(Comparator.comparingDouble(Book::getScore).reversed());
        }

        libraries.sort(Comparator.comparing(Library::getValue).reversed());
    }

    private void incrementOcccurenceOfTheBook(Book book) {
        occurrencesOfBooks.compute(book, (_u, count) -> count == null ? 1 : count++);
    }

    private int computeValueOfTheBook(Book book) {
        final Integer occurencesCount = occurrencesOfBooks.get(book);
        return book.getScore() / occurencesCount;
    }

    private double computeSumOfBooksValues() {
        double sum = 0;
        for (Book book : occurrencesOfBooks.keySet()) {
            sum += book.getValue();
        }
        return sum;
    }
}
