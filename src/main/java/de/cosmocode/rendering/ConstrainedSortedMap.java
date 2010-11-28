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
import java.util.SortedMap;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingSortedMap;
import com.google.common.collect.MapConstraint;

/**
 * A {@link MapConstraint}ed {@link SortedMap}.
 * 
 * @version 1.3
 * @author Willi Schoenborn
 *
 * @param <K> the generic key type
 * @param <V> the generic value type
 */
final class ConstrainedSortedMap<K, V> extends ForwardingSortedMap<K, V> {

    private final SortedMap<K, V> delegate;
    private final MapConstraint<K, V> constraint;
    
    public ConstrainedSortedMap(SortedMap<K, V> map, MapConstraint<K, V> constraint) {
        this.delegate = Preconditions.checkNotNull(map, "Map");
        this.constraint = Preconditions.checkNotNull(constraint, "Constraint");
    }
    
    @Override
    protected SortedMap<K, V> delegate() {
        return delegate;
    }

    @Override
    public V put(K key, V value) {
        constraint.checkKeyValue(key, value);
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
            constraint.checkKeyValue(entry.getKey(), entry.getValue());
        }
        super.putAll(map);
    }
    
}
