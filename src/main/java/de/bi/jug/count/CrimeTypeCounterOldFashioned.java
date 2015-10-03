package de.bi.jug.count;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import de.bi.jug.domain.Crime;

public class CrimeTypeCounterOldFashioned implements CrimeTypeCounter {

    @Override
	public Map<String, Long> countCrimeTypes(Path inputFile) throws IOException {

		Map<String, Long> result = Maps.newHashMap();

		List<String> csvFileLines = Files.readAllLines(inputFile);
		boolean firstLine = true;
		for (String line : csvFileLines) {
			if (firstLine) {
				firstLine = false;
				continue;
			}

			Crime crime = Crime.createFromInput(line);

			String primaryType = crime.getPrimaryType();

			if (result.containsKey(primaryType)) {
				Long count = result.get(primaryType);
				count++;
				result.put(primaryType, count);
			} else {
				result.put(primaryType, 1L);
			}

		}

		return result;
    }

}
