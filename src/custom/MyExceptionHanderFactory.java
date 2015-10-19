package custom;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class MyExceptionHanderFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	public MyExceptionHanderFactory(ExceptionHandlerFactory parent) {
		// TODO Auto-generated constructor stub
		this.parent = parent;
		
	}

	@Override
	public ExceptionHandler getExceptionHandler() {

		ExceptionHandler handler = new MyExceptionHandler(parent.getExceptionHandler());
		
		return handler;
	}

}
