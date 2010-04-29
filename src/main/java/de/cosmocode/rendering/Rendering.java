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
 * Static utility class for rendering concers. All comparision
 * methods are <strong>not necessarily transitive nor symetric</strong>.
 *
 * @author Willi Schoenborn
 */
public final class Rendering {

    private static final RenderingLevel MAX = new AbstractRenderingLevel() {
        
        @Override
        public int compareTo(RenderingLevel that) {
            return this == that ? 0 : 1;
        }
        
    };
    
    private Rendering() {
        
    }
    
    /**
     * Creates a {@link RenderingLevel} which is greater than everyother
     * level but equals to itself.
     * 
     * <p>
     *   The returned level is interoperable with other implementations.
     * </p>
     * 
     * <p>
     *   <strong>Note</strong>: In contrast to the name, it's still possible
     *   to write a custom level implementation which is greater than the returned
     *   level. Beware that all compare methods are not necessarily symetric.
     * </p>
     * 
     * @return a rendering level which is greater than every other level
     */
    public static RenderingLevel maxLevel() {
        return MAX;
    }

    /**
     * Uses the specified int to create a rendering level.
     * 
     * <p>
     *   The returned level is <strong>not</strong> interoperable with
     *   other implementations.
     * </p>
     * 
     * @param value the backing int value
     * @return a rendering level which compares int values
     *         to provide comparision
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
     * Creates a rendering level which is backed by the specified comparable.
     * 
     * <p>
     *   The returned level is interoperable when the given comparable supports it.
     * </p>
     * 
     * @param comparable the backing comparable
     * @return a rendering level which compares levels using the semantics
     *         of the specified comparable
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
