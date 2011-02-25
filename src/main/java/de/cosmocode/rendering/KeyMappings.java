package de.cosmocode.rendering;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import de.cosmocode.commons.Strings;

/**
 * Utility class for {@link KeyMapping}s.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
public final class KeyMappings {

    private static final KeyMapping DEFAULT;
    
    static {
        
        final Builder<Class<?>, KeyRenderer<?>> builder = ImmutableMap.builder();
        
        builder.put(CharSequence.class, CharSequenceKeyRenderer.INSTANCE);
        builder.put(Enum.class, EnumKeyRenderer.INSTANCE);
        builder.put(Object.class, ObjectKeyRenderer.INSTANCE);
        
        DEFAULT = newMapping(builder.build());
        
    }
    
    private KeyMappings() {
        
    }
    
    /**
     * Returns the immutable default mapping. The default mapping includes:
     * 
     * <table>
     *   <tr>
     *     <th>Type</th>
     *     <th>{@link KeyRenderer}</th>
     *     <th>Description</th>
     *   </tr>
     *   <tr>
     *     <td>{@link CharSequence}</td>
     *     <td>{@link CharSequenceKeyRenderer}</td>
     *     <td>returns the input unchanged</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Enum}</td>
     *     <td>{@link EnumKeyRenderer}</td>
     *     <td>uses {@link Enum#name()}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link Object}</td>
     *     <td>{@link ObjectKeyRenderer}</td>
     *     <td>uses {@link Strings#toString(Object)}</td>
     *   </tr>
     * </table>
     *
     * @since 1.3
     * @return an immutable mapping containing all predefined mapping entries
     */
    public static KeyMapping defaultMapping() {
        return DEFAULT;
    }

    /**
     * Returns a mapping backed by the given map
     * Changes in the supplied map will be visible in the returned mapping and
     * vice versa.
     * 
     * @since 1.1
     * @param map the backing map
     * @return a mapping backed by the given map
     * @throws NullPointerException if map is null
     */
    public static KeyMapping newMapping(Map<Class<?>, KeyRenderer<?>> map) {
        if (map instanceof KeyMapping) {
            return KeyMapping.class.cast(map);
        } else {
            return new SuperClassKeyMapping(map);
        }
    }
    
}
