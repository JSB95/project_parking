package JSB;

import java.util.ArrayList;

public class Car {
	
	// field
	
	static ArrayList<Car> carNum = new ArrayList<Car>();
	private int min;
	
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
	
	public void inCar(int car_num) {
		
	};
	
	public void outCar(int car_num) {
		
	};
	
	public void car_save() {
		
	};
	
	public void car_load() {
		
	};
	
	
	
	

}
