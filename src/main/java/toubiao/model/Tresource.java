package toubiao.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author nsl
 * 
 * 有2个自关联字段
 */
@Entity
@Table(name = "tresource")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tresource implements Serializable{

	private String id;
	private Tresourcetype tresourcetype;
	private Tresource parent;
	private String name;
	private String remark;
	private Integer seq;
	private String url;
	private String icon;
	private Set<Trole> troles = new HashSet<Trole>(0);
	private Set<Tresource> childrenSet = new HashSet<Tresource>(0);
	private Tresource module;
	private Set<Tresource> menuSet=new HashSet<Tresource>();
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID")
	public Tresource getModule() {
		return module;
	}

	
	public void setModule(Tresource module) {
		this.module = module;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	public Set<Tresource> getMenuSet() {
		return menuSet;
	}

	public void setMenuSet(Set<Tresource> menuSet) {
		this.menuSet = menuSet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public Tresource getParent() {
		return parent;
	}

	public void setParent(Tresource parent) {
		this.parent = parent;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
	public Set<Tresource> getChildrenSet() {
		return childrenSet;
	}

	public void setChildrenSet(Set<Tresource> children) {
		this.childrenSet = children;
	}



	public Tresource() {
	}

	
	@Id
	@Column(name = "ID", nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRESOURCETYPE_ID", nullable = false)
	public Tresourcetype getTresourcetype() {
		return this.tresourcetype;
	}

	public void setTresourcetype(Tresourcetype tresourcetype) {
		this.tresourcetype = tresourcetype;
	}




	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "SEQ")
	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Column(name = "URL", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "ICON", length = 100)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "trole_tresource", joinColumns = { @JoinColumn(name = "TRESOURCE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "TROLE_ID", nullable = false, updatable = false) })
	public Set<Trole> getTroles() {
		return this.troles;
	}

	public void setTroles(Set<Trole> troles) {
		this.troles = troles;
	}

	
	

}
