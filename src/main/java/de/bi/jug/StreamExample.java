package de.bi.jug;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bi.jug.inside.CrimePerLocationCounter;
import de.bi.jug.inside.CrimePerLocationCounterStream;

public class StreamExample {

    private static final Logger LOG = LoggerFactory.getLogger( StreamExample.class );

    private String file = "src/main/resources/Crimes_-_2001_to_present.csv";

    public static void main( String[] args ) {
        StreamExample streamy = new StreamExample();

        try {
            streamy.streamy();
        }
        catch ( IOException e ) {
            LOG.debug( e.getMessage(), e );
        }
    }

    public void streamy()
        throws IOException {

        CrimePerLocationCounter counter = new CrimePerLocationCounterStream();

        Instant start = Instant.now();
        Map<String, Map<String, Long>> crimeCountPerLocation = counter
            .countCrimePerLocation( Paths.get( file ) );
        Instant stop = Instant.now();

        LOG.info(
            "Done evaluating the data in {} milli sec ... Printing the result",
            stop.toEpochMilli() - start.toEpochMilli() );
        // output
        for ( Map.Entry<String, Map<String, Long>> entry : crimeCountPerLocation.entrySet() ) {
            System.out.println( "" );
            System.out.println( "-----------" );

            String location = entry.getKey();
            Map<String, Long> crimeTypeCount = entry.getValue();
            for ( String type : crimeTypeCount.keySet() ) {

                String msg = String.format( "%s %s: %s", location, type, crimeTypeCount.get( type ) );
                System.out.println( msg );

            }
        }

    }

}
