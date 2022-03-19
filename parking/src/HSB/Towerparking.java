package HSB;

import java.util.Date;
import java.util.Scanner;

public class Towerparking {
	
	public static void main(String[] args) {
		
		String[][] tower = new String[3][4];
		
		Scanner scanner = new Scanner(System.in);
		Date date = new Date();
		Controller.car_load(); // 파일 불러오기
		
			while (true) {
				try {
					Car car = new Car();
					Count count = new Count();
					for(Car temp : Controller.carlist) {
						System.out.println(temp.getCar()+"\t"+temp.getDate());
					}
					System.out.println("-----------------------");
					System.out.println("1. 입차 2. 출차 3. 매출확인"); 
					System.out.println("-----------------------");
					int ch = scanner.nextInt();
					//입차
					if (ch == 1) {
						String a = date.toString();
						System.out.println(a);
						System.out.print("차량 번호 입력 : "); int car_num = scanner.nextInt();
						System.out.println("*****현재 주차된 리스트 ******");
						Controller.inCar(a, car_num);
						System.out.println("*************************");
					} 
					//출차
					else if (ch == 2) {
						String b = date.toString();
						System.out.println(b);
						System.out.print("차량 번호 입력 : "); 
						int car_num = scanner.nextInt();
						System.out.println("차량번호 : "+car_num);
						System.out.println("출차시간 : "+b);
						
						boolean result =Controller.outCar(b , car_num);
						if(result==true) {
							System.out.println("출차가 완료되었습니다");
						}else {
							System.out.println("차 번호가 없습니다.");
						}
						
					//매출확인
					} else if (ch == 3) {
						System.out.print("연도 : "); int year = scanner.nextInt();
						System.out.print("월 : ");	int month = scanner.nextInt();
						Controller.list(year,month);
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
