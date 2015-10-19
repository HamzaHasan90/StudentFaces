package custom;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpServletRequest;

public class MyExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler handler;

	public MyExceptionHandler(ExceptionHandler handler) {
		this.handler = handler;
	}

	@Override
	public ExceptionHandler getWrapped() {
		// TODO Auto-generated method stub
		return handler;
	}

	@Override
	public void handle() throws FacesException {
		// TODO Auto-generated method stub

		Iterator<?> iterator = getUnhandledExceptionQueuedEvents().iterator();

		while (iterator.hasNext()) {
			ExceptionQueuedEvent queueEvent = (ExceptionQueuedEvent) iterator.next();
			ExceptionQueuedEventContext eventContext = (ExceptionQueuedEventContext) queueEvent.getSource();
			Throwable throwable = eventContext.getException();

			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String header = httpServletRequest.getHeader("faces-request");

			boolean bool = facesContext.getPartialViewContext().isAjaxRequest();

			if (!bool) {

				facesContext.addMessage(null, new FacesMessage(throwable.getLocalizedMessage(), null));
				facesContext.renderResponse();

			}
			iterator.remove();
		}

		super.handle();
	}

}
