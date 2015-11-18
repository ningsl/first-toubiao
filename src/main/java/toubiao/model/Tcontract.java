package toubiao.model;

import java.util.HashSet;
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
import javax.persistence.Table;

@Entity
@Table(name = "t_contract")
public class Tcontract {
	
	
	private Long id;
	
	private float amount;
	
	private String sn;
	
	private String name;
	
	private Set<Tproject>  projectSet=new HashSet<>();

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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_project_contract", joinColumns = { @JoinColumn(name = "contract_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "project_id", nullable = false) })
	public Set<Tproject> getProjectSet() {
		return projectSet;
	}

	public void setProjectSet(Set<Tproject> projectSet) {
		this.projectSet = projectSet;
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
	
	
	
}
