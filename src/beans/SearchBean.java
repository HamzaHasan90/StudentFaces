package beans;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import business.StudentBA;

@ManagedBean(name = "search")
@RequestScoped
public class SearchBean {
	StudentBA studentBA = new StudentBA();
	private Locale locale;

	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SearchBean() {
		// TODO Auto-generated constructor stub
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		locale = (Locale) request.getAttribute("locale");
		// FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		if (locale != null)
			System.out.println("Local in search is" + locale.getCountry());
	}

	public String goToSearch() {

		return "search";
	}

	@PostConstruct
	public void initialize() throws IOException {
		System.out.println("Initialized Search bean");


		//System.out.println("Successfully loaded properties for creating process");

	}

	@PreDestroy
	public void destroy() {
		System.out.println("Destroyed Search bean");
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
