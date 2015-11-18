package nsl.utils;

import java.util.Map;

import toubiao.constant.Dictionary;
import toubiao.pageModel.PageHelper;
import toubiao.utils.WebClassField;

/**
 * EasyUI 1.33工具类
 * @author nsl
 *
 */
public class EasyUI133Utils {
	
	private PageHelper defaultPageHelper=new PageHelper();

	/**
	 * @param ph        PageHelper
	 * @return
	 */
	public static String buildOrderHql(PageHelper ph){
		String orderString = "";
		String webClassName=ph.getWebClass().trim();
		String webField=ph.getSort().trim();
		String order=ph.getOrder();
		
		if(webClassName==null || webField==null || webClassName.equals("") || webField.equals(""))
			return "";
	
		Map<String, WebClassField> fieldMap=Dictionary.WEB_CLASS.get(ph.getWebClass());
		
		WebClassField field=fieldMap.get(webField);
		
		//如果字典中未记录 webfield,以前台webClass   Field 
		if(order==null || order.equals("")){
			order="ASC";
		}
		
		if(field==null){
			orderString = " order by "+webClassName+"." + webField + " " + order;
		}else{
			orderString = " order by "+field.getModelClassName()+"." + field.getModelFieldName() + " " + order;
		}
		return orderString;
	}
}
