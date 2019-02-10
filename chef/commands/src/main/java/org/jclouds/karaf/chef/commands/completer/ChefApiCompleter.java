/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.karaf.chef.commands.completer;

import static org.jclouds.karaf.chef.core.ChefHelper.CHEF_TOKEN;

import java.util.List;

import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.karaf.shell.api.console.CommandLine;
import org.apache.karaf.shell.api.console.Completer;
import org.apache.karaf.shell.api.console.Session;
import org.apache.karaf.shell.support.completers.StringsCompleter;
import org.jclouds.apis.ApiMetadata;
import org.jclouds.apis.Apis;
import org.jclouds.chef.ChefApi;
import org.jclouds.rest.ApiContext;

@Service
public class ChefApiCompleter implements Completer {

    private final StringsCompleter delegate = new StringsCompleter();

    @Reference
    private List<ApiContext<ChefApi>> chefServices;

    private final boolean displayApisWithoutService;

    public ChefApiCompleter(boolean displayApisWithoutService) {
        this.displayApisWithoutService = displayApisWithoutService;
    }

    @Override
    public int complete(Session session, CommandLine commandLine, List<String> candidates) {
        try {
            if (displayApisWithoutService) {
                for (ApiMetadata apiMetadata : Apis.contextAssignableFrom(CHEF_TOKEN)) {
                    delegate.getStrings().add(apiMetadata.getId());
                }
            } else if (chefServices != null) {
                for (ApiContext<ChefApi> ctx : chefServices) {
                    String api = ctx.getId();
                    if (Apis.withId(api) != null) {
                        delegate.getStrings().add(api);
                    }
                }
            }
        } catch (Exception ex) {
            // noop
        }
        return delegate.complete(session, commandLine, candidates);
    }

}
