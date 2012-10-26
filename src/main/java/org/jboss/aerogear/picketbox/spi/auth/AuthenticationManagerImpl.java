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

package org.jboss.aerogear.picketbox.spi.auth;

import org.jboss.aerogear.picketbox.auth.CredentialFactory;
import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.exception.ExceptionMessage;
import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketbox.cdi.PicketBoxIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuthenticationManagerImpl implements AuthenticationManager {

    @Inject
    private PicketBoxIdentity identity;

    @Inject
    private CredentialFactory credentialFactory;

    public boolean login(AeroGearUser aeroGearUser) {

        credentialFactory.setCredential(aeroGearUser);

        identity.login();

        if (!identity.isLoggedIn())
            ExceptionMessage.AUTHENTICATION_FAILED.throwException();

        return true;

    }

    public void logout() {
        if (identity.isLoggedIn()) {
            identity.logout();
        }
    }
}
