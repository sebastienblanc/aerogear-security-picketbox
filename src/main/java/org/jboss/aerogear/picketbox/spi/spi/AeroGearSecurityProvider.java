package org.jboss.aerogear.picketbox.spi.spi;

import org.jboss.aerogear.controller.router.Route;
import org.jboss.aerogear.controller.spi.SecurityProvider;
import org.jboss.aerogear.picketbox.model.AeroGearUser;
import org.jboss.aerogear.security.exception.ExceptionMessage;

import javax.inject.Inject;
import javax.servlet.ServletException;

public class AeroGearSecurityProvider implements SecurityProvider {

    @Inject
    private AeroGearUser user;

    @Override
    public void isRouteAllowed(Route route) throws ServletException {

        if (!user.hasRoles(route.getRoles())) {
            ExceptionMessage.AUTHENTICATION_FAILED.throwException();
        }
    }
}
