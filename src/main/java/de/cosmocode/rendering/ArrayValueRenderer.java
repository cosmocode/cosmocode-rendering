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

import java.lang.reflect.Array;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

/**
 * {@link Object}[] {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum ArrayValueRenderer implements ValueRenderer<Object> {

    INSTANCE;
    
    private final Set<Class<?>> longOrLess = ImmutableSet.<Class<?>>of(
        byte.class, short.class, char.class, int.class, long.class
    );
    
    private final Set<Class<?>> doubleOrLess = ImmutableSet.<Class<?>>of(
        float.class, double.class
    );
    
    @Override
    public void render(@Nullable Object array, Renderer r) throws RenderingException {
        if (array == null) {
            r.nullValue();
        } else {
            final Class<?> type = array.getClass();
            assert type.isArray();
            final Class<?> componentType = type.getComponentType();
            
            // to prevent auto-boxing
            if (componentType.isPrimitive()) {
                r.list();
                renderPrimitiveArray(array, componentType, r);
                r.endList();
            } else {
                r.value(Object[].class.cast(array));
            }
        }
    }
    
    private void renderPrimitiveArray(Object array, Class<?> componentType, Renderer r) {
        final int length = Array.getLength(array);
        if (componentType == boolean.class) {
            for (int i = 0; i < length; i++) {
                r.value(Array.getBoolean(array, i));
            }
        } else if (longOrLess.contains(componentType)) {
            for (int i = 0; i < length; i++) {
                r.value(Array.getLong(array, i));
            }
        } else if (doubleOrLess.contains(componentType)) {
            for (int i = 0; i < length; i++) {
                r.value(Array.getDouble(array, i));
            }
        } else {
            throw new AssertionError("Unknown primitive component type: " + componentType);
        }
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
}
