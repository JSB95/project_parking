package parking;

public class Car {
	
	// �ʵ�
	private String date; // ���� ��¥/�ð�
	private String car; // ���� ��ȣ
	private String tower; // ���� ��ġ
	
	
	// ������
	public Car() {}

	public Car(String date, String car, String tower) {
		this.date = date;
		this.car = car;
		this.tower = tower;
	}


	
	// �޼ҵ�
	

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