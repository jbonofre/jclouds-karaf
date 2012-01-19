/*
 * Copyright (C) 2011, the original authors
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.karaf.commands.blobstore.completer;

import org.jclouds.blobstore.BlobStore;
import org.jclouds.karaf.commands.cache.CacheProvider;

public class ContainerCompleter extends BlobStoreCompleterSupport {

    public ContainerCompleter() {
        cache = CacheProvider.getCache("container");
    }


    @Override
    public void updateCache(BlobStore blobStore) {
        cache.addAll(listContainers(blobStore));
    }
}