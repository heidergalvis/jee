package com.heider.rest;

import com.heider.dto.City;
import io.swagger.oas.annotations.Operation;
import io.swagger.oas.annotations.Parameter;
import io.swagger.oas.annotations.media.Content;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.responses.ApiResponse;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

@Path("ocds")
public class OcdsService {

    @PersistenceContext(unitName = "hola")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GET
    @Path("release/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consulta de release por ID",
            description = "Prototipo OCDS implementado por ACCE",
            responses = {
                    @ApiResponse(
                            description = "Release en formato OCDS",
                            content = @Content(schema = @Schema(description = "Raw", type = "json"))),
                    @ApiResponse(responseCode = "400", description = "Invalid ID"),
                    @ApiResponse(responseCode = "400", description = "Release not found")
            }
    )
    public Response getRelease(@Parameter(description = "Release ID", required = true) @PathParam("id") int number) {

        InputStream inputStreamRectangle =
                this.getClass().getResourceAsStream(
                        "/ocds-213czf-000-00001-02-tender.json");
        JSONObject rawRelease = new JSONObject(
                new JSONTokener(inputStreamRectangle));


       // EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hola");
        //EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<City> cities = entityManager.createQuery("select c from City c").getResultList();

        System.out.println("::::::::::::++++++::::::: " + cities.size());
        cities.forEach(
                System.out::println

        );

        return Response.ok(rawRelease.toString()).build();
    }

}
