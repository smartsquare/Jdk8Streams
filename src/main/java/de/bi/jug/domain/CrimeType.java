package de.bi.jug.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * Possible crime Types.
 */
public enum CrimeType {
    BATTERY( "BATTERY" ),
    CRIMINAL_DAMAGE( "CRIMINAL DAMAGE" ),
    ROBBERY( "ROBBERY" ),
    DECEPTIVE_PRACTICE( "DECEPTIVE PRACTICE" ),
    ASSAULT( "ASSAULT" ),
    NARCOTICS( "NARCOTICS" ),
    MOTOR_VEHICLE_THEFT( "MOTOR VEHICLE THEFT" ),
    THEFT( "THEFT" );

    private final String displayName;

    CrimeType( String displayName ) {
        this.displayName = displayName;
    }

    /**
     * Compares the primary type name with a {@link CrimeType}
     * 
     * @param primaryTypeName
     * @return
     */
    public boolean isSameType( String primaryTypeName ) {
        if ( StringUtils.isBlank( primaryTypeName ) ) {
            return false;
        }

        return primaryTypeName.equals( displayName );
    }
}
