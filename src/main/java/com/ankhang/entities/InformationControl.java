package com.ankhang.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides({
		@AttributeOverride(name = "createdDate", column = @Column(name="createddate")),
		@AttributeOverride(name = "modifiedDate", column = @Column(name="modifieddate")),
		@AttributeOverride(name = "createdBy", column = @Column(name="createdby")),
		@AttributeOverride(name = "modifiedBy", column = @Column(name="modifiedby"))
})
public class InformationControl {
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;

}

