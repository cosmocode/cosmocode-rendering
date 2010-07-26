package de.cosmocode.rendering;

/**
 * {@link Short} {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum ShortValueRenderer implements ValueRenderer<Short> {

    INSTANCE;
    
    @Override
    public void render(Short value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            renderer.value(value.longValue());
        }
    }
    
}
