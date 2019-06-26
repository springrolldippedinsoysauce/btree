import java.util.*;
import java.io.*;

public class BinarySearchTree implements java.io.Serializable
{
    //Private inner-class
    private class TreeNode implements java.io.Serializable
    {
        private String key;
        private Object value;
        private TreeNode leftChild;
        private TreeNode rightChild;
    
        /**********************************************************************
        *Alternate Constructor 
        *Import: inKey (String), inVal (Object)
        *Export: A new TreeNode
        **********************************************************************/
        public TreeNode (String inKey, Object inVal)
        {
            if (inKey == null)
            {
                throw new IllegalArgumentException ("Key cannot be null");
            }
            key = inKey;//consider "owning" the key
            value = inVal;
            rightChild = null;
            leftChild = null;
        }
        public String getKey ()
        {
            return key;
        }
        public Object getValue ()
        {
            return value;
        }
        public TreeNode getLeft ()
        {
            return leftChild;
        }
        public void setLeft (TreeNode newLeft)
        {
            leftChild = newLeft;
        }
        public TreeNode getRight ()
        {
            return rightChild;
        }
        public void setRight (TreeNode newRight)
        {
            rightChild = newRight;
        }
    }

    //Class field
    private TreeNode root;
    int size;
    
    /**************************************************************************
    *Default Constructor 
    *Import: none
    *Export: A new BinarySearchTree
    *Assertion: Creates a new BinarySearchTree
    **************************************************************************/
    public BinarySearchTree ()
    {
        root = null; //Start with an empty tree
        size = 0; //Counts the nodes
    }

    /**************************************************************************
    *Alternate Constructor 
    *Import: filename (String)
    *Export: A new BinarySearchTree
    *Assertion: Creates a new BinarySearchTree
    **************************************************************************/
    public BinarySearchTree (String filename)
    {
        root = null;
        size = 0;
        FileReader rdr = null;
        BufferedReader bufRdr = null;
        try
        {
            rdr = new FileReader(filename);
            bufRdr = new BufferedReader(rdr);
            String line = bufRdr.readLine();
            while (line != null)
            {
                try
                {
                    parseStock(line);    
                }
                catch (IllegalArgumentException arg)
                {
                    System.out.println(arg.getMessage());
                }
                line = bufRdr.readLine();
            }
            bufRdr.close();
            rdr.close();
        }
        catch (IOException e)   
        {
            if(rdr != null)
            {
                try
                {
                    rdr.close();
                }
                catch (IOException e2)
                {
                }
            }
        }
    }
  
    /**************************************************************************
    *Submodule: parseStock 
    *Import: line (String)
    *Export: none
    *Assertion: Parses line from stock exchange data and assigned to relevant
    *           variables
    **************************************************************************/
    private void parseStock (String line)
    {
        String [] stocktype = line.split(",");
        if(stocktype.length == 7)
        {
            String key = stocktype[0];    
            String ticker = stocktype[0];    
            int date = Integer.parseInt(stocktype[1]);    
            double open = Double.parseDouble(stocktype[2]);    
            double high = Double.parseDouble(stocktype[3]);    
            double low = Double.parseDouble(stocktype[4]);    
            double close = Double.parseDouble(stocktype[5]);    
            int volume = Integer.parseInt(stocktype[6]);    
            StockClass stock = new StockClass (ticker, date, open, high, low, close, volume);
            insert(key, stock);
        }
        else
        {
            throw new IllegalArgumentException ("Error: Invalid line!");
        }
    }

    /**************************************************************************
    *Submodule: getSize
    *Import: none
    *Export: size (int)
    *Assertion: Returns the size of the tree
    **************************************************************************/
    public int getSize ()
    {
        return size;
    }

    /**************************************************************************
    *Submodule: getIdealHeight
    *Import: none
    *Export: Ideail Height (int)
    *Assertion: Returns the ideal height of the Binary Search Tree
    **************************************************************************/
    public int getIdealHeight ()
    {
        return (int)(Math.log(size)/Math.log(2));
    }

