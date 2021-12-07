import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day4 extends AProblem {

  Queue<Integer> draws;

  public Day4(String fileName) throws IOException {
    super(fileName, "--- Day 4: Giant Squid ---");

    draws = Arrays.stream(lines.remove(0).split(","))
        .map(Integer::valueOf)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  private List<Board> genBoards() {
    List<Board> boards = new ArrayList<>();
    StringBuilder board = new StringBuilder();
    List<String> lineCopy = new ArrayList<>(lines);

    for (String line : lineCopy) {
      if (line.equals("") && board.length() != 0) {
        boards.add(new Board(board.toString()));
        board = new StringBuilder();
      } else {
        board.append(line).append("&");
      }
    }
    boards.add(new Board(board.toString()));

    return boards;
  }

  @Override
  String solvePartOne() {
    int remove = 1;
    Queue<Integer> copyDraws = new LinkedList<>(draws);
    List<Board> copyBoards = genBoards();

    while (!copyBoards.stream().map(Board::hasWon).collect(Collectors.toList()).contains(true)) {
      remove = copyDraws.remove();
      for (Board b : copyBoards) {
        b.markNum(remove);
      }
    }
    copyBoards = copyBoards.stream().filter(Board::hasWon).collect(Collectors.toList());
    int score = copyBoards.get(0).scoreBoard();

    return (score * remove) + "";
  }

  @Override
  String solvePartTwo() {
    int remove = 1;
    List<Board> boards = genBoards();

    while (boards.size() > 1) {
      remove = draws.remove();
      for (Board b : boards) {
        b.markNum(remove);
      }
      boards = boards.stream().filter(Board::hasNotWon).collect(Collectors.toList());
    }

    while (boards.get(0).hasNotWon()) {
      remove = draws.remove();
      boards.get(0).markNum(remove);
    }

    int score = boards.get(0).scoreBoard();
    return (score * remove) + "";
  }

}

class Board {
  final List<List<Integer>> board;
  final List<ArrayPos> marked;

  public Board(String boardString) {
    this.board = new ArrayList<>();
    this.marked = new ArrayList<>();

    if (boardString.charAt(0) == ' ') {
      boardString = boardString.substring(1);
    }
    boardString = boardString.replace("& ", "&");
    boardString = boardString.replace("  ", " ");
    List<String> lines = new ArrayList<>(List.of(boardString.split("&")));
    lines.remove("");

    for (String line : lines) {
      board.add(
          Arrays.stream(line.split(" "))
          .map(Integer::valueOf)
          .collect(Collectors.toList()));
    }
  }

  public Optional<ArrayPos> findNum(int n) {
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        if (board.get(row).get(col) == n) {
          return Optional.of(new ArrayPos(row, col));
        }
      }
    }
    return Optional.empty();
  }

  public void markNum(int n) {
    Optional<ArrayPos> posOptional = findNum(n);
    posOptional.ifPresent(this.marked::add);
  }

  public boolean hasNotWon() {
    return !hasWon();
  }

  public boolean hasWon() {
    int[] rowCounts = new int[5];
    int[] colCounts = new int[5];

    for (ArrayPos pos : marked) {
      int row = pos.row;
      int col = pos.col;
      rowCounts[row]++;
      colCounts[col]++;
    }

    for (int i = 0; i < 5; i++) {
      if (rowCounts[i] == 5 || colCounts[i] == 5) {
        return true;
      }
    }

    //return diagTLCount == 5 || diagTRCount == 5;
    return false;
  }

  int scoreBoard() {
    for (ArrayPos pos : marked) {
      board.get(pos.row).set(pos.col, 0);
    }

    int sum = 0;
    for (List<Integer> row : board) {
      for (Integer n : row) {
        sum += n;
      }
    }
    return sum;
  }


  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (List<Integer> row : board) {
      s.append(row).append("\n");
    }
    s.append("\n");
    return s.toString();
  }

}
