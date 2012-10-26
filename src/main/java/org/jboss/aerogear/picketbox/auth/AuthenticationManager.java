package org.jboss.aerogear.picketbox.auth;

import org.jboss.aerogear.picketbox.model.AeroGearUser;

public interface AuthenticationManager {

    boolean login(AeroGearUser aeroGearUser);

    void logout();
}
