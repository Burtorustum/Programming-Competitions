import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day16 extends AProblem {

  public Day16(String fileName) throws IOException {
    super(fileName, "--- Day 16: Packet Decoder ---");
  }

  @Override
  String solvePartOne() {
    List<Integer> results = new ArrayList<>();

    for (String line : lines) {
      List<Character> converted = convert(line);
      results.add(decodeVersionSum(converted, false));
    }

    return results.toString();
  }

  @Override
  String solvePartTwo() {
    List<BigInteger> results = new ArrayList<>();

    for (String line : lines) {
      List<Character> converted = convert(line);
      results.add(decode(converted, false));
    }

    return results.toString();
  }

  List<Character> convert(String line) {
    List<Character> converted = new ArrayList<>();
    for (char c : line.toCharArray()) {
      String hex = c + "";
      int i = Integer.parseInt(hex, 16);
      StringBuilder b = new StringBuilder(Integer.toBinaryString(i));
      while (b.length() != 4) {
        b.insert(0, "0");
      }
      for (char bin : b.toString().toCharArray())
      converted.add(bin);
    }

    return converted;
  }

  int decodeVersionSum(List<Character> encoded, boolean inNumPacks) {
    int sum = 0;

    String versionStr = "" + encoded.remove(0);
    versionStr += encoded.remove(0);
    versionStr += encoded.remove(0);
    sum += Integer.parseInt(versionStr, 2);

    String type = "" + encoded.remove(0);
    type += encoded.remove(0);
    type += encoded.remove(0);
    int typeID = Integer.parseInt(type, 2);

    // literal packet -------------------
    if (typeID == 4) {

      boolean running = true;
      while (running) {
        List<Character> section = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
          section.add(encoded.remove(0));
        }
        if (section.remove(0) == '0') {
          running = false;
        }
      }
      return sum;
    }

    // Operator Packet -----------------------
    int lengthTypeID = Integer.parseInt("" + encoded.remove(0));


    // next 15 bits = number of bits in all subpackets
    if (lengthTypeID == 0) {
      StringBuilder lengthStr = new StringBuilder();
      for (int i = 0; i < 15; i++) {
        lengthStr.append(encoded.remove(0));
      }

      int bitLength = Integer.parseInt(lengthStr.toString(), 2);
      List<Character> sub = encoded.subList(0, bitLength);

      while (sub.size() != 0) {
        sum += decodeVersionSum(sub, false);
      }

      return sum;
    }

    // next 11 bits = number of subpackets
    StringBuilder numPackStr = new StringBuilder();
    for (int i = 0; i < 11; i++) {
      numPackStr.append(encoded.remove(0));
    }
    int numPackets = Integer.parseInt(numPackStr.toString(), 2);
    for (int i = 0; i < numPackets; i++) {
      sum += decodeVersionSum(encoded, true);
    }

    return sum;
  }

  BigInteger decode(List<Character> encoded, boolean inNumPacks) {
    encoded.remove(0);
    encoded.remove(0);
    encoded.remove(0);

    String type = "" + encoded.remove(0);
    type += encoded.remove(0);
    type += encoded.remove(0);
    int typeID = Integer.parseInt(type, 2);

    // literal packet -------------------
    if (typeID == 4) {
      StringBuilder literal = new StringBuilder();
      boolean running = true;

      while (running) {
        List<Character> section = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
          section.add(encoded.remove(0));
        }

        if (section.remove(0) == '0') {
          running = false;
        }
        for (char c : section) {
          literal.append(c);
        }
      }

      return BigInteger.valueOf(Long.parseLong(literal.toString(), 2));
    }

    // Operator Packet -----------------------
    int lengthTypeID = Integer.parseInt("" + encoded.remove(0));
    List<BigInteger> results = new ArrayList<>();

    // next 15 bits = number of bits in all subpackets
    if (lengthTypeID == 0) {
      StringBuilder lengthStr = new StringBuilder();
      for (int i = 0; i < 15; i++) {
        lengthStr.append(encoded.remove(0));
      }
      int bitLength = Integer.parseInt(lengthStr.toString(), 2);
      List<Character> sub = encoded.subList(0, bitLength);

      while (sub.size() != 0) {
        results.add(decode(sub, false));
      }

    } else {

      // next 11 bits = number of subpackets
      StringBuilder numPackStr = new StringBuilder();
      for (int i = 0; i < 11; i++) {
        numPackStr.append(encoded.remove(0));
      }
      int numPackets = Integer.parseInt(numPackStr.toString(), 2);

      for (int i = 0; i < numPackets; i++) {
        results.add(decode(encoded, true));
      }
    }

    return switch (typeID) {
      case 0 -> results.stream().reduce(BigInteger::add).get();
      case 1 -> results.stream().reduce(BigInteger::multiply).get();
      case 2 -> results.stream().min(BigInteger::compareTo).get();
      case 3 -> results.stream().max(BigInteger::compareTo).get();
      case 5 -> results.get(0).compareTo(results.get(1)) > 0 ? BigInteger.ONE : BigInteger.ZERO;
      case 6 -> results.get(0).compareTo(results.get(1)) < 0 ? BigInteger.ONE : BigInteger.ZERO;
      case 7 -> results.get(0).equals(results.get(1)) ? BigInteger.ONE : BigInteger.ZERO;
      default -> new BigInteger("-2");
    };
  }
}
