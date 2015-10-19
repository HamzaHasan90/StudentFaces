package listeners;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class UpdateModuleListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("After UPDATE_MODEL phase");	
		
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("before UPDATE_MODEL phase");
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Locale locale = (Locale) session.getAttribute("locale");
		
		if (locale != null) {

			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		}
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.UPDATE_MODEL_VALUES;
	}

}
