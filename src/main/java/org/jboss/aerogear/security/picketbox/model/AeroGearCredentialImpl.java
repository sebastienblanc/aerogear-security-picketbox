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

package org.jboss.aerogear.security.picketbox.model;

import org.jboss.aerogear.security.idm.AeroGearIdentity;
import org.jboss.aerogear.security.idm.AeroGearPrincipal;
import org.jboss.aerogear.security.idm.AuthenticationKeyProvider;
import org.jboss.aerogear.security.model.AeroGearCredential;

import javax.inject.Inject;
import java.util.Collection;

public class AeroGearCredentialImpl implements AeroGearCredential {

    @Inject
    private AeroGearIdentity identity;

    @Inject
    private AuthenticationKeyProvider provider;

    @Inject
    private AeroGearPrincipal principal;

    public String getId() {
        return identity.getUsername();
    }

    public String getKey() {
        return identity.getKey();
    }

    public String getSecret() {
        return provider.getSecret();
    }

    public String getB32() {
        return provider.getB32();
    }

    public String getToken() {
        return provider.getToken();
    }

    public Collection<String> getRoles() {
        return principal.getRoles();
    }

}