package saveFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import nsl.utils.ClassPathUtils;

import org.aspectj.apache.bcel.classfile.Field;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import toubiao.constant.Web;

public class SaveTest {
	@Test
	public void saveDemo() throws IOException{
		String msg="hello";
		byte[] buffer=msg.getBytes();
		int count=buffer.length;
		FileOutputStream wf=new FileOutputStream("c:/1/Writer.txt");
        wf.write(buffer,0,count);
        wf.close();
	}
	
	@Test
	public void createDir() throws IOException{
		String msg="c:/hello/1.3";
		
		System.out.println(SaveTest.class.getSimpleName());
		File file=new File(msg);
		if(file.exists()){
			System.out.println("exist!");
		}
		file.mkdirs();
	}
	
	@Test
	public void dirIsExist(){
		File dir1=new File("Y:/EclipseProject/RecentlyProject/toubiao/resource/郑州联通/12230.0/合同照片 ");
		File dir2=new File("Y:\\1.docx");
		File dir3=new File("Y:/EclipseProject/RecentlyProject/toubiao/resource/郑州联通/12230.0/合同照片");
		//Y:\EclipseProject\RecentlyProject\toubiao\resource\郑州联通\12230.0\合同照片
		if(dir1.exists()){
			System.out.println("1  exists");
		}
		if(dir2.exists()){
			System.out.println("2  exists");
		}
		if(dir3.exists()){
			System.out.println("3  exists");
		}
	}
	
	@Test 
	public void getWebPath(){
		URL url=SaveTest.class.getResource("/");
		System.out.println(ClassPathUtils.getClassPath());
		//System.out.println(Web.LOCAL_ABS_PATH);
	}
	
}
