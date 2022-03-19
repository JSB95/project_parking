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
			System.out.println(carlist.get(i).getDate());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
			Date date = new Date();
			String outtime = sdf.format(date);
			System.out.println(outtime);
			
			String[] temp = carlist.get(i).getDate().split("-");
			String[] temp2 = outtime.split("-");
				
			int year =Integer.parseInt(temp2[0])-Integer.parseInt(temp[0]);
			
			
			return;
			}
		} // for end
		
		
	}
	
	

	// ���� ���� �޼ҵ�
	public static void car_save() {
		try {
			FileOutputStream outputStream = new FileOutputStream("D:/java/��������.txt");
			for(Car temp : carlist) {
				String carfile = temp.getDate()+","+temp.getCar()+","+temp.getTower()+"\n";
				outputStream.write(carfile.getBytes());
			}
		}catch(Exception e) {
			System.out.println("�˸�)) ���� ���� ����(�����ڿ��� ����)");
		}
	};
	
	// ���� �ҷ����� �޼ҵ�
	public static void car_load() {
		try {
			FileInputStream fileInputStream = new FileInputStream("D:/java/��������.txt");
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

		try {
			FileOutputStream outputStream = new FileOutputStream("D:/java/Ÿ������.txt");
			for(String temp : tower) {
				String towerfile = temp+"\n";
				outputStream.write(towerfile.getBytes());
			}
		}catch(Exception e) {
			System.out.println("�˸�)) ���� ���� ����(�����ڿ��� ����)");
		}
	}
		
	// Ÿ�� �ҷ����� �޼ҵ�
	public static void towerload() {
		try {
			FileInputStream fileInputStream = new FileInputStream("D:/java/Ÿ������.txt");
			byte[] bytes = new byte[1024];
			fileInputStream.read(bytes);
			String file = new String(bytes);
			String[] field = file.split("\n");
			int i=0; // �ε�����
			for(String temp : field) { 
				if(i+1==field.length) break;			
				String tower1 = new String(temp);
				tower[i]=tower1;
				i++; // �ε��� ����
			}
			
		} catch(Exception e) {
			System.out.println("�˸�)) ���� �ε� ����(�����ڿ��� ����)");
		}
	}
	
	
}