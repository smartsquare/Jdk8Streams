package de.bi.jug.count;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.Maps;

import de.bi.jug.domain.Crime;

public class CrimeTypeCounterStream implements CrimeTypeCounter {

    @Override
    public Map<String, Long> countCrimeTypes(Path inputFile) throws IOException {

        Map<String, Long> result = Maps.newHashMap();

		try (Stream<String> stream = Files.lines(inputFile).skip(1)) {

			result = stream.map(line -> Crime.createFromInput(line)).collect(
					groupingBy(Crime::getPrimaryType, counting()));

        }

        return result;
    }

}
