package org.jboss.aerogear.picketbox.rest;

import org.jboss.aerogear.picketbox.model.AeroGearUser;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public interface AuthenticationService {

    @Path("/otp")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(final AeroGearUser aeroGearUser);

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response otpLogin(final AeroGearUser aeroGearUser);

    @Path("/logout")
    @GET
    public void logout();

    @Path("/otp/secret")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSecret();

    @Path("/userinfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo();
}
