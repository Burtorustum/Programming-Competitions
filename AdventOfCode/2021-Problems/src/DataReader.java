import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataReader {

  public static List<String> getLines(String fileName) throws IOException {
    fileName = "AdventOfCode\\2021-Problems\\data files\\" + fileName;
    Path path = Paths.get(fileName);
    return Files.readAllLines(path, StandardCharsets.UTF_8);
  }
}
