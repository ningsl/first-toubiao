package toubiao.utils.excel;

/**
 * 记录class field 2 excel column information
 * @author nsl
 *
 */
public class FieldInfo  implements Comparable<FieldInfo>{

	private String calssName;
	private String fieldName;
	private String columnName;
	private int seq;
	
	public String getCalssName() {
		return calssName;
	}
	public void setCalssName(String calssName) {
		this.calssName = calssName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	@Override
	public int compareTo(FieldInfo field) {
		// TODO Auto-generated method stub
		 if (this.seq < field.seq)
	            return -1;
	        else if (this.seq > field.seq)
	            return 1;
	        else
	            return 0;
	}
}
