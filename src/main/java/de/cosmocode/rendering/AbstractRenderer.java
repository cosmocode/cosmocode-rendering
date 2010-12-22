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

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;

/**
 * Abstract base implementation of the {@link Renderer} interface.
 * 
 * @author Willi Schoenborn
 */
public abstract class AbstractRenderer implements Renderer {

    private Mapping mapping = Mappings.defaultMapping();

    @Override
    public void setMapping(Mapping mapping) {
        this.mapping = Preconditions.checkNotNull(mapping, "Mapping");
    }
    
    @Override
    public Renderer key(Object key) throws RenderingException {
        if (key instanceof CharSequence) {
            return key(CharSequence.class.cast(key));
        } else if (key instanceof Enum<?>) {
            return key(Enum.class.cast(key));
        } else {
            return key(key == null ? null : key.toString());
        }
    }
    
    @Override
    public Renderer key(Enum<?> key) throws RenderingException {
        return key(key == null ? null : key.name());
    }

    /**
     * Provides the default {@link RenderingLevel}.
     * 
     * @return the default level, must not be null
     */
    @Nonnull
    protected RenderingLevel defaultLevel() {
        return Rendering.maxLevel();
    }
    
    @Override
    public Renderer value(@Nullable Object value) throws RenderingException {
        if (value == null) {
            return nullValue();
        } else if (value instanceof Renderable) {
            return value(Renderable.class.cast(value), defaultLevel());
        } else {
            return unknownValue(value);
        }
    }

    /**
     * A hook allowing sub classes to add behaviour to 
     * the {@link AbstractRenderer#value(Object)} method.
     * 
     * <p>
     *   This implementation tries to find a mapping capable of rendering
     *   the given type using the current {@link Mapping}.
     * </p>
     * 
     * @param value the value of an unknown type, is never null
     * @return this
     * @throws RenderingException if rendering failed
     */
    protected Renderer unknownValue(Object value) throws RenderingException {
        final Class<? extends Object> type = value.getClass();
        final ValueRenderer<Object> renderer = mapping.find(type);
        if (renderer == null) {
            throw new RenderingException("No renderer registered for " + type);
        }
        return value(value, renderer);
    }
    
    @Override
    public <T> Renderer value(T value, ValueRenderer<? super T> renderer) throws RenderingException {
        Preconditions.checkNotNull(renderer, "Renderer");
        renderer.render(value, this);
        return this;
    }
    
    @Override
    public Renderer values(Object... values) throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        return values(Iterators.forArray(values));
    }
    
    @Override
    public Renderer values(Iterable<?> values) throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        return values(values.iterator());
    }
    
    @Override
    public <T> Renderer values(Iterable<? extends T> values, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        Preconditions.checkNotNull(renderer, "Renderer");
        return values(values.iterator(), renderer);
    }

    @Override
    public Renderer values(Iterable<? extends Renderable> values, RenderingLevel level) throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        Preconditions.checkNotNull(level, "Level");
        return values(values.iterator(), level);
    }
    
    @Override
    public Renderer values(Iterator<?> values) throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        while (values.hasNext()) {
            value(values.next());
        }
        return this;
    }
    
    @Override
    public <T> Renderer values(Iterator<? extends T> values, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        Preconditions.checkNotNull(renderer, "Renderer");
        while (values.hasNext()) {
            renderer.render(values.next(), this);
        }
        return this;
    }
    
    @Override
    public Renderer values(Iterator<? extends Renderable> values, RenderingLevel level) throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        Preconditions.checkNotNull(level, "Level");
        while (values.hasNext()) {
            value(values.next(), level);
        }
        return this;
    }
    
    @Override
    public Renderer value(Object... values) throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        return list().values(values).endList();
    }
    
    @Override
    public Renderer value(Iterable<?> values) throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        return value(values.iterator());
    }
    
    @Override
    public <T> Renderer value(Iterable<? extends T> values, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        Preconditions.checkNotNull(renderer, "Renderer");
        return value(values.iterator(), renderer);
    }
    
    @Override
    public Renderer value(Iterator<?> values) throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        return list().values(values).endList();
    }
    
    @Override
    public <T> Renderer value(Iterator<? extends T> values, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        Preconditions.checkNotNull(renderer, "Renderer");
        return list().values(values, renderer).endList();
    }
    
    @Override
    public Renderer value(Iterable<? extends Renderable> values, RenderingLevel level) throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        Preconditions.checkNotNull(level, "Level");
        return list().values(values, level).endList();
    }
    
    @Override
    public Renderer value(Iterator<? extends Renderable> values, RenderingLevel level) throws RenderingException {
        Preconditions.checkNotNull(values, "Values");
        Preconditions.checkNotNull(level, "Level");
        return list().values(values, level).endList();
    }
    
    @Override
    public Renderer pairs(Map<?, ?> pairs) throws RenderingException {
        Preconditions.checkNotNull(pairs, "Pairs");
        for (Entry<?, ?> entry : pairs.entrySet()) {
            key(entry.getKey()).value(entry.getValue());
        }
        return this;
    }
    
    @Override
    public <T> Renderer pairs(Map<?, ? extends T> pairs, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        Preconditions.checkNotNull(pairs, "Pairs");
        Preconditions.checkNotNull(renderer, "Renderer");
        for (Entry<?, ? extends T> entry : pairs.entrySet()) {
            key(entry.getKey());
            renderer.render(entry.getValue(), this);
        }
        return this;
    }
    
    @Override
    public Renderer pairs(Renderable pairs, RenderingLevel level) throws RenderingException {
        Preconditions.checkNotNull(pairs, "Pairs");
        Preconditions.checkNotNull(level, "Level");
        pairs.render(this, level);
        return this;
    }
    
    @Override
    public Renderer value(@Nullable Map<?, ?> pairs) throws RenderingException {
        return pairs == null ? nullValue() : map().pairs(pairs).endMap();
    }
    
    @Override
    public <T> Renderer value(@Nullable Map<?, ? extends T> pairs, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        Preconditions.checkNotNull(renderer, "Renderer");
        return pairs == null ? nullValue() : map().pairs(pairs, renderer).endMap();
    }
    
    @Override
    public Renderer value(@Nullable Renderable pairs, RenderingLevel level) throws RenderingException {
        Preconditions.checkNotNull(level, "Level");
        return pairs == null ? nullValue() : map().pairs(pairs, level).endMap();
    }
    
}
