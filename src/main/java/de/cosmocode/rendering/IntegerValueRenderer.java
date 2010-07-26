package de.cosmocode.rendering;

/**
 * {@link Integer} {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum IntegerValueRenderer implements ValueRenderer<Integer> {

    INSTANCE;
    
    @Override
    public void render(Integer value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            renderer.value(value.longValue());
        }
    }
    
}
