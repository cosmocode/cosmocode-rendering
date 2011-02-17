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

import javax.annotation.Nullable;

import com.google.common.io.ByteStreams;

/**
 * Default {@link InputStream} {@link ValueRenderer}.
 *
 * @since 1.2
 * @author Willi Schoenborn
 */
public enum InputStreamValueRenderer implements ValueRenderer<InputStream> {

    INSTANCE;
    
    @Override
    public void render(@Nullable InputStream value, Renderer r) throws RenderingException {
        if (value == null) {
            r.nullValue();
        } else {
            try {
                r.value(ByteStreams.toByteArray(value));
            } catch (IOException e) {
                throw new RenderingException(e);
            } finally {
                try {
                    value.close();
                } catch (IOException e) {
                    throw new RenderingException(e);
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
}
