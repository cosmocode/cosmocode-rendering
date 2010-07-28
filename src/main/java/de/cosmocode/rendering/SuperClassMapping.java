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

import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingMap;

import de.cosmocode.commons.reflect.Reflection;

/**
 * {@link Mapping} implementation which traverses the object hierarchy when
 * {@link Mapping#find(Class)} is called to find the most appropriate type mapping.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public final class SuperClassMapping extends ForwardingMap<Class<?>, ValueRenderer<?>> implements Mapping {

    private final Map<Class<?>, ValueRenderer<?>> renderers;

    public SuperClassMapping(Map<Class<?>, ValueRenderer<?>> renderers) {
        this.renderers = Preconditions.checkNotNull(renderers, "Renderers");
    }

    @Override
    protected Map<Class<?>, ValueRenderer<?>> delegate() {
        return renderers;
    }
    
    @Override
    public <T> ValueRenderer<T> find(Class<? extends T> type) {
        Preconditions.checkNotNull(type, "Type");
        final ValueRenderer<?> classRenderer = findForClass(type);
        if (classRenderer == null) {
            @SuppressWarnings("unchecked")
            final ValueRenderer<T> renderer = (ValueRenderer<T>) findForInterface(type);
            return renderer;
        } else {
            @SuppressWarnings("unchecked")
            final ValueRenderer<T> renderer = (ValueRenderer<T>) classRenderer;
            return renderer;
        }
    }
    
    private ValueRenderer<?> findForClass(Class<?> type) {
        if (Object.class.equals(type)) return null;
        final ValueRenderer<?> renderer = renderers.get(type);
        if (renderer == null) {
            return findForClass(type.getSuperclass());
        } else {
            return renderer;
        }
    }

    // traverses the inheritence tree in level order
    private ValueRenderer<?> findForInterface(Class<?> type) {
        final Iterable<Class<?>> interfaces = Reflection.getAllInterfaces(type);
        for (Class<?> iface : interfaces) {
            final ValueRenderer<?> renderer = renderers.get(iface);
            if (renderer == null) continue;
            return renderer;
        }
        return renderers.get(Object.class);
    }
    
}
