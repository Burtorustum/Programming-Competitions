// Mr Iwanski asked to think about
// singly linked list reverse in constant memory
// 12/7/2021
public class SinglyLinkedList<T> {
  Node<T> head;

  public static class Node<T> {
    final T val;
    Node<T> next;

    Node(T val) {
      this.val = val;
      this.next = null;
    }
  }

  public SinglyLinkedList() {
    this.head = null;
  }

  public SinglyLinkedList(Node<T> head) {
    this.head = head;
  }

  public SinglyLinkedList(T... values) {
    this.head = new Node<>(values[0]);
    Node<T> temp = this.head;

    for (int i = 1; i < values.length; i++) {
      temp.next = new Node<>(values[i]);
      temp = temp.next;
    }
  }

  // REVERSING LIST -----------------

  public void reverse(boolean recursive) {
    if (recursive) {
      this.head = reverseRec(this.head);
    } else {
      // Iterative solution:
      // Time complexity: Linear O(n)
      // Space complexity: Constant O(1)
      // -> 3 references to previously-created nodes. Space does not reference size of list

      Node<T> prev = null;
      Node<T> cur = this.head;
      Node<T> next;

      while (cur != null) {   // loop until tail reversed
        next = cur.next;      // next is the current's next
        cur.next = prev;      // set current next to prev
        prev = cur;           // set prev to current
        cur = next;           // set current to next
      }

      this.head = prev;       // set head to the last non-null node reversed
    }
  }

  // Recursive solution:
  // Time complexity: Linear O(n)
  // Space complexity: Constant O(1)
  // -> 1 reference to "rest" node. Space does not reference size of list (unless compiler is bad but I don't think so)
  private Node<T> reverseRec(Node<T> lead) {
    if (lead == null || lead.next == null)  // short-circuit makes this safe (need first part in case whole list is null)
      return lead;                          // return lead if at end of list

    Node<T> rest = reverseRec(lead.next);   // split the list and reverse rest of list
    lead.next.next = lead;                  // flip leading node and the node that follows
    lead.next = null;                       // Leading node has null before it, so when reversed null after it

    return rest;                            // return new head
  }

  // STRING GENERATION --------------

  @Override
  public String toString() {
    return this.genString(this.head);
  }

  private String genString(Node<T> node) {
    if (node == null){ return ""; }
    return node.val.toString() + " " + genString(node.next);
  }

  // RUN

  public static void main(String[] args) {
    System.out.println("RECURSIVE TEST: ------------");
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>(-1, 0, 1, 2, 3, 4, 5);
    System.out.println("original: " + list);
    list.reverse(true);
    System.out.println("reversed: " + list);

    System.out.println();

    System.out.println("ITERATIVE TEST: ------------");
    SinglyLinkedList<Integer> iterList = new SinglyLinkedList<>(-1, 0, 1, 2, 3, 82, 74);
    System.out.println("original: " + iterList);
    iterList.reverse(false);
    System.out.println("reversed: " + iterList);
  }
}
