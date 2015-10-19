package listeners;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class RenderListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 

	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub

		System.out.println("After RENDER_RESPONSE phase");

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub

		System.out.println("Before RENDER_RESPONSE phase");

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Locale locale = (Locale) session.getAttribute("locale");

		if (locale != null) {

			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		}

	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RENDER_RESPONSE;
	}

}
