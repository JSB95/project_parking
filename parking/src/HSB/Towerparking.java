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
					System.out.println("-----------------------");
					System.out.println("1. ���� 2. ���� 3. ����Ȯ��"); 
					System.out.println("-----------------------");
					int ch = scanner.nextInt();
					//����
					if (ch == 1) {
						String a = date.toString();
						System.out.println(a);
						System.out.print("���� ��ȣ �Է� : "); int car_num = scanner.nextInt();
						System.out.println("*****���� ������ ����Ʈ ******");
						Controller.inCar(a, car_num);
						System.out.println("*************************");
					} 
					//����
					else if (ch == 2) {
						String b = date.toString();
						System.out.println(b);
						System.out.print("���� ��ȣ �Է� : "); 
						int car_num = scanner.nextInt();
						boolean result =Controller.outCar(b , car_num);
						System.out.println("������ȣ : "+car_num);
						System.out.println("�����ð� : "+b);
						System.out.println("�����ݾ׸� �����Ͻðڽ��ϱ�?");
						if(result==true) {
							System.out.println("1.�����ϱ� 2.�ڷΰ���");
							int ch1= scanner.nextInt();
							if(ch1==1) {}
							else if(ch1==2) {}
							else {
								System.out.println("�˼������ൿ�Դϴ�.");
							}
						}else {
							System.out.println("�� ��ȣ�� �����ϴ�.");
						}
						
					//����Ȯ��
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
