package toubiao.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 *<br><b>类描述:</b>
 *<pre>所示PO的父类</pre>
 *@see
 *@since
 */

public class BaseModel implements Serializable
{
    
	/**
	 * 按属性打印
	 * @param 
	 */
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}