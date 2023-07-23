package com.tatamotors.production.dto;

import java.util.Date;

import lombok.Data;

@Data
public class WOResponse {

	private String workOrderNo;
	private Date slaDate;
	private String status;
}
