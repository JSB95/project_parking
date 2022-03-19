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

	// ���� ����Ʈ
	public static ArrayList<Car> carlist = new ArrayList<>();
	// ���� ����Ʈ
	public static ArrayList<Count> countlist = new ArrayList<Count>();
	// �������� ����Ʈ
	public static String[] tower = { "[    ]", "[    ]", "[    ]", "[    ]",
									"[    ]", "[    ]", "[    ]", "[    ]",
									"[    ]", "[    ]", "[    ]", "[    ]" };
	
	
	// ��� �޼ҵ�
	public static void print() {
		for(int i=0; i<tower.length; i++) {
			System.out.print(tower[i]);
			if(i%4==3) System.out.println();
		}
		
		int i=0;
		
		
	}
	
	// �ߺ�üũ, ��ȿ���˻� �޼ҵ�
	public static int check(int car_num) {
		DecimalFormat df = new DecimalFormat("0000");
		String carnum = df.format(car_num);
		for(String temp : tower) {
			if(temp.equals("["+carnum+"]")) {
				return 1; // ������ : �̹� ������ ������ȣ�� ��ġ // ������ : ��������
			}
		}
		if(car_num<0 || car_num>10000) {
			return 2; // ������ : �ùٸ��� ���� ������ȣ // ������ : �ùٸ��� ���� ������ȣ
		}
		for(String temp : tower) {
			if(temp.equals("[    ]")) {
				return 3; // ������ : ���� ���� // ������ : x
			}
			
		}
		
		return 4; // ������ : �������� ���� // ������ : ��ġ�ϴ� ������ȣ�� x 
	}
	
	// ���� �޼ҵ�
	public static boolean inCar(String intime, int car_num) {
		DecimalFormat df = new DecimalFormat("0000");
		String carnum = df.format(car_num);
		// ����
		for (int i = 0; i <tower.length; i++) {
			if (tower[i].equals("[    ]") ) {
				tower[i] = "["+carnum+"]";
				
				Car temp = new Car(intime, carnum,tower[i]);
				carlist.add(temp);
				car_save();
				towersave();
				
				return true; // ��������
			}
		}
		return false; // ������ �̹� �ߺ�/��ȿ�� �˻��ؼ� �������� ����
	};
	
	// ���� �޼ҵ�
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
	
	// �ݾ� ��� �޼ҵ�
	public static void count(String carnum) throws ParseException {
		for(int i=0; i<carlist.size(); i++) {
			if(carlist.get(i).getCar().equals(carnum)) {
			
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
				Date intime = sdf.parse(carlist.get(i).getDate());
				Instant instant1 = intime.toInstant(); // �����ð�
				Instant instant2 = Instant.now(); // �����ð�
				System.out.println("����(��): "+instant1.until(instant2, ChronoUnit.MINUTES) );
				return;
			}
		}
		
		
	}
	
	

	// ���� ���� �޼ҵ�
	public static void car_save() {
		// ����Ʈ�� ��� �Խù��� ���Ͽ� ����
		try {
			// 1. ������� Ŭ����
			FileOutputStream outputStream = new FileOutputStream("D:/java/��������.txt");
			// 2. ���Ͽ� �ۼ��� ���� [ ���پ� = �Խù� 1���� = ��ü 1���� ]
			for(Car temp : carlist) {
				String �ۼ����� = temp.getDate()+","+temp.getCar()+","+temp.getTower()+"\n";
			// 3. ����[���ڿ�] -> ����Ʈ�� ��ȯ [ �ܺ����(��Ʈ��) : ��Ŵ��� : ����Ʈ ]
			// 4. �������� [ write() ]
				outputStream.write(�ۼ�����.getBytes());
			}
		}catch(Exception e) {
			System.out.println("�˸�)) ���� ���� ����(�����ڿ��� ����)");
		}
	};
	
	// ���� �ҷ����� �޼ҵ�
	public static void car_load() {
		try {
			// 1. ���� �Է� Ŭ����
			FileInputStream fileInputStream = new FileInputStream("D:/java/��������.txt");
			// 2. ����Ʈ �迭 ����
			byte[] bytes = new byte[1024];
			// 3. ��� ����Ʈ �о�ͼ� ����Ʈ�� ����
			fileInputStream.read(bytes);
			// 4. ����Ʈ -> ���ڿ� ��ȯ
			String file = new String(bytes);
			// 5. ���ڿ� �ڸ��� [ ���پ�(\n) -> 1����ü ]
			String[] boards = file.split("\n");
			// 6. ���ڿ� �ڸ��� [ (,) -> �� �ʵ� ]
			int i=0; // �ε�����
			for(String temp : boards) { 
				if(i+1==boards.length) break;			
				String[] field = temp.split(",");
				// 7. ��üȭ
				Car car = new Car(field[0], field[1], field[2]);
				// 8. ����Ʈ ���
				carlist.add(car);
				i++; // �ε��� ����
			}
			
		} catch(Exception e) {
			System.out.println("�˸�)) ���� �ε� ����(�����ڿ��� ����)");
		}
	};
	
	// ���� ���� �޼ҵ�
	public static void save() {
		
	}
	// ���� �ҷ����� �޼ҵ�
	public static void load() {
		
	}
	
	// ����Ȯ�� �޼ҵ�
	public static void list(int year, int month) {
		
	}
	
	// Ÿ�� ���� �޼ҵ�
	public static void towersave() {
		// ����Ʈ�� ��� �Խù��� ���Ͽ� ����
		try {
			// 1. ������� Ŭ����
			FileOutputStream outputStream = new FileOutputStream("D:/java/Ÿ������.txt");
			// 2. ���Ͽ� �ۼ��� ���� [ ���پ� = �Խù� 1���� = ��ü 1���� ]
			for(String temp : tower) {
				String �ۼ����� = temp+"\n";
			// 3. ����[���ڿ�] -> ����Ʈ�� ��ȯ [ �ܺ����(��Ʈ��) : ��Ŵ��� : ����Ʈ ]
			// 4. �������� [ write() ]
				outputStream.write(�ۼ�����.getBytes());
			}
		}catch(Exception e) {
			System.out.println("�˸�)) ���� ���� ����(�����ڿ��� ����)");
		}
	}
		
	// Ÿ�� �ҷ����� �޼ҵ�
	public static void towerload() {
		try {
			// 1. ���� �Է� Ŭ����
			FileInputStream fileInputStream = new FileInputStream("D:/java/Ÿ������.txt");
			// 2. ����Ʈ �迭 ����
			byte[] bytes = new byte[1024];
			// 3. ��� ����Ʈ �о�ͼ� ����Ʈ�� ����
			fileInputStream.read(bytes);
			// 4. ����Ʈ -> ���ڿ� ��ȯ
			String file = new String(bytes);
			// 5. ���ڿ� �ڸ��� [ ���پ�(\n) -> 1����ü ]
			String[] field = file.split("\n");
			// 6. ���ڿ� �ڸ��� [ (,) -> �� �ʵ� ]
			int i=0; // �ε�����
			for(String temp : field) { 
				if(i+1==field.length) break;			
				// 7. ��üȭ
				String tower1 = new String(temp);
				// 8. ����Ʈ ���
				tower[i]=tower1;
				i++; // �ε��� ����
			}
			
		} catch(Exception e) {
			System.out.println("�˸�)) ���� �ε� ����(�����ڿ��� ����)");
		}
	}
	
	
}
