package StringCalculator;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

  List<String> negatives = new ArrayList<>();

  public int add(String numbers) throws Exception {
    int sum = 0;

    if(numbers.isEmpty()) return sum;

    List<String> delimiters = getDelimiters(numbers);

    if(checkSingleValue(numbers, delimiters)) return Integer.valueOf(numbers);

    int indexOfDelimiterNewLine = numbers.indexOf("\n");
    if (delimiters.size() > 2) numbers = numbers.substring( indexOfDelimiterNewLine+ 1);

    return calculateSum(numbers, delimiters);
  }

  boolean checkSingleValue(String input, List<String> delimiters) throws Exception {

    // if a delimiter is found, it is not a single value
    for (String delimiter : delimiters) {
      if (input.contains(delimiter)) {
        return false;
      }
    }

    checkSingleNegativeNumber(input);

    return true;
  }

  List<String> getDelimiters(String input) {

    List<String> delimiters = new ArrayList<>();
    delimiters.add(",");
    delimiters.add("\n");

    try {
      if(input.substring(0,2).equals("//")){
        String del = "";

        int indexOfDelimiterNewLine = input.indexOf("\n");
        del = del + input.substring(2, indexOfDelimiterNewLine);
        delimiters.add(del);
      }
    }
    catch(Exception e) {
      System.err.println("No extra delimiters found");
    }

    return delimiters;
  }

  String createCharactersRegex(List<String> delimiters) {
    String regex = "(,|\n";

    if(delimiters.size() == 2) {
      return regex + ')';
    }

    for(String c : delimiters) {
      if (!regex.contains(c)){
        regex = regex + '|' + c;
      }
    }

    return regex + ')';
  }

  int calculateSum(String numbers, List<String> delimiters) throws Exception {

    int sum = 0;

    String regex = createCharactersRegex(delimiters);

    String[] individualNumbers = numbers.split(regex);

    for (String number : individualNumbers) {
      checkMultipleNegativeNumbers(number);

      if (!number.equals("")) sum = sum + Integer.valueOf(number);
    }

    if(!negatives.isEmpty()) throwMultipleNegativeNumbersException();

    return sum;
  }

  void checkSingleNegativeNumber(String number) throws  Exception {
    if (number.charAt(0) == '-') throw new Exception("Negatives not allowed! Found: " + number);
  }

  void checkMultipleNegativeNumbers(String number) {
    if (number.charAt(0) == '-') {
      negatives.add(number);
    }
  }

  void throwMultipleNegativeNumbersException() throws Exception {
    String aux = "";
    for (String negative : negatives) {
      aux = aux + negative + " ";
    }

    throw new Exception("Negatives not allowed! Found: " + aux);
  }
}
