package toubiao.pageModel;

public class ProjectPhoto extends Photo{

	private Long projectId;
	
	private int seqInType;

	public int getSeqInType() {
		return seqInType;
	}

	public void setSeqInType(int seqInType) {
		this.seqInType = seqInType;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	
}
