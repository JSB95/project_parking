package parking;



public class Count {
	
	// 필드
	private int year, month, day;
	private int profit; // 매출
	
	// 생성자
	public Count() {
	}

	public Count(int year, int month, int day, int profit) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.profit = profit;
	}

	
	// 메소드
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	
}
