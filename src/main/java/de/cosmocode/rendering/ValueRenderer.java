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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A value renderer can be used to render arbitary values in a {@link Renderer}
 * in several different ways without the need to decorate them with {@link Renderable}. 
 *
 * @author Willi Schoenborn
 * @param <T> the generic value type
 */
public interface ValueRenderer<T> {

    /**
     * Adds the specified value to the given renderer by using
     * the semantics of this value renderer.
     * 
     * <p>
     *   Note: The given value must be rendered as a value according
     *   the grammar in {@link Renderer}.
     * </p>
     * 
     * @param value the value being added
     * @param renderer the renderer receiving the value
     * @throws RenderingException if adding the value to the renderer failed
     * @throws NullPointerException if renderer is null
     */
    void render(@Nullable T value, @Nonnull Renderer renderer) throws RenderingException;
    
}
