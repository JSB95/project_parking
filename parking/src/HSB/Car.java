package HSB;

public class Car {
	
	// �ʵ�
	private String date;
	private int car; // ���� ��ȣ
	
	
	
	// ������
	public Car() {}

	public Car(String date, int car) {
		this.date = date;
		this.car = car;
	}

	
	// �޼ҵ�
	

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
