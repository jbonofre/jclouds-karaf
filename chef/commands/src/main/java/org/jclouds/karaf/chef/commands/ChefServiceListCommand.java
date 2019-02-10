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
package org.jclouds.karaf.chef.commands;

import static org.jclouds.karaf.chef.core.ChefHelper.CHEF_TOKEN;

import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.jclouds.apis.Apis;

@Service
@Command(scope = "chef", name = "service-list", description = "Lists the Chef Services")
public class ChefServiceListCommand extends ChefCommandBase {

    @Override
    public Object execute() throws Exception {
        try {
            System.out.println("Chef APIs:");
            System.out.println("-------------");
            printChefApis(Apis.contextAssignableFrom(CHEF_TOKEN), getChefServices(), System.out);
        } catch (Exception ex) {
            // noop
        }
        return null;
    }
}
