package com.worldline.fpl.recruitment.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Transaction json representation
 * 
 * @author A525125
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "Transaction", description = "Transaction resource representation")
public class TransactionResponse extends AbstractTransaction implements
		Serializable {

	private static final long serialVersionUID = -2371720864101586328L;

	@NotNull
	@ApiModelProperty(value = "ID", required = true)
	private String id;
	
	private BigDecimal balance; 
	
	private String number;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
	

}
