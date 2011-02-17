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
 * Specified the current mode of a {@link Renderer}.
 *
 * @author Willi Schoenborn
 */
public enum Mode {

    INITIAL {
        
        @Override
        public boolean isAllowedAfter(Mode current) {
            return false;
        }
        
    }, 
    
    KEY {
        
        @Override
        public boolean isAllowedAfter(Mode current) {
            return current == MAP;
        }
        
    },
    
    LIST {
        
        @Override
        public boolean isAllowedAfter(Mode current) {
            return current == INITIAL || current == KEY || current == LIST;
        }
        
    }, 
    
    MAP {
        
        @Override
        public boolean isAllowedAfter(Mode current) {
            return current == INITIAL || current == KEY || current == LIST;
        }
        
    }, 
    
    DONE {
      
        @Override
        public boolean isAllowedAfter(Mode current) {
            return current == LIST || current == MAP;
        }
        
    };

    /**
     * Checks whether this mode is allowed to be used after
     * the specified mode.
     * 
     * @param current the current mode
     * @return true if this mode is allowed to be used after the specified mode
     */
    public abstract boolean isAllowedAfter(Mode current);
    
    /**
     * Checks whether the supplied mode is allowed after this mode.
     *
     * @since 1.3
     * @param next the next mode
     * @throws RenderingException if next is not allowed after this
     */
    public void checkAllowed(Mode next) {
        if (next.isAllowedAfter(this)) {
            return;
        } else {
            throw new RenderingException(String.format("%s is not allowed after %s", next, this));
        }
    }
    
}
