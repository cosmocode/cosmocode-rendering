package de.cosmocode.rendering;

import javax.annotation.Nonnull;

/**
 * 
 *
 * @since 
 * @author Willi Schoenborn
 */
interface ValueRendererRegistry {

    boolean contains(@Nonnull Class<?> type);
    
    <T> ValueRenderer<T> find(@Nonnull Class<? extends T> type);
    
}
