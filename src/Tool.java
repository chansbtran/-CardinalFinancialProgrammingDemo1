
/**
 * @author Chan Tran
 * @version 09/13/2017
 * 
 * This class is used for creating the tool. A tool has a tool type, brand, tool code, and daily charge.
 */
public class Tool {
	private String toolType;
	private String brand;
	private String toolCode;
	private double dailyCharge;
	
	public Tool()
	{
		toolType = "";
		brand = "";
		toolCode = "";
		dailyCharge = 0.00;
	}

	public Tool(String toolType, String brand, String toolCode, double dailyCharge) {
		this.toolType = toolType;
		this.brand = brand;
		this.toolCode = toolCode;
		this.dailyCharge = dailyCharge;
	}

	public String getToolType() {
		return toolType;
	}
	public void setToolType(String toolType) {
		this.toolType = toolType;
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getToolCode() {
		return toolCode;
	}
	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public double getDailyCharge() {
		return dailyCharge;
	}
	public void setDailyCharge(int dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	@Override
	public String toString() {
		return "Tool [toolType=" + toolType + ", brand=" + brand + ", toolCode=" + toolCode + ", dailyCharge="
				+ dailyCharge + "]";
	}
}
