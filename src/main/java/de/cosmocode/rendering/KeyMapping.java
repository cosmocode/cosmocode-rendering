package de.cosmocode.rendering;

import java.util.Map;

import com.ibm.icu.impl.ICUService.Key;

/**
 * A mapping of {@link Class} to {@link Key}
 * which can be used with {@link Renderer}s to plug-in
 * new functionality.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
interface KeyMapping extends Map<Class<?>, KeyRenderer<?>> {

    /**
     * Finds a {@link KeyRenderer} capable of rendering the given type.
     * This method differs from {@link KeyMapping#get(Object)} as it finds the most
     * appropriate {@link KeyRenderer} for the given type.
     * 
     * <p>
     *   Take a mapping which contains the keys {@code Long.class}, 
     *   {@code Number.class} and {@code Object.class}. Calling
     *   {@code mapping.get(Double.class)} would return null,
     *   because no such mapping exists. Using {@code mapping.find(Double.class)}
     *   in contrast would return the renderer mapped by {@code Number.class}
     *   as it is the most narrow type matching. Using {@code mapping.find(String.class)}
     *   would return the renderer for Object.
     * </p>
     * 
     * @since 1.3
     * @param <T> the generic class type
     * @param type the key type
     * @return a {@link KeyRenderer} which can be used to render a value of T
     *          or null if no renderer was found
     */
    <T> KeyRenderer<T> find(Class<? extends T> type);
    
}
