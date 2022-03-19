package HSB;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


import parking.Towerparking;

public class Controller {

	// 차량 리스트
	public static ArrayList<Car> carlist = new ArrayList<>();
	// 매출 리스트
	public static ArrayList<Count> countlist = new ArrayList<Count>();
	
	
	// 입차 메소드
	public static boolean inCar(String a, int car_num) {
		

		
		// 입차
		for (int i = 0; i <Towerparking.tower.length; i++) {
			if (Towerparking.tower[i].equals("[ ]") ) {
				Towerparking.tower[i] = "[ " + car_num + " ]";
				
				Car temp = new Car(a, car_num);
				carlist.add(temp);
				car_save();
				
				return true;
			}
			
		}
		return false;
	};
	
	// 출차 메소드
	public static void outCar(int car_num) {
		
	};
	
	// 금액 계산 메소드
	public static void count() {
		
	}
	
	// 차량 저장 메소드
	public static void car_save() {
		// 리스트내 모든 게시물을 파일에 저장
		try {
			// 1. 파일출력 클래스
			FileOutputStream outputStream = new FileOutputStream("D:/java/차량파일.txt");
			// 2. 파일에 작성할 내용 [ 한줄씩 = 게시물 1개씩 = 객체 1개씩 ]
			for(Car temp : carlist) {
				String 작성내용 = temp.getCar()+","+temp.getDate();
			// 3. 내용[문자열] -> 바이트열 변환 [ 외부통신(스트림) : 통신단위 : 바이트 ]
			// 4. 내보내기 [ write() ]
				outputStream.write(작성내용.getBytes());
			}
		}catch(Exception e) {
			System.out.println("알림)) 파일 저장 실패(관리자에게 문의)");
		}
	};
	
	// 차량 불러오기 메소드
	public static void car_load() {
		try {
			// 1. 파일 입력 클래스
			FileInputStream fileInputStream = new FileInputStream("D:/java/차량파일.txt");
			// 2. 바이트 배열 선언
			byte[] bytes = new byte[1024];
			// 3. 모든 바이트 읽어와서 바이트에 저장
			fileInputStream.read(bytes);
			// 4. 바이트 -> 문자열 변환
			String file = new String(bytes);
			// 5. 문자열 자르기 [ 한줄씩(\n) -> 1개객체 ]
			String[] boards = file.split("\n");
			// 6. 문자열 자르기 [ (,) -> 각 필드 ]
			int i=0; // 인덱스용
			for(String temp : boards) { 
				if(i+1==boards.length) break;			
				String[] field = temp.split(",");
				// 7. 객체화
				Car car = new Car(field[1],Integer.parseInt(field[0]));
				// 8. 리스트 담기
				carlist.add(car);
				i++; // 인덱스 증가
			}
			
		} catch(Exception e) {
			System.out.println("알림)) 파일 로드 실패(관리자에게 문의)");
		}
	};
	
	// 매출 저장 메소드
	public static void save() {
		
	}
	// 매출 불러오기 메소드
	public static void load() {
		
	}
	
	// 매출확인 메소드
	public static void list(int year, int month) {
		
	}
	
	
	
	
	
	
	
	
	
}
