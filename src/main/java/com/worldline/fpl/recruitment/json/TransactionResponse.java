package com.worldline.fpl.recruitment.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

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
	private Long id;

}
