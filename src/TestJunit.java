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
   }
   
   @Test
   public void testRental()
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