package toubiao.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_project_photo")
@PrimaryKeyJoinColumn(name="id")
@DynamicUpdate(true)
public class TprojectPhoto extends Tphoto {

	private Tproject project;

	private String testFieldString;
	
	private int seqInType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	public Tproject getProject() {
		return project;
	}

	public void setProject(Tproject project) {
		this.project = project;
	}

	public String getTestFieldString() {
		return testFieldString;
	}

	public void setTestFieldString(String testFieldString) {
		this.testFieldString = testFieldString;
	}

	public int getSeqInType() {
		return seqInType;
	}

	public void setSeqInType(int seqInType) {
		this.seqInType = seqInType;
	}
	
	
}
