package parking;


import java.util.ArrayList;

public class Car {
	
	// field
	
	static ArrayList<Car> carNum = new ArrayList<Car>();
	private int min, car;
	String time;
	
	
	public Car() {
		// TODO Auto-generated constructor stub
	}

	public Car(ArrayList<Car> carNum, int min) {
		super();
		this.carNum = carNum;
		this.min = min;
	}

	public ArrayList<Car> getCarNum() {
		return carNum;
	}

	public void setCarNum(ArrayList<Car> carNum) {
		this.carNum = carNum;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}
	
	// method
	
	public boolean inCar(int car_num) {
		

		
		// ÀÔÂ÷
		for (int i = 0; i <Towerparking.tower.length; i++) {
			if (Towerparking.tower[i].equals("[ ]") ) {
				Towerparking.tower[i] = "[ " + car_num + " ]";
				return true;
			} 
		}
		
		
		return false;
	};
	
	public void outCar(int car_num) {
		
	};
	
	public void car_save() {
		
	};
	
	public void car_load() {
		
	};
	
	
	
	

}
