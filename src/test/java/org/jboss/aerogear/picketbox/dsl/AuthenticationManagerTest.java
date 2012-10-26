package org.jboss.aerogear.picketbox.dsl;

import org.jboss.aerogear.picketbox.auth.AuthenticationManager;
import org.jboss.aerogear.picketbox.auth.AuthenticationManagerImpl;
import org.jboss.aerogear.picketbox.model.AeroGearUser;
import org.jboss.aerogear.security.exception.AeroGearSecurityException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketlink.credential.LoginCredentials;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthenticationManagerTest {

    @Mock
    private AeroGearUser aeroGearUser;
    @Mock
    private PicketBoxIdentity picketBoxIdentity;
    @Mock
    private LoginCredentials loginCredentials;


    @InjectMocks
    private AuthenticationManager authenticationManager = new AuthenticationManagerImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(aeroGearUser.getId()).thenReturn("john");
        when(aeroGearUser.getPassword()).thenReturn("123");
    }

    @Ignore
    @Test
    public void testLogin() throws Exception {
        when(picketBoxIdentity.isLoggedIn()).thenReturn(true);
        assertTrue(authenticationManager.login(aeroGearUser));
    }

    @Ignore
    @Test(expected = AeroGearSecurityException.class)
    public void testInvalidLogin() throws Exception {
        when(picketBoxIdentity.isLoggedIn()).thenReturn(false);
        authenticationManager.login(aeroGearUser);
    }

    @Ignore
    @Test
    public void testLogout() throws Exception {
        when(picketBoxIdentity.isLoggedIn()).thenReturn(true);
        authenticationManager.logout();
        verify(picketBoxIdentity).logout();
    }
}
