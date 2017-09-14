import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author Chan Tran
 * @version 09/13/2017
 * 
 * This class is used to calculate the time and cost of rental given a tool and the rental parameters.
 */
public class CalculateRentalTime {
	
	private Tool rentalTool;
	private RentalParameters rentalParam;
	private String returnDate;
	
	private int actualRented;
	private double preDiscount;
	private double discountAmount;
	private String discountAmountStr;
	private double finalCost;
	
	public CalculateRentalTime()
	{
		this.rentalTool = new Tool();
		this.rentalParam = new RentalParameters();
	}

	public CalculateRentalTime(Tool rentalTool, RentalParameters rentalParam) {
		this.rentalTool = rentalTool;
		this.rentalParam = rentalParam;
	}
	
	public void runCalculations()
	{
		this.returnDate = CalculateReturnDate(this.rentalParam.getRentDate(), this.rentalParam.getRentDays());
		this.actualRented = ActualChargeDays(this.rentalParam.getRentDate(), this.rentalParam.getRentDays());
		this.preDiscount = BeforeDiscount(this.rentalTool, this.actualRented);
		this.discountAmount = CalcDiscountAmount(this.preDiscount, this.rentalParam.getDiscount());
		this.discountAmountStr = CalcDiscountAmountStr(this.preDiscount, this.rentalParam.getDiscount());
		this.finalCost = FinalCost();
	}
	
	public String CalculateReturnDate(String dateGiven, int rentDays)
	{
		int day = Integer.parseInt(dateGiven.split("/")[1]);
		int month = Integer.parseInt(dateGiven.split("/")[0]);
		int year = Integer.parseInt(dateGiven.split("/")[2]);
		
		int newDay = day + rentDays;
		String returnDate = month + "/" + newDay + "/" + year;
		return returnDate;
	}
	
	public boolean isWeekend(String dateGiven)
	{
		String dayOfWeek = dayOfWeek(dateGiven);
		
		if (dayOfWeek.equals("Saturday") || dayOfWeek.equals("Sunday"))
			return true;
		else
			return false;
	}
	public String dayOfWeek(String dateGiven)
	{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String dayOfWeek = "";
		Date MyDate;
		
		try {
			MyDate = newDateFormat.parse(dateGiven);
			newDateFormat.applyPattern("EEEE");
			dayOfWeek = newDateFormat.format(MyDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dayOfWeek;
	}
	
	public boolean isJuly4(String dateGiven)
	{
		boolean isJuly4 = false;
		
		int day = Integer.parseInt(dateGiven.split("/")[1]);
		int month = Integer.parseInt(dateGiven.split("/")[0]);
		
		// Check for July 4th holiday
		if (month == 7 && day == 4)
		{
			isJuly4 = true;
		}
				
		return isJuly4;
	}
	public boolean isLaborDay(String dateGiven)
	{
		boolean isLaborDay = false;
		
		int day = Integer.parseInt(dateGiven.split("/")[1]);
		int month = Integer.parseInt(dateGiven.split("/")[0]);
		int year = Integer.parseInt(dateGiven.split("/")[2]);
		
		// Check if first Monday in September
		if (month == 9)
		{
			String newDay = "", newMonth = "";
			if (day < 10)
				newDay = "0" + day;
			else
				newDay = "" + day;
			if (month < 10)
				newMonth = "0" + month;
			else
				newMonth = "" + month;
			
			String newDate = year + "-" + newMonth + "-" + newDay;
			LocalDate given = LocalDate.parse(newDate);
			LocalDate firstMonday = given.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
			
			if (newDate.equals(firstMonday.toString()))
				isLaborDay = true;
		}
		
		return isLaborDay;
	}
	
	public int ActualChargeDays(String rentDate, int rentDays)
	{
		int day = Integer.parseInt(rentDate.split("/")[1]);
		int month = Integer.parseInt(rentDate.split("/")[0]);
		int year = Integer.parseInt(rentDate.split("/")[2]);
		
		int actualRentDays = 0;
		for (int i = 0; i < rentDays; i++)
		{
			int newDay = day + i;
			String newDate = month + "/" + newDay + "/20" + year;
			boolean isWeekend = isWeekend(newDate);
			boolean isJuly4 = isJuly4(newDate);
			boolean isLaborDay = isLaborDay(newDate);
			
			if (rentalTool.getToolType().equals("Ladder"))
			{
				actualRentDays++;
			}
			else if (rentalTool.getToolType().equals("Chainsaw"))
			{
				if (!isWeekend)
					actualRentDays++;
			}
			else if (rentalTool.getToolType().equals("Jackhammer"))
			{
				if (isJuly4)
				{
					if (isWeekend)
					{
						String dayOfWeek = dayOfWeek(newDate);
						if (dayOfWeek.equals("Saturday"))
						{
							if ((i - 1) >= 0)
								actualRentDays--;
						}
						else
						{
							if ((i + 1) < rentDays)
								actualRentDays--;
						}
					}
				}
				else
				{
					if (!isWeekend && !isLaborDay)
						actualRentDays++;					
				}
			}
		}
		
		return actualRentDays;
	}
	
	public double BeforeDiscount(Tool rentalTool, int actualRented)
	{
		DecimalFormat decimal = new DecimalFormat("#0.00");
		decimal.setRoundingMode(RoundingMode.HALF_UP);
		
		double preVal = rentalTool.getDailyCharge() * actualRented;
		String precision2 = decimal.format(Math.round(preVal * 100.00) / 100.00);
		return Double.parseDouble(precision2);
//		return rentalTool.getDailyCharge() * actualRented;
	}
	public double CalcDiscountAmount(double preDisc, int discount)
	{
		DecimalFormat decimal = new DecimalFormat("#0.00");
		decimal.setRoundingMode(RoundingMode.HALF_UP);
		
		double preVal = preDisc * discount / 100;
		String precision2 = decimal.format(Math.round(preVal * 100.00) / 100.00);
		return Double.parseDouble(precision2);
	}
	public String CalcDiscountAmountStr(double preDisc, int discount)
	{
		DecimalFormat decimal = new DecimalFormat("#0.00");
		decimal.setRoundingMode(RoundingMode.HALF_UP);
		
		double preVal = preDisc * discount / 100;
		String precision2 = decimal.format(Math.round(preVal * 100.00) / 100.00);
		return precision2;
	}
	public double FinalCost()
	{
		DecimalFormat decimal = new DecimalFormat("#0.00");
		decimal.setRoundingMode(RoundingMode.HALF_UP);
		
		double finalCost = 0.0;
		if (this.rentalParam.getRentDays() >= 1 && (this.rentalParam.getDiscount() >= 0 && this.rentalParam.getDiscount() <= 100))
		{
			finalCost = this.preDiscount - this.discountAmount;
		}
		else
		{
			setReturnDate("Exception");
		}
		
		String precision2 = decimal.format(Math.round(finalCost * 100.00) / 100.00);
		return Double.parseDouble(precision2);
//		return finalCost;
	}
	
	public int getActualRented() {
		return actualRented;
	}
	public void setActualRented(int actualRented) {
		this.actualRented = actualRented;
	}
	
	public double getFinalCost() {
		return finalCost;
	}
	public void setFinalCost(double finalCost) {
		this.finalCost = finalCost;
	}
	
	public double getPreDiscount() {
		return preDiscount;
	}
	public void setPreDiscount(double preDiscount) {
		this.preDiscount = preDiscount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	
	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	
	public String getDiscountAmountStr() {
		return discountAmountStr;
	}

	public void setDiscountAmountStr(String discountAmountStr) {
		this.discountAmountStr = discountAmountStr;
	}
	
}
