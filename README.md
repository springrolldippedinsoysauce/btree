How-to-run: Compile everything and run TreeProfiler without any command-line arguments

Purpose: A program which utilizes Tree Data Structures to store large amount of 
         data (e.g. files about stock information)

Files contains:

    Main function:
        -TreeProfiler.java
        -TreeIO.java
    Trees:
        -BlockTree.java
        -TwoThreeFourTree.java
        -BinarySearchTree.java
    Object for storage:
        -StockClass.java
    Text file:
        -stock.txt
        -half.txt
        -small.txt
        -ascending.txt
        -descending.txt

Test Files: <stock.txt, half.txt, small.txt, ascending.txt, descending.txt>

Functionality:

    TreeProfiler.java:
    >Provides usage information about the code when it runs with no 
     command-line arguments
    >Interactive testing environment:
        -Allows user to select a tree of their choice to interact
        -Interactive modes provides a wide range of option consisting of:
            -Loading new data into the selected trees
            -Finding a specific key in the tree
            -Inserting a new key and value into the tree
            -Deleting a specific key in the tree
            -Displaying the tree's overall statistic
            -Saving the tree as a text output or a serialized, binary export
            -Exiting to terminal
        -Profiling mode:
            -Allows the program to take in command-line arguments
            -Processes the command-line argument to display tree statistic
            -Takes in (in-order), type of tree, data size and input file
            -Does not modify any save files, its only purpose is to display 
             tree statistic

    TreeIO.java:
    >Responsible for all of File I/O methods of the whole program
    >Consist but not limited to:
        -Writing stored nodes onto a text file for all three of the trees
        -Serializing stored nodes and class into a serialized file
        -Loading serialized files into the tree to be reused
        -Saving tree as binary export
        -Loading binary export

    UnitTestBTree.java:
    >Test harness for BlockTree.java

    UnitTest234Tree.java:
    >Test harness for TwoThreeFourTree.java

    UnitTestBST.java:
    >Test harness for BinarySearchTree.java

    BlockTree.java:
    >A advanced tree data structure that supports a range of basic set of
     operations including Find, Insert and Delete. When working with large sets
     of data, it isn't possible to maintain the entire structure in the primary
     storage. Instead, its built to seperate into relatively small portion to
     maintained the primary data structure. 

    TwoThreeFourTree.java:  
    >A specific B-Tree with an order of 4, the only behaviour that differs 
     from a normal B-Tree is its splitting function. When it detects a full
     node, it will automatically split compare to normal B-Tree which only 
     split when needed.

    BinarySearchTree.java:
    >A tree data structure with at most 2 children, the left and right child.
     Each node contains a data, pointer to left child and pointer to right
     child, in which anything on the left is lesser than the root and anything
     on the right is greater than the root, this also applies to parent nodes.
     It covers a set of basic operations, e.g. Find, Insert and Delete.

    StockClass.java:
    >A class which contains all of the required information to form a 
     StockClass object, it is the data/value of all of the tree presented here,
     accompanied with its key which is a reference to itself.
    
    BlockNode.java:
    >A class that manages the storage function of both 234-Tree and B-Tree,
     contains Entry inner-class that stores a reference to key and value.

TODO: 

    Deletion for both B-Tree and 234-Tree
    >Not able to implement the deletion function for both tree due to its 
     complexity and sheer size of logical operation.

Known bugs:

    Bug#1: Some keys does not appear when attempting to find it
        >How-to-replicate: Try it until you get an exception thrown by program
    Bug#2: Stack overflow error
        >How-to-replicate: Serializing a degenerate binary tree
    Bug#3: AIOB issues
        >How-to-replicate: Giving the B-Tree or 234-Tree a severely randomized
                           set of data or a set of data that is too massive
    Bug#4: Some key isn't appearing as intended
        >How-to-replicate: For B-Tree, giving it a small order 
    Bug#5: The balance percentage giving weird symbols
        >How-to-replicate: When the order for B-Tree is too large, meaning 
                           when there is no height, it tries to get ideal 
                           height by using Math.log on 0.
