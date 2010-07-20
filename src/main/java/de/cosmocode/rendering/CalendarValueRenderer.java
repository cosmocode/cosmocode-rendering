package de.cosmocode.rendering;

import java.util.Calendar;

/**
 * Default {@link Calendar} {@link ValueRenderer}.
 *
 * @since 1.1
 * @author Willi Schoenborn
 */
enum CalendarValueRenderer implements ValueRenderer<Calendar> {

    INSTANCE;
    
    @Override
    public void render(Calendar value, Renderer renderer) throws RenderingException {
        if (value == null) {
            renderer.nullValue();
        } else {
            renderer.value(value.getTime());
        }
    }
    
}
