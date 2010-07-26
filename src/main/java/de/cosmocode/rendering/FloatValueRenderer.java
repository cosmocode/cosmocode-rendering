package de.cosmocode.rendering;

/**
 * {@link Float} {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum FloatValueRenderer implements ValueRenderer<Float> {

    INSTANCE;
    
    @Override
    public void render(Float value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            renderer.value(value.doubleValue());
        }
    }
    
}
