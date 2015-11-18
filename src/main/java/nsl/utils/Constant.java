package nsl.utils;

import java.io.File;
import java.net.URL;


public class Constant {
	public final static String MAVEN_ABS_PATH=getAbsPath();
	public final static String WEb_ABS_PATH=getAbsPath();
	
	//
	static private String getAbsPath(){
		URL url=Constant.class.getClassLoader().getResource("");
		
		String pathString=url.getPath();
		pathString=pathString.substring(1, pathString.length()-1);
		File file=new File(pathString);
		pathString=file.getParentFile().getParent();
		/*System.out.println(pathString);*/
		/*System.out.println(pathString);*/
		System.out.println(pathString);
		System.out.println(new File(pathString).length());
		
		return pathString;
	}
}
