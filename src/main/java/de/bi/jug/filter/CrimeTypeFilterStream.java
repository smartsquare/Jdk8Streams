package de.bi.jug.filter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

import de.bi.jug.domain.Crime;
import de.bi.jug.domain.CrimeType;

public class CrimeTypeFilterStream
    implements CrimeTypeFilter {

    @Override
    public List<Crime> filterCrimeTypes( Path inputFile, CrimeType type )
        throws IOException {

        List<Crime> result = Lists.newArrayList();

        try (Stream<String> stream = Files.lines( inputFile ).skip( 1 )) {

            result = stream
                .map( line -> Crime.createFromInput( line ) )
                .filter( crime -> type.isSameType( crime.getPrimaryType() ) )
                .collect( Collectors.toList() );
        }

        return result;
    }
}
