package HSB;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


import parking.Towerparking;

public class Controller {

	// ���� ����Ʈ
	public static ArrayList<Car> carlist = new ArrayList<>();
	// ���� ����Ʈ
	public static ArrayList<Count> countlist = new ArrayList<Count>();
	
	
	// ���� �޼ҵ�
	public static boolean inCar(String a, int car_num) {
		

		
		// ����
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
	
	// ���� �޼ҵ�
	public static void outCar(int car_num) {
		
	};
	
	// �ݾ� ��� �޼ҵ�
	public static void count() {
		
	}
	
	// ���� ���� �޼ҵ�
	public static void car_save() {
		// ����Ʈ�� ��� �Խù��� ���Ͽ� ����
		try {
			// 1. ������� Ŭ����
			FileOutputStream outputStream = new FileOutputStream("D:/java/��������.txt");
			// 2. ���Ͽ� �ۼ��� ���� [ ���پ� = �Խù� 1���� = ��ü 1���� ]
			for(Car temp : carlist) {
				String �ۼ����� = temp.getCar()+","+temp.getDate();
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
				Car car = new Car(field[1],Integer.parseInt(field[0]));
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
	
	
	
	
	
	
	
	
	
}
