package com.jsonknights.gdg2019;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.jsonknights.gdg2019.domain.SourceDto;
import org.apache.commons.lang3.tuple.Pair;
import org.zeroturnaround.zip.ZipUtil;

public class Main {

    private static final List<Pair<Path, Path>> inOutFiles = Arrays.asList(
            Pair.of(Paths.get("data/in/a_example.txt"), Paths.get("data/a.txt")),
            Pair.of(Paths.get("data/in/b_lovely_landscapes.txt"), Paths.get("data/b.txt")),
            Pair.of(Paths.get("data/in/c_memorable_moments.txt"), Paths.get("data/c.txt")),
            Pair.of(Paths.get("data/in/d_pet_pictures.txt"), Paths.get("data/d.txt")),
            Pair.of(Paths.get("data/in/e_shiny_selfies.txt"), Paths.get("data/e.txt"))
    );

    private static final Path srcOut = Paths.get("data/src.zip");
    private static final Path srcIn = Paths.get("src/main");

    public static void main(String[] args) throws IOException {
        prepareSrc();

        for (Pair<Path, Path> pathPair : inOutFiles) {
            InOutManager inOutManager = new InOutManager(pathPair.getLeft(), pathPair.getRight());

            List<SourceDto> sourceDtos = inOutManager.readSource();

            //todo

            inOutManager.submitResult();
        }
    }

    private static void prepareSrc() {
        ZipUtil.pack(srcIn.toFile(), srcOut.toFile());
    }
}
