package org.jboss.aerogear.picketbox.authz;

import org.jboss.aerogear.security.authz.AuthorizationManager;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketlink.authentication.AuthenticationException;

import javax.inject.Inject;

public class AuthorizationManagerImpl implements AuthorizationManager {

    @Inject
    private PicketBoxIdentity identity;

    @Override
    public boolean validate(String token) {

        boolean valid = false;

        if (token != null && !token.isEmpty()) {
            try {
                valid = identity.restoreSession(token);
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }

        return valid;

    }
}
