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

package org.jboss.aerogear.picketbox.model;

import org.jboss.aerogear.picketbox.auth.AuthenticationKeyProvider;
import org.picketbox.cdi.PicketBoxIdentity;

import javax.inject.Inject;
import java.util.Collection;

public class AeroGearCredential {

    @Inject
    private PicketBoxIdentity identity;

    @Inject
    private AuthenticationKeyProvider provider;

    public String getId() {
        return identity.getUserContext().getUser().getFirstName();
    }

    public Collection<String> getRoles() {
        return identity.getUserContext().getRoleNames();
    }

    public String getKey() {
        return identity.getUserContext().getUser().getKey();
    }

    public String getSecret() {
        return provider.getSecret();
    }

    public String getB32() {
        return provider.getB32();
    }

    public Object getToken() {
        return provider.getToken();
    }
}