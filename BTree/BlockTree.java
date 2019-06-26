import java.util.*;
import java.io.*;

public class BlockTree implements java.io.Serializable
{ 
    //private class fields for B-Tree
    private BlockNode root;
    private int size;
    private int height;
    //private class fields that sets the rules for a B-Tree
    private int minKeySize;
    private int minChildSize;
    private int maxKeySize;
    private int maxChildSize;

    /**************************************************************************
    *Default Constructor 
    *Import: none
    *Export: A B-Tree with various key per node
    *Assertion: This constructor sets the order for the new B-Tree.
    **************************************************************************/
    public BlockTree (int order)
    {
        root = null;//Starts with an empty B-Tree
        size = 0;//New B-Tree with no nodes inserted
        height = 0;//Will increment when splitting upwards
        //Order represents the minimum key size in a non-root node
        this.minKeySize = order;
        this.minChildSize = minKeySize + 1;
        this.maxKeySize = 2 * minKeySize;
        this.maxChildSize = maxKeySize + 1;
    }

    /**************************************************************************
    *Alternate Constructor 
    *Import: order (int), filename (String)
    *Export: A B-Tree with various key per node
    *Assertion: This constructor sets the order for the new B-Tree.
    **************************************************************************/
    public BlockTree (int order, String filename)
    {
        root = null;//Starts with an empty B-Tree
        size = 0;//New B-Tree with no nodes inserted
        height = 0;//Will increment when splitting upwards
        this.minKeySize = order;
        this.minChildSize = minKeySize + 1;
        this.maxKeySize = 2 * minKeySize;
        this.maxChildSize = maxKeySize + 1;
        FileReader fileRdr = null;
        BufferedReader bufRdr = null;
        try
        {
            fileRdr = new FileReader(filename);
            bufRdr = new BufferedReader(fileRdr);
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
            fileRdr.close();
        }
        catch (IOException e)   
        {
            if(fileRdr != null)
            {
                try
                {
                    fileRdr.close();
                }
                catch (IOException e2)
                {
                    //Nothing to do here
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
    *Assertion: Returns the total number of nodes inserted into B-Tree
    **************************************************************************/
    public int getSize ()
    {
        return size;
    }
        
    /**************************************************************************
    *Submodule: getHeight 
    *Import: none
    *Export: height (int)
    *Assertion: For every time it splits to root, height increases by one
    **************************************************************************/
    public int getHeight ()
    {
        return height;
    }

    /**************************************************************************
    *Submodule: getIdealHeight
    *Import: none
    *Export: Ideal Height (int)
    *Assertion: Returns the ideal height of the B-Tree
    **************************************************************************/
    public int getIdealHeight ()
    {
        return (int)(Math.log(size)/Math.log(maxChildSize));
    }

    /**************************************************************************
    *Submodule: insert
    *Import: key (String), value (Object)
    *Export: none
    *Assertion: Responsible for the insertion function of the B-Tree, acts as
    *           a wrapper method for insertRecursion
    **************************************************************************/
    public void insert(String key, Object value)
    {
        //If the B-Tree is empty, insert it as the new root
        if(root == null)
        {
            root = new BlockNode(null, maxKeySize, maxChildSize);
            root.insertNode(key, value);
            size++;
        }
        //If there are no childrens, add key and value to the only node
        else if(root.getChildSize() == 0)
        {
            //If current key count is lesser than the maximum key size, insert
            //Zero-based indexing array
            if(root.getKeySize() < maxKeySize - 1)
            {
                root.insertNode(key, value);
                size++;
            }
            //Needs to split if the key is full on one node
            //Zero-based indexing array
            else if(root.getKeySize() == maxKeySize - 1)
            {
                split(root);
                height++;
                //Insert again to test the splitted nodes
                insert(key, value);
            }
        }
        //If there are existing childrens, root and parents, toss into 
        //recursion to sort the keys and values.
        else
        {
            insertRecursion(root, key, value);
        }
    }
    
    /**************************************************************************
    *Submodule: insertRecursion
    *Import: current (BlockNode), key (String), value (Object)
    *Export: none
    *Assertion: To recursively insert node in the correct order, as well as 
    *           self balance itself for storage efficiency. Considers all types
    *           of cases before inserting.
    **************************************************************************/
    private void insertRecursion (BlockNode current, String key, Object value)
    {
        int index = 0;
        int numKeys = current.getKeySize();
        boolean tieBreaker = false;
        String compare;
        if(current.getChildSize() == 0)//Base case found!
        {
            //Still need to check if the current node is full before inserting
            //Zero-based indexing array
            if(current.getKeySize() < maxKeySize - 1)
            {
                current.insertNode(key, value);
                size++;
            }
            //Zero-based indexing array
            else if(current.getKeySize() == maxKeySize - 1)
            {
                //current becomes unusable after splitting
                split(current);
                //Insert again to test the new nodes
                insert(key, value);
            }
        }
        else
        {
            while(!tieBreaker)
            {
                //Variable compare is always changing
                compare = current.getNode(index).getKey();
                if(index == 0 && key.compareTo(compare) <= 0)//If its lesser or equal
                {
                    current = current.getChild(index);  
                    //Recurse if value is lesser  
                    insertRecursion(current, key, value);
                    //Acts as an break statement
                    tieBreaker = true;
                }
                else if(index == numKeys - 1 && key.compareTo(compare) > 0)//If its greater
                {
                    current = current.getChild(index);
                    //Recurse if value is greater
                    insertRecursion(current, key, value);
                    //Acts as an break statement
                    tieBreaker = true;
                }
                else if(index > 0)//Searching internal nodes
                {       
                    String previous = current.getNode(index - 1).getKey();
                    String next = current.getNode(index).getKey();
                    if(key.compareTo(previous) > 0 && key.compareTo(next) <= 0)
                    {
                        current = current.getChild(index);
                        //Recurse if the value is more than its previous and
                        //lesser than the next
                        insertRecursion(current, key, value);
                        //Acts as an break statement
                        tieBreaker = true;        
                    }
                }
                //Indexing moves the pointer to traverse the B-Tree
                index++;    
            }
        }
    }

    /**************************************************************************
    *Submodule: split
    *Import: nodeToSplit (BlockNode)
    *Export: none
    *Assertion: This function maintains the stability of the overall structure
    *           of the B-Tree. Only split when the node is full.
    **************************************************************************/
    private void split(BlockNode nodeToSplit)
    {
        BlockNode node = nodeToSplit;
        int numberOfKeys = node.getKeySize();
        int medianIndex = numberOfKeys/2;
        String medianKey = node.getNode(medianIndex).getKey();
        Object medianVal = node.getNode(medianIndex).getVal();
           
        //Fills the left side of the median node with values lesser than median
        BlockNode leftSide = new BlockNode(null, maxKeySize, maxChildSize);
        for(int i = 0; i < medianIndex; i++)
        {
            leftSide.insertNode(node.getNode(i).getKey(), node.getNode(i).getVal());        
        }   
        if(node.getChildSize() > 0)
        {
            for(int j = 0; j <= medianIndex; j++)
            {
                //New child is inserted into the tree
                BlockNode child = node.getChild(j);
                leftSide.insertChild(child);    
            }
        }   

        //Fills the right side of the median node with values greater than median            
        BlockNode rightSide = new BlockNode(null, maxKeySize, maxChildSize);
        for(int i = medianIndex + 1; i < numberOfKeys; i++)
        {
            rightSide.insertNode(node.getNode(i).getKey(), node.getNode(i).getVal());        
        }   
        if(node.getChildSize() > 0)
        {
            //Increments by one so it doesn't grab the median value
            for(int j = medianIndex + 1; j < node.getChildSize(); j++)
            {
                //New child is inserted into the tree
                BlockNode child = node.getChild(j);
                rightSide.insertChild(child);
            }
        }
            
        if(node.parent == null)
        {
            //New root, increases height of the B-Tree
            BlockNode newRoot = new BlockNode (null, maxKeySize, maxChildSize);
            newRoot.insertNode(medianKey, medianVal);
            node.parent = newRoot;
            root = newRoot;
            node = root;
            node.insertChild(leftSide);
            node.insertChild(rightSide);
            height++;
        }    
        else
        {
            //If value exist in parent node, push up
            BlockNode parent = node.parent;
            //Sets a new parent
            parent = node.parent;
            parent.insertNode(medianKey, medianVal);
            //Removing the node that has been split
            parent.removeChild(node);
            parent.insertChild(leftSide);
            parent.insertChild(rightSide);
            //If its full when added, split and try again
            if(parent.getKeySize() == maxKeySize)
            {
                split(parent);
            }
        }
    }

    /**************************************************************************
    *Submodule: find
    *Import: key (String)
    *Export: value (Object)
    *Assertion: Search function traverses the tree using the given key to find
    *           the exact same key as the import, returns an object when found. 
    *           Acts as an wrapper method for findRecursion.
    **************************************************************************/
    public Object find (String key)
    {
        Object value = null;
        boolean exist = false;
        int index = 0;
        //If the root is empty, just throws an exception
        if(root == null)
        {
            throw new IllegalArgumentException("Error: Tree is empty! Search failed!");
        }
        //If the root is not empty, check the internal nodes
        else
        {
            //Signifies that root is by itself, so just iterate the single node
            if(root.getChildSize() == 0)
            {
                while(!exist)
                {   
                    //Gets the key, returns value, end of story.
                    if(key.equals(root.getNode(index).getKey()))
                    {
                        value = root.getNode(index).getVal();
                        exist = true;
                    }
                    else
                    {
                        /******************************************************
                        *Keeps iterating until it reaches maximum key size,   *
                        *just means that there is nothing left to search.     *
                        *Throws an exception.                                 *
                        ******************************************************/
                        index++;        
                        if(index == root.getKeySize())
                        {
                            throw new IllegalArgumentException ("Key doesn't exist!");

                        }
                    }
                }
            }
            else if(root.getChildSize() > 0)
            {
                value = findRecursion(root, key);
            }
            
        }
        return value; 
    }

    /**************************************************************************
    *Submodule: findRecursion
    *Import: current (BlockNode), key (String)
    *Export: value (Object)
    *Assertion: Recursively traverse the entire tree whilst considering all 
    *           cases in which the key is located the in B-Tree. Returns an 
    *           object when it exist else throws an exception.
    **************************************************************************/
    private Object findRecursion (BlockNode current, String key)
    {
        Object value = null;
        int index = 0;
        int numKeys = current.getKeySize();
        boolean tieBreaker = false;
        String compare;
        if(current.getChildSize() == 0)
        {
            while(!tieBreaker)
            {
                if(key.equals(current.getNode(index).getKey()))
                {
                    value = current.getNode(index).getVal();
                    tieBreaker = true;
                }
                else
                {
                    /******************************************************
                    *Keeps iterating until it reaches maximum key size,   *
                    *just means that there is nothing left to search.     *
                    *Throws an exception.                                 *
                    ******************************************************/
                    index++; 
                    if(index == numKeys)
                    {
                        throw new IllegalArgumentException ("Key doesn't exist!");
                    }
                }
            } 
        }
        else
        {
            while(!tieBreaker)
            {
                //Variable compare is always changing
                compare = current.getNode(index).getKey();
                if(key.equals(compare))//Key found! Value retrieved.
                {
                    value = current.getNode(index).getVal();
                    tieBreaker = true;
                }
                else if(index == 0 && key.compareTo(compare) < 0)//If it is lesser
                {
                    //Gets the child with index to retrieve first node
                    BlockNode newChildToSearch = current.getChild(index);
                    value = findRecursion(newChildToSearch, key);
                    tieBreaker = true;
                }
                else if(index == numKeys - 1 && key.compareTo(compare) > 0)//If its greater
                {
                    //Gets the child with index numKeys to retrieve last node
                    BlockNode newChildToSearch = current.getChild(numKeys);
                    value = findRecursion(newChildToSearch, key);   
                    tieBreaker = true;
                }
                else if(index < numKeys - 1)//Searching internal nodes
                {
                    //It only has next to compare, no need for previous       
                    String next = current.getNode(index + 1).getKey();
                    if(key.compareTo(compare) > 0 && key.compareTo(next) < 0)
                    {
                        BlockNode newChildToSearch = current.getChild(index + 1);
                        value = findRecursion(newChildToSearch, key);
                        tieBreaker = true;        
                    }
                }
                //Indexing moves the pointer to traverse the B-Tree
                index++;    
            }
        }
        return value;        
    }

    /**************************************************************************
    *Submodule: toString
    *Import: none
    *Export: All the nodes when the insertion occurs
    *Assertion: Calls the toString method inside BlockNode class.
    **************************************************************************/
    public String toString()
    {
        return root.toString();
    }

    /**************************************************************************
    *Submodule: storeObj
    *Import: queue (DSAQueue)
    *Export: A queue full of values
    **************************************************************************/
    public DSAQueue storeObj(DSAQueue queue)
    {
        return root.storeObjBN(queue);    
    }

    /**************************************************************************
    *Submodule: delete
    *Import: key (String)
    *Export: none
    *Assertion: No plans for implementation of deletion function for B-Tree due
    *           to its complexity.
    **************************************************************************/
    public void delete (String key)
    {
        throw new IllegalArgumentException ("Deletion is not implemented!");
    }
}
