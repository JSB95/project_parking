package parking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Towerparking {
	
	public static void main(String[] args) {
		
		
		
		Scanner scanner = new Scanner(System.in);
		
		Controller.car_load(); // 파일 불러오기
		Controller.towerload(); 
		Controller.load();
		
			while (true) {
				Date date = new Date();
				try {
					// 주차공간 출력	
					
					Controller.print();
					System.out.println("");
					System.out.println("------Parking System--------");
					System.out.println("1. 입차 2. 출차 3. 매출확인");
					System.out.println("----------------------------");
					System.out.print("서비스를 선택해주세요. > ");
					int ch = scanner.nextInt();
					
			//////////////////////////////////////////////// 입차 /////////////////////////////////////////////
					if (ch == 1) {
					System.out.println("----------입차서비스-----------");
					System.out.print("차량 번호 입력 : "); int car_num = scanner.nextInt();
					int result = Controller.check(car_num);
					if(result==1) {
						System.err.println("이미 주차된 차량번호와 동일합니다.[주차실패]");
					}else if(result==2) {
						System.err.println("0000~9999 사이의 차량번호를 입력해주세요.[주차실패]");	
					}else if(result==3) {
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
						String intime = sdf.format(date);
						boolean result2 = Controller.inCar(intime, car_num);
						if(result2) {
							
							System.out.println("주차가 완료되었습니다 !!");
						}
					}else if(result==4) {
						System.out.println("현재 주차 공간이 부족합니다.[주차실패]");	
					}
					
			///////////////////////////////////////////// 출차 ////////////////////////////////////////////////////			
					} else if (ch == 2) {
						System.out.println("----------출차서비스-----------");
						System.out.print("차량 번호 입력 : "); 
						int car_num = scanner.nextInt();
						int result = Controller.check(car_num);
						if(result==1) {
							
							Controller.outCar(car_num);
							
							System.out.println("출차가 완료되었습니다 !! 안녕히 가십시오.");
						}else if(result==2) {
							System.err.println("0000~9999 사이의 차량번호를 입력해주세요.[출차실패]");	
						}else if(result==3) {
							System.err.println("주차된 차량과 일치하는 차량번호가 없습니다.[출차실패]");
						}
						
			///////////////////////////////////////////// 매출 확인 ////////////////////////////////////////////////////			
					} else if (ch == 3) {
						System.out.println("---------매출확인--------------");
						
						System.out.print("<매출검색>연도 : "); 
						int year = scanner.nextInt();
						System.out.print("<매출검색>월 : ");	
						int month = scanner.nextInt();
						int result = Controller.list(year, month);
						if(result == 1) {
							
						}else System.err.println("해당 년도 월의 매출이 없습니다.");
						
					} else {
						System.err.println("error");
					}
				} catch (Exception e) {
					System.out.println("잘못된 입력입니다.");
					scanner = new Scanner(System.in);
				}
			}
		
		
	}

}
