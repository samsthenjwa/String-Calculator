import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class StringCalculatorTest {

    @Test
    public void sumsEmptyStringTo0() {
        assertThat(Calculate.Add(""), is(0));
    }

    @Test
    public void sumsSingleNumberToItself() {
        assertThat(Calculate.Add("5"), is(5));
        assertThat(Calculate.Add("42"), is(42));
    }

    @Test
    public void sumsTwoNumbersSeperatedByComma() {
        assertThat(Calculate.Add("1,2"), is(3));
        assertThat(Calculate.Add("1,3"), is(4));
    }

    @Test
    public void sumsThreeNumbersSeperatedByComma() {
        assertThat(Calculate.Add("1,2,3"), is(6));
    }

    @Test
    public void sumsNumbersDelimitedByNewline() {
        assertThat(Calculate.Add("1\n2"), is(3));
    }

    @Test
    public void sumsNumbersDelimitedByCommaOrNewline() {
        assertThat(Calculate.Add("1,2\n3"), is(6));
    }

    @Test
    public void usesDelimiterSepcified() {
        assertThat(Calculate.Add("//;\n1;2"), is(3));
        assertThat(Calculate.Add("//.\n2.3.1"), is(6));
    }

    @Test
    public void mapsNumbersAbove1000ToLastThreeDigits() {
        assertThat(Calculate.Add("1002"), is(2));
        assertThat(Calculate.Add("1040,10002"), is(42));
    }

    @Test
    public void acceptsDelimiterOfArbitraryLength() {
        assertThat(Calculate.Add("//[***]\n1***2***3"), is(6));
    }

    @Test
    public void acceptsMultipleDelimiters() {
        assertThat(Calculate.Add("//[-][;]\n1-2;3"), is(6));
        assertThat(Calculate.Add("//[--][...]\n2--3...4"), is(9));
    }
}
