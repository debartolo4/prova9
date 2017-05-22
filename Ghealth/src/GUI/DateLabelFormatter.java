package GUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * @author G5 lab group
 * The Class Date Label Formatter for the calendar I/O.
 */
public class DateLabelFormatter extends AbstractFormatter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The date pattern. */
	private String datePattern = "dd/MM/yyyy";
	
	/** The date formatter. */
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	
	/**
	 * Parses text returning an arbitrary Object. Some formatters may return null.
	 */
	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	/** 
	 * Returns the string value to display for value
	 * @return Returns the string value to display for value
	 */
	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}
		
		return "";
	}

}