package JSB;

import java.util.Scanner;

public class Towerparking {
	
	public static void main(String[] args) {
		
		String[][] tower = new String[3][4];
		
		Scanner scanner = new Scanner(System.in);
		

			while (true) {
				try {
					Car car = new Car();
					Count count = new Count();
					System.out.println("1. 입차 2. 출차 3. 매출확인"); int ch = scanner.nextInt();
				
					if (ch == 1) {
						System.out.print("차량 번호 입력 : "); int car_num = scanner.nextInt();
						car.inCar(car_num);
					} else if (ch == 2) {
						System.out.print("차량 번호 입력 : "); int car_num = scanner.nextInt();
						car.outCar(car_num);
					} else if (ch == 3) {
						System.out.print("연도 : "); int year = scanner.nextInt();
						System.out.print("월 : ");	int month = scanner.nextInt();
						count.list(year, month);
					} else {
						System.err.println("error");
					}
				} catch (Exception e) {
					e.printStackTrace();
					scanner = new Scanner(System.in);
				}
			}
		
		
	}

}
