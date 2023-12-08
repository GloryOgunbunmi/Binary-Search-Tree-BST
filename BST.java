/**
 * @author Aduragbemi Ogunbunmi
 * Data Structures and Algorithms - CSCI 2302 
 * =====================================
 * Assignment:Complete the tasks listed in the beginning comment block in BST.java.
 * Note findKth is finding the order of the element, if the user invoke the method as t.findKth(5), 
 * your method should return the fifth element from the tree t. The smallest element is the 1st 
 * element and should be returned when t.findKth(1) is invoked 
 *
 * A Simple implementation of a BST
 * TODO: Implement 
 * 1. size method
 * 2. min/max methods
 * 3. height methods (both for the tree and any given branch)
 * 3. contains/search method that return a reference to the node in the BST (null if not found)
 * 4. remove method (Naive option: if the node to be removed is not the leaf node ==> re-insert its children into the BST
 *  (Should replace by max of left branch or min or right branch)
 * 5. implement findKth method - finding the Kth element in the tree
 *  
 *   
 * @author knguyen
 *
 */



/**
 * The Actual BST
 * @author knguyen
 *
 */
public class BST {
  //====================================
  //a nested Node class used in BST 
   class Node {
    //constructor
      private Node(int data){
         this.data = data;
      }
    	
    //attributes
      private int data;
      private Node left;
      private Node right;
   }  //=== END of INNER NODE CLASS =====
  //====================================

   public BST(){ //constructor
      this.root = null;
   }
  
  /**
   * Insert interface for user - the data will be inserted starting from the BST root
   * @param data
   */
   public void insert(int data){
      if(this.root == null){//handle empty tree
         this.root = new Node(data);
      }else { //tree has nodes, then start at the root
         insert(this.root, data);
      }
   }
  
  /**
   * Helper method that inserts the nodes with BST properties
   * @param v - node/root to start with
   * @param data - data to be inserted
   */
   private void insert(Node v, int data){
    //====handle exception here
      if(v == null)
         return;
    //==============================
      if(v.data < data) { //insert to the right branch
         if(v.right != null){
            insert(v.right, data);
         }else{
            v.right = new Node (data);
         }  
      }else if (v.data > data){ //insert to the left branch
         if(v.left != null){
            insert(v.left, data);
         }else{
            v.left = new Node(data);
         }
      }
   }
  
  
   //traverse the tree pre-order fashion - this is the interface for the user
   public String preOrder(){
      return doPreOrder(this.root); //start at the root
   }
	
   //this is the actual code handling the pre-order traversal
   private String doPreOrder(Node v){
      if(v != null){
         return v.data + " " +
            doPreOrder(v.left) +
            doPreOrder(v.right);
      	
      }else{ //nothing to do ==> extends out of the leaf
         return "";
      }
   }
	
  //traverse the tree in-order fashion - this is the interface for the user
   public String inOrder(){
      return doInOrder(this.root); //start at the root
   }
	
  //this is the actual code handling the in-order traversal
   private String doInOrder(Node v){
      if(v != null){
         return doInOrder(v.left) +
             v.data + " " +
            doInOrder(v.right);
      		
      }else{ //nothing to do ==> extends out of the leaf
         return "";
      }
   }

  /**
  public method to get the number of nodes in the BST| size method
  */
   public int size(){
      if(this.root == null)
         return 0;
      return size(this.root);
   }

  /** private method that actually find the number of nodes in the BST */
  
   private int size(Node v){
      if(v == null)
         return 0;
      return 1 + size(v.left) + size(v.right);
   }

   //min method to get the left most node
   public int min(){
      if(this.root == null)
         return 0;
      return min(this.root);
   }
   //will traverse the left side of the BST
   public int min(Node v){
      while (v.left!= null){
         v = v.left;
      }
      return v.data;
      
   }
   
   //max method to get the right most node
   public int max(){
      if(this.root == null)
         return 0;
      return max(this.root);
   }
   //will traverse the right side of the BST 
   public int max(Node v){
      while (v.right != null){
         v = v.right;
      }
      return v.data;
   }

   //height of tree
   public int height(){
      if(this.root==null)
         return 0;
      return height(this.root);
   }
   public int height(Node v){
      int left = height(v.left);
      int right = height(v.right);
      //will calculate going left onwards
      if (left>right)
         return 1+left;
      else
         return 1+right;
      
   }
   
   
   //contains method
   public Node contains(int num){
      Node v=search(root,num);
      return v;
   }
   
   public Node search(Node v, int val){
      if (v==null || v.data==val)        //empty
         return v; 
         //recognizes the valuse is smaller
      if (val <v.data)
         return search(v.left, val);
         //recognizes the value is larger
      return search(v.right, val);    
   }
   
   
   //to find the kth element
   public int findKth(int k)throws IllegalArgumentException{
      if(k < 0 || k >= size()){
         throw new IllegalArgumentException("Invalid");
      }
      return findKth(k, this.root);
   }
   public int findKth(int k, Node v){
      int n = size(this.root);
      //goes down the more left side
      if(k < n)
         return findKth(k, v.left);
         //goes down more right
      if(k > n)
         return findKth(k - 1, v.right);
         //node found
      return v.data;
   }
      
  //attributes
   private Node root;// the BST root itself
  
  //=========== END OF BST ==============
  
  //=========== DRIVER ================
   public static void main(String[] args)throws IllegalArgumentException{
      BST t = new BST(); //create a BST
     
     //insert some data into the tree
      t.insert(5);
      t.insert(2);
      t.insert(1);
      t.insert(3);
      t.insert(7);
     //print out the tree
      System.out.println(t.preOrder()); //expected 5 2 1 3 7
      System.out.println(t.inOrder()); //expected 1 2 3 5 7
     
      System.out.println("Tree size: " + t.size());
      System.out.println("Looking for: "+t.contains(5).data);
      System.out.println("the min is: "+t.min());
      System.out.println("the max is : "+t.max());
      System.out.println("Height is : "+t.height());
   }
}
