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

package org.jboss.aerogear.picketbox.model;

import org.picketbox.cdi.PicketBoxIdentity;
import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.User;

import javax.inject.Inject;
import java.util.Set;

public class AeroGearUser extends AbstractIdentityType implements User {

    @Inject
    private PicketBoxIdentity identity;

    private String id;
    private String firstName;
    private String otp;
    private String password;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getKey() {
        return String.format("%s%s", KEY_PREFIX, id);
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean hasRoles(Set<String> roles) {

        boolean hasRoles = false;

        if (identity.isLoggedIn()) {
            hasRoles = identity.getUserContext().getRoleNames().containsAll(roles);
        }

        return hasRoles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;    
    }

    //It should be removed on PicketLink IDM
    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public void setLastName(String s) {

    }

    @Override
    public String getFullName() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public void setEmail(String s) {

    }

}