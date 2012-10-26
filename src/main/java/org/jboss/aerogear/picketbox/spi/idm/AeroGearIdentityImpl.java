package org.jboss.aerogear.picketbox.spi.idm;

import org.jboss.aerogear.security.idm.AeroGearIdentity;
import org.picketbox.cdi.PicketBoxIdentity;

import javax.inject.Inject;

public class AeroGearIdentityImpl implements AeroGearIdentity {

    @Inject
    private PicketBoxIdentity identity;

    @Override
    public String getUsername() {
        return identity.getUserContext().getUser().getFirstName();
    }

    @Override
    public String getKey() {
        return identity.getUserContext().getUser().getKey();
    }
}
