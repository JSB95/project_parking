package KJW;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;





public class Controller {

	// 차량 리스트
	public static ArrayList<Car> carlist = new ArrayList<>();
	// 매출 리스트
	public static ArrayList<Count> countlist = new ArrayList<Count>();
	// 주차공간 리스트
	public static String[] tower = { "[    ]", "[    ]", "[    ]", "[    ]",
									"[    ]", "[    ]", "[    ]", "[    ]",
									"[    ]", "[    ]", "[    ]", "[    ]" };
	
	
	// 출력 메소드
	public static void print() {
		for(int i=0; i<tower.length; i++) {
			System.out.print(tower[i]);
			if(i%4==3) System.out.println();
		}
		
		int i=0;
		
		
	}
	
	// 중복체크, 유효성검사 메소드
	public static int check(int car_num) {
		DecimalFormat df = new DecimalFormat("0000");
		String carnum = df.format(car_num);
		for(String temp : tower) {
			if(temp.equals("["+carnum+"]")) {
				return 1; // 입차시 : 이미 주차된 차량번호와 일치 // 출차시 : 출차가능
			}
		}
		if(car_num<0 || car_num>10000) {
			return 2; // 입차시 : 올바르지 않은 차량번호 // 출차시 : 올바르지 않은 차량번호
		}
		for(String temp : tower) {
			if(temp.equals("[    ]")) {
				return 3; // 입차시 : 주차 가능 // 출차시 : x
			}
			
		}
		
		return 4; // 입차시 : 주차공간 부족 // 출차시 : 일치하는 차량번호가 x 
	}
	
	// 입차 메소드
	public static boolean inCar(String intime, int car_num) {
		DecimalFormat df = new DecimalFormat("0000");
		String carnum = df.format(car_num);
		// 입차
		for (int i = 0; i <tower.length; i++) {
			if (tower[i].equals("[    ]") ) {
				tower[i] = "["+carnum+"]";
				
				Car temp = new Car(intime, carnum,tower[i]);
				carlist.add(temp);
				car_save();
				towersave();
				
				return true; // 입차성공
			}
		}
		return false; // 위에서 이미 중복/유효성 검사해서 실패할일 없음
	};
	
	// 출차 메소드
	public static void outCar(int car_num) throws ParseException {
		DecimalFormat df = new DecimalFormat("0000");
		String carnum = df.format(car_num);
		for (int i = 0; i <tower.length; i++) {
			if (carlist.get(i).getCar().equals(carnum) ) {
				for(int j=0; j<tower.length; j++) {
					if(tower[j].equals("["+carnum+"]")) {
						tower[j]="[    ]";
					}
				}
				count(carnum);
				carlist.remove(i);
				car_save();
				towersave();
				return;
			}
		}
		
	};
	
	// 금액 계산 메소드
	public static void count(String carnum) throws ParseException {
		for(int i=0; i<carlist.size(); i++) {
			if(carlist.get(i).getCar().equals(carnum)) {
			
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
				Date intime = sdf.parse(carlist.get(i).getDate());
				Instant instant1 = intime.toInstant(); // 입차시간
				Instant instant2 = Instant.now(); // 출차시간
				System.out.println("차이(분): "+instant1.until(instant2, ChronoUnit.MINUTES) );
				return;
			}
		}
		
		
	}
	
	

	// 차량 저장 메소드
	public static void car_save() {
		// 리스트내 모든 게시물을 파일에 저장
		try {
			// 1. 파일출력 클래스
			FileOutputStream outputStream = new FileOutputStream("D:/java/차량파일.txt");
			// 2. 파일에 작성할 내용 [ 한줄씩 = 게시물 1개씩 = 객체 1개씩 ]
			for(Car temp : carlist) {
				String 작성내용 = temp.getDate()+","+temp.getCar()+","+temp.getTower()+"\n";
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
				Car car = new Car(field[0], field[1], field[2]);
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
	
	// 타워 저장 메소드
	public static void towersave() {
		// 리스트내 모든 게시물을 파일에 저장
		try {
			// 1. 파일출력 클래스
			FileOutputStream outputStream = new FileOutputStream("D:/java/타워파일.txt");
			// 2. 파일에 작성할 내용 [ 한줄씩 = 게시물 1개씩 = 객체 1개씩 ]
			for(String temp : tower) {
				String 작성내용 = temp+"\n";
			// 3. 내용[문자열] -> 바이트열 변환 [ 외부통신(스트림) : 통신단위 : 바이트 ]
			// 4. 내보내기 [ write() ]
				outputStream.write(작성내용.getBytes());
			}
		}catch(Exception e) {
			System.out.println("알림)) 파일 저장 실패(관리자에게 문의)");
		}
	}
		
	// 타워 불러오기 메소드
	public static void towerload() {
		try {
			// 1. 파일 입력 클래스
			FileInputStream fileInputStream = new FileInputStream("D:/java/타워파일.txt");
			// 2. 바이트 배열 선언
			byte[] bytes = new byte[1024];
			// 3. 모든 바이트 읽어와서 바이트에 저장
			fileInputStream.read(bytes);
			// 4. 바이트 -> 문자열 변환
			String file = new String(bytes);
			// 5. 문자열 자르기 [ 한줄씩(\n) -> 1개객체 ]
			String[] field = file.split("\n");
			// 6. 문자열 자르기 [ (,) -> 각 필드 ]
			int i=0; // 인덱스용
			for(String temp : field) { 
				if(i+1==field.length) break;			
				// 7. 객체화
				String tower1 = new String(temp);
				// 8. 리스트 담기
				tower[i]=tower1;
				i++; // 인덱스 증가
			}
			
		} catch(Exception e) {
			System.out.println("알림)) 파일 로드 실패(관리자에게 문의)");
		}
	}
	
	
}
