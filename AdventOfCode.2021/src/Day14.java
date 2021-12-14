import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day14 extends AProblem {

  public Day14(String fileName) throws IOException {
    super(fileName, "--- 14 ---");
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

    Set<String> uniqueChars = new HashSet<>(template.stream().map(s -> s.substring(0,1)).toList());
    uniqueChars.add(template.get(template.size()-1).substring(1));
    rules.forEach((key, value) -> uniqueChars.add(value.substring(1)));
    Map<String, BigInteger> pairCounts = allPairs(uniqueChars);

    Map<String, BigInteger> numChar = new HashMap<>();
    for (String s : uniqueChars) {
      int num = 0;
      for (char c : lines.get(0).toCharArray()) {
        if (s.charAt(0) == c) {
          num++;
        }
      }
      numChar.put(s, new BigInteger(num + ""));
    }

    for (String p : template) {
      pairCounts.put(p, pairCounts.get(p).add(BigInteger.ONE));
    }

    for (int i = 0; i < 40; i++) {

      Map<String, BigInteger> valChanges = new HashMap<>();
      for (String pair : pairCounts.keySet()) {
        if (pairCounts.get(pair).compareTo(BigInteger.ZERO) > 0) {
          String[] addPairs = applyRule(pair, rules);

          valChanges.put(addPairs[0], pairCounts.get(pair).add(valChanges.getOrDefault(addPairs[0], BigInteger.ZERO)));
          valChanges.put(addPairs[1], pairCounts.get(pair).add(valChanges.getOrDefault(addPairs[1], BigInteger.ZERO)));
          valChanges.put(pair, pairCounts.get(pair).multiply(new BigInteger("-1")).add(valChanges.getOrDefault(pair, BigInteger.ZERO)));

          if (rules.containsKey(pair)) {
            numChar.put(rules.get(pair).substring(1), numChar.get((rules.get(pair).substring(1))).add(pairCounts.get(pair)));
          }
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

  Map<String, BigInteger> allPairs(Set<String> uniques) {
    Map<String, BigInteger> allPairs = new HashMap<>();
    for (String s1 : uniques) {
      for (String s2 : uniques) {
        allPairs.put(s1+s2, BigInteger.ZERO);
      }
    }
    return allPairs;
  }
}
