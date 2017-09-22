import java.math.BigDecimal;
/**
 * @author Chan Tran
 * @version 09/18/2017
 * 
 * This class is used for creating the tool. A tool has a tool type, brand, tool code, and daily charge.
 */
public class Tool {
	private String toolType;
	private String brand;
	private String toolCode;
	private BigDecimal dailyCharge;
	
	public Tool()
	{
		toolType = "";
		brand = "";
		toolCode = "";
		dailyCharge = new BigDecimal(0);
	}

	public Tool(String toolType, String brand, String toolCode, double dailyCharge) {
		this.toolType = toolType;
		this.brand = brand;
		this.toolCode = toolCode;
		this.dailyCharge = new BigDecimal(dailyCharge).setScale(2, BigDecimal.ROUND_HALF_UP);
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

	public BigDecimal getDailyCharge() {
		return dailyCharge;
	}
	public void setDailyCharge(BigDecimal dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	@Override
	public String toString() {
		return "Tool [toolType=" + toolType + ", brand=" + brand + ", toolCode=" + toolCode + ", dailyCharge="
				+ dailyCharge + "]";
	}
}
