package FilterHqlTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import toubiao.pageModel.FilterCondition;
import toubiao.utils.FilterHqlFactory;

public class Demo1 {

	@Test
	public void buildHqlByConditionTest(){
		FilterCondition condition=new FilterCondition();
		condition.setClassName("project");
		condition.setClassField("deparment");
	
		List<String> list=new ArrayList<>();
		list.add("1分");
		list.add("2分");
		
		condition.setValueArray(list);
		
		String conditionString=FilterHqlFactory.buildHqlByCondition(condition);
		System.out.println(conditionString);
	}
	
	@Test
	public void buildHqlByListTest(){
		FilterCondition condition=new FilterCondition();
		condition.setClassName("project");
		condition.setClassField("department");
	
		List<String> list=new ArrayList<>();
		list.add("1分");
		list.add("2分");
		
		condition.setValueArray(list);
		
		List<FilterCondition> filterList=new ArrayList<>();
		filterList.add(condition);
		filterList.add(condition);
		
		String conditionString=FilterHqlFactory.buildHqlByList(filterList);
		System.out.println(conditionString);
		
	}
}
