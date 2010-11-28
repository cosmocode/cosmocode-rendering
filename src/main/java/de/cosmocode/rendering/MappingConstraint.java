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

import com.google.common.collect.MapConstraint;

/**
 * A {@link MapConstraint} which makes sure, none of 
 * {@link Mapping#ILLEGAL} get's remapped.
 * 
 * @version 1.3
 * @author Willi Schoenborn
 */
enum MappingConstraint implements MapConstraint<Class<?>, ValueRenderer<?>> {
    
    INSTANCE;
    
    @Override
    public void checkKeyValue(Class<?> key, ValueRenderer<?> value) {
        if (Mapping.ILLEGAL.contains(key)) {
            throw new IllegalArgumentException(String.format(
                "Can't remap '%s'", key
            ));
        }
    }
 
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
}
