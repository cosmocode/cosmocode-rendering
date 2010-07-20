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

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Multimap;

/**
 * Abstract base implementation of the {@link Renderer} interface.
 * 
 * <p>
 *   Implementation note: Enums are added using their {@link Enum#name()} method.
 *   {@link Date} and {@link Calendar} will converted to unix timestamps (seconds).
 * </p>
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractRenderer implements Renderer {

    private ValueRendererRegistry registry = DefaultValueRendererRegistry.INSTANCE;
    
    @Override
    public void setRegistry(ValueRendererRegistry registry) {
        this.registry = Preconditions.checkNotNull(registry, "Registry");
    }
    
    @Override
    public Renderer key(Object key) throws RenderingException {
        return key(key == null ? null : key.toString());
    }

    /**
     * Provides the default {@link RenderingLevel}.
     * 
     * @return the default level, must not be null
     */
    protected RenderingLevel defaultLevel() {
        return Rendering.maxLevel();
    }
    
    @Override
    public Renderer value(Object value) throws RenderingException {
        if (value == null) {
            return nullValue();
        } else if (value instanceof CharSequence) {
            return value(CharSequence.class.cast(value));
        } else if (value instanceof Number) {
            return numberValue(Number.class.cast(value));
        } else if (value instanceof Boolean) {
            return value(Boolean.class.cast(value).booleanValue());
        } else if (value instanceof Renderable) {
            return value(Renderable.class.cast(value), defaultLevel());
        } else if (value instanceof Date) {
            return value(Date.class.cast(value));
        } else if (value.getClass().isEnum()) {
            return value(Enum.class.cast(value));
        } else if (value instanceof Calendar) {
            return value(Calendar.class.cast(value));
        } else {
            return compoundValue(value);
        }
    }
    
    /**
     * Groups all {@link Number} values.
     * 
     * @param value the {@link Number} value
     * @return this
     */
    protected Renderer numberValue(Number value) {
        if (value instanceof Integer) {
            return value(Integer.class.cast(value).intValue());
        } else if (value instanceof Long) {
            return value(Long.class.cast(value).longValue());
        } else if (value instanceof Double) {
            return value(Double.class.cast(value).doubleValue());
        } else if (value instanceof Float) {
            return value(Float.class.cast(value).floatValue());
        } else if (value instanceof Byte) {
            return value(Byte.class.cast(value).byteValue());
        } else if (value instanceof Short) {
            return value(Short.class.cast(value).shortValue());
        } else {
            return unknownValue(value);
        }
    }

    /**
     * Groups all kind of collection style values.
     * 
     * @param value the untyped value
     * @return this
     */
    protected Renderer compoundValue(Object value) {
        if (value.getClass().isArray()) {
            return value(Object[].class.cast(value));
        } else if (value instanceof Iterable<?>) {
            return value(Iterable.class.cast(value));
        } else if (value instanceof Iterator<?>) {
            return value(Iterator.class.cast(value));
        } else if (value instanceof Map<?, ?>) {
            return value(Map.class.cast(value));
        } else if (value instanceof Multimap<?, ?>) {
            return value(Multimap.class.cast(value).asMap());
        } else {
            return unknownValue(value);
        }
    }

    /**
     * A hook allowing sub classes to add behaviour to 
     * the {@link AbstractRenderer#value(Object)} method.
     * 
     * <p>
     *   This implementation uses {@link Object#toString()}.
     * </p>
     * 
     * @param value the value of an unknown type, is never null
     * @return this
     */
    protected Renderer unknownValue(Object value) {
        final ValueRenderer<Object> renderer = registry.find(value.getClass());
        Preconditions.checkNotNull(renderer, "Renderer");
        renderer.render(value, this);
        return this;
    }
    
    private <T> Renderer value(Class<T> type, T value) {
        final ValueRenderer<T> renderer = registry.find(type);
        Preconditions.checkNotNull(renderer, "Renderer");
        // let the renderer take care of nulls
        renderer.render(value, this);
        return this;
        
    }
    
    @Override
    public <T> Renderer value(T value, ValueRenderer<? super T> renderer) throws RenderingException {
        Preconditions.checkNotNull(renderer, "Renderer");
        renderer.render(value, this);
        return this;
    };
    
    @Override
    public Renderer value(Date value) throws RenderingException {
        return value(Date.class, value);
    }
    
    @Override
    public Renderer value(Calendar value) throws RenderingException {
        return value(Calendar.class, value);
    }
    
    @Override
    public Renderer value(Enum<?> value) throws RenderingException {
        return value(Enum.class, value);
    }
    
    @Override
    public Renderer value(byte[] value) throws RenderingException {
        return value(byte[].class, value);
    }
    
    @Override
    public Renderer value(InputStream value) throws RenderingException {
        return value(InputStream.class, value);
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
    public Renderer value(Map<?, ?> pairs) throws RenderingException {
        if (pairs == null) return nullValue();
        return map().pairs(pairs).endMap();
    }
    
    @Override
    public <T> Renderer value(Map<?, ? extends T> pairs, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        Preconditions.checkNotNull(renderer, "Renderer");
        if (pairs == null) return nullValue();
        return map().pairs(pairs, renderer).endMap();
    }
    
    @Override
    public Renderer value(Renderable pairs, RenderingLevel level) throws RenderingException {
        Preconditions.checkNotNull(level, "Level");
        if (pairs == null) return nullValue();
        return map().pairs(pairs, level).endMap();
    }
    
}
