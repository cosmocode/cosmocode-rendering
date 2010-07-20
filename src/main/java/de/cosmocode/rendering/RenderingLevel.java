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
 * A {@link RenderingLevel} can be passed to 
 * {@link Renderable#render(Renderer, RenderingLevel)} to
 * provide different rendering behaviours within one implementation.
 * 
 * <p>
 *   {@link RenderingLevel}s can be compared using {@link #lt(RenderingLevel)}, {@link #le(RenderingLevel)}
 *   {@link #eq(RenderingLevel)} and so forth.
 * </p>
 * 
 * <p>
 *   This interface is especially designed for being implemented
 *   by enum types. This is why it does not extends {@link Comparable}. This is
 *   wouldn't be compliant/compatible with the enum type hierarchy.
 * </p>
 * 
 * <p>
 *   <strong>Note</strong>: RenderingLevels are not necessarily interoperable.
 *   You should probably stick to one custom level per application or make
 *   sure all used levels are interoperable.
 * </p>
 * 
 * @author Willi Schoenborn
 */
public interface RenderingLevel {

    /**
     * Compares this level with the specified one regarding the
     * less than relation.
     * 
     * @param level the other level
     * @return true if this level is less than the other, false otherwise
     * @throws ClassCastException if the specified level can't be cast for comparison
     */
    boolean lt(RenderingLevel level) throws ClassCastException;
    
    /**
     * Compares this level with the specified one regarding the
     * less or equals relation.
     * 
     * @param level the other level
     * @return true if this level is less or equals than the other, false otherwise
     * @throws ClassCastException if the specified level can't be cast for comparison
     */
    boolean le(RenderingLevel level) throws ClassCastException;
    
    /**
     * Compares this level with the specified one regarding the
     * equals relation.
     * 
     * @param level the other level
     * @return true if this levels is equals to the other, false otherwise
     * @throws ClassCastException if the specified level can't be cast for comparision
     */
    boolean eq(RenderingLevel level) throws ClassCastException;
    
    /**
     * Compares this level with the specified one regarding the
     * greater or equals relation.
     * 
     * @param level the other level
     * @return true if this level is greater or equals than the other, false otherwise
     * @throws ClassCastException if the specified level can't be cast for comparison
     */
    boolean ge(RenderingLevel level) throws ClassCastException;
    
    /**
     * Compares this level with the specified one regarding the
     * greater than relation.
     * 
     * @param level the other level
     * @return true if this level is greater than the other, false otherwise
     * @throws ClassCastException if the specified level can't be cast for comparison
     */
    boolean gt(RenderingLevel level) throws ClassCastException;
    
}
