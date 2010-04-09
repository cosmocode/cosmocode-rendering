package de.cosmocode.rendering;

/**
 * Abstract implementation of the {@link RenderingLevel} interface
 * which relies on the {@link Comparable} interface to provide
 * meaningful comparison logic.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractRenderingLevel implements RenderingLevel, Comparable<RenderingLevel> {

    @Override
    public boolean lt(RenderingLevel level) {
        return compareTo(level) < 0;
    }
    
    @Override
    public boolean le(RenderingLevel level) {
        return eq(level) || lt(level);
    }

    @Override
    public boolean eq(RenderingLevel level) {
        return compareTo(level) == 0;
    }

    @Override
    public boolean ge(RenderingLevel level) {
        return eq(level) || gt(level);
    }

    @Override
    public boolean gt(RenderingLevel level) {
        return compareTo(level) > 0;
    }
    
}
