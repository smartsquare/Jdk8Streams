package de.bi.jug.inside;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CrimeTypeFilterStreamTest {

    private String testFilePath = "src/test/resources/CrimesTestSet.csv";

	private CrimePerLocationCounter crimePerLocationCounter;

    private Path testFile;

    @Before
    public void setUp() {
		crimePerLocationCounter = new CrimePerLocationCounterStream();
        testFile = Paths.get( testFilePath );
    }

    @Test
	public void should_count_crimes_per_location_correctly()
        throws Exception {

		Map<String, Map<String, Long>> crimesPerLocation = crimePerLocationCounter
				.countCrimePerLocation(testFile);

		assertNotNull(crimesPerLocation);

		assertThat(crimesPerLocation.keySet(), hasSize(7));

		assertThat(crimesPerLocation.get("ALLEY").get("BATTERY"), equalTo(1L));
		assertThat(crimesPerLocation.get("STREET").get("CRIMINAL DAMAGE"),
				equalTo(2L));
		assertThat(crimesPerLocation.get("STREET").get("ROBBERY"), equalTo(1L));
    }

}
