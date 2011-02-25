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

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import de.cosmocode.patterns.Builder;

/**
 * A {@link Renderer} can be used to create data structures by
 * providing a fluent api. 
 * 
 * The data structures must adhere to the <a href="http://json.org">json</a> grammar:
 * <pre>
 * object
 *     {}
 *     { members }
 * members
 *     pair
 *     pair , members
 *     pair
 *     string : value
 * array
 *     []
 *     [ elements ]
 * elements
 *     value 
 *     value , elements
 * value
 *     string
 *     number
 *     object
 *     array
 *     true
 *     false
 *     null
 * </pre>
 * 
 * Nevertheless does the renderer not make assumptions about the format
 * of the built structure. This decision is left to sub classes.
 * 
 * <p>
 *   This interface offers several {@code value} methods for so called
 *   <em>primitive</em> or <em>low level</em> data types like {@code boolean}, {@code long},
 *   {@code double} and {@link CharSequence}. The way how these low level types are
 *   rendered is defined by subclasses, as this is extremely implementation specific. 
 *   Several more methods exist for <em>compound</em> values like Arrays, {@link Iterable}s, 
 *   {@link Iterator}s and {@link Map}s. The behaviour for all of these methods is
 *   specified very clearly as they delegate to each other or the value methods
 *   for primitive values. This behaviour is provided by {@link AbstractRenderer}.
 * </p>
 * 
 * <p>
 *   Every object type which is neither <em>primitive</em> nor <em>compound</em>
 *   will be rendered using the configured {@link ValueRenderer} of the associated
 *   {@link Mapping} instance of this {@link Renderer}. Feel free to supply custom
 *   {@link ValueRenderer} for any type you want. Implementations are encouraged
 *   to use {@link Mappings#defaultMapping()} as the default.
 * </p>
 * 
 * @author Willi Schoenborn
 */
public interface Renderer extends Builder<Object> {
    
    /**
     * Starts a list structure.
     * 
     * @return this
     * @throws RenderingException if list is not allowed at the current position.
     */
    Renderer list() throws RenderingException;
    
    /**
     * Ends a list structure.
     * 
     * @return this
     * @throws RenderingException if there is no list to end at the current position
     */
    Renderer endList() throws RenderingException;
    
    /**
     * Starts a map structure.
     * 
     * @return this
     * @throws RenderingException if map is not allowed at the current position
     */
    Renderer map() throws RenderingException;
    
    /**
     * Ends a map structure.
     * 
     * @return this
     * @throws RenderingException if there is no map to end at the current position
     */
    Renderer endMap() throws RenderingException;
    
    /**
     * Adds the specified key.
     * 
     * @param key the key being added
     * @return this
     * @throws RenderingException if currently not inside a map
     */
    Renderer key(@Nullable CharSequence key) throws RenderingException;
    
    /**
     * Adds the specified key.
     * 
     * @param key the key being added
     * @return this
     * @throws RenderingException if currently not inside a map
     */
    Renderer key(@Nullable Object key) throws RenderingException;
    
    /**
     * Adds the specified key.
     * 
     * @since 1.2
     * @param key the key to be added
     * @return this
     * @throws RenderingException if currently not inside a map
     */
    Renderer key(@Nullable Enum<?> key) throws RenderingException;

    /**
     * Changes the {@link Class} to {@link ValueRenderer} mapping of this
     * {@link Renderer}.
     * 
     * @since 1.1
     * @param mapping the new mapping
     * @throws NullPointerException if mapping is null
     */
    void setMapping(@Nonnull Mapping mapping);
    
    /**
     * Adds a null value.
     * 
     * @return this
     * @throws RenderingException if no value is allowed at the current position
     */
    Renderer nullValue() throws RenderingException;
    
    /**
     * Adds the specified value.
     * 
     * @param value the value being added
     * @return this
     * @throws RenderingException if the given value is not allowed at the current position
     */
    Renderer value(@Nullable Object value) throws RenderingException;
    
    /**
     * Adds the specified value using the given renderer for conversion. Null values are permitted
     * and passed to the renderer.
     * 
     * @param <T> the generic value type
     * @param value the value being added
     * @param renderer the value renderer used to convert value
     * @return this
     * @throws RenderingException if the given value is not allowed at the current position
     * @throws NullPointerException if renderer is null
     */
    <T> Renderer value(@Nullable T value, @Nonnull ValueRenderer<? super T> renderer) throws RenderingException;
    
    /**
     * Adds the specified value.
     * 
     * @param value the value being added
     * @return this
     * @throws RenderingException if no value is allowed at the current position
     */
    Renderer value(boolean value) throws RenderingException;

    /**
     * Adds the specified value.
     * 
     * @param value the value being added
     * @return this
     * @throws RenderingException if no value is allowed at the current position
     */
    Renderer value(long value) throws RenderingException;

