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
 * Tests map methods in {@link Renderer}.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractRendererMapTest implements UnitProvider<Renderer> {

    /**
     * Tests {@link Renderer#map()} to return this.
     */
    @Test
    public void mapThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.map());
    }
    
    /**
     * Tests {@link Renderer#map()} as first call.
     */
    @Test
    public void mapInitial() {
        unit().map();
    }
    
    /**
     * Tests {@link Renderer#map()} after key.
     */
    @Test
    public void mapKey() {
        unit().map().key("k").map();
    }
    
    /**
     * Tests {@link Renderer#map()} after list.
     */
    @Test
    public void mapList() {
        unit().list().map();
    }
    
    /**
     * Tests {@link Renderer#map()} after map.
     */
    @Test(expected = RenderingException.class)
    public void mapMap() {
        unit().map().map();
    }
    
    /**
     * Tests {@link Renderer#map()} after done.
     */
    @Test(expected = RenderingException.class)
    public void mapDone() {
        unit().map().endMap().map();
    }
    
    /**
     * Tests {@link Renderer#endMap()} to return this.
     */
    @Test
    public void endMapThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.map().endMap());
    }
    
    /**
     * Tests {@link Renderer#endMap()} as first call.
     */
    @Test(expected = RenderingException.class)
    public void endMapInitial() {
        unit().endMap();
    }

    /**
     * Tests {@link Renderer#endMap()} after key.
     */
    @Test(expected = RenderingException.class)
    public void endMapKey() {
        unit().map().key("k").endMap();
    }

    /**
     * Tests {@link Renderer#endMap()} after list.
     */
    @Test(expected = RenderingException.class)
    public void endMapList() {
        unit().list().endMap();
    }
    
    /**
     * Tests {@link Renderer#endMap()} after map.
     */
    @Test
    public void endMapMap() {
        unit().map().endMap();
    }
    
    /**
     * Tests {@link Renderer#endMap()} after done.
     */
    @Test(expected = RenderingException.class)
    public void endMapDone() {
        unit().map().endMap().endMap();
    }
    
}
