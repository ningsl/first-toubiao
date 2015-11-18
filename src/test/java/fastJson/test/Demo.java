package fastJson.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import toubiao.pageModel.Designer;

public class Demo {

	@Test
	public void featureTest(){
		Designer designer=new Designer();
		designer.setName("a");
		designer.setAge(20);
		
		Map<String, Designer> map=new HashMap<String, Designer>();
		map.put("a",designer);
		map.put("b",designer);
		
		SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;  
		
		
		
		/* byte[] bytes = JSON.toJSONBytes(map); */
		byte[] bytes = JSON.toJSONBytes(map,feature);  
		 System.out.println(new String(bytes));  
	}
}
