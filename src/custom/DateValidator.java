package custom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("DateValidator")
public class DateValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent uiComponent, Object object) throws ValidatorException {
		// TODO Auto-generated method stub

		String pattern = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String str = object.toString();

		try {
			Date date = dateFormat.parse(str);
			System.out.println("Date is good");
		} catch (ParseException e) {
			FacesMessage firstNameMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
					context.getApplication().getResourceBundle(context, "msgs").getString("error.nostudent"));
			FacesContext.getCurrentInstance().addMessage(uiComponent.getClientId(), firstNameMessage);

		}

		System.out.println(uiComponent.getId());

	}

}
