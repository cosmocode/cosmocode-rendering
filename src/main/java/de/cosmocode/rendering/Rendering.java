package de.cosmocode.rendering;

import com.google.common.base.Preconditions;

/**
 * 
 *
 * @author Willi Schoenborn
 */
public final class Rendering {

    /**
     * 
     * @param value
     * @return
     */
    public static RenderingLevel asLevel(int value) {
        return new IntegerRenderingLevel(value);
    }
    
    /**
     * {@link Integer} based {@link RenderingLevel} implementation.
     *
     * @author Willi Schoenborn
     */
    private static final class IntegerRenderingLevel extends AbstractRenderingLevel {

        private final int value;
        
        public IntegerRenderingLevel(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(RenderingLevel that) {
            return value - IntegerRenderingLevel.class.cast(that).value;
        }

    }
    
    /**
     * 
     * 
     * @param comparable
     * @return
     */
    public static RenderingLevel asLevel(final Comparable<RenderingLevel> comparable) {
        return new ComparableRenderingLevel(comparable);
    }
    
    /**
     * {@link Comparable} based {@link RenderingLevel} implementation.
     *
     * @author Willi Schoenborn
     */
    private static final class ComparableRenderingLevel extends AbstractRenderingLevel {
        
        private final Comparable<RenderingLevel> comparable;

        public ComparableRenderingLevel(Comparable<RenderingLevel> comparable) {
            this.comparable = Preconditions.checkNotNull(comparable, "Comparable");
        }

        @Override
        public int compareTo(RenderingLevel other) {
            return comparable.compareTo(other);
        }
        
    }

}
