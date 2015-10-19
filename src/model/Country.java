package model;

import java.util.ArrayList;
import java.util.List;

public class Country {

	private String countryName;
	private final List<String> citiesList = new ArrayList<>();

	public Country(String name, City... cities) {
		this.countryName = name;
		for (City c : cities) {
			this.citiesList.add(c.getName());
		}
		CountriesInfo.COUNTRY_NAMES.add(name);
		CountriesInfo.COUNTRIES_MAP.put(name, citiesList);
	}

	// =======Setters and getters ======

	public List<String> getCountriesList() {

		return citiesList;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
