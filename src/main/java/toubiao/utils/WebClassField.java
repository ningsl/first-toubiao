package toubiao.utils;

public class WebClassField {
	
	private String webClassName;
	private String webFieldName;
	private String modelClassName;
	private String modelFieldName;
	private String filterOperator;
	private String type;//string int long 
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWebClassName() {
		return webClassName;
	}
	public void setWebClassName(String webClassName) {
		this.webClassName = webClassName;
	}
	public String getWebFieldName() {
		return webFieldName;
	}
	public void setWebFieldName(String webFieldName) {
		this.webFieldName = webFieldName;
	}
	public String getModelClassName() {
		return modelClassName;
	}
	public void setModelClassName(String modelClassName) {
		this.modelClassName = modelClassName;
	}
	public String getModelFieldName() {
		return modelFieldName;
	}
	public void setModelFieldName(String modelFieldName) {
		this.modelFieldName = modelFieldName;
	}
	public String getFilterOperator() {
		return filterOperator;
	}
	public void setFilterOperator(String filterOperator) {
		this.filterOperator = filterOperator;
	}
	@Override
	public String toString() {
		return "WebClassField [webClassName=" + webClassName
				+ ", webFieldName=" + webFieldName + ", modelClassName="
				+ modelClassName + ", modelFieldName=" + modelFieldName
				+ ", filterOperator=" + filterOperator + "]";
	}
	
	
}
