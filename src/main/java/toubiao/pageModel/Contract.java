package toubiao.pageModel;

import java.util.HashSet;
import java.util.Set;

import toubiao.model.Tproject;

public class Contract {
	
	private Long id;
	
	private float amount;
	
	private String sn;
	
	private String name;
	
	private Set<Project>  projectSet=new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Project> getProjectSet() {
		return projectSet;
	}

	public void setProjectSet(Set<Project> projectSet) {
		this.projectSet = projectSet;
	}
	
	

}
