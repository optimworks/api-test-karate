package Utilities;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
//import com.github.javafaker.Faker;
import java.util.Locale;


public class Utilities {

	private static final String defaultLocale="en-GB";
	
    public static long number() {
        int offset = (int) (Math.random() * 1000) + 1;
        return System.currentTimeMillis() + offset;
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

//    public static Faker faker() {
//        return faker(defaultLocale);
//    }
//
//    public static Faker faker(String locale) {
//        return new Faker(new Locale(locale));
//    }

    public static String futureDate(int days, String format) {
        Date now = new Date();
        Date date = new Date(now.getTime() + TimeUnit.DAYS.toMillis((long) days));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
    
    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
    
    
    
}
