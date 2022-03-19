package JSB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class 계산 {

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
		System.out.println("초 차이 : " + sec);
		System.out.println("분 차이 : " + min);
		System.out.println("올림 분 : " + min1);
		System.out.println("시 차이 : " + hour);
		System.out.println("일 차이 : " + day);
		
		

	}

}
