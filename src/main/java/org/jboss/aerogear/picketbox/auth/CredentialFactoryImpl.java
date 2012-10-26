package org.jboss.aerogear.picketbox.auth;

import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketbox.core.authentication.credential.OTPCredential;
import org.picketlink.credential.LoginCredentials;

import javax.inject.Inject;

public class CredentialFactoryImpl implements CredentialFactory {

    private Object credential;

    @Inject
    private LoginCredentials loginCredentials;

    public void setCredential(AeroGearUser user) {
        this.credential = new OTPCredential(user.getId(), user.getPassword(), user.getOtp());
        loginCredentials.setCredential(this);
    }

    @Override
    public Object getValue() {
        return credential;
    }
}
