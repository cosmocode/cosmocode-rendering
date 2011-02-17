package de.cosmocode.rendering;

import javax.annotation.Nullable;

/**
 * A {@link ValueRenderer} for {@link Renderable}s which
 * delegates to {@link Renderable#render(Renderer, RenderingLevel)} using
 * {@link Rendering#maxLevel()}.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
public enum RenderableValueRenderer implements ValueRenderer<Renderable> {

    INSTANCE;
    
    @Override
    public void render(@Nullable Renderable value, Renderer r) throws RenderingException {
        r.value(value, Rendering.maxLevel());
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
}
