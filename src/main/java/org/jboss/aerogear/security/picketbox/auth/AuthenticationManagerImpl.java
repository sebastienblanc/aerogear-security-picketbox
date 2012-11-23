/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.aerogear.security.picketbox.auth;

import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.auth.CredentialFactory;
import org.jboss.aerogear.security.exception.AeroGearSecurityException;
import org.jboss.aerogear.security.exception.HttpStatus;
import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketbox.cdi.PicketBoxIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class AuthenticationManagerImpl implements AuthenticationManager {

    @Inject
    private PicketBoxIdentity identity;

    //TODO
    @Inject
    private CredentialFactory credentialFactory;

    private static final Logger LOGGER = Logger.getLogger(AuthenticationManagerImpl.class.getName());

    public boolean login(AeroGearUser aeroGearUser) {

        credentialFactory.setCredential(aeroGearUser);

        identity.login();

        if (!identity.isLoggedIn())
            throw new AeroGearSecurityException(HttpStatus.AUTHENTICATION_FAILED);


        LOGGER.info("IS LOGGED IN: " + identity.isLoggedIn());
        LOGGER.info("IS LOGGED IN: " + identity.getUserContext().getUser().getId());

        return true;

    }

    public void logout() {
        if (identity.isLoggedIn()) {
            identity.logout();
        }
    }

//    @ApplicationScoped
//    @Produces
//    @Token
//    public String getToken() {
//        return identity.getUserContext().getUser().getId();
//    }
}
