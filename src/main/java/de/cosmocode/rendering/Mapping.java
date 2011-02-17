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

import com.google.common.collect.ImmutableSet;

/**
 * A mapping of {@link Class} to {@link ValueRenderer}
 * which can be used with {@link Renderer}s to plug-in
 * new functionality.
 * 
 * @since 1.1
 * @author Willi Schoenborn
 */
public interface Mapping extends Map<Class<?>, ValueRenderer<?>> {
    
    /**
     * An immutable set, containing all classes which are not allowed to
     * be remapped.
     * 
     * @deprecated remapping has no restrictions anymore
     */
    @Deprecated
    ImmutableSet<Class<?>> ILLEGAL = ImmutableSet.of();
    
    /**
     * Finds a {@link ValueRenderer} capable of rendering the given type.
     * This method differs from {@link Mapping#get(Object)} as it finds the most
     * appropriate {@link ValueRenderer} for the given type.
     * 
     * <p>
     *   Take a mapping which contains the keys {@code Long.class}, 
     *   {@code Number.class} and {@code Object.class}. Calling
     *   {@code mapping.get(Double.class)} would return null,
     *   because no such mapping exists. Using {@code mapping.find(Double.class)}
     *   in contrast would return the renderer mapped by {@code Number.class}
     *   as it is the most narrow type matching. Using {@code mapping.find(String.class)}
     *   would return the renderer for Object.
     * </p>
     * 
     * @since 1.1
     * @param <T> the generic class type
     * @param type the key type
     * @return a {@link ValueRenderer} which can be used to render a value of T
     *          or null if no renderer was found
     */
    <T> ValueRenderer<T> find(Class<? extends T> type);
    
}
