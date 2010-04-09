package de.cosmocode.rendering;

/**
 * 
 *
 * @author Willi Schoenborn
 */
public interface Renderable {

    /**
     * 
     * 
     * @param renderer
     * @param level
     * @throws RenderingException
     */
    void render(Renderer renderer, RenderingLevel level) throws RenderingException;
    
}
