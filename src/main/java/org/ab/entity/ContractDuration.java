package org.ab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ContractDuration")
public class ContractDuration {

	@Id
	@Column(name="duration_idn")
	private String durationIdn;

	@Column(name="duration_description")
	private String durationDescription;

	public String getDurationIdn() {
		return durationIdn;
	}

	public String getDurationDescription() {
		return durationDescription;
	}

	private void setDurationIdn(final String durationIdn) {
		this.durationIdn = durationIdn;
	}

	private void setDurationDescription(final String durationDescription) {
		this.durationDescription = durationDescription;
	}
}
