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

import java.io.IOException;
import java.io.InputStream;

import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;

import de.cosmocode.commons.Enums;

/**
 * Default {@link InputStream} {@link ValueRenderer}.
 *
 * @since 
 * @author Willi Schoenborn
 */
public enum InputStreamValueRenderer implements ValueRenderer<InputStream> {

    INSTANCE;
    
    @Override
    public void render(InputStream value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            try {
                renderer.value(ByteStreams.toByteArray(value));
            } catch (IOException e) {
                throw new RenderingException(e);
            } finally {
                Closeables.closeQuietly(value);
            }
        }
    }
    
    @Override
    public String toString() {
        return Enums.toString(this);
    }
    
}
