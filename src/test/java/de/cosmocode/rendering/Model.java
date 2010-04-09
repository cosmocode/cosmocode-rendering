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
