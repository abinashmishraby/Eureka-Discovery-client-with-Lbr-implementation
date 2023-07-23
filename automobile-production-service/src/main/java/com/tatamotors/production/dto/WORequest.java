package com.tatamotors.production.dto;

import lombok.Data;

@Data
public class WORequest {

	private String partnerName;
	private String itemNo;
	private int quantity;
}