    /**************************************************************************
    *Submodule: find 
    *Import: key (String)
    *Export: An object
    *Assertion: Finds an object using the given key
    **************************************************************************/
    public Object find (String key)
    {
        return findRecursive (key, root);
    }
    
    /**************************************************************************
    *Submodule: findRecursive
    *Import: key (String), cur (TreeNode)
    *Export: An object
    *Assertion: Recursively finds the object with a given key
    **************************************************************************/
    private Object findRecursive (String key, TreeNode cur)
    {
        Object val = null;
        
        if (cur == null)
        {
            throw new NoSuchElementException ("Key " + key + " not found");//Base case: not found
        }
        else if (key.equals(cur.getKey()))//in the tree
        {
            val = cur.getValue();//Base case: found
        }
        else if (key.compareTo(cur.getKey()) < 0 )//recurse left
        {
            val = findRecursive(key, cur.getLeft());//Go left (recursive)
        }
        else//recurse right
        {
            val = findRecursive(key, cur.getRight());//Go right (recursive)
        }
        return val;
    }

    /**************************************************************************
    *Submodule: insert
    *Import: key (String), value (Object)
    *Export: none
    *Assertion: Inserts node into tree
    **************************************************************************/
    public void insert (String key, Object value)
    {
        root = insertRec(key, value, root);
    }

    //Wrapper methods, will call private recursive implementations
    /**************************************************************************
    *Submodule: insertRec
    *Import: key (String), value (Object), cur (TreeNode)
    *Export: updateNode (TreeNode)
    *Assertion: Recursively finds a spot to insert the node
    **************************************************************************/
    private TreeNode insertRec (String key, Object value, TreeNode cur)
    {
        TreeNode updateNode = cur;
        if (cur == null)//base case - found
        {
            TreeNode newNode = new TreeNode (key, value);//insertion point
            updateNode = newNode;
            size++;
        }
        else if (key.equals(cur.getKey()))//in the tree
        {
            throw new IllegalArgumentException ("Detected key conflicts, some keys are not unique!");
        }
        else if (key.compareTo(cur.getKey()) < 0)//recurse right & update current
        {
            cur.setLeft(insertRec(key, value, cur.getLeft()));
        }  
        else//recurse right & update current
        {
            cur.setRight(insertRec(key, value, cur.getRight()));
        }
        return updateNode;
    }

    //Wrapper methods, will call private recursive implementations
    /**************************************************************************
    *Submodule: delete
    *Import: key (String)
    *Export: none
    *Assertion: Deletes a node from the tree
    **************************************************************************/
    public void delete (String key)
    {
        root = deleteRec(key, root);
    }

    /**************************************************************************
    *Submodule: deleteRec 
    *Import: key (String), cur (TreeNode)
    *Export: updateNode (TreeNode)
    *Assertion: Recursively finds a node to delete
    **************************************************************************/
    private TreeNode deleteRec (String key, TreeNode cur)
    {
        TreeNode updateNode = cur;
        if (cur == null)
        {
            throw new IllegalArgumentException ("Don't try it!");//base case - not found
        }
        else if (key.equals(cur.getKey()))
        {
            updateNode = deleteNode(key, cur);//base case - found
            size--;
        }
        else if (key.compareTo(cur.getKey()) < 0)
        {
            cur.setLeft(deleteRec(key, cur.getLeft()));//recurse left
        }
        else//similar to insert
        {
            cur.setRight(deleteRec(key, cur.getRight()));//recurse right
        }
        return updateNode;
    }

