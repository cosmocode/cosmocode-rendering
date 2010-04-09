package de.cosmocode.rendering;

/**
 * 
 *
 * @author Willi Schoenborn
 * @param <T>
 */
public interface ValueRenderer<T> {

    /**
     * 
     * 
     * @param value
     * @param renderer
     * @throws RenderingException
     */
    void render(T value, Renderer renderer) throws RenderingException;
    
}
