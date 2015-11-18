package nsl.utils;

import java.io.File;
import java.net.URL;


public class ClassPathUtils {

	static private String classPathString=instantClassPath();
	
	private static String instantClassPath(){
		URL url=ClassPathUtils.class.getResource("/");
		String pathString=url.getPath();
		pathString=pathString.substring(1,pathString.length());
		return pathString;
	}
	
	static public String getClassPath(){
		return classPathString;
	}
	
	static public String getAbsPathBaseOnClassPath(String path){
		return classPathString+path;
	}
	
	static public File getFileBaseOnClassPath(String filePath){
		return new File(classPathString+filePath);
	}
	
}
