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
        int[] taskRestrictions = parseNumbers(strings.remove(0));
        int[] bookScores = parseNumbers(strings.remove(0));

        List<Library> libraries = new ArrayList<>();
        for (int i = 0; i < strings.size() / 2; i += 2) {
            int[] libraryInfo = parseNumbers(strings.get(i));
            List<Book> books = Arrays.stream(parseNumbers(strings.get(i + 1)))
                    .mapToObj(index -> new Book(index, bookScores[index]))
                    .collect(Collectors.toList());
            libraries.add(new Library(
                    libraryInfo[0],
                    libraryInfo[1],
                    libraryInfo[2],
                    books
            ));
        }

        return new SourceDto(
                taskRestrictions[0],
                taskRestrictions[1],
                taskRestrictions[2],
                bookScores,
                libraries
        );
    }

    public int[] parseNumbers(String string) {
        return Arrays.stream(string.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
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
