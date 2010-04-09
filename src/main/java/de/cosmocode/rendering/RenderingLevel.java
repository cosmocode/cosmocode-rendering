package de.cosmocode.rendering;

/**
 * 
 * TODO methods are allowed to throw ClassCastExceptions
 *
 * @author Willi Schoenborn
 */
public interface RenderingLevel {

    boolean lt(RenderingLevel level) throws ClassCastException;
    
    boolean le(RenderingLevel level) throws ClassCastException;
    
    boolean eq(RenderingLevel level) throws ClassCastException;
    
    boolean ge(RenderingLevel level) throws ClassCastException;
    
    boolean gt(RenderingLevel level) throws ClassCastException;
    
}
