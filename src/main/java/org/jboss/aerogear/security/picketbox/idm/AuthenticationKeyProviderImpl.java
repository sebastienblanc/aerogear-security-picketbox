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

import org.jboss.aerogear.security.auth.Secret;
import org.jboss.aerogear.security.auth.Token;
import org.jboss.aerogear.security.idm.AuthenticationKeyProvider;
import org.jboss.aerogear.security.otp.api.Base32;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.credential.PasswordCredential;
import org.picketlink.idm.model.User;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.logging.Logger;

public class AuthenticationKeyProviderImpl implements AuthenticationKeyProvider {

    private static final String IDM_SECRET_ATTRIBUTE = "serial";

    @Inject
    private IdentityManager identityManager;

    @Inject
    private PicketBoxIdentity identity;

    private static final Logger LOGGER = Logger.getLogger(AuthenticationKeyProviderImpl.class.getName());


    @Produces
    @Token
    public String getToken() {
        String id = null;
        if (identity.isLoggedIn()) {
            id = identity.getUserContext().getSession().getId().getId().toString();
        }
        return id;
    }

    @Produces
    @Secret
    public String getSecret() {

        User user = identity.getUserContext().getUser();

        String secret = user.getAttribute(IDM_SECRET_ATTRIBUTE);

        LOGGER.info("Is there a user? " + user.getId());
        LOGGER.info("Is there a secret? " + secret);

        if (secret == null) {
            secret = Base32.random();
            user.setAttribute(IDM_SECRET_ATTRIBUTE, secret);
            identityManager.updateCredential(user, new PasswordCredential("123"));
        }
        return secret;
    }
}
