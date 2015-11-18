package toubiao.pageModel;

import java.util.List;



public class FilterCondition {
	private String className;
	private String classField;
	private List<String> valueArray;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassField() {
		return classField;
	}
	public void setClassField(String classField) {
		this.classField = classField;
	}
	public List<String> getValueArray() {
		return valueArray;
	}
	public void setValueArray(List<String> valueArray) {
		this.valueArray = valueArray;
	}
	
	
}
