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

import de.cosmocode.patterns.Builder;

/**
 * TODO Unix timestamps!!!
 * TODO single null values are ok, complex null values are not
 *
 * @author Willi Schoenborn
 */
public interface Renderer extends Builder<Object> {

    Renderer list() throws RenderingException;
    
    Renderer endList() throws RenderingException;
    
    Renderer map() throws RenderingException;
    
    Renderer endMap() throws RenderingException;
    
    Renderer key(CharSequence key) throws RenderingException;
    
    Renderer key(Object key) throws RenderingException;
    
    Renderer nullValue() throws RenderingException;
    
    Renderer value(Object value) throws RenderingException;
    
    /**
     * TODO nulls will be given to the renderer!!!
     * 
     * @param <T>
     * @param value
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer value(T value, ValueRenderer<T> renderer) throws RenderingException;
    
    Renderer value(boolean value) throws RenderingException;
    
    Renderer value(long value) throws RenderingException;
    
    Renderer value(double value) throws RenderingException;
    
    Renderer value(Date value) throws RenderingException;
    
    Renderer value(Calendar value) throws RenderingException;
    
    Renderer value(Enum<?> value) throws RenderingException;
    
    Renderer value(CharSequence value) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer values(Object... values) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer values(Iterable<?> values) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param <T>
     * @param values
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer values(Iterable<T> values, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer values(Iterator<?> values) throws RenderingException;

    /**
     * TODO NPE
     * 
     * @param <T>
     * @param values
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer values(Iterator<T> values, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer value(Object... values) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer value(Iterable<?> values) throws RenderingException;

    /**
     * TODO NPE
     * 
     * @param <T>
     * @param values
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer value(Iterable<T> values, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer value(Iterator<?> values) throws RenderingException;

    /**
     * TODO NPE
     * 
     * @param <T>
     * @param values
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer value(Iterator<T> values, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param pairs
     * @return
     * @throws RenderingException
     */
    Renderer pairs(Map<?, ?> pairs) throws RenderingException;

    /**
     * TODO NPE
     * 
     * @param <T>
     * @param pairs
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer pairs(Map<?, T> pairs, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param pairs
     * @return
     * @throws RenderingException
     */
    Renderer pairs(Renderable pairs, RenderingLevel level) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param pairs
     * @return
     * @throws RenderingException
     */
    Renderer value(Map<?, ?> pairs) throws RenderingException;

    /**
     * TODO NPE
     * 
     * @param <T>
     * @param pairs
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer value(Map<?, T> pairs, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param pairs
     * @return
     * @throws RenderingException
     */
    Renderer value(Renderable pairs, RenderingLevel level) throws RenderingException;
    
    /**
     * TODO usually overridden by subclasses to narrow the return value
     */
    @Override
    Object build() throws RenderingException;
    
}
