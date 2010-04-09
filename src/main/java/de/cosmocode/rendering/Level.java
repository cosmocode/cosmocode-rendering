package de.cosmocode.rendering;

/**
 * An enum implementation of the {@link RenderingLevel} based on
 * clothing size letter codes as described in
 * <a href="http://en.wikipedia.org/wiki/EN_13402#Letter_codes">Wikipedia</a>.
 *
 * @author Willi Schoenborn
 */
public enum Level implements RenderingLevel {

    S, M, L, XL, XXL;
    
    @Override
    public boolean lt(RenderingLevel level) {
        return !ge(level);
    }
    
    @Override
    public boolean le(RenderingLevel level) {
        return eq(level) || lt(level);
    }
    
    @Override
    public boolean eq(RenderingLevel level) {
        return equals(level);
    }
    
    @Override
    public boolean ge(RenderingLevel level) {
        return eq(level) || gt(level);
    }
    
    @Override
    public boolean gt(RenderingLevel level) {
        return this.compareTo(Level.class.cast(level)) > 0;
    }
    
}
