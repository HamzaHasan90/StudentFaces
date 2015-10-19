package beans;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LocalBean {

	private Locale locale;
	private static Map<String, Object> locals;

	public LocalBean() {
		// TODO Auto-generated constructor stub
		locals = new LinkedHashMap<String, Object>();
		locals.put("English", Locale.ENGLISH);
		locals.put("Arabic", new Locale("ar", "JO"));
		System.out.println("Local bean init() ");
	}

	@PostConstruct
	public void init() {
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}

	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public void setLanguage(String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public Map<String, Object> getlocals() {
		return locals;
	}

}
