package com.ineuron.resources;

import com.codahale.metrics.annotation.Timed;
import com.ineuron.api.NLPResponse;
import com.ineuron.domain.nlp.service.NLPService;
import com.ineuron.domain.nlp.valueobject.ProductSelection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/nlp")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class NLPResource {


	private static final Logger LOGGER = LoggerFactory.getLogger(NLPResource.class);

	public NLPResource() {
		super();
	}

	@Path("/")
	@GET
	@Timed
	public Response getProductCategoryById(@QueryParam("text") String text) {
		try {
			NLPResponse response = new NLPResponse();
			ProductSelection parsedResult = NLPService.getInstance().parseText(text);
			response.setValue(parsedResult);
			response.setOriginalText(text);
			return Response.ok(response).build();
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}


}
