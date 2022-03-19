package HSB;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;





public class Controller {

	// 차량 리스트
	public static ArrayList<Car> carlist = new ArrayList<>();
	// 매출 리스트
	public static ArrayList<Count> countlist = new ArrayList<Count>();
	// 주차공간 리스트
	public static String[] tower = { 	"[    ]", "[    ]", "[    ]", "[    ]",
										"[    ]", "[    ]", "[    ]", "[    ]",
										"[    ]", "[    ]", "[    ]", "[    ]" };
	
	
	// 출력 메소드
	public static void print() {
		for(int i=0; i<tower.length; i++) {
			System.out.print(tower[i]);
			if(i%4==3) System.out.println();
		}
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
						count(carnum);
					}
				}

				carlist.remove(i);
				car_save();
				towersave();
				return;
			}
		}
		
	};
	
	// 금액 계산 메소드
	public static void count(String carnum) throws ParseException {
		DecimalFormat decimalFormat = new DecimalFormat("###,###원");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.KOREA);
		// 출차 하는 시간
		String dateend = "2022-03-25-10-02";
		long fee = 0;
		
		for (Car temp : carlist) {

			Date d1 = sdf.parse(temp.getDate());
			Date d2 = sdf.parse(dateend);
			
			long diff = d2.getTime() - d1.getTime();
			long min = diff / (1000 * 60);
			long day = diff / (1000 * 60 * 60 * 24);
			if (day > 0) {
				
				System.out.println("주차시간 : " + day + "일" + (min - 1440 * day) + "분");
				long min1 = (long) Math.ceil((min - 1440 * day) / 10.0) * 10;
				min = min1;
				fee = 50000 * day + min * 100;
				if(min>500) {
					fee=50000*(day+1);
				}
			} else {
				System.out.println("주차시간 : " + day + "일" + min + "분");
				long min1 = (long) Math.ceil((min - 1440 * day) / 10.0) * 10;
				min = min1;
				fee = min * 100 - 3000;
				if (fee > 50000) {
					fee = 50000;
				}
				if (min <= 30) {
					fee = 0;
				}

			}
			
			
			System.out.println("금액 : " + decimalFormat.format(fee));
				
			return;

		}
		
		
	}
	// 차량 저장 메소드
	public static void car_save() {
		try {
			FileOutputStream outputStream = new FileOutputStream("D:/java/차량파일.txt");
			for(Car temp : carlist) {
				String carfile = temp.getDate()+","+temp.getCar()+","+temp.getTower()+"\n";
				outputStream.write(carfile.getBytes());
			}
		}catch(Exception e) {
			System.out.println("알림)) 파일 저장 실패(관리자에게 문의)");
		}
	};
	
	

	// 차량 불러오기 메소드
	public static void car_load() {
		try {
			FileInputStream fileInputStream = new FileInputStream("D:/java/차량파일.txt");
			byte[] bytes = new byte[1024];
			fileInputStream.read(bytes);
			String file = new String(bytes);
			String[] carfile = file.split("\n");
			int i=0; 
			for(String temp : carfile) { 
				if(i+1==carfile.length) break;			
				String[] field = temp.split(",");
				Car car = new Car(field[0], field[1], field[2]);
				carlist.add(car);
				i++; 
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
		 System.out.println("-----------------"+month+"월 sales list----------------");
		
		 	
		 System.out.println("--------------------------------------------------------");
		 return;
	}
		
	// 타워 저장 메소드
	public static void towersave() {

		try {
			FileOutputStream outputStream = new FileOutputStream("D:/java/타워파일.txt");
			for(String temp : tower) {
				String towerfile = temp+"\n";
				outputStream.write(towerfile.getBytes());
			}
		}catch(Exception e) {
			System.out.println("알림)) 파일 저장 실패(관리자에게 문의)");
		}
	}
		
	// 타워 불러오기 메소드
	public static void towerload() {
		try {
			FileInputStream fileInputStream = new FileInputStream("D:/java/타워파일.txt");
			byte[] bytes = new byte[1024];
			fileInputStream.read(bytes);
			String file = new String(bytes);
			String[] field = file.split("\n");
			int i=0; // 인덱스용
			for(String temp : field) { 
				if(i+1==field.length) break;			
				String tower1 = new String(temp);
				tower[i]=tower1;
				i++; // 인덱스 증가
			}
			
		} catch(Exception e) {
			System.out.println("알림)) 파일 로드 실패(관리자에게 문의)");
		}
	}
		
}