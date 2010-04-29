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

import com.google.common.base.Preconditions;

/**
 * A decorator for {@link CollectionRenderer} which assumes
 * the constructed object is a {@link Map}.
 *
 * @author Willi Schoenborn
 */
public final class MapRenderer extends ForwardingRenderer {

    private final CollectionRenderer renderer;
    
    public MapRenderer(CollectionRenderer renderer) {
        this.renderer = Preconditions.checkNotNull(renderer, "Renderer");
    }

    @Override
    protected CollectionRenderer delegate() {
        return renderer;
    }
    
    @Override
    public Map<String, Object> build() throws RenderingException {
        final Object built = super.build();
        assert built instanceof Map<?, ?>;
        @SuppressWarnings("unchecked")
        final Map<String, Object> map = Map.class.cast(built);
        return map;
    }

}
