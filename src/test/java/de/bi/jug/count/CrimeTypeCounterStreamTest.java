package de.bi.jug.count;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CrimeTypeCounterStreamTest {

    private String testFilePath = "src/test/resources/CrimesTestSet.csv";

    private CrimeTypeCounter crimeTypeCounter;

    private Path testFile;

    @Before
    public void setUp() {
        crimeTypeCounter = new CrimeTypeCounterStream();
        testFile = Paths.get( testFilePath );
    }

    @Test
    public void should_count_crime_types_correctly()
        throws Exception {
        Map<String, Long> counts = crimeTypeCounter.countCrimeTypes( testFile );

        assertNotNull( counts );
        assertThat( counts.keySet().size(), equalTo( 8 ) );

        assertThat( counts.get( "BATTERY" ), equalTo( 4L ) );
        assertThat( counts.get( "CRIMINAL DAMAGE" ), equalTo( 2L ) );
        assertThat( counts.get( "ROBBERY" ), equalTo( 1L ) );
        assertThat( counts.get( "DECEPTIVE PRACTICE" ), equalTo( 1L ) );
        assertThat( counts.get( "ASSAULT" ), equalTo( 2L ) );
        assertThat( counts.get( "NARCOTICS" ), equalTo( 2L ) );
        assertThat( counts.get( "MOTOR VEHICLE THEFT" ), equalTo( 3L ) );
        assertThat( counts.get( "THEFT" ), equalTo( 4L ) );
    }

}
