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
 * Tests list methods in {@link Renderer}.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractRendererListTest implements UnitProvider<Renderer> {

    /**
     * Tests {@link Renderer#list()} to return this.
     */
    @Test
    public void listThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list());
    }
    
    /**
     * Tests {@link Renderer#list()} as first call.
     */
    @Test
    public void listInitial() {
        unit().list();
    }
    
    /**
     * Tests {@link Renderer#list()} after key.
     */
    @Test
    public void listKey() {
        unit().map().key("k").list();
    }
    
    /**
     * Tests {@link Renderer#list()} after list.
     */
    @Test
    public void listList() {
        unit().list().list();
    }
    
    /**
     * Tests {@link Renderer#list()} after a map.
     */
    @Test(expected = RenderingException.class)
    public void listMap() {
        unit().map().list();
    }
    
    /**
     * Tests {@link Renderer#list()} after done.
     */
    @Test(expected = RenderingException.class)
    public void listDone() {
        unit().list().endList().list();
    }
    
    /**
     * Tests {@link Renderer#endList()} to return this.
     */
    @Test
    public void endListThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list().endList());
    }
    
    /**
     * Tests {@link Renderer#endList()} as first call.
     */
    @Test(expected = RenderingException.class)
    public void endListInitial() {
        unit().endList();
    }

    /**
     * Tests {@link Renderer#endList()} after key.
     */
    @Test(expected = RenderingException.class)
    public void endListKey() {
        unit().map().key("k").endList();
    }
    
    /**
     * Tests {@link Renderer#endList()} after list.
     */
    @Test
    public void endListList() {
        unit().list().endList();
    }
    
    /**
     * Tests {@link Renderer#endList()} after map.
     */
    @Test(expected = RenderingException.class)
    public void endListMap() {
        unit().map().endList();
    }
    
    /**
     * Tests {@link Renderer#endList()} after done.
     */
    @Test(expected = RenderingException.class)
    public void endListDone() {
        unit().list().endList().endList();
    }

}
