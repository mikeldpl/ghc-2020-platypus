package com.jsonknights.gdg2019;

import com.jsonknights.gdg2019.domain.Book;
import com.jsonknights.gdg2019.domain.Library;
import com.jsonknights.gdg2019.domain.LibrarySubmission;
import com.jsonknights.gdg2019.domain.ResultDto;
import com.jsonknights.gdg2019.domain.SourceDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InOutManager {

    private Path in;
    private Path out;

    public InOutManager(Path in, Path out) {
        this.in = in;
        this.out = out;
    }

    public void submitResult(ResultDto resultDto) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(String.valueOf(resultDto.countOfLibrariesToSignUp));

        for (LibrarySubmission submission : resultDto.librarySubmissions) {
            lines.add(submission.libraryIndex + " " + submission.countOfBooksSentAfterScanning);
            String indexes = submission.indexesOfSentBooks.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" "));
            lines.add(indexes);
        }

        Files.write(out, lines);
    }

    public SourceDto readSource() throws IOException {
        List<String> strings = Files.readAllLines(in);
        if (strings.size() <= 1) {
            throw new IllegalArgumentException("File contains incorrect count of lines");
        }
        List<Integer> taskRestrictions = parseNumbers(strings.remove(0));
        List<Integer> bookScores = parseNumbers(strings.remove(0));
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < bookScores.size(); i++) {
            books.add(Book.builder().index(i).score(bookScores.get(i)).build());
        }

        List<Library> libraries = new ArrayList<>();
        for (int i = 0; i < strings.size(); i += 2) {
            if ("".equals(strings.get(i))) {
                if (strings.size() == i + 1) {
                    continue;
                } else {
                    throw new IllegalArgumentException();
                }
            }
            List<Integer> libraryInfo = parseNumbers(strings.get(i));
            List<Book> libBooks = parseNumbers(strings.get(i + 1))
                    .stream()
                    .map(books::get)
                    .collect(Collectors.toList());
            libraries.add(
                    Library.builder()
                            .index(i / 2)
                            .numberOfBooks(libraryInfo.get(0))
                            .timeToSignUpDays(libraryInfo.get(1))
                            .numberOfBooksShippedPerDay(libraryInfo.get(2))
                            .books(libBooks)
                            .build()
            );
        }

        return SourceDto.builder()
                .numberOfBooks(taskRestrictions.get(0))
                .numberOfLibraries(taskRestrictions.get(1))
                .numberOfDaysForScanning(taskRestrictions.get(2))
                .books(books)
                .libraries(libraries)
                .build();
    }

    public List<Integer> parseNumbers(String string) {
        return Arrays.stream(string.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

//    private SourceDto parsePhoto(String line, int index) {
//        List<String> words = Arrays.asList(line.split(" "));
//        if (words.size() < 3) {
//            throw new IllegalArgumentException("Line: '" + line + "' has incorrect format.");//do not have time to create appropriate exception class
//        }
//        String orientationSign = words.get(0);
//        int tagCount = Integer.parseInt(words.get(1));
//        if (words.size() - 2 != tagCount) {
//            throw new IllegalArgumentException("Line: '" + line + "' has incorrect count of tags.");
//        }
//        List<String> tags = words.subList(2, words.size());
//        return createPhoto(orientationSign, tags, index);
//    }

//    private SourceDto createPhoto(String orientationSign, List<String> tags, int index) {
//        SourceDto.PhotoBuilder photoBuilder = SourceDto.builder();
//        photoBuilder.tags(new HashSet<>(tags));
//        photoBuilder.index(index);
//        switch (orientationSign) {
//            case "H":
//                photoBuilder.horizontal(true);
//                break;
//            case "V":
//                photoBuilder.horizontal(false);
//                break;
//            default:
//                throw new IllegalArgumentException(orientationSign + " is incorrect orientation sign.");
//        }
//        return photoBuilder.build();
//    }

}
