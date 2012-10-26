package org.jboss.aerogear.picketbox.spi.idm;

import org.jboss.aerogear.security.idm.AeroGearPrincipal;
import org.picketbox.cdi.PicketBoxIdentity;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Set;

public class AeroGearPrincipalImpl implements AeroGearPrincipal {

    @Inject
    private PicketBoxIdentity identity;

    @Override
    public boolean hasRoles(Set<String> roles) {

        boolean hasRoles = false;

        if (identity.isLoggedIn()) {
            hasRoles = identity.getUserContext().getRoleNames().containsAll(roles);
        }

        return hasRoles;
    }

    @Override
    public Collection<String> getRoles() {
        return identity.getUserContext().getRoleNames();
    }
}
