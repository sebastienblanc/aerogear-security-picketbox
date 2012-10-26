package org.jboss.aerogear.picketbox.util;

import org.jboss.aerogear.picketbox.model.AeroGearCredential;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class HttpResponse {

    @Inject
    private AeroGearCredential credentials;

    private static final String AUTH_HEADER = "Auth-Token";

    public Response createResponse() {
        return Response.ok(credentials).header(AUTH_HEADER, credentials.getToken()).build();
    }

    public Response buildSecretUserInfoResponse() {
        return Response.ok(credentials).header(AUTH_HEADER, credentials.getToken()).build();
    }
}