import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * @author Chan Tran
 * @version 09/13/2017
 * 
 * This class is used for Junit testing. Testing out each class with expected values.
 */
public class TestJunit {
	
	// Testing functionality and methods of Tool
   @Test
   public void testTool()
   {
	   Tool toolTest = new Tool("Jackhammer", "Ridgid", "JAKR", 2.99);
	   assertEquals("Jackhammer", toolTest.getToolType());
	   assertEquals("Ridgid", toolTest.getBrand());
	   assertEquals("JAKR", toolTest.getToolCode());
	   
	   toolTest.setToolCode("1234");
	   assertEquals("1234", toolTest.getToolCode());
	   toolTest.setBrand("Brand1");
	   assertEquals("Brand1", toolTest.getBrand());
	   toolTest.setToolType("TestType");
	   assertEquals("TestType", toolTest.getToolType());
   }

	// Testing functionality and methods of Rental Parameters
   @Test
   public void testRentalParameters()
   {
	   RentalParameters rental = new RentalParameters("9/3/15", 5, 50);
	   
	   assertEquals(50, rental.getDiscount());
	   assertEquals(5, rental.getRentDays());
	   assertEquals("9/3/15", rental.getRentDate());
	   
	   rental.setDiscount(100);
	   assertEquals(100, rental.getDiscount());
	   rental.setRentDate("99/99/99");
	   assertEquals("99/99/99", rental.getRentDate());
	   rental.setRentDays(99);
	   assertEquals(99, rental.getRentDays());
   }
   
	// Testing functionality and methods of RentalCalculations under 6 different test cases.
   @Test
   public void testRentalCalculations()
   {
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
			
			switch(i)
			{
			case 1:
				assertEquals("Exception", calc.getReturnDate());
				break;
			case 2:
				assertEquals("9/8/15", calc.getReturnDate());
				assertEquals(5, calc.getActualRented());
				// Testing with Strings due to deprecated assertEquals(double, double)
				assertEquals(String.valueOf(9.95), String.valueOf(calc.getPreDiscount()));
				assertEquals(String.valueOf(1.0), String.valueOf(calc.getDiscountAmount()));
				assertEquals(String.valueOf(8.95), String.valueOf(calc.getFinalCost()));
				break;
			case 3:
				assertEquals("7/7/15", calc.getReturnDate());
				assertEquals(3, calc.getActualRented());
				// Testing with Strings due to deprecated assertEquals(double, double)
				assertEquals(String.valueOf(4.47), String.valueOf(calc.getPreDiscount()));
				assertEquals(String.valueOf(1.12), String.valueOf(calc.getDiscountAmount()));
				assertEquals(String.valueOf(3.35), String.valueOf(calc.getFinalCost()));
				break;
			case 4:
				assertEquals("9/9/15", calc.getReturnDate());
				assertEquals(3, calc.getActualRented());
				// Testing with Strings due to deprecated assertEquals(double, double)
				assertEquals(String.valueOf(8.97), String.valueOf(calc.getPreDiscount()));
				assertEquals(String.valueOf(0.0), String.valueOf(calc.getDiscountAmount()));
				assertEquals(String.valueOf(8.97), String.valueOf(calc.getFinalCost()));
				break;
			case 5:
				assertEquals("7/11/15", calc.getReturnDate());
				assertEquals(6, calc.getActualRented());
				// Testing with Strings due to deprecated assertEquals(double, double)
				assertEquals(String.valueOf(17.94), String.valueOf(calc.getPreDiscount()));
				assertEquals(String.valueOf(0.0), String.valueOf(calc.getDiscountAmount()));
				assertEquals(String.valueOf(17.94), String.valueOf(calc.getFinalCost()));
				break;
			case 6:
				assertEquals("7/6/20", calc.getReturnDate());
				assertEquals(1, calc.getActualRented());
				// Testing with Strings due to deprecated assertEquals(double, double)
				assertEquals(String.valueOf(2.99), String.valueOf(calc.getPreDiscount()));
				assertEquals(String.valueOf(1.5), String.valueOf(calc.getDiscountAmount()));
				assertEquals(String.valueOf(1.49), String.valueOf(calc.getFinalCost()));
				break;
			default:
				break;
			}
		}
   }
}