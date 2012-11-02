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

package org.jboss.aerogear.security.picketbox.spi.auth;

import org.jboss.aerogear.security.idm.AuthenticationKeyProvider;
import org.jboss.aerogear.security.auth.AuthenticationSecretKeyCode;
import org.jboss.aerogear.security.util.Hex;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketbox.core.util.Base32;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@SessionScoped
public class AuthenticationKeyProviderImpl implements AuthenticationKeyProvider, Serializable {

    @Inject
    private IdentityManager identityManager;

    @Inject
    private PicketBoxIdentity identity;

    private String secret;
    private User idmuser;

    @PostConstruct
    public void init() {
        if (identity.isLoggedIn()) {
            User user = identity.getUserContext().getUser();
            idmuser = identityManager.getUser(user.getKey());

            secret = idmuser.getAttribute("serial");
        }
    }

    public String getSecret() {

        if (secret == null) {
            secret = AuthenticationSecretKeyCode.create();
            idmuser.setAttribute("serial", secret);
        }
        return secret;
    }

    public String getToken() {
        String token = null;
        if (identity.isLoggedIn())
            token = identity.getUserContext().getSession().getId().getId().toString();

        return token;
    }

    public String getB32() {
        return Base32.encode(Hex.toAscii(getSecret()).getBytes());
    }
}
