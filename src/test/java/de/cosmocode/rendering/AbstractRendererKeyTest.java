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
 * Tests key methods in {@link Renderer}.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractRendererKeyTest implements UnitProvider<Renderer> {

    /**
     * Tests {@link Renderer#key(CharSequence)} with null.
     */
    @Test
    public void keyCharSequenceNull() {
        final CharSequence sequence = null;
        unit().map().key(sequence);
    }
    
    /**
     * Tests {@link Renderer#key(CharSequence)} with a non null string.
     */
    @Test
    public void keyCharSequence() {
        unit().map().key("k");
    }
    
    /**
     * Tests {@link Renderer#key(CharSequence)} to return this.
     */
    @Test
    public void keyCharSequenceThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.map().key("k"));
    }
    
    /**
     * Tests {@link Renderer#key(CharSequence)} as first call.
     */
    @Test(expected = RenderingException.class)
    public void keyCharSequenceInitial() {
        unit().key("k");
    }
    
    /**
     * Tests {@link Renderer#key(CharSequence)} after a key.
     */
    @Test(expected = RenderingException.class)
    public void keyCharSequenceKey() {
        unit().map().key("k").key("k");
    }

    /**
     * Tests {@link Renderer#key(CharSequence)} in a list.
     */
    @Test(expected = RenderingException.class)
    public void keyCharSequenceList() {
        unit().list().key("k");
    }
    
    /**
     * Tests {@link Renderer#key(CharSequence)} after done.
     */
    @Test(expected = RenderingException.class)
    public void keyCharSequenceDone() {
        unit().map().endMap().key("k");
    }
    
    /**
     * Tests {@link Renderer#key(Object))} with null.
     */
    @Test
    public void keyObjectNull() {
        final Object key = null;
        unit().map().key(key);
    }
    
    /**
     * Tests {@link Renderer#key(Object)}.
     */
    @Test
    public void keyObject() {
        unit().map().key(new Object());
    }
    
    /**
     * Tests {@link Renderer#key(Object)} to return this.
     */
    @Test
    public void keyObjectThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.map().key(new Object()));
    }

    /**
     * Tests {@link Renderer#key(Object)} as first call.
     */
    @Test(expected = RenderingException.class)
    public void keyObjectInitial() {
        unit().key(new Object());
    }
    
    /**
     * Tests {@link Renderer#key(Object)} after a key.
     */
    @Test(expected = RenderingException.class)
    public void keyObjectKey() {
        unit().map().key("k").key(new Object());
    }
    
    /**
     * Tests {@link Renderer#key(Object)} in a list.
     */
    @Test(expected = RenderingException.class)
    public void keyObjectList() {
        unit().list().key(new Object());
    }

    /**
     * Tests {@link Renderer#key(Object)} after done.
     */
    @Test(expected = RenderingException.class)
    public void keyObjectDone() {
        unit().map().endMap().key(new Object());
    }
    
}
