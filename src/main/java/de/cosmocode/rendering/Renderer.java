package de.cosmocode.rendering;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * TODO Unix timestamps!!!
 * TODO single null values are ok, complex null values are not
 *
 * @author Willi Schoenborn
 */
public interface Renderer {

    Renderer list() throws RenderingException;
    
    Renderer endList() throws RenderingException;
    
    Renderer map() throws RenderingException;
    
    Renderer endMap() throws RenderingException;
    
    Renderer key(CharSequence key) throws RenderingException;
    
    Renderer key(Object key) throws RenderingException;
    
    Renderer nullValue() throws RenderingException;
    
    Renderer value(Object value) throws RenderingException;
    
    /**
     * TODO nulls will be given to the renderer!!!
     * 
     * @param <T>
     * @param value
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer value(T value, ValueRenderer<T> renderer) throws RenderingException;
    
    Renderer value(boolean value) throws RenderingException;
    
    Renderer value(long value) throws RenderingException;
    
    Renderer value(double value) throws RenderingException;
    
    Renderer value(Date value) throws RenderingException;
    
    Renderer value(Calendar value) throws RenderingException;
    
    Renderer value(Enum<?> value) throws RenderingException;
    
    Renderer value(CharSequence value) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer values(Object... values) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer values(Iterable<?> values) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param <T>
     * @param values
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer values(Iterable<T> values, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer values(Iterator<?> values) throws RenderingException;

    /**
     * TODO NPE
     * 
     * @param <T>
     * @param values
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer values(Iterator<T> values, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer value(Object... values) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer value(Iterable<?> values) throws RenderingException;

    /**
     * TODO NPE
     * 
     * @param <T>
     * @param values
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer value(Iterable<T> values, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param values
     * @return
     * @throws RenderingException
     */
    Renderer value(Iterator<?> values) throws RenderingException;

    /**
     * TODO NPE
     * 
     * @param <T>
     * @param values
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer value(Iterator<T> values, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param pairs
     * @return
     * @throws RenderingException
     */
    Renderer pairs(Map<?, ?> pairs) throws RenderingException;

    /**
     * TODO NPE
     * 
     * @param <T>
     * @param pairs
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer pairs(Map<?, T> pairs, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param pairs
     * @return
     * @throws RenderingException
     */
    Renderer pairs(Renderable pairs, RenderingLevel level) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param pairs
     * @return
     * @throws RenderingException
     */
    Renderer value(Map<?, ?> pairs) throws RenderingException;

    /**
     * TODO NPE
     * 
     * @param <T>
     * @param pairs
     * @param renderer
     * @return
     * @throws RenderingException
     */
    <T> Renderer value(Map<?, T> pairs, ValueRenderer<T> renderer) throws RenderingException;
    
    /**
     * TODO NPE
     * 
     * @param pairs
     * @return
     * @throws RenderingException
     */
    Renderer value(Renderable pairs, RenderingLevel level) throws RenderingException;
    
    @Override
    String toString() throws RenderingException;
    
}
