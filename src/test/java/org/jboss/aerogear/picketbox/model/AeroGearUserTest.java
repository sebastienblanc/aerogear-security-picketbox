package org.jboss.aerogear.picketbox.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketbox.core.UserContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AeroGearUserTest {

    @InjectMocks
    private AeroGearUser aeroGearUser = new SampleAeroGearUser();
    @Mock
    private PicketBoxIdentity picketBoxIdentity;
    @Mock
    private UserContext userContext;

    private List<String> roleNames = Arrays.asList("manager", "developer");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(userContext.getRoleNames()).thenReturn(roleNames);
        when(picketBoxIdentity.isLoggedIn()).thenReturn(true);
        when(picketBoxIdentity.getUserContext()).thenReturn(userContext);
    }

    @Test
    public void testHasRoles() throws Exception {
        Set<String> roles = new HashSet<String>(roleNames);
        assertTrue(aeroGearUser.hasRoles(roles));
    }

    @Test
    public void testNoRoles() throws Exception {
        Set<String> roles = new HashSet<String>(Arrays.asList("guest"));
        assertFalse(aeroGearUser.hasRoles(roles));
    }

    @Test
    public void testUserNotLoggedIn() throws Exception {
        Set<String> roles = new HashSet<String>(roleNames);
        when(picketBoxIdentity.isLoggedIn()).thenReturn(false);
        assertFalse(aeroGearUser.hasRoles(roles));
    }
}
