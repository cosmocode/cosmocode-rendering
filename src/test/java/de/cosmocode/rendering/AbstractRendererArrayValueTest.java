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

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import de.cosmocode.junit.UnitProvider;

/**
 * Tests array type value methods in {@link Renderer}.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractRendererArrayValueTest implements UnitProvider<Renderer> {

    /**
     * Tests {@link Renderer#value(Object...)} with null.
     */
    @Test(expected = NullPointerException.class)
    public void nullArray() {
        final Object[] values = null;
        unit().value(values);
    }

    /**
     * Tests {@link Renderer#value(Object...)} with an object array.
     */
    @Test
    public void objectArray() {
        unit().value(new Object[] {});
        unit().value(new Object[] {this, getClass(), new Date()});
    }

    /**
     * Tests {@link Renderer#value(Object)} with a byte array.
     */
    @Test
    public void byteArray() {
        final Renderer unit = unit();
        unit.map();
        unit.key("binary").value(new byte[] {Byte.MIN_VALUE, -1, 0, 1, Byte.MAX_VALUE});
        unit.endMap();
    }
    
    /**
     * Tests {@link Renderer#value(Object)} with a primitive array (excluding byte).
     */
    @Test
    public void primitiveArray() {
        unit().value(new boolean[] {true, false, false});
        unit().value(new short[] {Short.MIN_VALUE, -1, 0, 1, Short.MAX_VALUE});
        unit().value(new char[] {Character.MIN_VALUE, 1, 'A', 32768, Character.MAX_VALUE});
        unit().value(new int[] {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE});
        unit().value(new long[] {Long.MIN_VALUE, -1, 0, 1, Long.MAX_VALUE});
        unit().value(new float[] {Float.MIN_VALUE, -1, 0, 1, Float.MAX_VALUE});
        unit().value(new double[] {Double.MIN_VALUE, -1, 0, 1, Double.MAX_VALUE});
    }
    
}
