package de.bi.jug.count;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface CrimeTypeCounter {

    /**
     * Counts crimes by crime type.
     * 
     * @param inputFile
     * @return
     * @throws IOException
     */
    public Map<String, Long> countCrimeTypes( Path inputFile )
        throws IOException;

}
