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

import java.nio.CharBuffer;

import org.junit.Assert;
import org.junit.Test;

import de.cosmocode.junit.UnitProvider;

/**
 * Tests reference type value methods in {@link Renderer}.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractRendererReferenceValueTest implements UnitProvider<Renderer> {
    
    /**
     * Tests {@link Renderer#value(CharSequence)} with null.
     */
    @Test
    public void valueCharSequenceNull() {
        final CharSequence sequence = null;
        unit().list().value(sequence);
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} to return this.
     */
    @Test
    public void valueCharSequenceThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list().value(""));
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} as first call.
     */
    @Test(expected = RenderingException.class)
    public void valueCharSequenceInitial() {
        unit().value("v");
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} after key.
     */
    @Test
    public void valueCharSequenceKey() {
        unit().map().key("k").value(new StringBuffer());
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} after list.
     */
    @Test
    public void valueCharSequenceList() {
        unit().list().value(new StringBuilder());
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} after map.
     */
    @Test(expected = RenderingException.class)
    public void valueCharSequenceMap() {
        unit().map().value("");
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} after done.
     */
    @Test(expected = RenderingException.class)
    public void valueCharSequenceDone() {
        unit().map().endMap().value(CharBuffer.allocate(1));
    }
    
}
