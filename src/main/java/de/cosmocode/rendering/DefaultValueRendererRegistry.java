package de.cosmocode.rendering;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSortedMap;

/**
 * 
 *
 * @since 
 * @author Willi Schoenborn
 */
enum DefaultValueRendererRegistry implements ValueRendererRegistry {

    INSTANCE;

    private final ImmutableSortedMap<Class<?>, ValueRenderer<?>> renderers = Rendering.getImmutableMapping();
    
    @Override
    public boolean contains(Class<?> type) {
        Preconditions.checkNotNull(type, "Type");
        return renderers.containsKey(type);
    }
    
    @Override
    public <T> ValueRenderer<T> find(Class<? extends T> type) {
        Preconditions.checkNotNull(type, "Type");
        return Rendering.find(renderers, type);
    }
    
}
