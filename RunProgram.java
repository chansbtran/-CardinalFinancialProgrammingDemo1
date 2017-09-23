import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author Chan Tran
 * @version 09/22/2017
 * 
 * This class is used to test and run the program. It will output the values into a display table to view.
 */
public class RunProgram {

	Tool rentTool;
	
	public RunProgram()
	{
		this.rentTool = new Tool();
	}

	public void displayResults(ArrayList<String> toolCodes, ArrayList<String> checkoutDate, ArrayList<Integer> rentalDays, ArrayList<Integer> discPercentBefore,
			ArrayList<String> dueDate, ArrayList<BigDecimal> dailyCharges, ArrayList<Integer> chargeDays, ArrayList<BigDecimal> preDiscount,
			ArrayList<Integer> discPercentAfter, ArrayList<BigDecimal> discountAmt, ArrayList<BigDecimal> finalCharge, ArrayList<String> testNum)
	{
		System.out.printf("%-30s%s%n", "", toStringArray(testNum, "", false));
		System.out.println("Checkout terms");
		System.out.printf("%-30s%s%n", "Tool code", toStringArray(toolCodes, "", false));
		System.out.printf("%-30s%s%n", "Checkout date", toStringArray(checkoutDate, "", false));
		System.out.printf("%-30s%s%n", "Rental days", toStringArray(rentalDays, "", false));
		System.out.printf("%-30s%s%n", "Discount", toStringArray(discPercentBefore, "%", false));
		
		System.out.println("Rental Agreement expected values");
		
		System.out.printf("%-30s%s%n", "Due date", toStringArray(dueDate, "", false));
		System.out.printf("%-30s%s%n", "Daily charge", toStringArray(dailyCharges, "$", true));
		System.out.printf("%-30s%s%n", "Charge days", toStringArray(chargeDays, "", false));
		System.out.printf("%-30s%s%n", "Pre-discount charge", toStringArray(preDiscount, "$", true));
		System.out.printf("%-30s%s%n", "Discount %", toStringArray(discPercentAfter, "%", false));
		System.out.printf("%-30s%s%n", "Discount amount", toStringArray(discountAmt, "$", true));
		System.out.printf("%-30s%s%n", "Final charge", toStringArray(finalCharge, "$", true));
	}
	
	private String toStringArray(ArrayList<?> printList, String indicator, boolean before)
	{
		String returnStr = "";
		for (int i = 0; i < printList.size(); i++)
		{
			if (before)
			{
				if (printList.get(i) == null)
					returnStr += String.format("%-10s", "");
				else
					returnStr += String.format("%-10s", indicator + printList.get(i));	
			}
			else
			{
				if (printList.get(i) == null)
					returnStr += String.format("%-10s", "");
				else
					returnStr += String.format("%-10s", printList.get(i) + indicator);	
			}
		}
		return returnStr;
	}
	
	public static void main(String args[])
	{
		RunProgram pro1 = new RunProgram();
		ArrayList<String> toolCodes = new ArrayList<>();
		ArrayList<String> checkoutDate = new ArrayList<>();
		ArrayList<Integer> rentalDays = new ArrayList<>();
		ArrayList<Integer> discPercentBefore = new ArrayList<>();
		ArrayList<String> dueDate = new ArrayList<>();
		ArrayList<BigDecimal> dailyCharges = new ArrayList<>();
		ArrayList<Integer> chargeDays = new ArrayList<>();
		ArrayList<BigDecimal> preDiscount = new ArrayList<>();
		ArrayList<Integer> discPercentAfter = new ArrayList<>();
		ArrayList<BigDecimal> discountAmt = new ArrayList<>();
		ArrayList<BigDecimal> finalCharge = new ArrayList<>();
		ArrayList<String> testNum = new ArrayList<>();
		
		for (int i = 1; i <= 6; i++)
		{
			String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
			double dailyCharge = 0.00;
			int rentDays = 0, discount = 0;
			
			switch(i)
			{
			case 1:
				toolType = "Jackhammer";
				toolBrand = "Ridgid";
				toolCode = "JAKR";
				rentDate = "9/3/15";
				dailyCharge = 2.99;
				rentDays = 5;
				discount = 101;
				break;
			case 2:
				toolType = "Ladder";
				toolBrand = "Werner";
				toolCode = "LADW";
				rentDate = "9/3/15";
				dailyCharge = 1.99;
				rentDays = 5;
				discount = 10;
				break;
			case 3:
				toolType = "Chainsaw";
				toolBrand = "Stihl";
				toolCode = "CHNS";
				rentDate = "7/2/15";
				dailyCharge = 1.49;
				rentDays = 5;
				discount = 25;
				break;
			case 4:
				toolType = "Jackhammer";
				toolBrand = "DeWalt";
				toolCode = "JAKD";
				rentDate = "9/3/15";
				dailyCharge = 2.99;
				rentDays = 6;
				discount = 0;
				break;
			case 5:
				toolType = "Jackhammer";
				toolBrand = "Ridgid";
				toolCode = "JAKR";
				rentDate = "7/2/15";
				dailyCharge = 2.99;
				rentDays = 9;
				discount = 0;
				break;
			case 6:
				toolType = "Jackhammer";
				toolBrand = "Ridgid";
				toolCode = "JAKR";
				rentDate = "7/2/20";
				dailyCharge = 2.99;
				rentDays = 4;
				discount = 50;
				break;
			default:
				break;
			}
			
			Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
			RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
			CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
			
			try
			{
				calc.runCalculations();
				
				dueDate.add(calc.getReturnDate());
				dailyCharges.add(tool.getDailyCharge());
				chargeDays.add(calc.getActualRented());
				preDiscount.add(calc.getPreDiscount());
				discPercentAfter.add(rental.getDiscount());
				discountAmt.add(calc.getDiscountAmount());
				finalCharge.add(calc.getFinalCost());
			}
			catch (Exception e)
			{
				dueDate.add("Exception");
				dailyCharges.add(null);
				chargeDays.add(null);
				preDiscount.add(null);
				discPercentAfter.add(null);
				discountAmt.add(null);
				finalCharge.add(null);
			}
			
			toolCodes.add(tool.getToolCode());
			checkoutDate.add(rental.getRentDate());
			rentalDays.add(rental.getRentDays());
			discPercentBefore.add(rental.getDiscount());
			testNum.add("Test " + i);
		}
		pro1.displayResults(toolCodes, checkoutDate, rentalDays, discPercentBefore, dueDate, dailyCharges, chargeDays, preDiscount, discPercentAfter, discountAmt, finalCharge, testNum);
	}
}
