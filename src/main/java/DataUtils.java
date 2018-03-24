import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换使用
 * */
public class DataUtils {
    public static Date parse(String Time, String pattern) {
        if (Time == null || "".equals(Time)) return null;
        try {
            return new SimpleDateFormat(pattern).parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parse(Date date, String pattern) {
        if (date == null) return null;
        try {
            return new SimpleDateFormat(pattern).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
