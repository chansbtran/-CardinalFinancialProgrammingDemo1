
/**
 * @author Chan Tran
 * @version 09/13/2017
 * 
 * This class is used for setting up the rental parameters to be calculated. Rental parameters include the rental date,
 * number of rented days, and the discount given. 
 */
public class RentalParameters {
	private String rentDate;
	private int rentDays;
	private int discount;
	
	public RentalParameters()
	{
		this.rentDate = "";
		this.rentDays = 0;
		this.discount = 0;
	}
	
	public RentalParameters(String rentDate, int rentDays, int discount) {
		this.rentDate = rentDate;
		this.rentDays = rentDays;
		this.discount = discount;
	}

	public String getRentDate() {
		return rentDate;
	}
	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}
	
	public int getRentDays() {
		return rentDays;
	}
	public void setRentDays(int rentDays) {
		this.rentDays = rentDays;
	}
	
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
		
}
