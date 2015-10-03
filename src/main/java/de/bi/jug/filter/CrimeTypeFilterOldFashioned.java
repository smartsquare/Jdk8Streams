package de.bi.jug.filter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.google.common.collect.Lists;

import de.bi.jug.domain.Crime;
import de.bi.jug.domain.CrimeType;

/**
 * Created by sascharodekamp on 26.09.15.
 */
public class CrimeTypeFilterOldFashioned
    implements CrimeTypeFilter {

    @Override
    public List<Crime> filterCrimeTypes( Path inputFile, CrimeType type )
        throws IOException {

        List<Crime> result = Lists.newArrayList();

        List<String> csvFileLines = Files.readAllLines( inputFile );
        boolean firstLine = true;
        for ( String line : csvFileLines ) {
            if ( firstLine ) {
                firstLine = false;
                continue;
            }

            Crime crime = Crime.createFromInput( line );

            String primaryType = crime.getPrimaryType();

            if ( type.isSameType( primaryType ) ) {
                result.add( crime );
            }
        }

        return result;
    }
}
