package edu.grinnell.csc207.util;

/**
 * Nodes intended for use as branches and leaves in a BitTree class.
 * Is parametrically typed.
 *
 * @param <T>
 *   The type of the value of the node
 *
 * @author Cade Johnston
 */
public class BitTreeNode<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The value of the node. */
  private T value;

  /** The left child of the node. */
  private BitTreeNode<T> leftNode;

  /** The right child of the node. */
  private BitTreeNode<T> rightNode;

  /** The parent of the node. */
  private BitTreeNode<T> parentNode;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build an empty BitTreeNode.
   */
  public BitTreeNode() {
  } // BitTree()

  /**
   * Build an empty BitTreeNode with a parent.
   *
   * @param parent
   *   The parent of the node.
   */
  public BitTreeNode(BitTreeNode<T> parent) {
    this.parentNode = parent;
  } // BitTree(BitTreeNode<T>)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Update the value of this node, discarding the old value.
   *
   * @param newVal
   *   The new value of the node.
   */
  public void setValue(T newVal) {
    this.value = newVal;
  } // setValue(T)

  /**
   * Return the value of this node.
   *
   * @return
   *   The value of this node.
   */
  public T getValue() {
    return this.value;
  } // getValue()

  /**
   * Update the left child of this node.
   *
   * @param newLeft
   *   The new left child of this node.
   */
  public void setLeft(BitTreeNode<T> newLeft) {
    this.leftNode = newLeft;
  } // setLeft(BitTreeNode<T>)

  /**
   * Return the left child of this node.
   *
   * @return
   *   The left child of this node.
   */
  public BitTreeNode<T> getLeft() {
    return this.leftNode;
  } // getLeft()

  /**
   * Update the right child of this node.
   *
   * @param newRight
   *   The new right child of this node.
   */
  public void setRight(BitTreeNode<T> newRight) {
    this.rightNode = newRight;
  } // setRight(BitTreeNode<T>)

  /**
   * Return the left child of this node.
   *
   * @return
   *   The left child of this node.
   */
  public BitTreeNode<T> getRight() {
    return this.rightNode;
  } // getRight()

  /**
   * Update the parent of this node.
   *
   * @param newParent
   *   The new parent of this node.
   */
  public void setParent(BitTreeNode<T> newParent) {
    this.parentNode = newParent;
  } // setParent(BitTreeNode<T>)

  /**
   * Return the parent of this node.
   *
   * @return
   *   The parent of this node.
   */
  public BitTreeNode<T> getParent() {
    return this.parentNode;
  } // getParent()

  /**
   * Return if this node has any children.
   *
   * @return
   *   If the node has any children.
   */
  public boolean hasChild() {
    return ((this.leftNode != null) || (this.rightNode != null));
  } // hasChild()

  /**
   * Return if this node has a left child.
   *
   * @return
   *   If the node has a left child.
   */
  public boolean hasLeftChild() {
    return (this.leftNode != null);
  } // hasLeftChild()

  /**
   * Return if this node has a right child.
   *
   * @return
   *   If the node has a right child.
   */
  public boolean hasRightChild() {
    return (this.rightNode != null);
  } // hasRightChild()

} // Class BitTreeNode<T>
