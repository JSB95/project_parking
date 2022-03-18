package parking;

import java.util.Date;
import java.util.Scanner;

public class Towerparking {
	
	public static String[] tower = { "[ ]", "[ ]", "[ ]", "[ ]",
									"[ ]", "[ ]", "[ ]", "[ ]",
									"[ ]", "[ ]", "[ ]", "[ ]" };
	Date date = new Date();

	
	public static void main(String[] args) {
		
		
		
		Scanner scanner = new Scanner(System.in);
		

			while (true) {
				try {
					Car car = new Car();
					Count count = new Count();
					for (int i = 0; i < tower.length; i++) {
						
						System.out.print(tower[i]);

						// 줄바꿈
						if (i % 4 == 3) {
							System.out.println("");
						}	
					}
					System.out.println("1. 입차 2. 출차 3. 매출확인"); int ch = scanner.nextInt();
				
					if (ch == 1) {
						System.out.print("차량 번호 입력 : "); int car_num = scanner.nextInt();
						boolean result = car.inCar(car_num);
						if(result) {
							System.out.println("입차가 완료되었습니다.");
						}else {
							System.out.println("만차입니다.");
						}
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
