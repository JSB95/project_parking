package HSB;

import java.util.Date;
import java.util.Scanner;

public class Towerparking {
	
	public static void main(String[] args) {
		
		String[][] tower = new String[3][4];
		
		Scanner scanner = new Scanner(System.in);
		Date date = new Date();
		Controller.car_load(); // ���� �ҷ�����
		
			while (true) {
				try {
					Car car = new Car();
					Count count = new Count();
					for(Car temp : Controller.carlist) {
						System.out.println(temp.getCar()+"\t"+temp.getDate());
					}
					System.out.println("1. ���� 2. ���� 3. ����Ȯ��"); int ch = scanner.nextInt();
				
					if (ch == 1) {
						String a = date.toString();
						System.out.println(a);
						System.out.print("���� ��ȣ �Է� : "); int car_num = scanner.nextInt();
						Controller.inCar(a, car_num);
					} else if (ch == 2) {
						System.out.print("���� ��ȣ �Է� : "); int car_num = scanner.nextInt();
						Controller.outCar(car_num);
					} else if (ch == 3) {
						System.out.print("���� : "); int year = scanner.nextInt();
						System.out.print("�� : ");	int month = scanner.nextInt();
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
