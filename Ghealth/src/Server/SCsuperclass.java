package Server;

public class SCsuperclass {

	/**
	* Gets the month name.
	*
	* @param month_num the month_num
	* @return the month name
	*/
	protected String getMonthName(int month_num){

	String months[] = {"January","February","March","April","May","June","July","August","September","October","November","December","no_month"};

	if(month_num>12 || month_num<1) return months[12];

	return months[month_num-1];
	}
	
}
