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
 *
 * @author Willi Schoenborn
 */
public class Model implements Renderable {

    private String forename;
    
    private String surname;

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    @Override
    public void render(Renderer renderer, RenderingLevel level) throws RenderingException {
        if (level.ge(Level.S)) {
            
        }
        if (level.ge(Level.M)) {
            
        }
        if (level.ge(Level.L)) {
            
        }
        if (level.ge(Level.XL)) {
            
        }
        if (level.ge(Level.XXL)) {
            
        }
    }

}
