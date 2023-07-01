package org.gameday.resource;

import  org.gameday.model.Team;
import  org.gameday.service.TeamService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.math.BigDecimal;
import java.util.List;

@Path("/team")
public class TeamResource {

    @Inject
    TeamService service;

    @GET
    @Path("/findAll")
    public List<Team> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/find/{teamID}")
    public Team findByID(String ID) {
        return service.findByID(ID);
    }
    //find by description
    @GET
    @Path("/findByDescription/{description}")
    public List<Team> findByDescription(String description) {
        return service.findByDescription(description);
    }

    @POST
    public void addEntry(Team team) throws Exception {
        service.addEntry(team);
    }



}

