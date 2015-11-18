package toubiao.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import toubiao.pageModel.Photo;

@Entity
@Table(name = "t_certificate_photo")
@PrimaryKeyJoinColumn(name="id")
@DynamicUpdate(true)
public class TcertificatePhoto extends Tphoto{
	private String certificateName;
	private String certificateSn;
	private Tdesigner designer;
	private int seqInType;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "designer_id")
	public Tdesigner getDesigner() {
		return designer;
	}
	public void setDesigner(Tdesigner designer) {
		this.designer = designer;
	}
	
	@Override
	public String toString() {
		return "CertificatePic [certificateName=" + certificateName
				+ ", certificateSn=" + certificateSn + ", designer=" + designer
				+ "]";
	}
	
	
	public int getSeqInType() {
		return seqInType;
	}
	public void setSeqInType(int seqInType) {
		this.seqInType = seqInType;
	}
	
	
	
}
