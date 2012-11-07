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

import org.jboss.aerogear.security.idm.AeroGearPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketbox.core.UserContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AeroGearPrincipalTest {

    @Mock
    private PicketBoxIdentity identity;

    @Mock
    private UserContext userContext;

    @InjectMocks
    private AeroGearPrincipal aeroGearPrincipal;

    @Before
    public void setUp() {
        aeroGearPrincipal = new AeroGearPrincipalImpl();
        MockitoAnnotations.initMocks(this);
        Collection<String> roleNames = Arrays.asList("manager", "developer");
        when(userContext.getRoleNames()).thenReturn(roleNames);
        when(identity.getUserContext()).thenReturn(userContext);
        when(identity.isLoggedIn()).thenReturn(true);
    }


    @Test
    public void testHasRoles() throws Exception {
        Set<String> roles = new HashSet<String>(Arrays.asList("manager", "developer"));
        assertTrue(aeroGearPrincipal.hasRoles(roles));
    }

    @Test
    public void testRoleNotFound() throws Exception {
        Set<String> roles = new HashSet<String>(Arrays.asList("guest"));
        assertFalse(aeroGearPrincipal.hasRoles(roles));
    }

    @Test
    public void testGetRoles() throws Exception {
        assertEquals(2, aeroGearPrincipal.getRoles().size());
    }
}
