package de.cosmocode.rendering;

/**
 * Implementation for {@link Rendering#minLevel()}.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
enum MinRenderingLevel implements RenderingLevel {

    INSTANCE;

    @Override
    public boolean lt(RenderingLevel level) throws ClassCastException {
        // this is special
        return true;
    }

    @Override
    public boolean le(RenderingLevel level) throws ClassCastException {
        // this is special
        return true;
    }

    @Override
    public boolean eq(RenderingLevel level) throws ClassCastException {
        return this == level;
    }

    @Override
    public boolean ge(RenderingLevel level) throws ClassCastException {
        return eq(level);
    }

    @Override
    public boolean gt(RenderingLevel level) throws ClassCastException {
        return false;
    }

    @Override
    public String toString() {
        return "Rendering.minLevel()";
    }
    
}
