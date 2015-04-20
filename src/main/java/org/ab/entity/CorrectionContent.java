package org.ab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="CorrectionContent")
public class CorrectionContent {

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "correction"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "correctionId", unique = true, nullable = false)
	private Integer correctionId;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Correction correction;

	@Column(name="correctionHtml")
	private String correctionHtml;

	public Correction getCorrection() {
		return correction;
	}

	public String getCorrectionHtml() {
		return correctionHtml;
	}

	public Integer getCorrectionId() {
		return correctionId;
	}

	public void setCorrection(final Correction correction) {
		this.correction = correction;
	}

	public void setCorrectionHtml(final String correctionHtml) {
		this.correctionHtml = correctionHtml;
	}

	public void setCorrectionId(final Integer correctionId) {
		this.correctionId = correctionId;
	}

}
