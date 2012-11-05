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

import org.jboss.aerogear.security.model.AeroGearUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.picketbox.core.authentication.credential.OTPCredential;
import org.picketlink.credential.LoginCredentials;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CredentialFactoryTest {

    @Mock
    private LoginCredentials loginCredentials;

    @InjectMocks
    private CredentialFactoryImpl credentialFactory;

    @Before
    public void setUp() throws Exception {
        credentialFactory = new CredentialFactoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    private AeroGearUser buildUser(String username, String password, String otp){
        AeroGearUser aeroGearUser = new AeroGearUser();
        aeroGearUser.setId(username);
        aeroGearUser.setPassword(password);
        aeroGearUser.setEmail(username + "@doe.com");
        aeroGearUser.setOtp(otp);
        return aeroGearUser;
    }

    @Test
    public void testGetSimpleCredential() throws Exception {
        credentialFactory.setCredential(buildUser("john", "123", null));
        OTPCredential credential = (OTPCredential) credentialFactory.getValue();
        assertEquals("john", credential.getUserName());
    }

    @Test
    public void testGetOtpCredential() throws Exception {
        credentialFactory.setCredential(buildUser("john", "123", "myOtp"));
        OTPCredential credential = (OTPCredential) credentialFactory.getValue();
        assertEquals("myOtp", credential.getOtp());
    }
}
