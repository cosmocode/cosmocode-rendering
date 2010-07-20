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

/**
 * {@link Integer} based {@link RenderingLevel} implementation.
 *
 * @author Willi Schoenborn
 */
final class IntegerRenderingLevel extends AbstractRenderingLevel {

    private final int value;
    
    public IntegerRenderingLevel(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(RenderingLevel that) {
        return value - IntegerRenderingLevel.class.cast(that).value;
    }

    @Override
    public int hashCode() {
        return value ^ 963245782;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof IntegerRenderingLevel) {
            final IntegerRenderingLevel other = IntegerRenderingLevel.class.cast(that);
            return value == other.value;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Rendering.asLevel(" + value + ")";
    }

}
