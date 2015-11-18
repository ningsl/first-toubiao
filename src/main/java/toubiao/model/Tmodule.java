package toubiao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
 * @author nsl
 * 先不使用
 */
public class Tmodule {
	private String id;
	private String name;
	private String title;
	private Set<Tresource> menuSet;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmodule")
	public Set<Tresource> getMenuSet() {
		return menuSet;
	}
	public void setMenuSet(Set<Tresource> menuSet) {
		this.menuSet = menuSet;
	}
	
	@Id
	@Column(name = "ID", nullable = false, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
