package parking;

import java.util.Date;
import java.util.Scanner;

public class Towerparking {
	
	public static String[] tower = { "[ ]", "[ ]", "[ ]", "[ ]",
									"[ ]", "[ ]", "[ ]", "[ ]",
									"[ ]", "[ ]", "[ ]", "[ ]" };
	Date date = new Date();

	
	public static void main(String[] args) {
		
		
		
		Scanner scanner = new Scanner(System.in);
		

			while (true) {
				try {
					Car car = new Car();
					Count count = new Count();
					for (int i = 0; i < tower.length; i++) {
						
						System.out.print(tower[i]);

						// �ٹٲ�
						if (i % 4 == 3) {
							System.out.println("");
						}	
					}
					System.out.println("1. ���� 2. ���� 3. ����Ȯ��"); int ch = scanner.nextInt();
				
					if (ch == 1) {
						System.out.print("���� ��ȣ �Է� : "); int car_num = scanner.nextInt();
						boolean result = car.inCar(car_num);
						if(result) {
							System.out.println("������ �Ϸ�Ǿ����ϴ�.");
						}else {
							System.out.println("�����Դϴ�.");
						}
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