    /**************************************************************************
    *Submodule: deleteNode
    *Import: key (String), del (TreeNode)
    *Export: updateNode (TreeNode)
    *Assertion: Deletes a node
    **************************************************************************/
    public TreeNode deleteNode (String key, TreeNode del)
    {
        TreeNode updateNode = null;
        if ((del.getLeft() == null)&&(del.getRight() == null))
        {
            updateNode = null;
        }
        else if ((del.getLeft() != null)&&(del.getRight() == null))
        {
            updateNode = del.getLeft();
        }
        else if ((del.getLeft() == null)&&(del.getRight() != null))
        {
            updateNode = del.getRight();
        }
        else
        {
            updateNode = promoteSuccessor(del.getRight());
            if (updateNode != del.getRight())
            {
                updateNode.setRight(del.getRight());
            }
            updateNode.setLeft(del.getLeft());
        }
        return updateNode;
    }

    /**************************************************************************
    *Submodule: promoteSuccessor
    *Import: cur (TreeNode)
    *Export: successor (TreeNode)
    *Assertion: Promotes the left mode child of the right subtree into the new
    *           successor
    **************************************************************************/
    public TreeNode promoteSuccessor (TreeNode cur)
    {
        //Assertion: successor will be the left most child of the right subtree
        TreeNode successor = cur;
        //IF cur.getLeft == null //not needed in code
        //  successor = cur//base case - no left children
        //ELSE
        if (cur.getLeft() != null)
        {
            successor = promoteSuccessor(cur.getLeft());
            if (successor == cur.getLeft())//parent of successor needs
            {
                cur.setLeft(successor.getRight());//to adopt right child
            }
        }
        return successor;
    }

    //Gets the longest pathway from root to leaf
    /**************************************************************************
    *Submodule: getHeight
    *Import: none
    *Export: Height integer
    *Assertion: Gets the longest pathway from root to leaf
    **************************************************************************/
    public int getHeight ()
    {
        return heightRec(root);
    }
    
    /**************************************************************************
    *Submodule: heightRec
    *Import: cur (TreeNode)
    *Export: currHt (int)
    *Assertion: Returns current height
    **************************************************************************/
    private int heightRec (TreeNode cur)
    {
        int currHt, leftHt, rightHt;
        
        if (cur == null)
        {
            currHt = -1;
        }
        else
        {
            leftHt = heightRec(cur.getLeft());
            rightHt = heightRec(cur.getRight());
            //Get highest of left vs right branches
            if (leftHt > rightHt)
            {
                currHt = leftHt + 1;
            }
            else    
            {
                currHt = rightHt + 1;
            }
        }
        return currHt;
    }
    
    /**************************************************************************
    *Submodule: toString
    *Import: none
    *Export: Values in order 
    *Assertion: Prints all of the value in order
    **************************************************************************/
    public String toString ()
    {
        return toStringRec(root);
    }

    /**************************************************************************
    *Submodule: toStringRec
    *Import: none
    *Export: Values in order 
    *Assertion: Recursively store string in order
    **************************************************************************/
    private String toStringRec (TreeNode cur)
    {
        String str = "";
        if (cur != null)
        {
            str += toStringRec(cur.getLeft());
            str += cur.getValue() + "\n";
            str += toStringRec(cur.getRight());
        }
        return str;
    }

    /**************************************************************************
    *Submodule: storeObjBST
    *Import: size (int)
    *Export: Queue filled with stock information
    *Assertion: Fills the queue with all of the tree value
    **************************************************************************/
    public DSAQueue storeObjBST (int size)
    {
        DSAQueue queue = new DSAQueue(size);
        return storeObjBSTRec(root, queue);
    }

    /**************************************************************************
    *Submodule: storeObjBSTRec
    *Import: cur (TreeNode), queue (DSAQueue)
    *Export: queue (DSAQueue)
    *Assertion: Fills the queue with all of the tree value in order
    **************************************************************************/
    private DSAQueue storeObjBSTRec (TreeNode cur, DSAQueue queue)
    {
        if(cur != null)
        {
            storeObjBSTRec(cur.getLeft(), queue);
            queue.enqueue(cur.getValue());
            storeObjBSTRec(cur.getRight(), queue);
        }
        return queue;
    }
}
