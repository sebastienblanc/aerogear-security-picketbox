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

import org.abstractj.cuckootp.api.Base32;
import org.jboss.aerogear.security.idm.AuthenticationKeyProvider;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketbox.core.UserContext;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.model.User;

import javax.inject.Inject;

public class AuthenticationKeyProviderImpl implements AuthenticationKeyProvider {

    private static final String IDM_SECRET_ATTRIBUTE = "serial";
    private User user;
    private UserContext context;

    @Inject
    public AuthenticationKeyProviderImpl(IdentityManager identityManager, PicketBoxIdentity identity) {
        if (identity.isLoggedIn()) {
            context = identity.getUserContext();
            user = identityManager.getUser(context.getUser().getId());
        }
    }

    public String getToken() {
        return context.getSession().getId().getId().toString();
    }

    public String getSecret() {

        String secret = user.getAttribute(IDM_SECRET_ATTRIBUTE);

        if (secret == null) {
            secret = new Base32().random();
            context.getUser().setAttribute(IDM_SECRET_ATTRIBUTE, secret);
        }
        return secret;
    }
}
