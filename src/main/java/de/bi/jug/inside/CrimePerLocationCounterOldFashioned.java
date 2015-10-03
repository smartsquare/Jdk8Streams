package de.bi.jug.inside;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.bi.jug.domain.Crime;

public class CrimePerLocationCounterOldFashioned implements
		CrimePerLocationCounter {

	private static final Logger LOG = LoggerFactory
			.getLogger(CrimePerLocationCounterOldFashioned.class);

	@Override
	public Map<String, Map<String, Long>> countCrimePerLocation(Path inputFile)
			throws IOException {

		Map<String, List<Crime>> crimesPerLocation = collectCrimesPerLocation(inputFile);
		Map<String, Map<String, Long>> crimeCountPerLocation = countCrimeTypePerLocation(crimesPerLocation);

		return crimeCountPerLocation;

	}

	private Map<String, List<Crime>> collectCrimesPerLocation(Path inputFile)
			throws IOException, FileNotFoundException {
		Map<String, List<Crime>> crimesPerLocation = Maps.newHashMap();

		try (BufferedReader reader = new BufferedReader(new FileReader(
				inputFile.toFile()))) {

			// skip first line, because its the CSV Header
			reader.readLine();

			String line = null;
			while ((line = reader.readLine()) != null) {
				Crime crime = Crime.createFromInput(line);
				if (crime == null) {
					continue;
				}
				String locationDescription = crime.getLocationDescription();

				if (crimesPerLocation.containsKey(locationDescription)) {
					List<Crime> crimes = crimesPerLocation
							.get(locationDescription);
					crimes.add(crime);
					crimesPerLocation.put(locationDescription, crimes);
				} else {
					crimesPerLocation.put(locationDescription,
							Lists.newArrayList(crime));
				}
			}
		}
		return crimesPerLocation;
	}

	private Map<String, Map<String, Long>> countCrimeTypePerLocation(
			Map<String, List<Crime>> crimesPerLocation) {

		Map<String, Map<String, Long>> crimeCountPerLocation = Maps
				.newHashMap();

		for (String location : crimesPerLocation.keySet()) {

			List<Crime> crimesPerLocations = crimesPerLocation.get(location);

			Map<String, Long> crimeTypeCount = Maps.newHashMap();

			for (Crime crime : crimesPerLocations) {
				String primaryType = crime.getPrimaryType();

				if (crimeTypeCount.containsKey(primaryType)) {
					Long count = crimeTypeCount.get(primaryType);
					count++;
					crimeTypeCount.put(primaryType, count);
				} else {
					crimeTypeCount.put(primaryType, 1L);
				}

			}

			crimeCountPerLocation.put(location, crimeTypeCount);

		}
		return crimeCountPerLocation;
	}

}
