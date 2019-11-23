import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Provides an implementation of a binary search tree
 * with no balance constraints, implemented with linked nodes.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-04-30
 */
public class Bst<T extends Comparable<T>> implements Iterable<T> {

   ////////////////////////////////////////////////////////////////////////////
   // I M P L E M E N T   T H E   C O N T A I N S   M E T H O D   B E L O W  //
   ////////////////////////////////////////////////////////////////////////////
   /**
    * Returns true if this bst contains element, false otherwise. 
    */
   public boolean contains(T element) {
      Node n = root; // copy of the bst root Node
      int cmp = 0; // compareTo integer
      while (n != null) { // while loop or recursion should work
         cmp = element.compareTo(n.element); //compares element to current n.element
         if (cmp == 0) {
            return true;
         } // if same, returns true
         else if (cmp < 0) {
            n = n.left;
         } // if element<n.element; n = the left(lesser) node value
         else if (cmp > 0) {
            n = n.right;
         } // if element>n.element; n = right (greater) node value
      } // loops until equivalent value found
      return false;
   } // if never found, then returns false once n == null; Means it's not in the BST


   ////////////////////////////////////////////////////////////////////
   //  D O   N O T   M O D I F Y   B E L O W   T H I S   P O I N T   //
   ////////////////////////////////////////////////////////////////////


   /** Sample driver. */
   public static void main(String[] args) {
      Bst<Integer> bst = new Bst<>();
      int N = 10;
      Integer[] values = new Integer[N];
      for (int i = 1; i <= N; i++) {
         values[i - 1] = i;
      }
      Collections.<Integer>shuffle(Arrays.<Integer>asList(values));
      for (Integer value : values) {
         bst.add(value);
      }
      System.out.println(bst);
      for (int i = (N - N - 5); i <= N + 5; i++) {
         System.out.println(i + ": " + bst.contains(i));
      }
   
      
   }


   ////////////
   // Fields //
   ////////////

   // the root of this bst
   private Node root;

   // the number of nodes in this bst
   private int size;

   /** Defines the node structure for this bst. */
   private class Node {
      T element;
      Node left;
      Node right;
   
      /** Constructs a node containing the given element. */
      public Node(T elem) {
         element = elem;
      }
   }


   ////////////////////
   // M E T R I C S  //
   ////////////////////

   /**
    * Returns the number of elements in this bst.
    */
   public int size() {
      return size;
   }

   /**
    * Returns true if this bst is empty, false otherwise.
    */
   public boolean isEmpty() {
      return size == 0;
   }

   /**
    * Returns the height of this bst.
    */
   public int height() {
      return height(root);
   }

   /**
    * Returns the height of node n in this bst.
    */
   private int height(Node n) {
      if (n == null) {
         return 0;
      }
      int leftHeight = height(n.left);
      int rightHeight = height(n.right);
      return 1 + Math.max(leftHeight, rightHeight);
   }


   ////////////////////////////////////
   // A D D I N G   E L E M E N T S  //
   ////////////////////////////////////

   /**
    * Ensures this bst contains the specified element. Uses an iterative implementation.
    */
   public void add(T element) {
      // special case if empty
      if (root == null) {
         root = new Node(element);
         size++;
         return;
      }
   
      // find where this element should be in the tree
      Node n = root;
      Node parent = null;
      int cmp = 0;
      while (n != null) {
         parent = n;
         cmp = element.compareTo(parent.element);
         if (cmp == 0) {
            // don't add a duplicate
            return;
         } else if (cmp < 0) {
            n = n.left;
         } else {
            n = n.right;
         }
      }
   
      // add element to the appropriate empty subtree of parent
      if (cmp < 0) {
         parent.left = new Node(element);
      } else {
         parent.right = new Node(element);
      }
      size++;
   }
   
   /**
    * Ensures this bst contains the specified element. Calls a recursive method.
    */
   public void put(T element) {
      root = put(element, root);
   }

   /**
    * Ensures this bst contains the specified element. Uses a recursive implementation.
    */
   private Node put(T element, Node n) {
      if (n == null) {
         size++;
         return new Node(element);
      }
      int cmp = element.compareTo(n.element);
      if (cmp < 0) {
         n.left = put(element, n.left);
      } else if (cmp > 0) {
         n.right = put(element, n.right);
      }
      return n;
   }


   //////////////////////
   // T O S T R I N G  //
   //////////////////////

   /**
    * Returns a string representation of the elements in this bst listed in
    * ascending natural order.
    */
   @Override
   public String toString() {
      return inorderList(root).toString();
   }

   /**
    * Returns a List containing the elements of this bst in ascending natural order.
    */
   private List<T> inorderList(Node n) {
      List<T> list = new ArrayList<>();
      buildInorderList(root, list);
      return list;
   }

   /**
    * Builds list from the elements of this bst in ascending natural order.
    */
   private void buildInorderList(Node n, List<T> list) {
      if (n != null) {
         buildInorderList(n.left, list);
         list.add(n.element);
         buildInorderList(n.right, list);
      }
   }


   ////////////////////////
   // I T E R A T I O N  //
   ////////////////////////

   /**
    * Provides an iterator over the elements in this bst. Elements will be
    * returned in ascending natural order.
    */
   @Override
   public Iterator<T> iterator() {
      return inorderList(root).iterator();      
   }

}