    /**
     * Adds the specified value.
     * 
     * @param value the value being added
     * @return this
     * @throws RenderingException if no value is allowed at the current position
     */
    Renderer value(double value) throws RenderingException;

    /**
     * Adds the specified value.
     * 
     * @param value the value being added
     * @return this
     * @throws RenderingException if no value is allowed at the current position
     */
    Renderer value(@Nullable CharSequence value) throws RenderingException;
    
    /**
     * Adds the specified values.
     * 
     * @param <T> generic element type
     * @param values the values being added
     * @return this
     * @throws RenderingException if no values are allowed at the current position
     * @throws NullPointerException if values is null
     */
    <T> Renderer values(@Nonnull T... values) throws RenderingException;

    /**
     * Adds the specified values.
     * 
     * @param values the values being added
     * @return this
     * @throws RenderingException if no values are allowed at the current position
     * @throws NullPointerException if values is null
     */
    Renderer values(@Nonnull Iterable<?> values) throws RenderingException;
    
    /**
     * Adds the specified values using the given renderer for conversion. Null elements are permitted
     * and passed to the renderer.
     * 
     * @param <T> the generic value type
     * @param values the values being added
     * @param renderer the value renderer used to convert all elements in values
     * @return this
     * @throws RenderingException if no values are allowed at the current position
     * @throws NullPointerException if values or renderer is null
     */
    <T> Renderer values(@Nonnull Iterable<? extends T> values, @Nonnull ValueRenderer<? super T> renderer) 
        throws RenderingException;
    
    /**
     * Adds the specified values.
     * 
     * @param values the values being added
     * @return this
     * @throws RenderingException if no values are allowed at the current position
     * @throws NullPointerException if values is null
     */
    Renderer values(@Nonnull Iterator<?> values) throws RenderingException;

    /**
     * Adds the specified values using the given renderer for conversion. Null elements are permitted
     * and passed to the renderer.
     * 
     * @param <T> the generic value type
     * @param values the values being added
     * @param renderer the value renderer used to convert all elements in values
     * @return this
     * @throws RenderingException if no values are allowed at the current position
     * @throws NullPointerException if values or renderer is null
     */
    <T> Renderer values(@Nonnull Iterator<? extends T> values, @Nonnull ValueRenderer<? super T> renderer) 
        throws RenderingException;
    
    /**
     * Adds the specified values using the given level.
     * 
     * @param values the values being added
     * @param level the value being passed to {@link Renderable#render(Renderer, RenderingLevel)}
     * @return this
     * @throws RenderingException if no values are allowed at the current position
     * @throws NullPointerException if values or level is null
     */
    Renderer values(@Nonnull Iterable<? extends Renderable> values, @Nonnull RenderingLevel level)
        throws RenderingException;
    
    /**
     * Adds the specified values using the given level.
     * 
     * @param values the values being added
     * @param level the value being passed to {@link Renderable#render(Renderer, RenderingLevel)}
     * @return this
     * @throws RenderingException if no values are allowed at the current position
     * @throws NullPointerException if values or level is null
     */
    Renderer values(@Nonnull Iterator<? extends Renderable> values, @Nonnull RenderingLevel level)
        throws RenderingException;
    
    /**
     * Add the specified values as a list. This is equivalent to:
     * {@code list().values(values).endList()}.
     * 
     * @param <T> generic element type
     * @param values the values being added
     * @return this
     * @throws RenderingException if list is not allowed at the current position.
     * @throws NullPointerException if values is null
     */
    <T> Renderer value(@Nonnull T... values) throws RenderingException;

    /**
     * Add the specified values as a list. This is equivalent to:
     * {@code list().values(values).endList()}.
     * 
     * @param values the values being added
     * @return this
     * @throws RenderingException if list is not allowed at the current position.
     * @throws NullPointerException if values is null
     */
    Renderer value(@Nonnull Iterable<?> values) throws RenderingException;

    /**
     * Adds the specified values as a list using the given renderer. This is equivalent to:
     * {@code list().values(values, renderer).endList()}.
     * 
     * @param <T> the generic value type
     * @param values the values being added
     * @param renderer the value renderer used to convert all elements in values
     * @return this
     * @throws RenderingException if list is not allowed at the current position.
     * @throws NullPointerException if values or renderer is null
     */
    <T> Renderer value(@Nonnull Iterable<? extends T> values, @Nonnull ValueRenderer<? super T> renderer) 
        throws RenderingException;
    
    /**
     * Adds the specified values as a list using the given level. This is equivalent to:
     * {@code list().values(values, level).endList()}.
     * 
     * @param values the values being added
     * @param level the level being passed to 
     * @return this
     * @throws RenderingException if list is not allowed at the current position
     * @throws NullPointerException if values or level is null
     */
    Renderer value(@Nonnull Iterable<? extends Renderable> values, @Nonnull RenderingLevel level)
        throws RenderingException;

