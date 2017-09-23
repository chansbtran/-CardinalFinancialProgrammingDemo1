import java.math.BigDecimal;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Chan Tran
 * @version 09/22/2017
 * 
 * This class is used to calculate the time and cost of rental given a tool and the rental parameters.
 */
public class CalculateRentalTime {
	
	private Tool rentalTool;
	private RentalParameters rentalParam;
	private String returnDate;
	
	private int actualRented;
	private BigDecimal preDiscount;
	private BigDecimal discountAmount;
	private BigDecimal finalCost;
	
	private static DateTimeFormatter mdyyFormat = DateTimeFormatter.ofPattern("M/d/yy");
	private static int roundUp = BigDecimal.ROUND_HALF_UP;
	
	public CalculateRentalTime()
	{
		this.rentalTool = new Tool();
		this.rentalParam = new RentalParameters();
	}

	public CalculateRentalTime(Tool rentalTool, RentalParameters rentalParam) {
		this.rentalTool = rentalTool;
		this.rentalParam = rentalParam;
	}
	
	public void runCalculations() throws Exception
	{
		if ((rentalParam.getDiscount() >= 0 && rentalParam.getDiscount() <= 100) && rentalParam.getRentDays() > 0)
		{
			returnDate = calculateReturnDate(rentalParam.getRentDate(), rentalParam.getRentDays());
			actualRented = actualChargeDays(rentalParam.getRentDate(), rentalParam.getRentDays());
			preDiscount = beforeDiscount(rentalTool.getDailyCharge(), actualRented);
			discountAmount = calcDiscountAmount(preDiscount, rentalParam.getDiscount());
			finalCost = finalCost(preDiscount, discountAmount);
		}
		else
		{
			throw new Exception("Exception found. Please check discount percentage or rental days.");
		}
	}
	
	public String calculateReturnDate(String dateGiven, int rentDays)
	{
		LocalDate returnDate = LocalDate.parse(dateGiven, mdyyFormat).plusDays(rentDays);
		return returnDate.format(mdyyFormat);
	}
	
	public boolean isWeekend(LocalDate dateGiven)
	{
		if (dateGiven.getDayOfWeek() == DayOfWeek.SATURDAY || dateGiven.getDayOfWeek() == DayOfWeek.SUNDAY)
			return true;
		else
			return false;
	}
	public boolean isJuly4(LocalDate dateGiven)
	{
		boolean isJuly4 = false;
		
		// Check for July 4th holiday
		if (dateGiven.getMonthValue() == 7 && dateGiven.getDayOfMonth() == 4)
		{
			isJuly4 = true;
		}
		return isJuly4;
	}
	public boolean isLaborDay(LocalDate dateGiven)
	{
		boolean isLaborDay = false;
		
		// Check if first Monday in September
		if (dateGiven.getMonthValue() == 9)
		{
			LocalDate firstMonday = dateGiven.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
			if (dateGiven.equals(firstMonday))
				isLaborDay = true;
		}
		return isLaborDay;
	}
	public boolean isHoliday(LocalDate dateGiven)
	{
		boolean isHoliday = false;
		
		boolean isJuly4 = isJuly4(dateGiven);
		boolean isLaborDay = isLaborDay(dateGiven);
		
		if (isJuly4 || isLaborDay)
		{
			isHoliday = true;
		}
		return isHoliday;
	}
	
	public int actualChargeDays(String rentDate, int rentDays)
	{
		LocalDate localD = LocalDate.parse(rentDate, mdyyFormat);
		
		int actualRentDays = 0;
		for (int i = 1; i <= rentDays; i++)
		{
			LocalDate incrementDays = localD.plusDays(i);
			
			if (rentalTool.getToolType().equals("Ladder"))
			{
				actualRentDays++;
			}
			else if (rentalTool.getToolType().equals("Chainsaw"))
			{
				if (!isWeekend(incrementDays))
					actualRentDays++;
			}
			else if (rentalTool.getToolType().equals("Jackhammer"))
			{
				if (isHoliday(incrementDays))
				{
					if (isJuly4(incrementDays))
					{
						if (isWeekend(incrementDays))
						{
							if (incrementDays.getDayOfWeek() == DayOfWeek.SATURDAY)
							{
								if ((i - 1) > 0)
									actualRentDays--;
							}
							else
							{
								if ((i + 1) <= rentDays)
									actualRentDays--;
							}
						}
					}
					else if (isLaborDay(incrementDays))
					{
						// Do nothing. No charge for jackhammer on holidays.
						// This loop can be removed but is kept as a placeholder.
					}
				}
				else
				{
					if (!isWeekend(incrementDays))
						actualRentDays++;
				}
			}
		}
		return actualRentDays;
	}
	
	public BigDecimal beforeDiscount(BigDecimal dailyCharge, int actualRented)
	{
		BigDecimal actualDaysRented = new BigDecimal(actualRented);
		BigDecimal beforeDiscountAmt = dailyCharge.multiply(actualDaysRented);
		return beforeDiscountAmt.setScale(2, roundUp);
	}
	public BigDecimal calcDiscountAmount(BigDecimal preDisc, int discount)
	{
		BigDecimal discountBD = new BigDecimal(discount);
		BigDecimal discountAmt = preDisc.multiply(discountBD).divide(new BigDecimal(100));
		return discountAmt.setScale(2, roundUp);
	}
	public BigDecimal finalCost(BigDecimal preDiscountAmt, BigDecimal discountAmt)
	{
		BigDecimal finalCost = new BigDecimal(0);
		finalCost = preDiscountAmt.subtract(discountAmt);
		return finalCost.setScale(2, roundUp);
	}
	
	public int getActualRented() {
		return actualRented;
	}
	public void setActualRented(int actualRented) {
		this.actualRented = actualRented;
	}
	
	public BigDecimal getFinalCost() {
		return finalCost;
	}
	public void setFinalCost(BigDecimal finalCost) {
		this.finalCost = finalCost;
	}
	
	public BigDecimal getPreDiscount() {
		return preDiscount;
	}
	public void setPreDiscount(BigDecimal preDiscount) {
		this.preDiscount = preDiscount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
}
