package StringCalculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTest {

  StringCalculator strCalculator = new StringCalculator();

  @Test
  public void GIVEN_emptyString_EXPECT_zero() throws Exception {
    int sum = strCalculator.add("");
    assertEquals(0, sum);
  }

  @Test
  public void GIVEN_singleNumberString_EXPECT_singleNumber() throws Exception {
    int sum1 = strCalculator.add("1");
    assertEquals(1, sum1);

    int sum2 = strCalculator.add("11");
    assertEquals(11, sum2);

    int sum3 = strCalculator.add("111");
    assertEquals(111, sum3);
  }

  @Test
  public void GIVEN_twoNumbersSeparatedByComma_EXPECT_sum() throws Exception {
    int sum1 = strCalculator.add("1,1");
    assertEquals(2, sum1);

    int sum2 = strCalculator.add("99,1");
    assertEquals(100, sum2);

    int sum3 = strCalculator.add("11,11");
    assertEquals(22, sum3);
  }

  @Test
  public void GIVEN_twoNumbersSeparatedByNewline_EXPECT_sum() throws Exception {
    int sum1 = strCalculator.add("1\n1");
    assertEquals(2, sum1);

    int sum2 = strCalculator.add("99\n1");
    assertEquals(100, sum2);

    int sum3 = strCalculator.add("11\n11");
    assertEquals(22, sum3);
  }

  @Test
  public void GIVEN_unknownAmountOfNumbersSeparatedByComma_EXPECT_sum() throws Exception {
    int sum1 = strCalculator.add("1,1,1");
    assertEquals(3, sum1);

    int sum2 = strCalculator.add("40,1,1,120");
    assertEquals(162, sum2);

    int sum3 = strCalculator.add("0,111,2000,1");
    assertEquals(2112, sum3);
  }

  @Test
  public void GIVEN_unknownAmountOfNumbersSeparatedByNewline_EXPECT_sum() throws Exception {
    int sum1 = strCalculator.add("1\n1\n1");
    assertEquals(3, sum1);

    int sum2 = strCalculator.add("40\n1\n1\n120");
    assertEquals(162, sum2);

    int sum3 = strCalculator.add("0\n111\n2000\n1");
    assertEquals(2112, sum3);
  }

  @Test
  public void GIVEN_unknowAmountOfNumbersSeparatedByNewlineOrComma_EXPECT_sum() throws Exception {
    int sum1 = strCalculator.add("1\n1,1");
    assertEquals(3, sum1);

    int sum2 = strCalculator.add("40,1\n1,120");
    assertEquals(162, sum2);

    int sum3 = strCalculator.add("0\n111,2000\n1");
    assertEquals(2112, sum3);
  }

  @Test
  public void GIVEN_delimiter_AND_singleNumber_EXPECT_singleNumber() throws Exception {
    int sum1 = strCalculator.add("//;\n12");
    assertEquals(12, sum1);

    int sum2 = strCalculator.add("//;\n1235");
    assertEquals(1235, sum2);

    int sum3 = strCalculator.add("//aaa\n1");
    assertEquals(1, sum3);
  }

  @Test
  public void GIVEN_delimiter_EXPECT_sum() throws Exception {
    int sum1 = strCalculator.add("//;\n1;2");
    assertEquals(3, sum1);

    int sum2 = strCalculator.add("//;\n1;2,3\n5");
    assertEquals(11, sum2);

    int sum3 = strCalculator.add("//aaa\n1aaa2,3\n5");
    assertEquals(11, sum3);
  }

  // Negative tests
  @Test(expected = Exception.class)
  public void GIVEN_singleNegativeNumber_EXPECT_exception() throws Exception {
    strCalculator.add("-1");
  }

  @Test(expected = Exception.class)
  public void GIVEN_unknownAmountOfNumbers_EXPECT_exception() throws Exception {
    strCalculator.add("1,-22\n3");
  }

  @Test(expected = Exception.class)
  public void GIVEN_unknownAmountOfNumbers_AND_delimiter_EXPECT_exception() throws Exception {
    strCalculator.add("//;1,-22\n3;44");
  }

  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  @Test
  public void GIVEN_singleNegativeNumber_EXPECT_exception_message() throws Exception {
    expectedEx.expect(Exception.class);
    expectedEx.expectMessage("Negatives not allowed! Found: -22");
    strCalculator.add("-22");
  }

  @Test
  public void GIVEN_unknownAmountOfNegativeNumbers_EXPECT_exception_message() throws Exception {
    expectedEx.expect(Exception.class);
    expectedEx.expectMessage("Negatives not allowed! Found: -22 -444");
    strCalculator.add("-22,33\n-444\n1");
  }

  @Test
  public void GIVEN_unknownAmountOfNegativeNumbers_AND_delimiter_EXPECT_exception_message() throws Exception {
    expectedEx.expect(Exception.class);
    expectedEx.expectMessage("Negatives not allowed! Found: -22 -444 -3");
    strCalculator.add("//aaa\n-22,33\n-444aaa-3\n1");
  }
}