import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day9 extends AProblem {

  public Day9(String fileName) throws IOException {
    super(fileName, "--- 9 ---");
  }

  @Override
  String solvePartOne() {
    ArrayList<ArrayList<Integer>> hmap = new ArrayList<>();
    getInput(hmap);

    int riskSum = 0;

    for (int row = 0; row < hmap.size(); row++) {
      for (int col = 0; col < hmap.get(row).size(); col++) {

        boolean right = checkRight(hmap, row, col);
        boolean left = checkLeft(hmap, row, col);
        boolean up = checkUp(hmap, row, col);
        boolean down = checkDown(hmap, row, col);

        if (right && left && up && down) {
          riskSum += hmap.get(row).get(col) + 1;
        }
      }
    }

    return riskSum + "";
  }

  @Override
  String solvePartTwo() {
    ArrayList<ArrayList<Integer>> hmap = new ArrayList<>();
    getInput(hmap);

    List<Integer> basinSizes = new ArrayList<>();

    for (int row = 0; row < hmap.size(); row++) {
      for (int col = 0; col < hmap.get(row).size(); col++) {

        boolean right = checkRight(hmap, row, col);
        boolean left = checkLeft(hmap, row, col);
        boolean up = checkUp(hmap, row, col);
        boolean down = checkDown(hmap, row, col);

        if (right && left && up && down) {
          int curRow = row;
          int curCol = col;

          HashSet<ArrayPos> basin = new HashSet<>();
          basin.add(new ArrayPos(curRow, curCol));

          List<ArrayPos> toEvaluate = new ArrayList<>();
          toEvaluate.add(new ArrayPos(curRow, curCol));

          while (!toEvaluate.isEmpty()) {
            ArrayPos pos = toEvaluate.remove(0);
            curRow = pos.row;
            curCol = pos.col;

            if (checkRightBasin(hmap, curRow, curCol) && !basin.contains(new ArrayPos(curRow, curCol+1))) {
                basin.add(new ArrayPos(curRow, curCol+1));
                toEvaluate.add(new ArrayPos(curRow, curCol+1));
            }

            if (checkLeftBasin(hmap, curRow, curCol) && !basin.contains(new ArrayPos(curRow, curCol-1))) {
                basin.add(new ArrayPos(curRow, curCol-1));
                toEvaluate.add(new ArrayPos(curRow, curCol-1));
              }

            if (checkUpBasin(hmap, curRow, curCol) && !basin.contains(new ArrayPos(curRow-1, curCol))) {
                basin.add(new ArrayPos(curRow-1, curCol));
                toEvaluate.add(new ArrayPos(curRow-1, curCol));
              }

            if (checkDownBasin(hmap, curRow, curCol) && !basin.contains(new ArrayPos(curRow+1, curCol))) {
                basin.add(new ArrayPos(curRow+1, curCol));
                toEvaluate.add(new ArrayPos(curRow+1, curCol));
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
        row.add(Integer.parseInt(line.substring(i, i+1)));
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