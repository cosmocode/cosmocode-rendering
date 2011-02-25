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

/**
 * A {@link ValueRenderer} for {@link Renderable}s which
 * delegates to {@link Renderable#render(Renderer, RenderingLevel)} using
 * {@link Rendering#maxLevel()}.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
public enum RenderableValueRenderer implements ValueRenderer<Renderable> {

    INSTANCE;
    
    @Override
    public void render(@Nullable Renderable value, Renderer r) throws RenderingException {
        r.value(value, Rendering.maxLevel());
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
}
