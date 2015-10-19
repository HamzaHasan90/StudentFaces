package filters;

import java.io.IOException;
import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocaleFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

		System.out.println("Initializing filter");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("Class filtered");

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession httpSession = httpServletRequest.getSession();
		Locale locale = (Locale) httpSession.getAttribute("locale");

		filterChain.doFilter(httpServletRequest, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroying filter");
	}

}
