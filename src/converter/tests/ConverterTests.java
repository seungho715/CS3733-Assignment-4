package converter.tests;

import converter.ElbonianArabicConverter;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the ElbonianArabicConverter class.
 */
public class ConverterTests {

    @Test
    public void ElbonianToArabicSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("1");
        assertEquals(converter.toElbonian(), "I");
    }

    @Test
    public void ArabicToElbonianSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("I");
        assertEquals(converter.toArabic(), 1);
    }

    @Test(expected = MalformedNumberException.class)
    public void malformedNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new MalformedNumberException("TEST");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new ValueOutOfBoundsException("0");
    }

    // TODO Add more test cases
    @Test
    public void testlowerCase() throws ValueOutOfBoundsException, MalformedNumberException {
        ElbonianArabicConverter conv = new ElbonianArabicConverter("dDlLvV");
        assertEquals(conv.toArabic(),444);
    }

    @Test
    public void testmaxNum() throws ValueOutOfBoundsException, MalformedNumberException {
        ElbonianArabicConverter conv = new ElbonianArabicConverter("MMMDDDCCCLLLXXXVVVIII");
        assertEquals(conv.toArabic(),4998);
    }

    @Test
    public void testZero() throws ValueOutOfBoundsException, MalformedNumberException {
        ElbonianArabicConverter conv = new ElbonianArabicConverter("");
        assertEquals(conv.toArabic(),0);
    }

    @Test(expected = MalformedNumberException.class)
    public void testoutoforder() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MDd");
    }

    @Test(expected = MalformedNumberException.class)
    public void testwrongFour() throws ValueOutOfBoundsException, MalformedNumberException {
        ElbonianArabicConverter conv = new ElbonianArabicConverter("CCCC");
    }

    @Test (expected = ValueOutOfBoundsException.class)
    public void testNegative() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter conv = new ElbonianArabicConverter("-1");
        conv.toElbonian();
    }

    @Test
    public void testElbonian() throws ValueOutOfBoundsException, MalformedNumberException {
        ElbonianArabicConverter conv = new ElbonianArabicConverter("MCV");
        assertEquals(conv.toElbonian(), "MCV");
    }

    @Test
    public void testSimpleElbo() throws ValueOutOfBoundsException, MalformedNumberException {
        ElbonianArabicConverter conv = new ElbonianArabicConverter("1000");
        assertEquals(conv.toElbonian(), "M");
    }

}