    /**
     * Add the specified values as a list. This is equivalent to:
     * {@code list().values(values).endList()}.
     * 
     * @param values the values being added
     * @return this
     * @throws RenderingException if list is not allowed at the current position.
     * @throws NullPointerException if values is null
     */
    Renderer value(@Nonnull Iterator<?> values) throws RenderingException;

    /**
     * Adds the specified values as a list using the given renderer. This is equivalent to:
     * {@code list().values(values, renderer).endList()}.
     * 
     * @param <T> the generic value type
     * @param values the values being added
     * @param renderer the value renderer used to convert all elements in values
     * @return this
     * @throws RenderingException if list is not allowed at the current position.
     * @throws NullPointerException if values or renderer is null
     */
    <T> Renderer value(@Nonnull Iterator<? extends T> values, @Nonnull ValueRenderer<? super T> renderer) 
        throws RenderingException;
    
    /**
     * Adds the specified values as a list using the given level. This is equivalent to:
     * {@code list().values(values, level).endList()}.
     * 
     * @param values the values being added
     * @param level the level being passed to 
     * @return this
     * @throws RenderingException if list is not allowed at the current position
     * @throws NullPointerException if values or level is null
     */
    Renderer value(@Nonnull Iterator<? extends Renderable> values, @Nonnull RenderingLevel level)
        throws RenderingException;
    
    /**
     * Adds the specified pairs. Pairs a basically a sequence of
     * {@code key(entry.getKey()).value(entry.getValue())} calls.
     * 
     * @param pairs the pairs being added.
     * @return this
     * @throws RenderingException if no pairs are allowed at the current position
     * @throws NullPointerException if pairs is null
     */
    Renderer pairs(@Nonnull Map<?, ?> pairs) throws RenderingException;

    /**
     * Adds the specified pairs using the given renderer for value conversion. The keys will be passed
     * directly to {@link #key(Object)}.
     * 
     * @param <T> the generic value type
     * @param pairs the pairs being added
     * @param renderer the value renderer used to convert all values in pairs
     * @return this
     * @throws RenderingException if no pairs are allowed at the current position
     * @throws NullPointerException if pairs or renderer is null
     */
    <T> Renderer pairs(@Nonnull Map<?, ? extends T> pairs, @Nonnull ValueRenderer<? super T> renderer) 
        throws RenderingException;
    
    /**
     * Adds the specified pairs by passing control to {@link Renderable#render(Renderer, RenderingLevel)}
     * of the given renderable using the specified level.
     * 
     * @param pairs the pairs being added
     * @param level the level being passed to the renderable
     * @return this
     * @throws RenderingException if no pairs are allowed at the current position
     * @throws NullPointerException if pairs or level is null
     */
    Renderer pairs(@Nonnull Renderable pairs, @Nonnull RenderingLevel level) throws RenderingException;
    
    /**
     * Adds the specified pairs as a map. This is equivalent to:
     * {@code map().pairs(pairs).endMap()}.
     * 
     * @param pairs the pairs being added
     * @return this
     * @throws RenderingException if map is not allowed at the current position
     */
    Renderer value(@Nullable  Map<?, ?> pairs) throws RenderingException;

    /**
     * Adds the specified pairs as a map using the given renderer for value conversion. The keys will be passed
     * directly to {@link #key(Object)}. This is equivalent to:
     * {@code map().pairs(pairs, renderer).endMap()}.
     * 
     * @param <T> the generic value type
     * @param pairs the pairs being added
     * @param renderer the value renderer used to convert all values in pairs
     * @return this
     * @throws RenderingException if map is not allowed at the current position
     * @throws NullPointerException if renderer is null
     */
    <T> Renderer value(@Nullable  Map<?, ? extends T> pairs, @Nonnull ValueRenderer<? super T> renderer) 
        throws RenderingException;
    
    /**
     * Adds the specified pairs by passing control to {@link Renderable#render(Renderer, RenderingLevel)}
     * of the given renderable using the specified level. This is equivalent to:
     * {@code map().pairs(pairs, level).endMap()}.
     * 
     * @param pairs the pairs being added
     * @param level the level being passed to the renderable
     * @return this
     * @throws RenderingException if map is not allowed at the current position
     * @throws NullPointerException if level is null
     */
    Renderer value(@Nullable  Renderable pairs, @Nonnull RenderingLevel level) throws RenderingException;
    
    /**
     * Builds the final data structure.
     * <p>
     *   Implementation note: This method is usually overridden by subclasses
     *   to narrow the return value to a more appropriate type.
     * </p>
     * 
     * @return the built structure
     * @throws RenderingException if the structure is not finished yet
     * @throws UnsupportedOperationException if the implementation uses streaming and
     *         does not support in memory production
     */
    @Override
    Object build() throws RenderingException;
    
}
