package KJW;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;






public class Controller {

	// ���� ����Ʈ
	public static ArrayList<Car> carlist = new ArrayList<>();
	// ���� ����Ʈ
	public static ArrayList<Count> countlist = new ArrayList<Count>();
	// �ӽ� ����� ����Ʈ
	public static int[] sum = new int[31]; 
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
			DecimalFormat decimalFormat = new DecimalFormat("###,###��");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.KOREA);
			// ���� �ϴ� �ð�
			String dateend = sdf.format(date);
			
			long fee = 0;
			
			for (Car temp : carlist) {

				Date d1 = sdf.parse(temp.getDate());
				Date d2 = sdf.parse(dateend);
				
				long diff = d2.getTime() - d1.getTime();
				long min = diff / (1000 * 60);
				long day = diff / (1000 * 60 * 60 * 24);
				if (day > 0) {
					
					System.out.println("�����ð� : " + day + "��" + (min - 1440 * day) + "��");
					long min1 = (long) Math.ceil((min - 1440 * day) / 10.0) * 10;
					min = min1;
					fee = 50000 * day + min * 100;
				} else {
					System.out.println("�����ð� : " + day + "��" + min + "��");
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
				
				String[] a = dateend.split("-");
				
				Count temp2 = new Count(Integer.parseInt(a[0]),Integer.parseInt(a[1]),Integer.parseInt(a[2]),(int)fee);
				countlist.add(temp2);
				
				System.out.println("�ݾ� : " + decimalFormat.format(fee));
				
				save();
				return;

			}
			
			
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
	}
	
	// ���� ���� �޼ҵ�
	public static void save() {

		try {
			FileOutputStream outputStream = new FileOutputStream("D:/java/��������.txt");
			for(Count temp : countlist) {
				String countfile = temp.getYear()+","+temp.getMonth()+","+temp.getDay()+","+temp.getProfit()+"\n";
				outputStream.write(countfile.getBytes());
			}
		}catch(Exception e) {
			System.out.println("�˸�)) ���� ���� ����(�����ڿ��� ����)");
		}
	}
	// ���� �ҷ����� �޼ҵ�
	public static void load() {
		try {
			FileInputStream fileInputStream = new FileInputStream("D:/java/��������.txt");
			byte[] bytes = new byte[1024];
			fileInputStream.read(bytes);
			String file = new String(bytes);
			String[] countfile = file.split("\n");
			int i=0; 
			for(String temp : countfile) { 
				if(i+1==countfile.length) break;			
				String[] field = temp.split(",");
				Count count = new Count(Integer.parseInt(field[0]),Integer.parseInt(field[1]),Integer.parseInt(field[2]),Integer.parseInt(field[3]) );
				countlist.add(count);
				i++; 
			}
			
		} catch(Exception e) {
			System.out.println("�˸�)) ���� �ε� ����(�����ڿ��� ����)");
		}
	}
	
	// �޼ҵ�
	public static void alist(int year, int month) {
		
	}
	
	
	
	// ����Ȯ�� �޼ҵ�
	public static boolean list(int year, int month) {
		boolean data = false;
		
		// ���� �� ��ġ�ϴ� ������ ���� ����
		
		for(Count temp : countlist) { // ���� ����Ʈ �ҷ��ͼ�
			if(temp.getYear()==year && temp.getMonth()==month) { // ���� �� ��ġ�ϸ�
				for(int i=0; i<31; i++) { // i = ��¥
					if(temp.getDay()==i+1) { // ��¥�� ��ġ�ϸ�
						sum[i] += temp.getProfit(); // �ӽ� ����� �迭�� ����
					}
				}
				data = true;	
			} 
		}
		
		for(int i=0; i<31; i++) { // �Ϻ� �հ� ���
			if(sum[i]!=0) {
				System.out.println((i+1)+"��\t"+sum[i]+"��");
				sum[i]=0;
			}	
		}
		
		if(data==true) {
			return true;  // ���� �� ��ġ�ϴ� ������ ����
		}
		return false; // ���� �� ��ġ�ϴ� ������ ����
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
	
	
} // c e
