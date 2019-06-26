public class BlockNode implements java.io.Serializable
{
    /**************************************************************************
    *Public inner class: Entry 
    *Assertion: Holds all the information of a single node
    **************************************************************************/
    public class Entry implements java.io.Serializable
    {
        public String key;
        public Object value;
        /**********************************************************************
        *Submodule: Alternate Constructor
        *Import: key (String), value (Object)
        *Export: A new Entry with new informations
        *Assertion: Creates an entry that contains the information of both key
        *           and value.
        **********************************************************************/
        public Entry (String key, Object value)
        {
            this.key = key;
            this.value = value;
        }
        /**********************************************************************
        *Submodule: getKey
        *Import: none
        *Export: String
        *Assertion: Returns the information of the current key.
        **********************************************************************/
        public String getKey ()
        {
            return this.key;
        }
        /**********************************************************************
        *Submodule: getVal
        *Import: none
        *Export: Object
        *Assertion: Returns the information of the current value.
        **********************************************************************/
        public Object getVal ()
        {
            return this.value;
        }    
    }   
    public BlockNode [] childrens;//Array of child pointer
    public Entry [] nodes;//Array of node
    public int keySize;//indexing of node array
    public int childSize;//indexing of children array
    public BlockNode parent;//parent node
    /**********************************************************************
    *Alternate Constructor
    *Import: parent (BlockNode), maxKeySize (int), maxChildSize (int)
    *Export: A new BlockNode with given size
    *Assertion: Creates a new block node to be use/insert inside the B-Tree
    **********************************************************************/
    public BlockNode (BlockNode parent, int maxKeySize, int maxChildSize)
    {
        keySize = 0;
        childSize = 0;
        this.parent = parent;
        nodes =  new Entry [maxKeySize];
        childrens = new BlockNode [maxChildSize];
    }
    /**********************************************************************
    *Submodule: insertNode
    *Import: key (String), value (Object)
    *Export: none
    *Assertion: Inserts a new Entry into the B-Tree and immediately sorts 
    *           it afterwards.
    **********************************************************************/
    public void insertNode(String key, Object value)
    {
        Entry newNode = new Entry(key, value);
        nodes[keySize] = newNode;
        keySize++;
        //Sorts immediately after insert
        entryInsSort(); 
    }
    /**********************************************************************
    *Submodule: insertChild
    *Import: childToInsert (BlockNode)
    *Export: none
    *Assertion: Inserts an array of Entry or a children onto the B-Tree and
    *           immediately sorts it afterwards.
    **********************************************************************/
    public void insertChild(BlockNode childToInsert)
    {
        childToInsert.parent = this;
        childrens[childSize] = childToInsert;
        childSize++;
        //Sorts immediately after insert
        childInsSort();
    }
    /**********************************************************************
    *Submodule: getNode
    *Import: index (int)
    *Export: nodes (Entry)
    *Assertion: Get one of the node with a given index
    **********************************************************************/
    public Entry getNode (int index)
    {
        return nodes[index];
    }
    /**********************************************************************
    *Submodule: getChild
    *Import: index (int)
    *Export: childrens (BlockNode)
    *Assertion: Get one of the array of nodes with a given index
    **********************************************************************/
    public BlockNode getChild (int index)
    {
        return childrens[index];
    }
    /**********************************************************************
    *Submodule: getKeySize
    *Import: none
    *Export: keySize (int)
    *Assertion: Get the number of keys for every children/array of nodes
    **********************************************************************/
    public int getKeySize ()
    {
        return keySize;
    }
    /**********************************************************************
    *Submodule: getChildSize
    *Import: none
    *Export: childSize
    *Assertion: Get the number of childrens for every array of children
    **********************************************************************/
    public int getChildSize ()
    {
        return childSize;
    }
    /**********************************************************************
    *Submodule: removeChild
    *Import: childToRemove (BlockNode)
    *Export: none
    *Assertion: Removes a specified child from the B-Tree
    **********************************************************************/
    public void removeChild(BlockNode childToRemove)
    {
        boolean exist = false;
        for(int i = 0; i < childSize; i++)
        {
            if(exist)
            {
                childrens[i-1] = childrens[i];
            }
            //If the children searched is the same as given children
            //set to true
            exist = childrens[i] == childToRemove;
        }
        childSize--;
        childrens[childSize] = null;
    }
    /**********************************************************************
    *Used to sort internal node, which contains both key and value, in
    *ascending order. Sorts by key.
    **********************************************************************/
    private void entryInsSort()
    {
        Entry temp;
        for (int nn = 1; nn < keySize; nn++)
        //Start inserting at element 1 
        //(0 is pointless)
        {
            int ii = nn;
            while (ii > 0 && nodes[ii-1].getKey().compareTo
                  (nodes[ii].getKey()) > 0)
            //Insert into sub-array to left of nn.
            //Use > to keep the sort stable.
            {
                temp = nodes[ii];
                nodes[ii] = nodes[ii-1];
                nodes[ii-1] = temp;
                ii--;
            }   
        }
    }
    /**********************************************************************
    *Used to sort childrens, which contains the information of an entire 
    *node, in ascending order. Sorts by key.        
    **********************************************************************/
    private void childInsSort()
    {
        BlockNode temp;
        for (int nn = 1; nn < childSize; nn++)
        //Start inserting at element 1 
        //(0 is pointless)
        {
            int ii = nn;
            //Can only be sorted by key
            while (ii > 0 && childrens[ii-1].getNode(0).getKey().compareTo
                  (childrens[ii].getNode(0).getKey()) > 0)
            //Insert into sub-array to left of nn.
            //Use > to keep the sort stable.
            {
                temp = childrens[ii];
                childrens[ii] = childrens[ii-1];
                childrens[ii-1] = temp;
                ii--;
            }   
        }
    }
    /**********************************************************************
    *Submodule: toString
    *Import: none
    *Export: String
    *Assertion: Recursively prints out the B-Tree for every new node
    *           created
    **********************************************************************/
    public String toString ()
    {
        String str = "===================================NEW NODE===================================\n";
        //Prints only keySize times to showcase the nodes
        for(int i = 0; i < keySize; i++)
        {
            str += nodes[i].getVal().toString() + "\n";
        }
        //Recursively prints the entire three
        for(int i = 0; i < childSize; i++)
        {
            str += childrens[i].toString();
        }
        return str;
    }

    /**************************************************************************
    *Submodule: storeObjBN
    *Import: queue (DSAQueue)
    *Export: queue (DSAQueue)
    *Assertion: Recursively adds all the values into the queue
    **************************************************************************/
    public DSAQueue storeObjBN (DSAQueue queue)
    {
        for(int i = 0; i < keySize; i++)
        {
            queue.enqueue(nodes[i].getVal());
        }
        for(int i = 0; i < childSize; i++)
        {
            childrens[i].storeObjBN(queue);
        }
        return queue;
    }
}
