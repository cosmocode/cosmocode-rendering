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

import org.apache.commons.codec.binary.Base64;

import com.google.common.base.Charsets;

import de.cosmocode.commons.Enums;

/**
 * Default {@code byte[]} {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum ByteArrayValueRenderer implements ValueRenderer<byte[]> {

    INSTANCE;
    
    @Override
    public void render(byte[] value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            renderer.value(new String(Base64.encodeBase64(value), Charsets.UTF_8));
        }
    }
    
    @Override
    public String toString() {
        return Enums.toString(this);
    }
    
}
