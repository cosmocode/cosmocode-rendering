package de.cosmocode.rendering;

import java.util.SortedMap;

import com.google.common.base.Preconditions;

/**
 * 
 *
 * @since 
 * @author Willi Schoenborn
 */
final class SortedMapValueRendererRegistry implements ValueRendererRegistry {

    private final SortedMap<Class<?>, ValueRenderer<?>> renderers;
    
    public SortedMapValueRendererRegistry(SortedMap<Class<?>, ValueRenderer<?>> renderers) {
        this.renderers = Preconditions.checkNotNull(renderers, "Renderers");
    }
    
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
