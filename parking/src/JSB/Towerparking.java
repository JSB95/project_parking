package JSB;

import java.util.Scanner;

public class Towerparking {
	
	public static void main(String[] args) {
		
		String[][] tower = new String[3][4];
		
		Scanner scanner = new Scanner(System.in);
		

			while (true) {
				try {
					Car car = new Car();
					Count count = new Count();
					System.out.println("1. ���� 2. ���� 3. ����Ȯ��"); int ch = scanner.nextInt();
				
					if (ch == 1) {
						System.out.print("���� ��ȣ �Է� : "); int car_num = scanner.nextInt();
						car.inCar(car_num);
					} else if (ch == 2) {
						System.out.print("���� ��ȣ �Է� : "); int car_num = scanner.nextInt();
						car.outCar(car_num);
					} else if (ch == 3) {
						System.out.print("���� : "); int year = scanner.nextInt();
						System.out.print("�� : ");	int month = scanner.nextInt();
						count.list(year, month);
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
