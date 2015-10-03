package de.bi.jug.filter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import de.bi.jug.domain.Crime;
import de.bi.jug.domain.CrimeType;

public interface CrimeTypeFilter {

    /**
     * Filter the input file by crime type and returns a list of these crimes.
     * 
     * @param inputFile
     * @param type
     * @return
     * @throws IOException
     */
    public List<Crime> filterCrimeTypes( Path inputFile, CrimeType type )
        throws IOException;

}
