
public class RunProgram {

	Tool rentTool;
	
	public RunProgram()
	{
		this.rentTool = new Tool();
	}
	
	public RunProgram(String tool, String brand, String toolCode, double dailyCharge)
	{
		this.rentTool = new Tool(tool, brand, toolCode, dailyCharge);
	}
	
	public void toString(Tool tool, RentalParameters rentalParam, CalculateRentalTime calcRent)
	{
		System.out.println("\t\t\t\t Test 1");
		System.out.println("Checkout terms");
		System.out.println("Tool code\t\t\t" + tool.getToolCode());
		System.out.println("Checkout date\t\t\t" + rentalParam.getRentDate());
		System.out.println("Rental days\t\t\t" + rentalParam.getRentDays());
		System.out.println("Discount\t\t\t" + rentalParam.getDiscount() + "%");
		System.out.println("Rental Agreement expected values");
		System.out.println("Due date\t\t\t" + calcRent.getReturnDate());
		System.out.println("Daily charge\t\t\t" + "$" + tool.getDailyCharge());
		System.out.println("Charge days\t\t\t" + calcRent.getActualRented());
		System.out.println("Pre-discount charge\t\t" + calcRent.getPreDiscount());
		System.out.println("Discount %\t\t\t" + rentalParam.getDiscount() + "%");
		System.out.println("Discount amount\t\t\t" + "$" + calcRent.getDiscountAmount());
		System.out.println("Final charge\t\t\t" + "$" + calcRent.getFinalCost());
	}
	
	public void toString(Tool tool, RentalParameters rentalParam, CalculateRentalTime calcRent, int testNum)
	{
		System.out.println("\t\t\t\t Test " + testNum);
		System.out.println("Checkout terms");
		System.out.println("Tool code\t\t\t" + tool.getToolCode());
		System.out.println("Checkout date\t\t\t" + rentalParam.getRentDate());
		System.out.println("Rental days\t\t\t" + rentalParam.getRentDays());
		System.out.println("Discount\t\t\t" + rentalParam.getDiscount() + "%");
		System.out.println("Rental Agreement expected values");
		System.out.println("Due date\t\t\t" + calcRent.getReturnDate());
		
		if (calcRent.getReturnDate().equals("Exception"))
		{
			System.out.println("Daily charge\t\t\t");
			System.out.println("Charge days\t\t\t");
			System.out.println("Pre-discount charge\t\t");
			System.out.println("Discount %\t\t\t");
			System.out.println("Discount amount\t\t\t");
			System.out.println("Final charge\t\t\t");
		}
		else
		{
			System.out.println("Daily charge\t\t\t" + "$" + tool.getDailyCharge());
			System.out.println("Charge days\t\t\t" + calcRent.getActualRented());
			System.out.println("Pre-discount charge\t\t" + "$" + calcRent.getPreDiscount());
			System.out.println("Discount %\t\t\t" + rentalParam.getDiscount() + "%");
			System.out.println("Discount amount\t\t\t" + "$" + calcRent.getDiscountAmountStr());
			System.out.println("Final charge\t\t\t" + "$" + calcRent.getFinalCost());
		}
	}
	
	public static void main(String args[])
	{
		RunProgram pro1 = new RunProgram();
		
//		String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
//		double dailyCharge = 0.00;
//		int rentDays = 0, discount = 0;
//		
//		toolType = "Jackhammer";
//		toolBrand = "Ridgid";
//		toolCode = "JAKR";
//		rentDate = "7/2/15";
//		dailyCharge = 2.99;
//		rentDays = 9;
//		discount = 0;
//		
//		Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
//		RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
//		CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
//		calc.runCalculations();
//		pro1.toString(tool, rental, calc, 1);
		
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
			calc.runCalculations();
			
			pro1.toString(tool, rental, calc, i);
			System.out.println("--------------------------------------------------------");
		}
	}
}
