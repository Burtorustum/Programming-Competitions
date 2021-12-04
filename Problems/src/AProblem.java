import java.io.IOException;
import java.util.List;

public abstract class AProblem {
  protected List<String> lines;

  public AProblem(String fileName) throws IOException {
    lines = new DataReader(fileName).lines;
  }

  abstract String solvePartOne();
  abstract String solvePartTwo();

  abstract String name();
}
