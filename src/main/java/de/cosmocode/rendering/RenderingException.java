package de.cosmocode.rendering;

/**
 * Indicates an error during the rendering process.
 *
 * @author Willi Schoenborn
 */
public class RenderingException extends RuntimeException {

    private static final long serialVersionUID = 1548302642553998149L;

    public RenderingException() {
        super();
    }

    public RenderingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RenderingException(String message) {
        super(message);
    }

    public RenderingException(Throwable cause) {
        super(cause);
    }
    
}
