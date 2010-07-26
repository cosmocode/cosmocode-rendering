package de.cosmocode.rendering;

import java.util.Iterator;

/**
 * {@link Iterator} {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum IteratorValueRenderer implements ValueRenderer<Iterator<?>> {

    INSTANCE;
    
    @Override
    public void render(Iterator<?> value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            renderer.value(renderer);
        }
    }
    
}
