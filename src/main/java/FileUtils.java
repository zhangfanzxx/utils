import java.io.*;
import java.util.Random;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/2/24 0024
 * Time: 9:38
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {
    static String dir16 = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F";
    static final Random random = new Random();

    public static String get16dir(int plies) {
        String[] split = dir16.split(",");
        if (plies < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (; plies > 0; plies--) {
            sb.append("/" + split[random.nextInt(split.length)]);
        }
        return sb.toString();
    }

    public static String getUUID(String suffix) {
        return UUID.randomUUID().toString().replace("-", "") + suffix;
    }

    public static String generateParentPath(String parentPath) {
        File file = new File(parentPath);
        if (file.exists()) {
            return "文件夹已存在";
        } else {
            file.mkdirs();
        }
        return "文件创建成功";
    }

    public static String deleteFile(String FileName) {
        return deleteFile(new File(FileName));

    }

    public static String deleteFile(File file) {
        if (!file.exists()) {
            return "文件不存在";
        } else {
            delete(file);
            return "删除成功";
        }
    }

    public static String generateFile(InputStream is, File file) {
        FileOutputStream fos =null;
        try {
            fos =new FileOutputStream(file);
            int i;
            byte[] buffer=new byte[1024];
            while((i=is.read(buffer))!=-1){
                fos.write(buffer,0,i);
            }
            is.close();
            fos.close();
            return "创建成功";
        } catch (Exception e) {
            return "创建失败";
        } finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    is=null;
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    fos=null;
                    e.printStackTrace();
                }
            }
        }
    }

    private static void delete(File file) {
        if (file.isDirectory()) {
            for (File file1 : file.listFiles()) {
                delete(file1);
            }
        }
        file.delete();
    }


}
