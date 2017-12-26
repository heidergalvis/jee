package com.heider.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author hgalvis
 */
@Path("hello")
public class HelloRest {

    @GET
    public String hello() {
        return "Hello Rest!";
    }
}
