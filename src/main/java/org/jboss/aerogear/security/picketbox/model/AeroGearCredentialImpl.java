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

import org.jboss.aerogear.security.idm.AeroGearPrincipal;
import org.jboss.aerogear.security.idm.AuthenticationKeyProvider;
import org.jboss.aerogear.security.model.AeroGearCredential;
import org.picketbox.cdi.PicketBoxIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

public class AeroGearCredentialImpl implements AeroGearCredential {

    private String id;
    private String key;
    private String secret;
    private String b32;
    private String token;
    private Collection<String> roles;

    @Inject
    public AeroGearCredentialImpl(AuthenticationKeyProvider provider, AeroGearPrincipal principal, PicketBoxIdentity identity) {
        this.id = identity.getUserContext().getUser().getId();
        this.key = identity.getUserContext().getUser().getKey();
        this.secret = provider.getSecret();
        this.b32 = provider.getB32();
        this.token = provider.getToken();
        this.roles = principal.getRoles();
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }

    public String getB32() {
        return b32;
    }

    public String getToken() {
        return token;
    }

    public Collection<String> getRoles() {
        return roles;
    }
}