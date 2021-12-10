import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Day10 extends AProblem {

  private final HashMap<Character, Character> chunkLimits;

  public Day10(String fileName) throws IOException {
    super(fileName, "--- Day 10: Syntax Scoring ---");

    this.chunkLimits = new HashMap<>();
    chunkLimits.put('{', '}');
    chunkLimits.put('[', ']');
    chunkLimits.put('(', ')');
    chunkLimits.put('<', '>');
  }

  @Override
  String solvePartOne() {

    List<Character> corruptions = new ArrayList<>();

    for (String line : lines) {
      Stack<Character> stack = new Stack<>();

      for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);
        if (chunkLimits.containsKey(c)) { stack.push(c);}
        else {
          char expected = chunkLimits.get(stack.pop());
          if (c != expected) {
            corruptions.add(c);
            break;
          }
        }
      }
    }

    int sum = 0;

    for (Character c : corruptions) {
      if      (c == ')')  sum += 3;
      else if (c == ']')  sum += 57;
      else if (c == '}')  sum += 1197;
      else                sum += 25137;
    }

    return "" + sum;
  }



  @Override
  String solvePartTwo() {

    List<String> incompleteLines = new ArrayList<>();

    for (String line : lines) {
      Stack<Character> stack = new Stack<>();
      boolean lineGood = true;

      for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);
        if (chunkLimits.containsKey(c)) { stack.push(c); }
        else {
          char expected = chunkLimits.get(stack.pop());
          if (c != expected) {
            lineGood = false;
            break;
          }
        }
      }
      if (lineGood) {
        incompleteLines.add(line);
      }
    }

    List<BigInteger> results = new ArrayList<>();
    final BigInteger FIVE = new BigInteger("5");

    for (String line : incompleteLines) {
      Stack<Character> stack = new Stack<>();

      for (char c : line.toCharArray()) {
        if (chunkLimits.containsKey(c)) { stack.push(c); }
        else                            { stack.pop(); }
      }

      BigInteger total = BigInteger.ZERO;

      while (!stack.empty()) {
        char c = chunkLimits.get(stack.pop());

        if      (c == ')') total    = total.multiply(FIVE).add(new BigInteger("1"));
        else if (c == ']') total    = total.multiply(FIVE).add(new BigInteger("2"));
        else if (c == '}') total    = total.multiply(FIVE).add(new BigInteger("3"));
        else total                  = total.multiply(FIVE).add(new BigInteger("4"));
      }
      results.add(total);
    }

    results.sort(BigInteger::compareTo);
    return results.get(results.size() / 2).toString();
  }
}
