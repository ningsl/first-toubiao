package nsl.utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileUtils {
	
	  private static String MESSAGE = "";  

	public static String file2String(File file){
        StringBuffer result =new StringBuffer() ;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result = result.append("\n" +s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
	
	public static String inputStream2String(InputStream in){
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(in));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result = result + "\n" +s;
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
	
	public static boolean string2File(String string,File f){
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(string);  
	        output.close();
		} 
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}  
		return true;
	}
	
	public static boolean string2File(String string,File f,String codeSet){
		//UTF-16LE
		
		try {
			/*BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(string);  
	        output.close();*/
	        
	        OutputStreamWriter os2 = new OutputStreamWriter(new FileOutputStream(f),codeSet);  
            os2.write(string);  
            os2.close();
		} 
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}  
		return true;
	}
	
	  
	  
    /** 
     * 复制单个文件 
     *  
     * @param srcFileName 
     *            待复制的文件名 
     * @param descFileName 
     *            目标文件名 
     * @param overlay 
     *            如果目标文件存在，是否覆盖 
     * @return 如果复制成功返回true，否则返回false 
     */  
    public static boolean copyFile(String srcFileName, String destFileName,  
            boolean overlay) {  
        File srcFile = new File(srcFileName);  
  
        // 判断源文件是否存在  
        if (!srcFile.exists()) {  
            MESSAGE = "源文件：" + srcFileName + "不存在！";  
            throw new RuntimeException(MESSAGE);
            //return false;  
        } else if (!srcFile.isFile()) {  
            MESSAGE = "复制文件失败，源文件：" + srcFileName + "不是一个文件！";  
            throw new RuntimeException(MESSAGE);
           // return false;  
        }  
  
        // 判断目标文件是否存在  
        File destFile = new File(destFileName);  
        if (destFile.exists()) {  
            // 如果目标文件存在并允许覆盖  
            if (overlay) {  
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件  
                new File(destFileName).delete();  
            }  
        } else {  
            // 如果目标文件所在目录不存在，则创建目录  
            if (!destFile.getParentFile().exists()) {  
                // 目标文件所在目录不存在  
                if (!destFile.getParentFile().mkdirs()) {  
                    // 复制文件失败：创建目标文件所在目录失败  
                    return false;  
                }  
            }  
        }  
  
        // 复制文件  
        int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
  
        try {  
            in = new FileInputStream(srcFile);  
            out = new FileOutputStream(destFile);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
            return true;  
        } catch (FileNotFoundException e) {  
            return false;  
        } catch (IOException e) {  
            return false;  
        } finally {  
            try {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	  
	    /** 
	     * 复制整个目录的内容 
	     *  
	     * @param srcDirName 
	     *            待复制目录的目录名 
	     * @param destDirName 
	     *            目标目录名 
	     * @param overlay 
	     *            如果目标目录存在，是否覆盖 
	     * @return 如果复制成功返回true，否则返回false 
	     */  
	    public static boolean copyDirectory(String srcDirName, String destDirName,  
	            boolean overlay) {  
	        // 判断源目录是否存在  
	        File srcDir = new File(srcDirName);  
	        if (!srcDir.exists()) {  
	            MESSAGE = "复制目录失败：源目录" + srcDirName + "不存在！";  
	            throw new RuntimeException(MESSAGE);
	            //return false;  
	        } else if (!srcDir.isDirectory()) {  
	            MESSAGE = "复制目录失败：" + srcDirName + "不是目录！";  
	            throw new RuntimeException(MESSAGE); 
	            //return false;  
	        }  
	  
	        // 如果目标目录名不是以文件分隔符结尾，则加上文件分隔符  
	        if (!destDirName.endsWith(File.separator)) {  
	            destDirName = destDirName + File.separator;  
	        }  
	        File destDir = new File(destDirName);  
	        // 如果目标文件夹存在  
	        if (destDir.exists()) {  
	            // 如果允许覆盖则删除已存在的目标目录  
	            if (overlay) {  
	                new File(destDirName).delete();  
	            } else {  
	                MESSAGE = "复制目录失败：目的目录" + destDirName + "已存在！";  
	                throw new RuntimeException(MESSAGE); 
	                //return false;  
	            }  
	        } else {  
	            // 创建目的目录  
	            System.out.println("目的目录不存在，准备创建。。。");  
	            if (!destDir.mkdirs()) {  
	                System.out.println("复制目录失败：创建目的目录失败！");  
	                return false;  
	            }  
	        }  
	  
	        boolean flag = true;  
	        File[] files = srcDir.listFiles();  
	        for (int i = 0; i < files.length; i++) {  
	            // 复制文件  
	            if (files[i].isFile()) {  
	                flag = FileUtils.copyFile(files[i].getAbsolutePath(),  
	                        destDirName + files[i].getName(), overlay);  
	                if (!flag)  
	                    break;  
	            } else if (files[i].isDirectory()) {  
	                flag = FileUtils.copyDirectory(files[i].getAbsolutePath(),  
	                        destDirName + files[i].getName(), overlay);  
	                if (!flag)  
	                    break;  
	            }  
	        }  
	        if (!flag) {  
	            MESSAGE = "复制目录" + srcDirName + "至" + destDirName + "失败！";  
	            throw new RuntimeException(MESSAGE);
	            //return false;  
	        } else {  
	            return true;  
	        }  
	    }  
	  
    public static void main(String[] args) {  
        String srcDirName = "C:/test/test0/test1";  
        String destDirName = "c:/ttt";  
        FileUtils.copyDirectory(srcDirName, destDirName, true);  
    }  
	 
}
