package de.cosmocode.rendering;

import java.util.Map;

/**
 * {@link Map} {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum MapValueRenderer implements ValueRenderer<Map<?, ?>> {

    INSTANCE;
    
    @Override
    public void render(Map<?, ?> value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            renderer.value(value);
        }
    }
    
}
