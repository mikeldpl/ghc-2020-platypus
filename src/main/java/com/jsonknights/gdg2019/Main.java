package com.jsonknights.gdg2019;

import com.jsonknights.gdg2019.domain.SourceDto;
import org.apache.commons.lang3.tuple.Pair;
import org.zeroturnaround.zip.ZipUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final List<Pair<Path, Path>> inOutFiles = Arrays.asList(
            Pair.of(Paths.get("data/in/a_example.txt"), Paths.get("data/a.txt")),
            Pair.of(Paths.get("data/in/b_read_on.txt"), Paths.get("data/b.txt")),
            Pair.of(Paths.get("data/in/c_incunabula.txt"), Paths.get("data/c.txt")),
            Pair.of(Paths.get("data/in/d_tough_choices.txt"), Paths.get("data/d.txt")),
            Pair.of(Paths.get("data/in/e_so_many_books.txt"), Paths.get("data/e.txt")),
            Pair.of(Paths.get("data/in/f_libraries_of_the_world.txt"), Paths.get("data/f.txt"))
    );

    private static final Path srcOut = Paths.get("data/src.zip");
    private static final Path srcIn = Paths.get("src/main");

    public static void main(String[] args) throws IOException {
        prepareSrc();

        for (Pair<Path, Path> pathPair : inOutFiles) {
            InOutManager inOutManager = new InOutManager(pathPair.getLeft(), pathPair.getRight());

            SourceDto sourceDto = inOutManager.readSource();
            Enricher enricher = new Enricher(sourceDto.libraries);
            enricher.enrichBooks();
            enricher.enrichLibraries();

            System.out.println(sourceDto);

            //todo
            // final ResultDto resultDto = new ResultDto(-1, null);

            // inOutManager.submitResult(resultDto);
        }
    }

    private static void prepareSrc() {
        ZipUtil.pack(srcIn.toFile(), srcOut.toFile());
    }
}
