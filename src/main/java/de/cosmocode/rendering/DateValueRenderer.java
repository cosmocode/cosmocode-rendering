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

import java.util.Date;

import javax.annotation.Nullable;

/**
 * Default {@link Date} {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum DateValueRenderer implements ValueRenderer<Date> {

    INSTANCE;
    
    @Override
    public void render(@Nullable Date value, Renderer r) throws RenderingException {
        if (value == null) {
            r.nullValue();
        } else {
            r.value(value.getTime() / 1000); 
        }
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
}
