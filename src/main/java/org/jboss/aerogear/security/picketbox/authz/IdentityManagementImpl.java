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

package org.jboss.aerogear.security.picketbox.authz;


import org.jboss.aerogear.security.authz.IdentityManagement;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * <i>IdentityManagement</i> allows to assign a set of roles to {@link org.jboss.aerogear.security.model.AeroGearUser} on Identity Manager provider
 */
@ApplicationScoped
public class IdentityManagementImpl implements IdentityManagement {

    @Inject
    private GrantConfiguration grantConfiguration;

    /**
     * This method allows to specify which <i>roles</i> must be assigned to {@link org.jboss.aerogear.security.model.AeroGearUser}
     * @param roles The list of roles.
     * @return {@link GrantMethods} is a builder which a allows to apply a list of roles to the specified {@link org.jboss.aerogear.security.model.AeroGearUser}.
     */
    @Override
    public GrantMethods grant(String... roles) {
        return grantConfiguration.roles(roles);
    }
}
