import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 extends AProblem {

  public Day8(String fileName) throws IOException {
    super(fileName, "--- Day 8: ---");
  }

  @Override
  String solvePartOne() {
  // 1-2, 4-4, 7-3, 8-7
    List<String> outputs = lines.stream()
        .map(s -> s.split(" \\| ")[1])
        .collect(Collectors.toList());

    int count = 0;
    for (String o : outputs) {
      String[] split = o.split(" ");
      for (String s : split) {
        if (s.length() == 7 || s.length() == 3 || s.length() == 4 || s.length() == 2) {
          count++;
        }
      }
    }

    return count + "";
  }

  @Override
  String solvePartTwo() {
    List<String> input = lines.stream()
        .map(s -> s.split(" \\| ")[0])
        .collect(Collectors.toList());

    List<String> output = lines.stream()
        .map(s -> s.split(" \\| ")[1])
        .collect(Collectors.toList());

    String[] nums = new String[10];
    String t, tl, tr, m, bl, br, b;

    int count = 0;

    for (int i = 0; i < input.size(); i++) {
      String[] split = input.get(i).split(" ");
      for (String s : split) {
        if (s.length() == 2) {
          nums[1] = s;
        } else if (s.length() == 4) {
          nums[4] = s;
        } else if (s.length() == 3) {
          nums[7] = s;
        } else if (s.length() == 7) {
          nums[8] = s;
        }
      }
      for (String s : split) {
        if (!s.equals(nums[4]) && !s.equals(nums[8])
            && s.contains(""+ nums[4].charAt(0))
            && s.contains(""+ nums[4].charAt(1))
            && s.contains(""+ nums[4].charAt(2))
            && s.contains(""+ nums[4].charAt(3))) {
          nums[9] = s;
        }
      }

      bl = nums[8];
      for (int k = 0; k < nums[9].length(); k++) {
        bl =  bl.replace("" + nums[9].charAt(k), "");
      }

      for (String s : split) {
        if (!s.equals(nums[8])
            && s.contains(""+ nums[7].charAt(0))
            && s.contains(""+ nums[7].charAt(1))
            && s.contains(""+ nums[7].charAt(2))
            && s.contains(bl)) {
          nums[0] = s;
        }
      }

      for (String s : split) {
        if (!s.equals(nums[9]) && !s.equals(nums[0]) && s.length() == 6) {
          nums[6] = s;
        }
      }

      for (String s : split) {
        if (!s.equals(nums[0]) && !s.equals(nums[6]) && !s.equals(nums[8]) && s.contains(bl + "")) {
          nums[2] = s;
        }
      }

      for (String s : split) {
        if (!s.equals(nums[0]) && !s.equals(nums[1]) && !s.equals(nums[4]) && !s.equals(nums[7]) && !s.equals(nums[8]) && !s.equals(nums[9])
            && s.contains(""+ nums[1].charAt(0))
            && s.contains(""+ nums[1].charAt(1))) {
          nums[3] = s;
        }
      }

      for (String s : split) {
        if (!s.equals(nums[0])
            && !s.equals(nums[1])
            && !s.equals(nums[2])
            && !s.equals(nums[3])
            && !s.equals(nums[4])
            && !s.equals(nums[6])
            && !s.equals(nums[7])
            && !s.equals(nums[8])
            && !s.equals(nums[9])) {
          nums[5] = s;
        }
      }


      StringBuilder s = new StringBuilder();
      for (String entry : output.get(i).split(" ")) {

        char[] entryChars = entry.toCharArray();
        int max = -1;


        // TODO this is all so dumb why didnt I sort the strings
        for (int x = 0; x < 10; x++) {
          boolean accept = true;
          for (char c: entryChars) {
            if (!nums[x].contains(c + "")) {
              accept = false;
              break;
            }
          }
          if (accept && x > max  && nums[x].length() == entryChars.length) {
            max = x;
          }
        }
        s.append(max);
      }
      count += Integer.parseInt(s.toString());

    }

    return count + "";
  }
}
