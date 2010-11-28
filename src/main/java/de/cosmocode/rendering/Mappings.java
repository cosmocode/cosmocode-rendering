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

import java.io.InputStream;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;

/**
 * Utility class for {@link Mapping}s.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public final class Mappings {
    
    private static final Mapping DEFAULT;

    static {
        final Builder<Class<?>, ValueRenderer<?>> builder = ImmutableMap.builder();

        builder.put(Object[].class, ArrayValueRenderer.INSTANCE);
        builder.put(boolean.class, BooleanValueRenderer.INSTANCE);
        builder.put(Boolean.class, BooleanValueRenderer.INSTANCE);
        builder.put(byte[].class, ByteArrayValueRenderer.INSTANCE);
        builder.put(Byte.class, ByteValueRenderer.INSTANCE);
        builder.put(Calendar.class, CalendarValueRenderer.INSTANCE);
        builder.put(char.class, CharacterValueRenderer.INSTANCE);
        builder.put(Character.class, CharacterValueRenderer.INSTANCE);
        builder.put(CharSequence.class, CharSequenceValueRenderer.INSTANCE);
        builder.put(Class.class, ClassValueRenderer.INSTANCE);
        builder.put(Date.class, DateValueRenderer.INSTANCE);
        builder.put(double.class, DoubleValueRenderer.INSTANCE);
        builder.put(Double.class, DoubleValueRenderer.INSTANCE);
        builder.put(Enum.class, EnumValueRenderer.INSTANCE);
        builder.put(float.class, FloatValueRenderer.INSTANCE);
        builder.put(Float.class, FloatValueRenderer.INSTANCE);
        builder.put(InputStream.class, InputStreamValueRenderer.INSTANCE);
        builder.put(int.class, IntegerValueRenderer.INSTANCE);
        builder.put(Integer.class, IntegerValueRenderer.INSTANCE);
        builder.put(Iterable.class, IterableValueRenderer.INSTANCE);
        builder.put(Iterator.class, IteratorValueRenderer.INSTANCE);
        builder.put(long.class, LongValueRenderer.INSTANCE);
        builder.put(Long.class, LongValueRenderer.INSTANCE);
        builder.put(Map.class, MapValueRenderer.INSTANCE);
        builder.put(Multimap.class, MultimapValueRenderer.INSTANCE);
        builder.put(Object.class, ObjectValueRenderer.INSTANCE);
        builder.put(short.class, ShortValueRenderer.INSTANCE);
        builder.put(Short.class, ShortValueRenderer.INSTANCE);
        builder.put(Table.class, TableValueRenderer.INSTANCE);

        // concrete re-mapping to reduce iteration overhead
        builder.put(String.class, CharSequenceValueRenderer.INSTANCE);
        builder.put(Collection.class, IterableValueRenderer.INSTANCE);
        builder.put(AbstractCollection.class, IterableValueRenderer.INSTANCE);
        builder.put(List.class, IterableValueRenderer.INSTANCE);
        builder.put(AbstractList.class, IterableValueRenderer.INSTANCE);
        builder.put(ArrayList.class, IterableValueRenderer.INSTANCE);
        builder.put(Set.class, IterableValueRenderer.INSTANCE);
        builder.put(AbstractSet.class, IterableValueRenderer.INSTANCE);
        builder.put(AbstractMap.class, MapValueRenderer.INSTANCE);
        
        DEFAULT = newMapping(builder.build());
    }
    
    private Mappings() {
        
    }
    
    /**
     * Returns the immutable default mapping. The default mapping includes:
     * 
     * <table>
     *   <tr>
     *     <th>Type</th>
     *     <th>{@link ValueRenderer}</th>
     *     <th>Description</th>
     *   </tr>
     *   <tr>
     *     <td>{@code Object[]}</td>
     *     <td>{@link ArrayValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(Object...)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Boolean} and {@code boolean}</td>
     *     <td>{@link BooleanValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(boolean)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code byte[]}</td>
     *     <td>{@link ByteArrayValueRenderer}</td>
     *     <td>produces an UTF-8 Base64 encoded String and delegates to {@link Renderer#value(CharSequence)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Byte} and {@code byte}</td>
     *     <td>{@link ByteValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(long)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Calendar}</td>
     *     <td>{@link CalendarValueRenderer}</td>
     *     <td>uses {@link Calendar#getTime()}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Character} and {@code char}</td>
     *     <td>{@link CharacterValueRenderer}</td>
     *     <td>produces a string of length 1 and delegates to {@link Renderer#value(CharSequence)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link CharSequence}</td>
     *     <td>{@link CharacterValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(CharSequence)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Class}</td>
     *     <td>{@link ClassValueRenderer}</td>
     *     <td>retrieves the FQCN and delegates to {@link Renderer#value(CharSequence)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Date}</td>
     *     <td>{@link DateValueRenderer}</td>
     *     <td>renderes the unix timestamp (seconds)</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Double} and {@code double}</td>
     *     <td>{@link DoubleValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(double)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Enum}</td>
     *     <td>{@link EnumValueRenderer}</td>
     *     <td>uses {@link Enum#name()}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Float} and {@code float}</td>
     *     <td>{@link FloatValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(double)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link InputStream}</td>
     *     <td>{@link InputStreamValueRenderer}</td>
     *     <td>collects all bytes</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Integer} and {@code int}</td>
     *     <td>{@link IntegerValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(long)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Iterable}</td>
     *     <td>{@link IterableValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(Iterable)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Iterator}</td>
     *     <td>{@link IteratorValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(Iterator)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Long} and {@code long}</td>
     *     <td>{@link LongValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(long)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Map}</td>
     *     <td>{@link MapValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(Map)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Multimap}</td>
     *     <td>{@link MultimapValueRenderer}</td>
     *     <td>uses {@link Multimap#asMap()}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Object}</td>
     *     <td>{@link ObjectValueRenderer}</td>
     *     <td>uses {@link Object#toString()}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Short} and {@code short}</td>
     *     <td>{@link ShortValueRenderer}</td>
     *     <td>delegates to {@link Renderer#value(long)}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Table}</td>
     *     <td>{@link TableValueRenderer}</td>
     *     <td>uses {@link Table#rowMap()} and delegates to {@link Renderer#value(Map)}</td>
     *   </tr>
     * </table>
     * 
     * @since 1.1
     * @return an immutable mapping containing all predefined mapping entries
     */
    public static Mapping defaultMapping() {
        return Mappings.DEFAULT;
    }
    
    /**
     * Returns a new mutable {@link Mapping} populated with the default mapping entries.
     * 
     * @since 1.1
     * @return a mutable mapping
     */
    public static Mapping newMapping() {
        return newMapping(Maps.newHashMap(Mappings.defaultMapping()));
    }

    /**
     * Returns a mapping backed by the given map
     * Changes in the supplied map will be visible in the returned mapping and
     * vice versa.
     * 
     * @since 1.1
     * @param map the backing map
     * @return a mapping backed by the given map
     * @throws NullPointerException if map is null
     */
    public static Mapping newMapping(Map<Class<?>, ValueRenderer<?>> map) {
        if (map instanceof Mapping) {
            return Mapping.class.cast(map);
        } else {
            return new SuperClassMapping(map);
        }
    }
    
}
