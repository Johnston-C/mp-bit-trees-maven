package edu.grinnell.csc207.util;

import java.io.InputStream;
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

  private void propogateEmpty(char direction, BitTreeNode<String> branch) {
    if (direction == '0') {
      if (!(branch.hasLeftChild())) {
        branch.setLeft(new BitTreeNode<String>(branch));
      }
    } else if (direction == '1') {
      if (!(branch.hasRightChild())) {
        branch.setRight(new BitTreeNode<String>(branch));
      }
    } else {
      throw new IndexOutOfBoundsException("Unexpected character " + direction + " in the 'bits' string.");
    } // if / else if / else
  } //

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
        propogateEmpty(bits.charAt(depth), cursor);
        // Since the current bit has been confirmed to be '0' or '1':
        if (bits.charAt(depth) == '0') {
          cursor = cursor.getLeft();
        } else {
          cursor = cursor.getRight();
        } // if / else
      } // while
      cursor.setValue(value);
    } else {
      throw new IndexOutOfBoundsException("Input length " + bits.length() + " is not the expected " + this.size + ".");
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
          cursor = cursor.getLeft();
        } else if (bits.charAt(depth) == '1') {
          cursor = cursor.getRight();
        } else {
          throw new IndexOutOfBoundsException("Unexpected character " + bits.charAt(depth) + " in the 'bits' string.");
        }// if / else if / else
      } // while
      return cursor.getValue();
    } else {
      throw new IndexOutOfBoundsException("Input length " + bits.length() + " is not the expected " + this.size + ".");
    } // if / else
  } // get(String, String)

  /**
   *
   */
  public void dump(PrintWriter pen) {
    // STUB
  } // dump(PrintWriter)

  /**
   *
   */
  public void load(InputStream source) {
    
    // STUB
  } // load(InputStream)

} // class BitTree
