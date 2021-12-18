import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day18 extends AProblem {

  public Day18(String fileName) throws IOException {
    super(fileName, "--- Day 18: Snailfish ---");
  }

  @Override
  String solvePartOne() {
    List<Node> input = getInput();

    Node result = input.remove(0);
    while (!input.isEmpty()) {
      result = add(result, input.remove(0));
    }

    return "" + result.magnitude();
  }

  @Override
  String solvePartTwo() {
    List<Node> input = getInput();
    int maxMag = 0;

    for (int i = 0; i < input.size(); i++) {
      for (int k = i+1; k < input.size(); k++) {
        Node a = input.get(i);
        Node b = input.get(k);

        maxMag = Math.max(maxMag, add(a, b).magnitude());
        maxMag = Math.max(maxMag, add(b, a).magnitude());
      }
    }

    return "" + maxMag;
  }

  Node add(Node a, Node b) {
    Node result = new Node(null, parse(a.toString()), parse(b.toString()));
    result.setAllRoot();

    while (true) {
      if (result.canExplode(0)) {
        result.explode(0);
        continue;
      } else if (result.canSplit()){
        result.split();
        continue;
      }
      break;
    }
    return result;
  }

  Node parse(String line) {
    Stack<Character> charStack = new Stack<>();
    Stack<Node> resStack = new Stack<>();

    for (char c : line.toCharArray()) {
      if (c == ']') {
        StringBuilder sub = new StringBuilder();
        sub.append(c);
        while (charStack.peek() != '[') {
          sub.append(charStack.pop());
        }

        sub.append(charStack.pop());
        sub.reverse();
        String subString = sub.toString();

        String[] split = subString.split(",");
        split[0] = split[0].replaceAll("\\[", "");
        split[0] = split[0].replaceAll("]", "");
        split[1] = split[1].replaceAll("\\[", "");
        split[1] = split[1].replaceAll("]", "");
        //System.out.println(Arrays.toString(split));

        if (split[0].length() != 0 && split[1].length() != 0) {
          resStack.add(new Node(
              new Node(Integer.parseInt(split[0])),
              new Node(Integer.parseInt(split[1]))));
        } else if (split[0].length() != 0) {
            resStack.add(new Node(
                new Node(Integer.parseInt(split[0])),
                resStack.pop()));
        } else if (split[1].length() != 0) {
            resStack.add(new Node(
                resStack.pop(),
               new Node(Integer.parseInt(split[1]))));
          } else {
            Node n =resStack.pop();
            resStack.add(new Node(resStack.pop(), n));
        }

      } else {
        charStack.add(c);
      }
    }
    return resStack.pop();
  }

  List<Node> getInput() {
    List<Node> out = new ArrayList<>();

    for (String line : lines) {
      if (line.equals("")) {
        break;
      }
      if (line.charAt(0) == '#') {
        continue;
      }
      Node n = parse(line);
      n.setAllRoot();
      out.add(n);
    }
    return out;
  }

  static class Node {
    public Node root;
    public Node left;
    public Node right;
    public int val;

    private Node(Node root, Node left, Node right, int val) {
      this.root = root;
      this.left = left;
      this.right = right;
      this.val = val;
    }

    public Node(Node root, int val) {
      this(root, null, null, val);
    }

    public Node(Node left, Node right) {
      this(null, left, right, -1);
    }

    public Node(int val) {
      this(null, null, null, val);
    }

    public Node(Node root, Node left, Node right) {
      this(root, left, right, -1);
    }

    public void setAllRoot() {
      this.root = null;
      this.left.setAllRootRec(this);
      this.right.setAllRootRec(this);
    }

    private void setAllRootRec(Node root) {
      this.root = root;
      if (this.isLeaf()) {
        return;
      }
      this.left.setAllRootRec(this);
      this.right.setAllRootRec(this);
    }

    public boolean isLeaf() {
      return this.val != -1;
    }

    public boolean canExplode(int depth) {
      if (!this.isLeaf() && depth > 3) {
        return true;
      }
      if (this.isLeaf()) {
        return false;
      }
      return this.left.canExplode(depth+1) || this.right.canExplode(depth+1);
    }

    public boolean canSplit() {
      if (this.isLeaf()) {
        return this.val >= 10;
      }
      return this.left.canSplit() || this.right.canSplit();
    }

    public boolean split() {
      if (this.isLeaf()) {
        if (this.val >= 10) {
          int leftVal = val / 2;
          int rightVal = leftVal*2 == this.val ? leftVal : leftVal+1;
          this.left = new Node(this, leftVal);
          this.right = new Node(this, rightVal);
          this.val = -1;
          return true;
        }
        return false;
      }

      return this.left.split() || this.right.split();
    }

    public boolean explode(int depth) {
      if (depth > 3 && !this.isLeaf()) {
        if (!this.left.isFarLeft()) {
          this.left.addNextLeftLeafUp(this.left.val);
        }
        if (!this.right.isFarRight()) {
          this.right.addNextRightLeafUp(this.right.val);
        }
        this.left = null;
        this.right = null;
        this.val = 0;
        return true;
      }
      if (this.isLeaf()) {
        return false;
      }

      return this.left.explode(depth+1) || this.right.explode(depth+1);
    }

    public void addNextLeftLeafUp(int val) {
      if (root == null) {
        this.left.addToFarRightLeaf(val);
      } else if (root.left == this) {
        root.addNextLeftLeafUp(val);
      } else {
        root.left.addToFarRightLeaf(val);
      }
    }

    public void addToFarRightLeaf(int val) {
      if (this.isLeaf()) {
        this.val = this.val + val;
        return;
      }
      this.right.addToFarRightLeaf(val);
    }

    public void addNextRightLeafUp(int val) {
      if (root == null) {
        this.right.addToFarLeftLeaf(val);
      } else if (root.right == this) {
        root.addNextRightLeafUp(val);
      } else {
        root.right.addToFarLeftLeaf(val);
      }
    }

    public void addToFarLeftLeaf(int val) {
      if (this.isLeaf()) {
        this.val = this.val + val;
        return;
      }
      this.left.addToFarLeftLeaf(val);
    }

    private boolean isFarLeft() {
      if (!this.isLeaf()) {
        return false;
      }
      return this.isFarLeftRec();
    }

    private boolean isFarLeftRec() {
      if (this.root == null) {
        return true;
      }
      if (this.root.left == this) {
        return this.root.isFarLeftRec();
      }
      return false;
    }

    private boolean isFarRight() {
      if (!this.isLeaf()) {
        return false;
      }
      return this.isFarRightRec();
    }

    private boolean isFarRightRec() {
      if (this.root == null) {
        return true;
      }
      if (this.root.right == this) {
        return this.root.isFarRightRec();
      }
      return false;
    }

    public int magnitude() {
      if (this.isLeaf()) {
        return this.val;
      }
      return 3*this.left.magnitude() + 2*this.right.magnitude();
    }

    @Override
    public String toString() {
      if (this.isLeaf()) {
        return "" + val;
      }
      return "[" + this.left.toString() + "," + this.right.toString() + "]";
    }
  }

}
