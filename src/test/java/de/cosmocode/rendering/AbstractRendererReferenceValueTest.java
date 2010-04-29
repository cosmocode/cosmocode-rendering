package de.cosmocode.rendering;

import java.nio.CharBuffer;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import de.cosmocode.junit.UnitProvider;

/**
 * Tests reference type value methods in {@link Renderer}.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractRendererReferenceValueTest implements UnitProvider<Renderer> {
    
    /**
     * Tests {@link Renderer#value(Date)} with null.
     */
    @Test
    public void valueDateNull() {
        final Date date = null;
        unit().list().value(date);
    }
    
    /**
     * Tests {@link Renderer#value(Date)} to return this.
     */
    @Test
    public void valueDateThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list().value(new Date()));
    }
    
    /**
     * Tests {@link Renderer#value(Date)} as first call.
     */
    @Test(expected = RenderingException.class)
    public void valueDateInitial() {
        unit().value(new Date());
    }
    
    /**
     * Tests {@link Renderer#value(Date)} after key.
     */
    @Test
    public void valueDateKey() {
        unit().map().key("k").value(new Date());
    }
    
    /**
     * Tests {@link Renderer#value(Date)} after list.
     */
    @Test
    public void valueDateList() {
        unit().list().value(new Date());
    }
    
    /**
     * Tests {@link Renderer#value(Date)} after map.
     */
    @Test(expected = RenderingException.class)
    public void valueDateMap() {
        unit().map().value(new Date());
    }
    
    /**
     * Tests {@link Renderer#value(Date)} after done.
     */
    @Test(expected = RenderingException.class)
    public void valueDateDone() {
        unit().map().endMap().value(new Date());
    }
    
    /**
     * Tests {@link Renderer#value(Calendar)} with null.
     */
    @Test
    public void valueCalendarNull() {
        final Calendar calendar = null;
        unit().list().value(calendar);
    }
    
    /**
     * Tests {@link Renderer#value(Calendar)} to return this.
     */
    @Test
    public void valueCalendarThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list().value(Calendar.getInstance()));
    }
    
    /**
     * Tests {@link Renderer#value(Calendar)} as first call.
     */
    @Test(expected = RenderingException.class)
    public void valueCalendarInitial() {
        unit().value(Calendar.getInstance());
    }
    
    /**
     * Tests {@link Renderer#value(Calendar)} after key.
     */
    @Test
    public void valueCalendarKey() {
        unit().map().key("k").value(Calendar.getInstance());
    }
    
    /**
     * Tests {@link Renderer#value(Calendar)} after list.
     */
    @Test
    public void valueCalendarList() {
        unit().list().value(Calendar.getInstance());
    }
    
    /**
     * Tests {@link Renderer#value(Calendar)} after map.
     */
    @Test(expected = RenderingException.class)
    public void valueCalendarMap() {
        unit().map().value(Calendar.getInstance());
    }
    
    /**
     * Tests {@link Renderer#value(Calendar)} after done.
     */
    @Test(expected = RenderingException.class)
    public void valueCalendarDone() {
        unit().map().endMap().value(Calendar.getInstance());
    }
    
    /**
     * Tests {@link Renderer#value(Enum)} with null.
     */
    @Test
    public void valueEnumNull() {
        final Mode mode = null;
        unit().list().value(mode);
    }
    
    /**
     * Tests {@link Renderer#value(Enum)} to return this.
     */
    @Test
    public void valueEnumThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list().value(Mode.INITIAL));
    }
    
    /**
     * Tests {@link Renderer#value(Enum)} as first call.
     */
    @Test(expected = RenderingException.class)
    public void valueEnumInitial() {
        unit().value(Mode.INITIAL);
    }
    
    /**
     * Tests {@link Renderer#value(Enum)} after key.
     */
    @Test
    public void valueEnumKey() {
        unit().map().key("k").value(Mode.INITIAL);
    }
    
    /**
     * Tests {@link Renderer#value(Enum)} after list.
     */
    @Test
    public void valueEnumList() {
        unit().list().value(Mode.INITIAL);
    }
    
    /**
     * Tests {@link Renderer#value(Enum)} after map.
     */
    @Test(expected = RenderingException.class)
    public void valueEnumMap() {
        unit().map().value(Mode.INITIAL);
    }
    
    /**
     * Tests {@link Renderer#value(Enum)} after done.
     */
    @Test(expected = RenderingException.class)
    public void valueEnumDone() {
        unit().map().endMap().value(Mode.INITIAL);
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} with null.
     */
    @Test
    public void valueCharSequenceNull() {
        final CharSequence sequence = null;
        unit().list().value(sequence);
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} to return this.
     */
    @Test
    public void valueCharSequenceThis() {
        final Renderer unit = unit();
        Assert.assertSame(unit, unit.list().value(""));
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} as first call.
     */
    @Test(expected = RenderingException.class)
    public void valueCharSequenceInitial() {
        unit().value("v");
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} after key.
     */
    @Test
    public void valueCharSequenceKey() {
        unit().map().key("k").value(new StringBuffer());
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} after list.
     */
    @Test
    public void valueCharSequenceList() {
        unit().list().value(new StringBuilder());
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} after map.
     */
    @Test(expected = RenderingException.class)
    public void valueCharSequenceMap() {
        unit().map().value(new String());
    }
    
    /**
     * Tests {@link Renderer#value(CharSequence)} after done.
     */
    @Test(expected = RenderingException.class)
    public void valueCharSequenceDone() {
        unit().map().endMap().value(CharBuffer.allocate(1));
    }
    
}
