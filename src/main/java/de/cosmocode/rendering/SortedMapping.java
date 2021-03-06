/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.cosmocode.rendering;

import java.util.SortedMap;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingSortedMap;
import com.google.common.collect.Maps;

import de.cosmocode.commons.reflect.Reflection;

/**
 * {@link Mapping} implementation based on a {@link SortedMap} which assumes the underlying
 * map using a hierarchy based sorting as provided by {@link Reflection#orderByHierarchy()}.
 *
 * @deprecated use {@link SuperClassMapping}
 * @since 1.1
 * @author Willi Schoenborn
 */
@Deprecated
public final class SortedMapping extends ForwardingSortedMap<Class<?>, ValueRenderer<?>> implements Mapping {

    private final SortedMap<Class<?>, ValueRenderer<?>> renderers;

    public SortedMapping() {
        this.renderers = Maps.newTreeMap(Reflection.orderByHierarchy());
    }
    
    public SortedMapping(SortedMap<Class<?>, ValueRenderer<?>> renderers) {
        this.renderers = Preconditions.checkNotNull(renderers, "Renderers");
    }
    
    @Override
    protected SortedMap<Class<?>, ValueRenderer<?>> delegate() {
        return renderers;
    }
    
    @Override
    public <T> ValueRenderer<T> find(Class<? extends T> type) {
        Preconditions.checkNotNull(type, "Type");
        for (Entry<Class<?>, ValueRenderer<?>> entry : renderers.entrySet()) {
            if (entry.getKey().isAssignableFrom(type)) {
                @SuppressWarnings("unchecked")
                final ValueRenderer<T> renderer = (ValueRenderer<T>) entry.getValue();
                return renderer;
            }
        }
        return null;
    }

}
