package KJW;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Towerparking {
	
	public static void main(String[] args) {
		
		
		
		Scanner scanner = new Scanner(System.in);
		
		Controller.car_load(); // ���� �ҷ�����
		Controller.towerload(); 
		Controller.load();
		
			while (true) {
				Date date = new Date();
				try {
					// �������� ���	
					
					Controller.print();
					
					
					System.out.println("1. ���� 2. ���� 3. ����Ȯ��"); int ch = scanner.nextInt();
			//////////////////////////////////////////////// ���� /////////////////////////////////////////////
					if (ch == 1) {
					
					System.out.print("���� ��ȣ �Է� : "); int car_num = scanner.nextInt();
					int result = Controller.check(car_num);
					if(result==1) {
						System.out.println("�̹� ������ ������ȣ�� �����մϴ�.[��������]");
					}else if(result==2) {
						System.out.println("0000~9999 ������ ������ȣ�� �Է����ּ���.[��������]");	
					}else if(result==3) {
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
						String intime = sdf.format(date);
						boolean result2 = Controller.inCar(intime, car_num);
						if(result2) {
							System.out.println("������ �Ϸ�Ǿ����ϴ�.");
						}
					}else if(result==4) {
						System.out.println("���� ���� ������ �����մϴ�.[��������]");	
					}
					
			///////////////////////////////////////////// ���� ////////////////////////////////////////////////////			
					} else if (ch == 2) {
						System.out.print("���� ��ȣ �Է� : "); int car_num = scanner.nextInt();
						int result = Controller.check(car_num);
						if(result==1) {
							
							Controller.outCar(car_num);
							
							System.out.println("������ �Ϸ�Ǿ����ϴ�.");
						}else if(result==2) {
							System.out.println("0000~9999 ������ ������ȣ�� �Է����ּ���.[��������]");	
						}else if(result==3) {
							System.out.println("������ ������ ��ġ�ϴ� ������ȣ�� �����ϴ�.[��������]");
						}
						
			///////////////////////////////////////////// ���� Ȯ�� ////////////////////////////////////////////////////			
					} else if (ch == 3) {
						System.out.print("���� : "); int year = scanner.nextInt();
						System.out.print("�� : ");	int month = scanner.nextInt();
						boolean result = Controller.list(year, month);
						if(result) {
							
						}else System.out.println("�ش� �⵵ ���� ������ �����ϴ�.");
						
					} else {
						System.err.println("error");
					}
				} catch (Exception e) {
					System.out.println("�߸��� �Է��Դϴ�.");;
					scanner = new Scanner(System.in);
				}
			}
		
		
	}

}
