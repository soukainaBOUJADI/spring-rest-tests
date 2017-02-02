package com.worldline.fpl.recruitment.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Account json representation
 * 
 * @author A525125
 *
 */
@Data
@ApiModel(value = "Account", description = "Account resource representation")
public class AccountResponse implements Serializable {

	private static final long serialVersionUID = 1311670657098492357L;

	@NotNull
	@ApiModelProperty(value = "ID", required = true)
	private String id;

	@NotNull
	@ApiModelProperty(value = "Number", required = true)
	private String number;

	@NotNull
	@ApiModelProperty(value = "Type", required = true)
	private String type;

	@NotNull
	@ApiModelProperty(value = "Balance", required = true)
	private BigDecimal balance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	

}
