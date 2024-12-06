package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Trees intended to be used in storing mappings between fixed-length
 * sequences of bits and corresponding values.
 *
 * @author Cade Johnston
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The height of the tree. */
  private int size;

  /** The root node of the tree. */
  private BitTreeNode<String> rootNode;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Initialize a tree of a given size.
   *
   * @param n
   *   The size of the bit tree.
   */
  public BitTree(int n) {
    this.size = n;
    this.rootNode = new BitTreeNode<String>();
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   * Propogate the branches of the tree if needed.
   *
   * @param direction
   *   A character determining which child to check.
   * @param branch
   *   The node to check if it has a child.
   */
  private void propogate(char direction, BitTreeNode<String> branch) {
    if (direction == '0') {
      if (!(branch.hasLeftChild())) {
        branch.setLeft(new BitTreeNode<String>(branch));
      } // if
    } else if (direction == '1') {
      if (!(branch.hasRightChild())) {
        branch.setRight(new BitTreeNode<String>(branch));
      } // if
    } else {
      throw new IndexOutOfBoundsException("Unexpected character " + direction
              + " in the 'bits' string.");
    } // if / else if / else
  } // propogate(char, BitTreeNode<String>)

  /**
   * Recursively navigate the bit tree to get all paths and values.
   *
   * @param current
   *   The current node.
   * @param path
   *   The path up to now.
   * @param pen
   *   The PrintWriter that will output the Strings.
   */
  private void dumpHelper(BitTreeNode<String> current, String path, PrintWriter pen) {
    if (path.length() == this.size) {
      pen.println(path + "," + current.getValue());
    } else {
      if (current.hasLeftChild()) {
        dumpHelper(current.getLeft(), path + "0", pen);
      } // if
      if (current.hasRightChild()) {
        dumpHelper(current.getRight(), path + "1", pen);
      } // if
    } // if / else
  } // dumpHelper(BitTreeNode<String>, String, PrintWriter)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Set the value of a node in the tree.
   *
   * @param bits
   *   The path to follow in the tree.
   * @param value
   *   The value to put in the tree.
   */
  public void set(String bits, String value) {
    if (bits.length() == this.size) {
      BitTreeNode<String> cursor = this.rootNode;
      int depth = 0;
      while (depth < size) {
        propogate(bits.charAt(depth), cursor);
        // Since the current bit has been confirmed to be '0' or '1':
        if (bits.charAt(depth) == '0') {
          cursor = cursor.getLeft();
        } else {
          cursor = cursor.getRight();
        } // if / else
        depth++;
      } // while
      cursor.setValue(value);
    } else {
      throw new IndexOutOfBoundsException("Input length " + bits.length() + " is not the expected "
              + this.size + ".");
    } // if / else
  } // set(String, String)

  /**
   * Get the value of a node in the tree.
   *
   * @param bits
   *   The path to follow in the tree.
   * @return
   *   The value to of the node in the tree.
   */
  public String get(String bits) {
    if (bits.length() == this.size) {
      BitTreeNode<String> cursor = this.rootNode;
      int depth = 0;
      while (depth < size) {
        if (bits.charAt(depth) == '0') {
          if (cursor.hasLeftChild()) {
            cursor = cursor.getLeft();
          } else {
            throw new IndexOutOfBoundsException("The given path does not lead"
                    + " to a valid character.");
          } // if / else
        } else if (bits.charAt(depth) == '1') {
          if (cursor.hasRightChild()) {
            cursor = cursor.getRight();
          } else {
            throw new IndexOutOfBoundsException("The given path does not lead"
                    + " to a valid character.");
          } // if / else
        } else {
          throw new IndexOutOfBoundsException("Unexpected character " + bits.charAt(depth)
                  + " in the 'bits' string.");
        } // if / else if / else
        depth++;
      } // while
      return cursor.getValue();
    } else {
      throw new IndexOutOfBoundsException("Input length " + bits.length() + " is not the expected "
              + this.size + ".");
    } // if / else
  } // get(String, String)

  /**
   * Print the contents of the tree out in CSV format.
   *
   * @param pen
   *   The PrintWriter that will output the values.
   */
  public void dump(PrintWriter pen) {
    dumpHelper(rootNode, "", pen);
  } // dump(PrintWriter)

  /**
   * Fill the tree with values at the end of specified paths, found in
   * the source.
   *
   * @param source
   *   An input stream to take data from.
   */
  public void load(InputStream source) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(source));
      String[] nextArgument;
      while (in.ready()) {
        nextArgument = in.readLine().split(",");
        if (nextArgument.length == 2) {
          set(nextArgument[0], nextArgument[1]);
        } else {
          throw new IOException();
        } // if / else
      } // while
      in.close();
    } catch (IOException e) {
      // It's fine that it did not close, it will be removed eventually.
    } // try / catch
  } // load(InputStream)

} // class BitTree
