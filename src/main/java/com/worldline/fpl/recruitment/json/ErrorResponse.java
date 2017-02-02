package com.worldline.fpl.recruitment.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Error response
 * 
 * @author A525125
 *
 */
@Data
@AllArgsConstructor
@ApiModel(value = "ErrorResponse", description = "Error resource representation")
public class ErrorResponse implements Serializable {


	private static final long serialVersionUID = 4372672070020226566L;

	@NotNull
	@ApiModelProperty(value = "Error code", required = true, example = "<errorcode>", dataType = "java.lang.String")
	private ErrorCode errorCode;

	@ApiModelProperty(value = "Error message")
	private String errorMessage;
}
