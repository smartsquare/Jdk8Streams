package de.bi.jug.inside;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface CrimePerLocationCounter {

    /**
     * Count crimes per location and type. Returns an object which shows how many crimes per type reported for the
     * different locations.
     * 
     * @param inputFile
     * @return
     * @throws IOException
     */
    public Map<String, Map<String, Long>> countCrimePerLocation( Path inputFile )
        throws IOException;
}
