package nsl.utils;

public class ChineseCoding {

	public static String codeFromChinese(String chinese,String charset) throws Exception {
		if(chinese==null || chinese.length()==0 || charset==null || charset.length()==0){
			throw new Exception();
			}
		byte[] bytes=chinese.getBytes();
		String resultString=new String(bytes, charset);
		System.out.println(resultString);
		return resultString;
	}
}
