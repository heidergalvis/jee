package com.heider.rest;

import com.heider.service.CalculatorService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author hgalvis
 */
@Path("calculate")
public class CalculatorEndpoint {

    @Inject
    private CalculatorService calculatorService;

    @GET
    @Path("doubleOf/{number}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer doubleOf(@PathParam("number") int number) {
        return calculatorService.doubleOf(number);
    }
}
