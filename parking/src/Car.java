package parking;

public class Car {
	
	// 필드
	private String date; // 입차 날짜/시간
	private String car; // 차량 번호
	private String tower; // 주차 위치
	
	
	// 생성자
	public Car() {}

	public Car(String date, String car, String tower) {
		this.date = date;
		this.car = car;
		this.tower = tower;
	}


	
	// 메소드
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getTower() {
		return tower;
	}

	public void setTower(String tower) {
		this.tower = tower;
	}
	
	
	
}