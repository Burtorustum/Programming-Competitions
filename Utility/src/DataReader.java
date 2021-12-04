import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataReader {
  public List<String> lines;

  public DataReader(String fileName) throws IOException {
    fileName = "Problems\\src\\data files\\" + fileName;
    Path path = Paths.get(fileName);
    this.lines = Files.readAllLines(path, StandardCharsets.UTF_8);
  }
}
