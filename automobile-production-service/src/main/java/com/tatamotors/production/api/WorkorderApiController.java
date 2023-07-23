package com.tatamotors.production.api;

import java.util.Date;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tatamotors.production.dto.WORequest;
import com.tatamotors.production.dto.WOResponse;

@RestController
@RequestMapping("/workorder")
public class WorkorderApiController {

	@PostMapping(consumes= {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public WOResponse placeOrder(@RequestBody WORequest orderRequest) {
		WOResponse ordeResponse = null;
		
		ordeResponse = new WOResponse();
		ordeResponse.setWorkOrderNo(UUID.randomUUID().toString());
		ordeResponse.setSlaDate(new Date());
		ordeResponse.setStatus("accepted");
		return ordeResponse;
	}
}
