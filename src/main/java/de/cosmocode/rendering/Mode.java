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
        public boolean isAllowedAfter(Mode mode) {
            return false;
        }
        
    }, 
    
    KEY {
        
        @Override
        public boolean isAllowedAfter(Mode mode) {
            return mode == MAP;
        }
        
    },
    
    LIST {
        
        @Override
        public boolean isAllowedAfter(Mode mode) {
            return mode == INITIAL || mode == KEY || mode == LIST;
        }
        
    }, 
    
    MAP {
        
        @Override
        public boolean isAllowedAfter(Mode mode) {
            return mode == INITIAL || mode == KEY || mode == LIST;
        }
        
    }, 
    
    DONE {
      
        @Override
        public boolean isAllowedAfter(Mode mode) {
            return mode == LIST || mode == MAP;
        }
        
    };

    /**
     * Checks whether this mode is allowed to be used after
     * the specified mode.
     * 
     * @param mode the current mode
     * @return true if this mode is allowed to be used after the specified mode
     */
    public abstract boolean isAllowedAfter(Mode mode);
    
}
