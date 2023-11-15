/* Creates a basic class for a Binary Search Tree*/
public class BinTree {
  //declaring root variable
  private TreeNode root;

  //construtor method
  public BinTree() { root = null; }

  //other constructor method
  public BinTree(char initValue)
  {
    root = new TreeNode(initValue);
  }

  /* Subclass for Nodes */
  public class TreeNode{
    private char value;
    private TreeNode left;
    private TreeNode right;

    //TreeNode constructor
    public TreeNode(char initValue)
    {
      value = initValue;
      left = null;
      right = null;
    }

    //other constructor method
    public TreeNode(char initValue, TreeNode initLeft, TreeNode initRight)
    {
      value = initValue;
      left = initLeft;
      right = initRight;
    }
    
    //returns value of Node
    public char getValue() { return value; }

    //returns the node to the left
    public TreeNode getLeft() { return left; }

    //returns the node to the right
    public TreeNode getRight() { return right; }

    //sets value of Node
    public void setValue (char newVal) { value = newVal; }

    //sets Node to the left
    public void setLeft (TreeNode newLeft) { left = newLeft; }

    //sets Node to the right
    public void setRight (TreeNode newRight) { right = newRight; }
  }

  //inserts item to the correct postion in the binary tree
  private void recInsertItem (TreeNode current, TreeNode toInsert, char item, String code)
  {
    //if the current character we are examining is a period
    if (code.substring(0, 1).equals(".")) {
      if (current.left == null)//in case there is no node we will insert the currNode to the left
      {
        current.left = toInsert;
      }
      else{//we will recurs and shift the curr treenode to the left if there is a node to the left
        recInsertItem(current.left, toInsert, item, code.substring(1));
      }
    }
      //if there is a -, we will instead traverse to the right
    else if (code.substring(0, 1).equals("-"))
    {
      if (current.right == null) //if the node to the right is empty or null, we will assign the phrase to the right of current
      {
        current.right = toInsert;
      }
      else{ //if there is a node to the right, we will recurs and alter current to now be the node to the right
        recInsertItem(current.right, toInsert, item, code.substring(1));
      }
    }
  }

  //inserts item
  public void InsertItem(char item, String code)
  {
    if (root == null) {
      root = new TreeNode(item);
      return;  
    }
    else recInsertItem (root, new TreeNode(item), item, code);
  }

  //calls the InOrder method
  public void InOrder (){     
    InOrder(root);
  }

  //displays the numbers in the binary tree in InOrder output
  private void InOrder (TreeNode toStart){
    TreeNode tempNode = toStart;

     //go left if possible
     if (tempNode.getLeft() != null)
     {
       InOrder(tempNode.getLeft()); 
     }

     //print current node
     System.out.println(tempNode.getValue());

     //go right if possible
     if (tempNode.getRight() != null)
     {
       InOrder(tempNode.getRight());
     }
   }

  
  public char translateCode(String toFind){
    //call translate code method recursively, starting from the root
    return translateCode(toFind, root);
  }

  //checks if there is a certain number in the binary tree
  private char translateCode(String toFind, TreeNode curNode){
    TreeNode node = curNode;

    //return final character
    if (toFind.isEmpty()){
      return node.getValue();
    }

    //if there is a dash move right and down
    if (toFind.substring(0, 1).equals("-")) {
      if (node.right != null){
        //if cannot move right return the translated letter
        return translateCode(toFind.substring(1), node.right);
      }
      else{
        //if there is an error return '?'
        return '?';
      }
    }
    else if (toFind.substring(0, 1).equals(".")) { //if there is a dot move left and down
      if (node.left != null){
        //if cannot move left return the translated letter
        return translateCode(toFind.substring(1), node.left);
      }
      else{
        //if there is an error return '?'
        return '?';
      }
    }  
    //if there is an error '?'
    return '?';
  }

  //calls deleteNode method
  public void deleteNode(char toRemove)
  { root = deleteNode (root, toRemove); }

  //deletes a certain number in the binary tree
  private TreeNode deleteNode(TreeNode tempRoot, char toRemove)
  {
    // Base case - tree empty
    if (tempRoot == null)
      return tempRoot;
  
    // Follow tree

    if (toRemove < tempRoot.value) 
      tempRoot.left = deleteNode(tempRoot.left, toRemove);
    else if (toRemove > tempRoot.value)
      tempRoot.right = deleteNode(tempRoot.right, toRemove);
  
     // If toRemove is the same as tempRoot.value,
     // then it must be deleted.
    else {
      // leaf or one child
      if (tempRoot.left == null)
        return tempRoot.right;
      else if (tempRoot.right == null)
        return tempRoot.left;
    
      // 2 children - find left-most node in right subtree
      tempRoot.value = minValue(tempRoot.right);

      // delete InOrder successor
      tempRoot.right = deleteNode(tempRoot.right, tempRoot.value);
    }
    return tempRoot;
  }

  //returns the smallest value in the binary tree
  private char minValue(TreeNode tempRoot)
  {
    char iMin = tempRoot.value;
    //Uses a while loop to search for the smallest value by checking the values on the left nodes
    while (tempRoot.left != null)
      {
      //Sets the smallest value to the variable 
      iMin = tempRoot.left.value;
      //updates the temp root in the search 
      tempRoot = tempRoot.left;
    }
    //returns the minimum value
    return iMin;
  }
}