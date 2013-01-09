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

import org.picketbox.cdi.idm.DefaultJPATemplate;
import org.picketbox.core.config.ConfigurationBuilder;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class PicketBoxConfigurer {

    private static final int TIMEOUT_IN_MINUTES = 30;

    @Inject
    private DefaultJPATemplate jpaTemplate;

    @Produces
    public ConfigurationBuilder produceConfiguration() {
        ConfigurationBuilder builder = new ConfigurationBuilder();

        builder.authentication();

        builder
                .identityManager()
                .jpaStore().template(this.jpaTemplate);

        builder
                .sessionManager()
                .sessionTimeout(TIMEOUT_IN_MINUTES)
                .inMemorySessionStore();

        return builder;
    }

}