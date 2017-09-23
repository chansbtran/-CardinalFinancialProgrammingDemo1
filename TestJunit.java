import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;

/**
 * @author Chan Tran
 * @version 09/22/2017
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
   public void RentalCalc1()
   {
		String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
		double dailyCharge = 0.00;
		int rentDays = 0, discount = 0;
		
		toolType = "Jackhammer";
		toolBrand = "Rigid";
		toolCode = "JAKR";
		rentDate = "9/3/15";
		dailyCharge = 2.99;
		rentDays = 5;
		discount = 101;
		
		Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
		RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
		CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
		try
		{
			calc.runCalculations();
			
			assertEquals(null, calc.getReturnDate());
			assertEquals(0, calc.getActualRented());
			assertEquals(null, calc.getPreDiscount());
			assertEquals(null, calc.getDiscountAmount());
			assertEquals(null, calc.getFinalCost());
		}
		catch (Exception e)
		{
			assertEquals(null, calc.getReturnDate());
			assertEquals(0, calc.getActualRented());
			assertEquals(null, calc.getPreDiscount());
			assertEquals(null, calc.getDiscountAmount());
			assertEquals(null, calc.getFinalCost());
		}
	}
   
   @Test
   public void RentalCalc2()
   {
	   for (int i = 1; i <= 6; i++)
		{
			String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
			double dailyCharge = 0.00;
			int rentDays = 0, discount = 0;
			
			toolType = "Ladder";
			toolBrand = "Werner";
			toolCode = "LADW";
			rentDate = "9/3/15";
			dailyCharge = 1.99;
			rentDays = 5;
			discount = 10;
			
			Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
			RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
			CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
			try
			{
				calc.runCalculations();
				
				assertEquals("9/8/15", calc.getReturnDate());
				assertEquals(5, calc.getActualRented());
				assertEquals(new BigDecimal(9.95).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getPreDiscount());
				assertEquals(new BigDecimal(1.00).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getDiscountAmount());
				assertEquals(String.valueOf(8.95), String.valueOf(calc.getFinalCost()));
			}
			catch (Exception e)
			{
				assertEquals(null, calc.getReturnDate());
				assertEquals(0, calc.getActualRented());
				assertEquals(null, calc.getPreDiscount());
				assertEquals(null, calc.getDiscountAmount());
				assertEquals(null, calc.getFinalCost());
			}
		}
	}
   
   @Test
   public void RentalCalc3()
   {
	   for (int i = 1; i <= 6; i++)
		{
			String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
			double dailyCharge = 0.00;
			int rentDays = 0, discount = 0;
			
			toolType = "Chainsaw";
			toolBrand = "Stihl";
			toolCode = "CHNS";
			rentDate = "7/2/15";
			dailyCharge = 1.49;
			rentDays = 5;
			discount = 25;
			
			Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
			RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
			CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
			try
			{
				calc.runCalculations();
				
				assertEquals("7/7/15", calc.getReturnDate());
				assertEquals(3, calc.getActualRented());
				assertEquals(new BigDecimal(4.47).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getPreDiscount());
				assertEquals(new BigDecimal(1.12).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getDiscountAmount());
				assertEquals(new BigDecimal(3.35).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getFinalCost());
			}
			catch (Exception e)
			{
				assertEquals(null, calc.getReturnDate());
				assertEquals(0, calc.getActualRented());
				assertEquals(null, calc.getPreDiscount());
				assertEquals(null, calc.getDiscountAmount());
				assertEquals(null, calc.getFinalCost());
			}
		}
	}
   
   @Test
   public void RentalCalc4()
   {
	   for (int i = 1; i <= 6; i++)
		{
			String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
			double dailyCharge = 0.00;
			int rentDays = 0, discount = 0;
			
			toolType = "Jackhammer";
			toolBrand = "DeWalt";
			toolCode = "JAKD";
			rentDate = "9/3/15";
			dailyCharge = 2.99;
			rentDays = 6;
			discount = 0;
			
			Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
			RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
			CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
			try
			{
				calc.runCalculations();
				
				assertEquals("9/9/15", calc.getReturnDate());
				assertEquals(3, calc.getActualRented());
				assertEquals(new BigDecimal(8.97).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getPreDiscount());
				assertEquals(new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getDiscountAmount());
				assertEquals(new BigDecimal(8.97).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getFinalCost());
			}
			catch (Exception e)
			{
				assertEquals(null, calc.getReturnDate());
				assertEquals(0, calc.getActualRented());
				assertEquals(null, calc.getPreDiscount());
				assertEquals(null, calc.getDiscountAmount());
				assertEquals(null, calc.getFinalCost());
			}
		}
	}
   
   @Test
   public void RentalCalc5()
   {
	   for (int i = 1; i <= 6; i++)
		{
			String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
			double dailyCharge = 0.00;
			int rentDays = 0, discount = 0;
			
			toolType = "Jackhammer";
			toolBrand = "Rigid";
			toolCode = "JAKR";
			rentDate = "7/2/15";
			dailyCharge = 2.99;
			rentDays = 9;
			discount = 0;
			
			Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
			RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
			CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
			try
			{
				calc.runCalculations();
				
				assertEquals("7/11/15", calc.getReturnDate());
				assertEquals(5, calc.getActualRented());
				assertEquals(new BigDecimal(14.95).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getPreDiscount());
				assertEquals(new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getDiscountAmount());
				assertEquals(new BigDecimal(14.95).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getFinalCost());
			}
			catch (Exception e)
			{
				assertEquals(null, calc.getReturnDate());
				assertEquals(0, calc.getActualRented());
				assertEquals(null, calc.getPreDiscount());
				assertEquals(null, calc.getDiscountAmount());
				assertEquals(null, calc.getFinalCost());
			}
		}
	}
   
   @Test
   public void RentalCalc6()
   {
	   for (int i = 1; i <= 6; i++)
		{
			String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
			double dailyCharge = 0.00;
			int rentDays = 0, discount = 0;
			
			toolType = "Jackhammer";
			toolBrand = "Rigid";
			toolCode = "JAKR";
			rentDate = "7/2/20";
			dailyCharge = 2.99;
			rentDays = 4;
			discount = 50;
			
			Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
			RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
			CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
			try
			{
				calc.runCalculations();
				assertEquals("7/6/20", calc.getReturnDate());
				assertEquals(1, calc.getActualRented());
				assertEquals(new BigDecimal(2.99).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getPreDiscount());
				assertEquals(new BigDecimal(1.50).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getDiscountAmount());
				assertEquals(new BigDecimal(1.49).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getFinalCost());
			}
			catch (Exception e)
			{
				assertEquals(null, calc.getReturnDate());
				assertEquals(0, calc.getActualRented());
				assertEquals(null, calc.getPreDiscount());
				assertEquals(null, calc.getDiscountAmount());
				assertEquals(null, calc.getFinalCost());
			}
		}
	}
   
   @Test
   public void testRentalStuff()
   {
		String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
		double dailyCharge = 0.00;
		int rentDays = 0, discount = 0;
		
		
		toolType = "Jackhammer";
		toolBrand = "Ridgid";
		toolCode = "JAKR";
		rentDate = "9/30/15";
		dailyCharge = 2.99;
		rentDays = 5;
		discount = 10;
		
		Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
		RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
		CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
		try
		{
			calc.runCalculations();
			
			assertEquals("10/5/15", calc.getReturnDate());
		}
		catch (Exception e)
		{
			assertEquals(null, calc.getReturnDate());
			assertEquals(0, calc.getActualRented());
			assertEquals(null, calc.getPreDiscount());
			assertEquals(null, calc.getDiscountAmount());
			assertEquals(null, calc.getFinalCost());
		}
   }
   
   // Extra testing
   @Test
   public void RentalTestExtra1()
   {
		String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
		double dailyCharge = 0.00;
		int rentDays = 0, discount = 0;
		
		
		toolType = "Jackhammer";
		toolBrand = "Ridgid";
		toolCode = "JAKR";
		rentDate = "5/1/15";
		dailyCharge = 2.99;
		rentDays = 50;
		discount = 10;
		
		Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
		RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
		CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
		try
		{
			calc.runCalculations();
			
			assertEquals("6/20/15", calc.getReturnDate());
			assertEquals(35, calc.getActualRented());
			assertEquals(new BigDecimal(104.65).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getPreDiscount());
			assertEquals(new BigDecimal(10.47).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getDiscountAmount());
			assertEquals(new BigDecimal(94.18).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getFinalCost());
		}
		catch (Exception e)
		{
			assertEquals(null, calc.getReturnDate());
			assertEquals(0, calc.getActualRented());
			assertEquals(null, calc.getPreDiscount());
			assertEquals(null, calc.getDiscountAmount());
			assertEquals(null, calc.getFinalCost());
		}
   }
   @Test
   public void RentalTestExtra2()
   {
		String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
		double dailyCharge = 0.00;
		int rentDays = 0, discount = 0;
		
		
		toolType = "Jackhammer";
		toolBrand = "Ridgid";
		toolCode = "JAKR";
		rentDate = "6/29/15";
		dailyCharge = 2.99;
		rentDays = 20;
		discount = 10;
		
		Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
		RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
		CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
		try
		{
			calc.runCalculations();
			
			assertEquals("7/19/15", calc.getReturnDate());
			assertEquals(13, calc.getActualRented());
			assertEquals(new BigDecimal(38.87).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getPreDiscount());
			assertEquals(new BigDecimal(3.89).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getDiscountAmount());
			assertEquals(new BigDecimal(34.98).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getFinalCost());
		}
		catch (Exception e)
		{
			assertEquals(null, calc.getReturnDate());
			assertEquals(0, calc.getActualRented());
			assertEquals(null, calc.getPreDiscount());
			assertEquals(null, calc.getDiscountAmount());
			assertEquals(null, calc.getFinalCost());
		}
   }
   @Test
   public void RentalTestExtra3()
   {
		String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
		double dailyCharge = 0.00;
		int rentDays = 0, discount = 0;
		
		
		toolType = "Ladder";
		toolBrand = "Werner";
		toolCode = "LADW";
		rentDate = "6/29/15";
		dailyCharge = 1.99;
		rentDays = 20;
		discount = 10;
		
		Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
		RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
		CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
		try
		{
			calc.runCalculations();
			
			assertEquals("7/19/15", calc.getReturnDate());
			assertEquals(20, calc.getActualRented());
			assertEquals(new BigDecimal(39.80).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getPreDiscount());
			assertEquals(new BigDecimal(3.98).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getDiscountAmount());
			assertEquals(new BigDecimal(35.82).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getFinalCost());
		}
		catch (Exception e)
		{
			assertEquals(null, calc.getReturnDate());
			assertEquals(0, calc.getActualRented());
			assertEquals(null, calc.getPreDiscount());
			assertEquals(null, calc.getDiscountAmount());
			assertEquals(null, calc.getFinalCost());
		}
   }
   @Test
   public void RentalTestExtra4()
   {
		String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
		double dailyCharge = 0.00;
		int rentDays = 0, discount = 0;
		
		
		toolType = "Chainsaw";
		toolBrand = "Stihl";
		toolCode = "CHNS";
		rentDate = "6/29/15";
		dailyCharge = 1.49;
		rentDays = 20;
		discount = 10;
		
		Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
		RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
		CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
		try
		{
			calc.runCalculations();
			
			assertEquals("7/19/15", calc.getReturnDate());
			assertEquals(14, calc.getActualRented());
			assertEquals(new BigDecimal(20.86).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getPreDiscount());
			assertEquals(new BigDecimal(2.09).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getDiscountAmount());
			assertEquals(new BigDecimal(18.77).setScale(2, BigDecimal.ROUND_HALF_UP), calc.getFinalCost());
		}
		catch (Exception e)
		{
			assertEquals(null, calc.getReturnDate());
			assertEquals(0, calc.getActualRented());
			assertEquals(null, calc.getPreDiscount());
			assertEquals(null, calc.getDiscountAmount());
			assertEquals(null, calc.getFinalCost());
		}
   }
   @Test
   public void RentalTestExtra5()
   {
	   //change this
		String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
		double dailyCharge = 0.00;
		int rentDays = 0, discount = 0;
		
		toolType = "Jackhammer";
		toolBrand = "Rigid";
		toolCode = "JAKR";
		rentDate = "9/3/15";
		dailyCharge = 2.99;
		rentDays = 5;
		discount = -1;
		
		Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
		RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
		CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
		try
		{
			calc.runCalculations();
		}
		catch (Exception e)
		{
			assertEquals(null, calc.getReturnDate());
			assertEquals(0, calc.getActualRented());
			assertEquals(null, calc.getPreDiscount());
			assertEquals(null, calc.getDiscountAmount());
			assertEquals(null, calc.getFinalCost());
		}
	}
   @Test
   public void RentalTestExtra6()
   {
	   //change this
		String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
		double dailyCharge = 0.00;
		int rentDays = 0, discount = 0;
		
		toolType = "Jackhammer";
		toolBrand = "Rigid";
		toolCode = "JAKR";
		rentDate = "7/1/15";
		dailyCharge = 2.99;
		rentDays = 0;
		discount = 1;
		
		Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
		RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
		CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
		try
		{
			calc.runCalculations();
			assertEquals("7/5/15", calc.getReturnDate());
			assertEquals(1, calc.getActualRented());
		}
		catch (Exception e)
		{
			assertEquals(null, calc.getReturnDate());
			assertEquals(0, calc.getActualRented());
			assertEquals(null, calc.getPreDiscount());
			assertEquals(null, calc.getDiscountAmount());
			assertEquals(null, calc.getFinalCost());
		}
	}

   @Test
   public void RentalTestExtra7()
   {
	   //change this
		String toolType = "", toolBrand = "", toolCode = "", rentDate = "";
		double dailyCharge = 0.00;
		int rentDays = 0, discount = 0;
		
		toolType = "Jackhammer";
		toolBrand = "Rigid";
		toolCode = "JAKR";
		rentDate = "7/1/15";
		dailyCharge = 2.99;
		rentDays = 365;
		discount = 1;
		
		Tool tool = new Tool(toolType, toolBrand, toolCode, dailyCharge);
		RentalParameters rental = new RentalParameters(rentDate, rentDays, discount);
		CalculateRentalTime calc = new CalculateRentalTime(tool, rental);
		try
		{
			calc.runCalculations();
			assertEquals("6/30/16", calc.getReturnDate());
			assertEquals(259, calc.getActualRented());
			// rent days - (52 * weekends) - holidays
			// 365 - (52 * 2) - 2 = 259
		}
		catch (Exception e)
		{
			assertEquals(null, calc.getReturnDate());
			assertEquals(0, calc.getActualRented());
			assertEquals(null, calc.getPreDiscount());
			assertEquals(null, calc.getDiscountAmount());
			assertEquals(null, calc.getFinalCost());
		}
	}
}