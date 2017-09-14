import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit {
	
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
   
   @Test
   public void testRentalCalculations()
   {
	   Tool tool1 = new Tool("Jackhammer", "Ridgid", "JAKR", 2.99);
	   RentalParameters rental1 = new RentalParameters("9/3/15", 5, 50);
	   CalculateRentalTime calc1 = new CalculateRentalTime(tool1, rental1);
	   calc1.runCalculations();
	   
	   assertEquals("9/8/15", calc1.getReturnDate());
	   assertEquals(50, rental1.getDiscount());
	   assertEquals(5, rental1.getRentDays());
   }
}