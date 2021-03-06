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

import org.jboss.aerogear.security.model.AeroGearUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.model.SimpleUser;
import org.picketlink.idm.model.User;

import static org.mockito.Mockito.when;

public class GrantConfigurationTest {

    @Mock
    private IdentityManager manager;

    @Mock
    private User user;

    @InjectMocks
    private GrantConfiguration grantConfiguration;

    @Before
    public void setUp() throws Exception {
        grantConfiguration = new GrantConfiguration();
        MockitoAnnotations.initMocks(this);
    }

    private AeroGearUser buildUser(String username) {
        AeroGearUser aeroGearUser = new AeroGearUser();
        aeroGearUser.setId(username);
        aeroGearUser.setEmail(username + "@doe.com");
        aeroGearUser.setPassword("123");
        return aeroGearUser;
    }

    @Test
    public void testGrant() throws Exception {
        AeroGearUser aeroGearUser = buildUser("john");
        String[] role = new String[]{"ADMIN"};
        grantConfiguration.roles(role).to(aeroGearUser);
    }
}
