package JSB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ��� {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm", Locale.KOREA);
		
		String datestart = "2022-04-19-06-13";
		System.out.println(datestart);
		String dateend = "2022-04-19-06-50";
		System.out.println(dateend);
		Date d1 = sdf.parse(datestart);
		Date d2 = sdf.parse(dateend);
		
		long diff = d2.getTime() - d1.getTime();
		long sec = diff / 1000;
		long min = diff / (1000 * 60);
		long hour = diff / (1000 * 60 * 60);
		long day = diff / (1000 * 60 * 60 * 24);
		long min1 = (long) (Math.ceil((min - 30.0) / 10) * 10);
		System.out.println("�� ���� : " + sec);
		System.out.println("�� ���� : " + min);
		System.out.println("�ø� �� : " + min1);
		System.out.println("�� ���� : " + hour);
		System.out.println("�� ���� : " + day);
		
		

	}

}
