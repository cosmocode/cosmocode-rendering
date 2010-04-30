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

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import de.cosmocode.junit.UnitProvider;

/**
 * Tests object value methods in {@link Renderer}.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractRendererObjectValueTest implements UnitProvider<Renderer> {

    /**
     * Tests {@link Renderer#value(Object)} with null.
     */
    @Test
    public void valueObjectNull() {
        final Object value = null;
        unit().list().value(value);
    }
    
    /**
     * Tests {@link Renderer#value(Object)} to return this.
     */
    @Test
    public void valueObjectThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list().value(this));
    }
    
    /**
     * Tests {@link Renderer#value(Object)} with a simple (non complex) value as first call.
     */
    @Test(expected = RenderingException.class)
    public void valueObjectSimpleInitial() {
        final Object value = Calendar.getInstance();
        unit().value(value);
    }
    
    /**
     * Tests {@link Renderer#value(Object)} with a complex value as first call.
     */
    @Test
    public void valueObjectComplexInitial() {
        final Object value = Maps.newHashMap();
        unit().value(value);
    }
    
    /**
     * Tests {@link Renderer#value(Object)} after key.
     */
    @Test
    public void valueObjectKey() {
        unit().map().key("k").value(new Object());
    }
    
    /**
     * Tests {@link Renderer#value(Object)} after list.
     */
    @Test
    public void valueObjectList() {
        unit().list().value(String.CASE_INSENSITIVE_ORDER);
    }
    
    /**
     * Tests {@link Renderer#value(Object)} after map.
     */
    @Test(expected = RenderingException.class)
    public void valueObjectMap() {
        unit().map().value(Ordering.natural());
    }
    
    /**
     * Tests {@link Renderer#value(Object)} after done.
     */
    @Test(expected = RenderingException.class)
    public void valueObjectDone() {
        unit().map().endMap().value(new Object());
    }

}
