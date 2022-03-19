package HSB;

public class Car {
	
	// 필드
	private String date;
	private int car; // 차량 번호
	
	
	
	// 생성자
	public Car() {}

	public Car(String date, int car) {
		this.date = date;
		this.car = car;
	}

	
	// 메소드
	

	public int getCar() {
		return car;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setCar(int car) {
		this.car = car;
	}

	
	
}
