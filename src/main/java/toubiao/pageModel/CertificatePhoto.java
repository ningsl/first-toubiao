package toubiao.pageModel;

import toubiao.model.Tdesigner;

public class CertificatePhoto extends Photo{
	private String certificateName;
	private String certificateSn;
	private int seqInType;
	private Long designerId;
	private String designerName;
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	public String getCertificateSn() {
		return certificateSn;
	}
	public void setCertificateSn(String certificateSn) {
		this.certificateSn = certificateSn;
	}
	public int getSeqInType() {
		return seqInType;
	}
	public void setSeqInType(int seqInType) {
		this.seqInType = seqInType;
	}
	public Long getDesignerId() {
		return designerId;
	}
	public void setDesignerId(Long designerId) {
		this.designerId = designerId;
	}
	public String getDesignerName() {
		return designerName;
	}
	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	
	
}
