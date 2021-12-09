import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day9 extends AProblem {

  public Day9(String fileName) throws IOException {
    super(fileName, "--- Day 9: Smoke Basin ---\n");
  }

  @Override
  String solvePartOne() {
    ArrayList<ArrayList<Integer>> hMap = new ArrayList<>();
    getInput(hMap);

    int riskSum = 0;

    for (int row = 0; row < hMap.size(); row++) {
      for (int col = 0; col < hMap.get(row).size(); col++) {

        boolean right = checkRight(hMap, row, col);
        boolean left = checkLeft(hMap, row, col);
        boolean up = checkUp(hMap, row, col);
        boolean down = checkDown(hMap, row, col);

        if (right && left && up && down) {
          riskSum += hMap.get(row).get(col) + 1;
        }
      }
    }

    return riskSum + "";
  }

  @Override
  String solvePartTwo() {
    ArrayList<ArrayList<Integer>> hMap = new ArrayList<>();
    getInput(hMap);

    List<Integer> basinSizes = new ArrayList<>();

    for (int row = 0; row < hMap.size(); row++) {
      for (int col = 0; col < hMap.get(row).size(); col++) {

        boolean right = checkRight(hMap, row, col);
        boolean left  = checkLeft(hMap, row, col);
        boolean up    = checkUp(hMap, row, col);
        boolean down  = checkDown(hMap, row, col);

        if (right && left && up && down) {

          HashSet<ArrayPos> basin = new HashSet<>();
          basin.add(new ArrayPos(row, col));

          List<ArrayPos> toEvaluate = new ArrayList<>();
          toEvaluate.add(new ArrayPos(row, col));

          while (!toEvaluate.isEmpty()) {
            ArrayPos pos = toEvaluate.remove(0);

            boolean addRight = checkRightBasin(hMap, pos.row, pos.col)
                && !basin.contains(new ArrayPos(pos.row, pos.col + 1));

            boolean addLeft = checkLeftBasin(hMap, pos.row, pos.col)
                && !basin.contains(new ArrayPos(pos.row, pos.col - 1));

            boolean addUp = checkUpBasin(hMap, pos.row, pos.col)
                && !basin.contains(new ArrayPos(pos.row - 1, pos.col));

            boolean addDown = checkDownBasin(hMap, pos.row, pos.col)
                && !basin.contains(new ArrayPos(pos.row + 1, pos.col));

            if (addRight) {
              basin.add(new ArrayPos(pos.row, pos.col + 1));
              toEvaluate.add(new ArrayPos(pos.row, pos.col + 1));
            }

            if (addLeft) {
              basin.add(new ArrayPos(pos.row, pos.col - 1));
              toEvaluate.add(new ArrayPos(pos.row, pos.col - 1));
            }

            if (addUp) {
              basin.add(new ArrayPos(pos.row - 1, pos.col));
              toEvaluate.add(new ArrayPos(pos.row - 1, pos.col));
            }

            if (addDown) {
              basin.add(new ArrayPos(pos.row + 1, pos.col));
              toEvaluate.add(new ArrayPos(pos.row + 1, pos.col));
            }
          }

          basinSizes.add(basin.size());
        }
      }
    }

    return basinSizes.stream()
        .sorted(Integer::compareTo)
        .skip(basinSizes.size()-3)
        .reduce((a,b)-> a*b)
        .get().toString();
  }

  private void getInput(ArrayList<ArrayList<Integer>> map) {
    for (String line : lines) {
      ArrayList<Integer> row = new ArrayList<>();
      for (int i = 0; i < line.length(); i++) {
        row.add(Integer.parseInt(line.substring(i, i + 1)));
      }
      map.add(row);
    }
  }

  boolean checkRight(ArrayList<ArrayList<Integer>> map, int row, int col) {
    if (col == map.get(row).size() - 1) {
      return true;
    }
    return map.get(row).get(col) < map.get(row).get(col+1);
  }

  boolean checkRightBasin(ArrayList<ArrayList<Integer>> map, int row, int col) {
    if (col == map.get(row).size()-1) {
      return false;
    }
    return 9 != map.get(row).get(col+1);
  }

  boolean checkLeft(ArrayList<ArrayList<Integer>> map, int row, int col) {
    if (col == 0) {
      return true;
    }
    return map.get(row).get(col) < map.get(row).get(col-1);
  }

  boolean checkLeftBasin(ArrayList<ArrayList<Integer>> map, int row, int col) {
    if (col == 0) {
      return false;
    }
    return 9 != map.get(row).get(col-1);
  }

  boolean checkUp(ArrayList<ArrayList<Integer>> map, int row, int col) {
    if (row == 0) {
      return true;
    }
    return map.get(row).get(col) < map.get(row-1).get(col);
  }

  boolean checkUpBasin(ArrayList<ArrayList<Integer>> map, int row, int col) {
    if (row == 0) {
      return false;
    }
    return 9 != map.get(row-1).get(col);
  }

  boolean checkDown(ArrayList<ArrayList<Integer>> map, int row, int col) {
    if (row == map.size()-1) {
      return true;
    }
    return map.get(row).get(col) < map.get(row+1).get(col);
  }

  boolean checkDownBasin(ArrayList<ArrayList<Integer>> map, int row, int col) {
    if (row == map.size()-1) {
      return false;
    }
    return 9 != map.get(row+1).get(col);
  }
}