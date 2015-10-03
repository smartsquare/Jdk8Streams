package de.bi.jug.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CrimeTest {


	@Test
	public void should_create_valid_crime_object_form_given_input_line()
			throws Exception {

		String inputLine = "10181133,HY369256,07/31/2015 06:00:00 PM,038XX N RACINE AVE,0917,MOTOR VEHICLE THEFT,\"CYCLE, SCOOTER, BIKE W-VIN\",\"SCHOOL, PRIVATE, GROUNDS\",false,false,1923,019,44,6,07,1167607,1925580,2015,08/17/2015 03:03:40 PM,41.95134222,-87.659277266,\"(41.95134222, -87.659277266)\"";
		Crime crime = Crime.createFromInput(inputLine);

		assertThat(crime.getId(), equalTo("10181133"));

		assertThat(crime.getCaseNumber(), equalTo("HY369256"));
		assertThat(crime.getDate(), equalTo("07/31/2015 06:00:00 PM"));
		assertThat(crime.getBlock(), equalTo("038XX N RACINE AVE"));
		assertThat(crime.getIucr(), equalTo("0917"));
		assertThat(crime.getPrimaryType(), equalTo("MOTOR VEHICLE THEFT"));
		assertThat(crime.getDescription(),
				equalTo("CYCLE, SCOOTER, BIKE W-VIN"));
		assertThat(crime.getLocationDescription(),
				equalTo("SCHOOL, PRIVATE, GROUNDS"));
		assertThat(crime.getArrest(), equalTo("false"));
		assertThat(crime.getDomestic(), equalTo("false"));
		assertThat(crime.getBeat(), equalTo("1923"));
		assertThat(crime.getDistrict(), equalTo("019"));
		assertThat(crime.getWard(), equalTo("44"));
		assertThat(crime.getCommunityArea(), equalTo("6"));
		assertThat(crime.getFbiCode(), equalTo("07"));
		assertThat(crime.getxCoordinate(), equalTo("1167607"));
		assertThat(crime.getyCoordinate(), equalTo("1925580"));
		assertThat(crime.getYear(), equalTo("2015"));
		assertThat(crime.getUpdatedOn(), equalTo("08/17/2015 03:03:40 PM"));
		assertThat(crime.getLatitude(), equalTo("41.95134222"));
		assertThat(crime.getLongitude(), equalTo("-87.659277266"));
		assertThat(crime.getLocation(), equalTo("(41.95134222, -87.659277266)"));
	}

	@Test
	public void should_create_valid_crime_object_even_if_fields_empty()
			throws Exception {

		String inputLine = ",,07/31/2015 06:00:00 PM,038XX N RACINE AVE,0917,MOTOR VEHICLE THEFT,\"CYCLE, SCOOTER, BIKE W-VIN\",SCHOOL,false,false,1923,019,44,6,07,1167607,1925580,2015,08/17/2015 03:03:40 PM,41.95134222,-87.659277266,\"(41.95134222, -87.659277266)\"";
		Crime crime = Crime.createFromInput(inputLine);

		assertThat(crime.getId(), equalTo(""));
		assertThat(crime.getCaseNumber(), equalTo(""));
		assertThat(crime.getDate(), equalTo("07/31/2015 06:00:00 PM"));
	}

	@Test
	public void should_return_null_if_not_enough_fields_could_be_extracted_from_input_string()
			throws Exception {

		String inputLineWithMissingFields = "0917,MOTOR VEHICLE THEFT,\"CYCLE, SCOOTER, BIKE W-VIN\",SCHOOL,false,false,1923,019,44,6,07,1167607,1925580,2015,08/17/2015 03:03:40 PM,41.95134222,-87.659277266,\"(41.95134222, -87.659277266)\"";
		Crime crime = Crime.createFromInput(inputLineWithMissingFields);

		assertThat(crime, is(nullValue()));
	}

}
