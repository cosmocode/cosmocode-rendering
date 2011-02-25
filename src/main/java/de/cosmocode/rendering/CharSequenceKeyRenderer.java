package de.cosmocode.rendering;

import javax.annotation.Nullable;

/**
 * {@link KeyRenderer} for {@link CharSequence}s.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
public enum CharSequenceKeyRenderer implements KeyRenderer<CharSequence> {

    INSTANCE;
    
    @Override
    public CharSequence apply(@Nullable CharSequence key) {
        return key;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
}
