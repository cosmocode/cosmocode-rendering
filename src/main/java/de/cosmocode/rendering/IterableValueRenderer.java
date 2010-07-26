package de.cosmocode.rendering;

/**
 * {@link Iterable} {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
public enum IterableValueRenderer implements ValueRenderer<Iterable<?>> {

    INSTANCE;
    
    @Override
    public void render(Iterable<?> value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            renderer.value(value);
        }        
    }
    
}
