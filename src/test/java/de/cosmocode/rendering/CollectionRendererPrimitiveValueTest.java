package de.cosmocode.rendering;

/**
 * Tests primitive type value methods in {@link CollectionRenderer}.
 *
 * @author Willi Schoenborn
 */
public final class CollectionRendererPrimitiveValueTest extends AbstractRendererPrimitiveValueTest {

    @Override
    public Renderer unit() {
        return new CollectionRenderer();
    }

}
