package de.bi.jug.inside;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.Maps;

import de.bi.jug.domain.Crime;

public class CrimePerLocationCounterStream implements CrimePerLocationCounter {

	@Override
    public Map<String, Map<String, Long>> countCrimePerLocation(Path inputFile)
            throws IOException {

		Map<String, Map<String, Long>> crimesPerLocation = Maps.newHashMap();
        try (Stream<String> stream = Files.lines(inputFile).skip(1)) {

            crimesPerLocation = stream
                    .map(line -> Crime.createFromInput(line))
					.filter(crime -> crime != null)
                    .collect(
							groupingBy(
									Crime::getLocationDescription,
									groupingBy(Crime::getPrimaryType,
											counting())));

        }

		return crimesPerLocation;
    }

}
