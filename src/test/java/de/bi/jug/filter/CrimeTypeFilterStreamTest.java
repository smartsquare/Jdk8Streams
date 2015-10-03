package de.bi.jug.filter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.bi.jug.domain.Crime;
import de.bi.jug.domain.CrimeType;

public class CrimeTypeFilterStreamTest {

    private String testFilePath = "src/test/resources/CrimesTestSet.csv";

    private CrimeTypeFilter crimeTypeFilter;

    private Path testFile;

    @Before
    public void setUp() {
        crimeTypeFilter = new CrimeTypeFilterStream();
        testFile = Paths.get( testFilePath );
    }

    @Test
    public void should_filter_crime_types_correctly()
        throws Exception {

        List<Crime> crimes = crimeTypeFilter.filterCrimeTypes( testFile, CrimeType.NARCOTICS );

        assertNotNull( crimes );
        assertThat( crimes, hasSize( 2 ) );

        crimes = crimeTypeFilter.filterCrimeTypes( testFile, CrimeType.MOTOR_VEHICLE_THEFT );
        assertThat( crimes, hasSize( 3 ) );
    }

}
