import java.io.IOException;
import java.util.List;

public abstract class AProblem {
  protected final String filename;
  protected final String day;
  protected List<String> lines;

  public AProblem(String fileName, String day) throws IOException {
    lines = new DataReader(fileName).lines;
    this.filename = fileName;
    this.day = day;
  }

  abstract String solvePartOne();
  abstract String solvePartTwo();

  String name() {
    return this.day + (this.filename.contains("test") ? " TEST" : "");
  }
}
