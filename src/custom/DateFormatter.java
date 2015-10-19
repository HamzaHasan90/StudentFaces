package custom;

import java.sql.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DateFormatter {

	public String setDisplayEffectiveDate(String date) {
		ArrayList validDateList = new ArrayList();
		validDateList.add("dd-MMM-yyyy");
		validDateList.add("dd.MM.yyyy");
		validDateList.add("dd/MM/yyyy");
		validDateList.add("MM/dd/yyyy");
		Date effectiveDate = null;

		System.out.println("setDisplayEffectiveDate1 :: date = " + date);
		if (date != null) {
			boolean correctFormat = false;
			int i = 0;
			String dateFormat = null;
			// Parse the previous string back into a Date.
			ParsePosition pos = new ParsePosition(0);
			System.out.println("setDisplayEffectiveDate2 :: date = " + date);
			while (!correctFormat) {
				if (i > validDateList.size() - 1) {
					break;
				}
				// for (int j=0;j<validDateList.size();j++)
				// {
				dateFormat = validDateList.get(i++).toString().trim();
				System.out.println("setDisplayEffectiveDate3 :: dateFormat = " + dateFormat);
				try {
					SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
					fmt.setLenient(false);
					effectiveDate = new Date(fmt.parse(date, pos).getTime());
					// log.debug("setDisplayEffectiveDate2 :: dateFormat = "+
					// dateFormat);
					// log.debug("setDisplayEffectiveDate2 :: effectiveDate = "+
					// effectiveDate);
					correctFormat = true;
				} catch (Exception e) {
					// log.debug("setDisplayEffectiveDate3 :: dateFormat = "+
					// dateFormat);
					correctFormat = false;
				}
				// }
			}
			System.out.println("setDisplayEffectiveDate :: correctFormat = " + correctFormat);
			System.out.println("setDisplayEffectiveDate2 :: effectiveDate = " + effectiveDate);
			if (!correctFormat) {
				// effectiveDate = new Date();
				System.out.println("setDisplayEffectiveDate :: effectiveDate (error) = " + effectiveDate);
			}

		}

		java.sql.Date sDate = new java.sql.Date(effectiveDate.getTime());
		SimpleDateFormat fmt1 = new SimpleDateFormat("dd-MMM-yyyy");
		return fmt1.format(sDate);
	}
}
