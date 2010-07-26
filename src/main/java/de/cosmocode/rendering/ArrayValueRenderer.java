package de.cosmocode.rendering;

/**
 * {@link Object}[] {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum ArrayValueRenderer implements ValueRenderer<Object[]> {

    INSTANCE;
    
    @Override
    public void render(Object[] value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            renderer.value(value);
        }
    }
    
}
