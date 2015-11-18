package toubiao.constant;


import java.net.URL;



public class Web {
	public final static String WEB_ABS_PATH=getAbsPath();
	
	public final static String WEB_DOWNLOAD_PATH=getWebDownloadPath();
	
	
	//
	static private String getAbsPath(){
		URL url=Web.class.getResource("/");
		
		String pathString=url.getPath();
		pathString=pathString.substring(1, pathString.length()-1);
		
		/*File file=new File(pathString);*/
		
		/*System.out.println(pathString);*/
		/*System.out.println(pathString);*/
		
		/*pathString=pathString.replaceAll("\\", "/");*/
		
		return "Y:/EclipseProject/RecentlyProject/toubiao/src/main/webapp";
	}


	private static String getWebDownloadPath() {
		// TODO Auto-generated method stub
		String webPath=getAbsPath();
		
		return webPath+"/WEB-INF/download";
	}
}
