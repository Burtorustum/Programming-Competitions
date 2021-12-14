import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day14 extends AProblem {

  public Day14(String fileName) throws IOException {
    super(fileName, "--- Day 14: Extended Polymerization ---\n");
  }

  @Override
  String solvePartOne() {
    List<String> template = getTemplatePairs(lines.get(0));
    HashMap<String, String> rules = genRules();

    for (int i = 0; i < 10; i++) {
      template = applyRules(template, rules);
    }
    String s = template.stream().reduce((s1, s2) -> s1 + s2.charAt(1)).get();
    List<Character> chars = s.chars().mapToObj(e -> (char) e).toList();

    Set<Entry<Character, Long>> count = chars.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet();

    return "" +
        (count.stream().max(Map.Entry.comparingByValue()).get().getValue()
        - count.stream().min(Map.Entry.comparingByValue()).get().getValue());

  }

  @Override
  String solvePartTwo() {
    List<String> template = getTemplatePairs(lines.get(0));
    Map<String, String> rules = genRules();

    Map<String, BigInteger> pairCounts = new HashMap<>();
    rules.forEach((key, value) -> pairCounts.put(key, BigInteger.ZERO));
    for (String p : template) {
      if (pairCounts.containsKey(p)) {
        pairCounts.put(p, pairCounts.get(p).add(BigInteger.ONE));
      }
    }

    Map<String, BigInteger> numChar = new HashMap<>();
    rules.forEach((key, value) -> numChar.put(value.substring(1), BigInteger.ZERO));
    for (char c : lines.get(0).toCharArray()) {
      numChar.put(c+"", numChar.containsKey(c+"") ? BigInteger.ONE.add(numChar.get(c+"")) : BigInteger.ONE);
    }

    for (int i = 0; i < 40; i++) {

      Map<String, BigInteger> valChanges = new HashMap<>();
      for (String pair : pairCounts.keySet()) {
        if (pairCounts.containsKey(pair) && pairCounts.get(pair).compareTo(BigInteger.ZERO) > 0) {
          for (String out : applyRule(pair, rules)) {
            valChanges.put(out, pairCounts.get(pair).add(valChanges.getOrDefault(out, BigInteger.ZERO)));
          }
          valChanges.put(pair, pairCounts.get(pair).multiply(BigInteger.valueOf(-1)).add(valChanges.getOrDefault(pair, BigInteger.ZERO)));

          numChar.put(
              rules.get(pair).substring(1),
              numChar.get((rules.get(pair).substring(1))).add(pairCounts.get(pair)));
        }
      }

      for (String pair : valChanges.keySet()) {
        pairCounts.put(pair, pairCounts.get(pair).add(valChanges.get(pair)));
      }
    }

    return (numChar.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue()
        .subtract(
            numChar.entrySet().stream().min(Map.Entry.comparingByValue()).get().getValue())).toString();
  }

  HashMap<String, String> genRules() {
    HashMap<String, String> rules = new HashMap<>();

    for (String s : lines.subList(2, lines.size())) {
      rules.put(s.substring(0,2), s.charAt(0) + s.substring(6));
    }

    return rules;
  }

  String[] applyRule(String in, Map<String, String> rules) {
    String[] res = new String[2];

    res[0] = rules.get(in);
    res[1] = rules.get(in).charAt(1) + in.substring(1);
    return res;
  }

  List<String> applyRules(List<String> template, HashMap<String, String> rules) {
    List<String> result = new ArrayList<>();

    for (String curPair : template) {
      result.add(rules.get(curPair));
      result.add(rules.get(curPair).substring(1) + curPair.substring(1));
    }

    return result;
  }

  List<String> getTemplatePairs(String template) {
    List<String> temp = new ArrayList<>();
    for (int i = 0; i < template.length() - 1; i++) {
      temp.add(template.substring(i, i+2));
    }
    return temp;
  }

}
