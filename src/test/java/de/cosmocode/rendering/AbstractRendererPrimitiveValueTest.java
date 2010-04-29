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

import org.junit.Assert;
import org.junit.Test;

import de.cosmocode.junit.UnitProvider;

/**
 * Tests primitive type value methods in {@link Renderer}.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractRendererPrimitiveValueTest implements UnitProvider<Renderer> {

    /**
     * Tests {@link Renderer#nullValue()} to return this.
     */
    @Test
    public void nullValueThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list().nullValue());
    }
    
    /**
     * Tests {@link Renderer#nullValue()} as first call.
     */
    @Test(expected = RenderingException.class)
    public void nullValueInitial() {
        unit().nullValue();
    }
    
    /**
     * Tests {@link Renderer#nullValue()} after key.
     */
    @Test
    public void nullValueKey() {
        unit().map().key("k").nullValue();
    }
    
    /**
     * Tests {@link Renderer#nullValue()} after list.
     */
    @Test
    public void nullValueList() {
        unit().list().nullValue();
    }
    
    /**
     * Tests {@link Renderer#nullValue()} after map.
     */
    @Test(expected = RenderingException.class)
    public void nullValueMap() {
        unit().map().nullValue();
    }
    
    /**
     * Tests {@link Renderer#nullValue()} after done.
     */
    @Test(expected = RenderingException.class)
    public void nullValueDone() {
        unit().map().endMap().nullValue();
    }
    
    /**
     * Tests {@link Renderer#value(boolean)} to return this.
     */
    @Test
    public void valueBooleanThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list().value(true));
    }
    
    /**
     * Tests {@link Renderer#value(boolean)} as first call.
     */
    @Test(expected = RenderingException.class)
    public void valueBooleanInitial() {
        unit().value(true);
    }
    
    /**
     * Tests {@link Renderer#value(boolean)} after key.
     */
    @Test
    public void valueBooleanKey() {
        unit().map().key("k").value(false);
    }
    
    /**
     * Tests {@link Renderer#value(boolean)} after list.
     */
    @Test
    public void valueBooleanList() {
        unit().list().value(false);
    }
    
    /**
     * Tests {@link Renderer#value(boolean)} after map.
     */
    @Test(expected = RenderingException.class)
    public void valueBooleanMap() {
        unit().map().value(true);
    }
    
    /**
     * Tests {@link Renderer#value(boolean)} after done.
     */
    @Test(expected = RenderingException.class)
    public void valueBooleanDone() {
        unit().map().endMap().value(true);
    }
    
    /**
     * Tests {@link Renderer#value(long)} to return this.
     */
    @Test
    public void valueLongThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list().value(0));
    }
    
    /**
     * Tests {@link Renderer#value(long)} as first call.
     */
    @Test(expected = RenderingException.class)
    public void valueLongInitial() {
        unit().value(1);
    }
    
    /**
     * Tests {@link Renderer#value(long)} after key.
     */
    @Test
    public void valueLongKey() {
        unit().map().key("k").value(2);
    }
    
    /**
     * Tests {@link Renderer#value(long)} after list.
     */
    @Test
    public void valueLongList() {
        unit().list().value(Long.MAX_VALUE);
    }
    
    /**
     * Tests {@link Renderer#value(long)} after map.
     */
    @Test(expected = RenderingException.class)
    public void valueLongMap() {
        unit().map().value(Long.MIN_VALUE);
    }
    
    /**
     * Tests {@link Renderer#value(long)} after done.
     */
    @Test(expected = RenderingException.class)
    public void valueLongDone() {
        unit().map().endMap().value(17);
    }
    
    /**
     * Tests {@link Renderer#value(double)} to return this.
     */
    @Test
    public void valueDoubleThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list().value(0d));
    }
    
    /**
     * Tests {@link Renderer#value(double)} as first call.
     */
    @Test(expected = RenderingException.class)
    public void valueDoubleInitial() {
        unit().value(1d);
    }
    
    /**
     * Tests {@link Renderer#value(double)} after key.
     */
    @Test
    public void valueDoubleKey() {
        unit().map().key("k").value(Double.NEGATIVE_INFINITY);
    }
    
    /**
     * Tests {@link Renderer#value(double)} after list.
     */
    @Test
    public void valueDoubleList() {
        unit().list().value(Double.MAX_VALUE);
    }
    
    /**
     * Tests {@link Renderer#value(double)} after map.
     */
    @Test(expected = RenderingException.class)
    public void valueDoubleMap() {
        unit().map().value(Double.MIN_VALUE);
    }
    
    /**
     * Tests {@link Renderer#value(double)} after done.
     */
    @Test(expected = RenderingException.class)
    public void valueDoubleDone() {
        unit().map().endMap().value(Double.NaN);
    }

}
