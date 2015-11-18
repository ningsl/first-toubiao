package toubiao.pageModel;

import java.util.ArrayList;
import java.util.List;

import toubiao.model.Tdesigner;

public class Department {
	private Long id;
	
	private String status;
	
	private String name;
	
	private int seq;
	
	private List<Designer> designerList=new ArrayList<Designer>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<Designer> getDesignerList() {
		return designerList;
	}

	public void setDesignerList(List<Designer> designerList) {
		this.designerList = designerList;
	}
	
	
}
