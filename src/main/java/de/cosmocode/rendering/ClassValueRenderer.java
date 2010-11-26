package de.cosmocode.rendering;

import javax.annotation.Nullable;

/**
 * A {@link ValueRenderer} for {@link Class}es.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
public enum ClassValueRenderer implements ValueRenderer<Class<?>> {

    INSTANCE;
    
    @Override
    public void render(@Nullable Class<?> value, Renderer r) throws RenderingException {
        if (value == null) {
            r.nullValue();
        } else {
            r.value(value.getName());
        }
    }
    
}
