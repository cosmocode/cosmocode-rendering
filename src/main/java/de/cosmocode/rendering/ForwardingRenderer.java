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

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.ForwardingObject;

/**
 * An abstract decorator for {@link Renderer}s.
 *
 * @author Willi Schoenborn
 */
public abstract class ForwardingRenderer extends ForwardingObject implements Renderer {

    @Override
    protected abstract Renderer delegate();

    @Override
    public Object build() throws RenderingException {
        return delegate().build();
    }

    @Override
    public Renderer endList() throws RenderingException {
        return delegate().endList();
    }

    @Override
    public Renderer endMap() throws RenderingException {
        return delegate().endMap();
    }

    @Override
    public Renderer key(CharSequence key) throws RenderingException {
        return delegate().key(key);
    }

    @Override
    public Renderer key(Object key) throws RenderingException {
        return delegate().key(key);
    }

    @Override
    public Renderer list() throws RenderingException {
        return delegate().list();
    }

    @Override
    public Renderer map() throws RenderingException {
        return delegate().map();
    }

    @Override
    public Renderer nullValue() throws RenderingException {
        return delegate().nullValue();
    }

    @Override
    public Renderer pairs(Map<?, ?> pairs) throws RenderingException {
        return delegate().pairs(pairs);
    }

    @Override
    public <T> Renderer pairs(Map<?, ? extends T> pairs, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        return delegate().pairs(pairs, renderer);
    }

    @Override
    public Renderer pairs(Renderable pairs, RenderingLevel level) throws RenderingException {
        return delegate().pairs(pairs, level);
    }

    @Override
    public Renderer value(boolean value) throws RenderingException {
        return delegate().value(value);
    }

    @Override
    public Renderer value(Calendar value) throws RenderingException {
        return delegate().value(value);
    }

    @Override
    public Renderer value(CharSequence value) throws RenderingException {
        return delegate().value(value);
    }

    @Override
    public Renderer value(Date value) throws RenderingException {
        return delegate().value(value);
    }

    @Override
    public Renderer value(double value) throws RenderingException {
        return delegate().value(value);
    }

    @Override
    public Renderer value(Enum<?> value) throws RenderingException {
        return delegate().value(value);
    }

    @Override
    public Renderer value(Iterable<?> values) throws RenderingException {
        return delegate().value(values);
    }

    @Override
    public <T> Renderer value(Iterable<? extends T> values, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        return delegate().value(values, renderer);
    }

    @Override
    public Renderer value(Iterator<?> values) throws RenderingException {
        return delegate().value(values);
    }

    @Override
    public <T> Renderer value(Iterator<? extends T> values, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        return delegate().value(values, renderer);
    }

    @Override
    public Renderer value(long value) throws RenderingException {
        return delegate().value(value);
    }

    @Override
    public Renderer value(Map<?, ?> pairs) throws RenderingException {
        return delegate().value(pairs);
    }

    @Override
    public <T> Renderer value(Map<?, ? extends T> pairs, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        return delegate().value(pairs, renderer);
    }

    @Override
    public Renderer value(Object... values) throws RenderingException {
        return delegate().value(values);
    }

    @Override
    public Renderer value(Object value) throws RenderingException {
        return delegate().value(value);
    }

    @Override
    public Renderer value(Renderable pairs, RenderingLevel level) throws RenderingException {
        return delegate().value(pairs, level);
    }

    @Override
    public <T> Renderer value(T value, ValueRenderer<? super T> renderer) throws RenderingException {
        return delegate().value(value, renderer);
    }

    @Override
    public Renderer values(Iterable<?> values) throws RenderingException {
        return delegate().values(values);
    }

    @Override
    public <T> Renderer values(Iterable<? extends T> values, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        return delegate().values(values, renderer);
    }

    @Override
    public Renderer values(Iterator<?> values) throws RenderingException {
        return delegate().values(values);
    }

    @Override
    public <T> Renderer values(Iterator<? extends T> values, ValueRenderer<? super T> renderer) 
        throws RenderingException {
        return delegate().values(values, renderer);
    }

    @Override
    public Renderer values(Object... values) throws RenderingException {
        return delegate().values(values);
    }

}
