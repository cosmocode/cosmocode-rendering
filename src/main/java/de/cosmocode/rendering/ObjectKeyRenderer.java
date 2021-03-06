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

import javax.annotation.Nullable;

import de.cosmocode.commons.Strings;

/**
 * An {@link Object} {@link KeyRenderer}.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
public enum ObjectKeyRenderer implements KeyRenderer<Object> {

    INSTANCE;
    
    @Override
    public CharSequence apply(@Nullable Object key) {
        return Strings.toString(key);
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
}
