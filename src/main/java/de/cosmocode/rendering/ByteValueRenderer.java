package de.cosmocode.rendering;

/**
 * {@link Byte} {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum ByteValueRenderer implements ValueRenderer<Byte> {

    INSTANCE;
    
    @Override
    public void render(Byte value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            renderer.value(value.longValue());
        }
    }
    
}
