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

package org.jboss.aerogear.security.picketbox.idm;

import org.jboss.aerogear.security.auth.LoggedUser;
import org.jboss.aerogear.security.idm.AeroGearCredential;
import org.picketbox.cdi.PicketBoxIdentity;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Set;

/**
 * Represents the current logged in Credential
 */
public class AeroGearCredentialImpl implements AeroGearCredential {

    @Inject
    private PicketBoxIdentity identity;

    /**
     * Represents the current {@link org.jboss.aerogear.security.model.AeroGearUser} logged in.
     */
    @Produces
    @LoggedUser
    public String getId() {
        String id = null;
        if (identity.isLoggedIn()) {
            id = identity.getUserContext().getUser().getId();
        }
        return id;
    }

    /**
     * Role validation against the IDM
     * @param roles
     * @return
     */
    @Override
    public boolean hasRoles(Set<String> roles) {

        boolean hasRoles = false;

        if (identity.isLoggedIn()) {
            hasRoles = identity.getUserContext().getRoleNames().containsAll(roles);
        }

        return hasRoles;
    }
}