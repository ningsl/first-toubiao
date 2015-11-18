package toubiao.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nsl.utils.MathUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;

import com.mysql.jdbc.Field;

import toubiao.constant.Dictionary;
import toubiao.controller.ResourceController;
import toubiao.pageModel.FilterCondition;

public class FilterHqlFactory {
	
	static Logger logger = LogManager.getLogger(FilterHqlFactory.class.getName());
	
	/**
	 * 根据list 返回 Hql. like "( ( (prject.department like '%一分%') or (prject.department like '%网优%') ) and ( (prject.classByProsssion like '%移动通信%') or (prject.classByProsssion like '%数据交换%') ) "
	 * element=(prject.department like '%一分%')   part=( (prject.department like '%一分%') or (prject.department like '%网优%') 
	 * @param conditionList
	 * @return
	 */
	public static String buildHqlByList(List<FilterCondition> conditionList){
		
		if(conditionList==null || conditionList.size()==0){
			return "";
		}
		
		String hqlString="";
		for(FilterCondition condition:conditionList){
			String hqlPart=buildHqlByCondition(condition);
		/*	System.out.println("**************"+hqlPart);*/
			if(hqlString.equals("")){
				hqlString=hqlPart;
			}else{
				hqlString+=" and "+hqlPart;
			}
		}
		
		if(!hqlString.equals(""))
			hqlString=" ( "+hqlString+" )";
		
		return hqlString;
	}

	public static String buildHqlByCondition(FilterCondition condition){
		if(condition==null){
			return "";
		}
		String webClassName=condition.getClassName();
		String webField=condition.getClassField();
		List<String> valueList=condition.getValueArray();
		
		if(webClassName==null ||webField==null || valueList==null || valueList.size()==0){
			return "";
		}
		
		Map<String, WebClassField> fieldMap=Dictionary.WEB_CLASS.get(webClassName);
		
		WebClassField field=fieldMap.get(webField);
		
		//如果字典中未记录 webfield,以前台condition 值生成field,默认为type=string
		if(field==null){
			field=new WebClassField();
			field.setModelClassName(webClassName);
			field.setModelFieldName(webField);
			field.setType("string");
		}
		
		String hql="";
		String key=field.getModelClassName()+"."+field.getModelFieldName();
		String operator=field.getFilterOperator();
				
		for(String queryExpression:valueList){
			
			String hqlPart=query2HqlElement(field,queryExpression);
			//String hqlPart=likeOperator(key,queryExpression);
			if(hql.equalsIgnoreCase("")){
				hql=hqlPart;
			}else {
				hql+="or"+hqlPart;
			}
		}
		hql=" ( "+hql+" ) ";
	/*	System.out.println(hql);*/
		return hql;
		
		
		
		/*if(className.equalsIgnoreCase("project") || className.equalsIgnoreCase("department")){
			return projectBuilder(condition);
		}
		
		return "";*/
	}
	
	public static String query2HqlElement(WebClassField field,String queryExpression){
		String key=field.getModelClassName()+"."+field.getModelFieldName();
		String operator=field.getFilterOperator();
		String type=field.getType();
		int index=-1;
		String queryValue;
		String queryValue2;
		queryExpression=queryExpression.trim();
		
		String hqlElement="";
		
		//handle =,1.string 2,number
		if((index=queryExpression.indexOf("="))>-1){
			queryValue=queryExpression.substring(1,queryExpression.length()).trim();
			if(type.equalsIgnoreCase("string")){
				hqlElement=" ("+key+"= '"+queryValue+"') ";
				logger.debug(hqlElement);
				return hqlElement;
			}
			if(type.equalsIgnoreCase("int")){
				int quaryInt=Integer.parseInt(queryValue);
				hqlElement=" ("+key+"="+quaryInt+") ";
				return hqlElement;
			}
			if(type.equalsIgnoreCase("long")){
				long quaryLong=Long.parseLong(queryValue);
				hqlElement=" ("+key+"="+quaryLong+") ";
				return hqlElement;
			}
			if(type.equalsIgnoreCase("float")){
				float quaryFloat=Float.parseFloat(queryValue);
				hqlElement=" ("+key+"="+quaryFloat+") ";
				return hqlElement;
			}
			//如果 type 未赋值 或是其他值得话，当做string 处理
			hqlElement=" ("+key+"= '"+queryValue+"') ";
			return hqlElement;
		}
		
		//handle like
		if((index=queryExpression.indexOf("&"))>-1){
			queryValue=queryExpression.substring(1,queryExpression.length()).trim();
			hqlElement=" ("+key+" like '%"+queryValue+"%' )";
			logger.debug(hqlElement);
			return hqlElement;
		}
		
		//handle > 非数字，不处理，返回""
		if((index=queryExpression.indexOf(">"))>-1){
			queryValue=queryExpression.substring(1,queryExpression.length()).trim();
			if(!MathUtils.isNumeric(queryValue)){//非数字，不处理，返回""
				return "";
			}
			hqlElement=" ("+key+" > "+queryValue+") ";
			logger.debug(hqlElement);
			return hqlElement;
		}
		//handle < 非数字，不处理，返回""
		if((index=queryExpression.indexOf("<"))>-1){
			queryValue=queryExpression.substring(1,queryExpression.length()).trim();
			if(!MathUtils.isNumeric(queryValue)){//非数字，不处理，返回""
				return "";
			}
			hqlElement=" ("+key+" < "+queryValue+") ";
			logger.debug(hqlElement);
			return hqlElement;
		}
		
		//handle [] ,非数，返 ""
		logger.debug(queryExpression);
		if(queryExpression.indexOf("[")>-1 && queryExpression.indexOf("]")>-1){
			queryExpression=queryExpression.replace("[", "");
			queryExpression=queryExpression.replace("]", "");
			System.out.println(queryExpression);
			String[] queryValuses=queryExpression.split(",");
			if(queryValuses.length==2){
				String min=queryValuses[0].trim();
				String max=queryValuses[1].trim();
				System.out.println("min="+min+"********       max="+max);
				if(MathUtils.isNumeric(min) && MathUtils.isNumeric(max)){
					hqlElement=" ("+key+" >= "+min+ " and "+key+" <= "+max+") ";
					
					return hqlElement;
				}
			}
		}
		
		return "";
	}
	
	
	private static String projectBuilder(FilterCondition condition){
		String className=condition.getClassName();
		String classField=condition.getClassField();
		List<String> valueList=condition.getValueArray();
		
		
		String operatorName="like";
		String key=className+"."+classField;
		
		String hql="";
		
		for(String value:valueList){
			String hqlPart=likeOperator(key,value);
			if(hql.equalsIgnoreCase("")){
				hql=hqlPart;
			}else {
				hql+="or"+hqlPart;
			}
		}
		hql=" ( "+hql+" ) ";
	/*	System.out.println(hql);*/
		return hql;
	}
	
	private static String likeOperator(String key,String value){		
		String condition;
		condition=" ("+key+" like '%"+value+"%') ";		
		return  condition;
	}
}
