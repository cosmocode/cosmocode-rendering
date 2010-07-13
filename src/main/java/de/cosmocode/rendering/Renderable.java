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

/**
 * Custom object may implement this interface to change the 
 * default rendering behaviour of {@link Renderer}s when
 * attempting to render instances of that type.
 *
 * @author Willi Schoenborn
 */
public interface Renderable {

    /**
     * Renders this instance using the specified renderer. The implementation
     * may decide to make use of the supplied level which allows
     * providing different behaviours when rendering this object.
     * 
     * <p>
     *   <strong>Note</strong>: Renderables are only allowed to render themselves
     *   as key-value pairs.
     * </p>
     * 
     * @param renderer the renderer being used
     * @param level the current level
     * @throws RenderingException if any method on renderer failed
     */
    void render(Renderer renderer, RenderingLevel level) throws RenderingException;
    
}
