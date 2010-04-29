package de.cosmocode.rendering;

/**
 * Tests reference type value methods in {@link CollectionRenderer}.
 *
 * @author Willi Schoenborn
 */
public final class CollectionsRendererReferenceValueTest extends AbstractRendererReferenceValueTest {

    @Override
    public Renderer unit() {
        return new CollectionRenderer();
    }

}
