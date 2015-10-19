package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountriesInfo {
	// Maps state name to Map that has city names & associated populations

	public static final Map<String, List<String>> COUNTRIES_MAP = new HashMap<>();
	public static final List<String> COUNTRY_NAMES = new ArrayList<>();

	public static final Country[] COUNTRIES = {
			new Country("Jordan", new City("Amman"), new City("Irbid"), new City("Zarqa"), new City("Balqa")),
			new Country("Saudi Arabia", new City("Riyadh"), new City("Jeddah"), new City("Makkah"),
					new City("Tabuk")), new Country("Iraq", new City("Baghdad")) };

};
