package de.cosmocode.rendering;

import com.google.common.collect.Multimap;

/**
 * Default {@link Multimap} {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum MultimapValueRenderer implements ValueRenderer<Multimap<?, ?>> {

    INSTANCE;
    
    @Override
    public void render(Multimap<?, ?> value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            renderer.value(value.asMap());
        }
    }
    
}
