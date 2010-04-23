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

import com.google.common.base.Preconditions;

/**
 * 
 *
 * @author Willi Schoenborn
 */
public final class Rendering {

    /**
     * 
     * @param value
     * @return
     */
    public static RenderingLevel asLevel(int value) {
        return new IntegerRenderingLevel(value);
    }
    
    /**
     * {@link Integer} based {@link RenderingLevel} implementation.
     *
     * @author Willi Schoenborn
     */
    private static final class IntegerRenderingLevel extends AbstractRenderingLevel {

        private final int value;
        
        public IntegerRenderingLevel(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(RenderingLevel that) {
            return value - IntegerRenderingLevel.class.cast(that).value;
        }

    }
    
    /**
     * 
     * 
     * @param comparable
     * @return
     */
    public static RenderingLevel asLevel(final Comparable<RenderingLevel> comparable) {
        return new ComparableRenderingLevel(comparable);
    }
    
    /**
     * {@link Comparable} based {@link RenderingLevel} implementation.
     *
     * @author Willi Schoenborn
     */
    private static final class ComparableRenderingLevel extends AbstractRenderingLevel {
        
        private final Comparable<RenderingLevel> comparable;

        public ComparableRenderingLevel(Comparable<RenderingLevel> comparable) {
            this.comparable = Preconditions.checkNotNull(comparable, "Comparable");
        }

        @Override
        public int compareTo(RenderingLevel other) {
            return comparable.compareTo(other);
        }
        
    }

}
