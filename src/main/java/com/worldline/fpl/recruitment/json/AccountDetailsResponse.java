package com.worldline.fpl.recruitment.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Account details json representation
 * 
 * @author A525125
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "AccountDetails", description = "Account details resource representation")
public class AccountDetailsResponse extends AccountResponse implements
		Serializable {

	private static final long serialVersionUID = -6427772324577115079L;

	@NotNull
	@ApiModelProperty(value = "Creation date", required = true)
	private Date creationDate;

	@NotNull
	@ApiModelProperty(value = "Activation status of the account", required = true)
	private boolean active;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
