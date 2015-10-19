package beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.event.AjaxBehaviorEvent;

import model.CountriesInfo;

@ManagedBean(name = "ajaxBean")
public class AjaxBean {

	private String name;
	private String countryName;
	private boolean isCityListDisabled = true;

	public String getCountryName() {
		return countryName;
	}

	public boolean getIsCityListDisabled() {
		return (isCityListDisabled);
	}

	public void setCountryName(String name) {
		this.countryName = name;
		isCityListDisabled = false;
	}

	public List<String> getCountries() {
		return (CountriesInfo.COUNTRY_NAMES);
	}

	public List<String> getCities() {
		return (CountriesInfo.COUNTRIES_MAP.get(countryName));
	}

	public void checkDate(FacesContext context, UIComponent component, Object value) {

		String pattern = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String str = value.toString();

		try {
			Date date = dateFormat.parse(str);
			System.out.println("Date is good");
		} catch (ParseException e) {

			FacesMessage firstNameMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					"Error on Date format please change");
			FacesContext.getCurrentInstance().addMessage("form2:dateInput", firstNameMessage);

		}

	}

	public String setTheName() throws Exception {

		PartialViewContext viewContext = FacesContext.getCurrentInstance().getPartialViewContext();
		if (viewContext.isPartialRequest()) {
			if (name != null) {
				System.out.println("Name is avaliable");
			}
			throw new Exception();
		}

		return null;

	}

	public void throwException() throws Exception {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}