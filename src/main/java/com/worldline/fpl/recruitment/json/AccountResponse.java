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
	private Long id;

	@NotNull
	@ApiModelProperty(value = "Number", required = true)
	private String number;

	@NotNull
	@ApiModelProperty(value = "Type", required = true)
	private String type;

	@NotNull
	@ApiModelProperty(value = "Balance", required = true)
	private BigDecimal balance;

}
