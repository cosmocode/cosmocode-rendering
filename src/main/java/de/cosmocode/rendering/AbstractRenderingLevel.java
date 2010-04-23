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
 * Abstract implementation of the {@link RenderingLevel} interface
 * which relies on the {@link Comparable} interface to provide
 * meaningful comparison logic.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractRenderingLevel implements RenderingLevel, Comparable<RenderingLevel> {

    @Override
    public boolean lt(RenderingLevel level) {
        return compareTo(level) < 0;
    }
    
    @Override
    public boolean le(RenderingLevel level) {
        return eq(level) || lt(level);
    }

    @Override
    public boolean eq(RenderingLevel level) {
        return compareTo(level) == 0;
    }

    @Override
    public boolean ge(RenderingLevel level) {
        return eq(level) || gt(level);
    }

    @Override
    public boolean gt(RenderingLevel level) {
        return compareTo(level) > 0;
    }
    
}
