package utilesTest;

import org.junit.Test;

import toubiao.utils.FilterHqlFactory;
import toubiao.utils.WebClassField;

public class FilterHqlFactroyTest {

	@Test
	public void test1(){
		WebClassField field=new WebClassField();
		String expression[]={"[0,50000]"};
		field.setWebClassName("project");
		field.setWebFieldName("investAmount");
		
		String elementString=FilterHqlFactory.query2HqlElement(field, expression[0]);
		System.out.println(elementString);
	}
	
}
