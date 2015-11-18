package toubiao.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "t_department")
public class Tdepartment {

	private Long id;
	
	private String status;
	
	private String name;
	
	private int seq;
	
	private List<Tdesigner> designerList=new ArrayList<Tdesigner>();
	
	private Set<Tproject>  projectSet=new HashSet<Tproject>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	public List<Tdesigner> getDesignerList() {
		return designerList;
	}

	public void setDesignerList(List<Tdesigner> designerList) {
		this.designerList = designerList;
	}
	
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_department_project", joinColumns = { @JoinColumn(name = "department_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "project_id", nullable = false) })
	public Set<Tproject> getProjectSet() {
		return projectSet;
	}

	public void setProjectSet(Set<Tproject> projectSet) {
		this.projectSet = projectSet;
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
