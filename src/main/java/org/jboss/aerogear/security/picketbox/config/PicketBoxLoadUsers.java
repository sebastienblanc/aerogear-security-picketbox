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

package org.jboss.aerogear.security.picketbox.config;

import org.picketbox.core.identity.impl.JPAIdentityStoreContext;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.credential.PlainTextPassword;
import org.picketlink.idm.model.Role;
import org.picketlink.idm.model.SimpleRole;
import org.picketlink.idm.model.SimpleUser;
import org.picketlink.idm.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Singleton
@Startup
public class PicketBoxLoadUsers {


    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Inject
    private IdentityManager identityManager;

    /**
     * <p>Loads some users during the first construction.</p>
     */
    //TODO this entire initialization code will be removed
    @PostConstruct
    public void create() {
        JPAIdentityStoreContext.set(this.entityManager);

        User user = new SimpleUser("john");

        user.setEmail("john@doe.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        /*
         * Disclaimer: PlainTextPassword will encode passwords in SHA-512 with SecureRandom-1024 salt
         * See http://lists.jboss.org/pipermail/security-dev/2013-January/000650.html for more information
         */
        this.identityManager.updateCredential(user, new PlainTextPassword("123"));

        Role roleDeveloper = new SimpleRole("simple");
        Role roleAdmin = new SimpleRole("admin");

        this.identityManager.add(roleDeveloper);
        this.identityManager.add(roleAdmin);

        identityManager.grantRole(user, roleDeveloper);
        identityManager.grantRole(user, roleAdmin);

        JPAIdentityStoreContext.clear();
    }

}
