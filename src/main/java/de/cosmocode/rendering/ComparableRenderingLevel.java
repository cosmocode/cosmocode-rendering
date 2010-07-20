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
 * {@link Comparable} based {@link RenderingLevel} implementation.
 *
 * @author Willi Schoenborn
 */
final class ComparableRenderingLevel extends AbstractRenderingLevel {
    
    private final Comparable<? super RenderingLevel> comparable;

    public ComparableRenderingLevel(Comparable<? super RenderingLevel> comparable) {
        this.comparable = Preconditions.checkNotNull(comparable, "Comparable");
    }

    @Override
    public int compareTo(RenderingLevel other) {
        return comparable.compareTo(other);
    }

    @Override
    public int hashCode() {
        return comparable.hashCode() ^ 951525787;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that == null) {
            return false;
        } else if (that instanceof ComparableRenderingLevel) {
            final ComparableRenderingLevel other = ComparableRenderingLevel.class.cast(that);
            return compareTo(other) == 0;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Rendering.asLevel(" + comparable + ")";
    }
    
}
