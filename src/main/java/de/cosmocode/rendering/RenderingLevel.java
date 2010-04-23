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
 * 
 * TODO methods are allowed to throw ClassCastExceptions
 *
 * @author Willi Schoenborn
 */
public interface RenderingLevel {

    boolean lt(RenderingLevel level) throws ClassCastException;
    
    boolean le(RenderingLevel level) throws ClassCastException;
    
    boolean eq(RenderingLevel level) throws ClassCastException;
    
    boolean ge(RenderingLevel level) throws ClassCastException;
    
    boolean gt(RenderingLevel level) throws ClassCastException;
    
}
