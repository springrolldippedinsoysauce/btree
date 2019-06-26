import java.util.*;
import java.text.*;
import java.io.*;

public class TreeProfiler
{
    public static void main (String [] args)
    {
        int choice;
        Scanner sc = new Scanner (System.in);
        if(args.length < 1)
        {
            usage();
        }
        else if("-i".equals(args[0]))
        {
            /*************************************************************
            *All of the exception thrown is catched here, it is also the *
            *interactive environment of the program                      *
            *************************************************************/
            try
            {
                do
                {
                    System.out.println("Choose one of the trees:\n(1) -Binary Search Tree\n(2) -Block Tree\n(3) -Two-Three-Four Tree\n(0) -Quit");    
                    System.out.print(">Choice: ");
                    choice = sc.nextInt();
                    switch (choice)
                    {
                        case 1:
                            mainMenuBST();
                            break;
                        case 2:
                            mainMenuBT();
                            break;
                        case 3:
                            mainMenuTTFT();
                            break;
                        case 0:
                            System.out.println("Exiting to terminal, Goodbye!");;
                            break;
                        default:    
                            System.out.println("Choose one of the options given!");
                            break;
                    }
                }while(choice != 0);
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
            catch(InputMismatchException i)
            {
                System.out.println("Error: Invalid input detected! Program stopped...");
            }
            catch(NoSuchElementException n)
            {
                System.out.println("Error: " + n.getMessage());
            }
            catch(StackOverflowError s)
            {
                System.out.println("Stack overflow detected! Causes:\n(1) Degenerate Tree\n(2) See above\n(3) See above\n(4) See above\n(5) See above\n(6) See above\n(7) See above\n(8) See above\n(9) See above");
            }
        }
        else if("-p".equals(args[0]))
        {
            /******************************************************************
            *As per the assignment requirement: Profiling mode should allow the
            *choice of tree, data size and input file (on the command line);
            *and output only the statistics and any other information that may
            *assist understanding and decisions for the report.
            ******************************************************************/
            try
            {
                System.out.println("...Profiling mode activated...");
                profiler(args[1], Integer.parseInt(args[2]), args[3]);
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                /**************************************************************
                *If the user does have enough command-line arguments, prints 
                *out a reminder as to how to use the profiling mode
                **************************************************************/
                System.out.println("==========REMINDER==========");
                usage();
            }
            catch(ArithmeticException a)
            {
                System.out.println("Input file for profiling is wrong!");
            }
        }
        else
        {
            /******************************************************************
            *Any other modes other than "-i" and "-p" will display a reminder
            ******************************************************************/
            System.out.println("==========REMINDER==========");
            usage();
        }
    }

    /**************************************************************
    *Reusing from Week 2 Practical from the class SortsTestHarness*
    ***************************************************************/
    public static void usage ()
    {
        System.out.println("Usage: java TreeProfiler m [t d f], square brackets are exclusive to profiling mode");
        System.out.println("    where m is the type of which mode to use");
        System.out.println("    m is one of the modes");
        System.out.println("        -i - interactive testing environment");
        System.out.println("        -p - profiling mode");
        System.out.println("    t is one of the trees");
        System.out.println("        BST - Binary Search Tree");
        System.out.println("        BT - Block Tree");
        System.out.println("        TTFT - Two Three Four Tree");
        System.out.println("    d is the data size");
        System.out.println("        Note: Only BT requires data size, e.g. order");
        System.out.println("    f is the input file");
        System.out.println("        e.g. stock.txt, half.txt, small.txt");
    }

    /**************************************************************************
    *Submodule: mainMenuBST
    *Import: none   
    *Export: none
    *Assertion: Interactive environment for Binary-Tree, contains all of the 
    *           options to interact with the tree.        
    **************************************************************************/
    public static void mainMenuBST ()
    {
        DSAQueue load = null;
        DecimalFormat dp2 = new DecimalFormat("#0.00");
        BinarySearchTree tree = null;
        int choice, saveChoice, loadChoice;
        Object val = null;
        Scanner sc = new Scanner (System.in);
        do
        {
            System.out.println("(1) -Load new data\n(2) -Tree find\n(3) -Tree insert\n(4) -Tree delete\n(5) -Tree statistic\n(6) -Save tree\n(0) -Quit");    
            System.out.print(">Choice: ");
            choice = sc.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("Loading function\n(1) -CSV\n(2) -Serialized File\n(3) -Binary Import\n(0) -Return");
                    System.out.print(">Choice: ");
                    loadChoice = sc.nextInt();
                    switch(loadChoice)
                    {
                        case 1:
                            tree = new BinarySearchTree(strInput("Enter a file to load to the tree: "));
                            break;
                        case 2:
                            tree = (BinarySearchTree)TreeIO.load(strInput("Enter the load file: "));
                            break;
                        case 3:
                            tree = new BinarySearchTree();
                            load = TreeIO.open(strInput("Enter the name of the binary file: "));
                            while(!load.isEmpty())
                            {
                                StockClass stock = (StockClass)load.dequeue();   
                                tree.insert(stock.getTicker(), stock); 
                            }
                            break;
                        case 0:
                            System.out.println("...Returning to previous menu...");
                            break;
                        default:
                            System.out.println("Wrong output file type. Returning to menu....");
                            break;
                    }
                    break;
                case 2:
                    val = tree.find(strInput("Enter a key to find on the tree: "));
                    System.out.println(((StockClass)val).status());
                    break;
                case 3:
                    String key = strInput("Enter a valid ticker [e.g. AAA]: ");    
                    String ticker = key;    
                    System.out.println("Enter a valid date [e.g. YYYYMMDD]:");
                    int date = sc.nextInt();    
                    System.out.println("Enter a valid open [e.g. >= 0]:");
                    double open = sc.nextDouble();    
                    System.out.println("Enter a valid high [e.g. >= 0]:");
                    double high = sc.nextDouble();   
                    System.out.println("Enter a valid low [e.g. >= 0]:");
                    double low = sc.nextDouble();   
                    System.out.println("Enter a valid close [e.g. >= 0]:");
                    double close = sc.nextDouble(); 
                    System.out.println("Enter a valid volume [e.g. >= 0]:");
                    int volume = sc.nextInt();  
                    StockClass stock = new StockClass (ticker, date, open, high, low, close, volume);
                    tree.insert(key, stock);
                    break;
                case 4:
                    tree.delete(strInput("Enter a key to delete on the tree: "));
                    break;
                case 5:
                    System.out.println("Displaying tree statistic");
                    System.out.println("Tree size: " + tree.getSize());
                    System.out.println("Tree height: " + tree.getHeight());
                    int actualHeight = tree.getHeight();
                    int idealHeight = tree.getIdealHeight();
                    double balance = ((double)idealHeight / (double)actualHeight) * 100.0;
                    System.out.println("Tree balance: " + dp2.format(balance) + "%");
                    break;
                case 6:
                    System.out.println("Save as?\n(1) -Text output\n(2) -Serialized File\n(3) -Binary Export\n(0) -Return");
                    System.out.print(">Choice: ");
                    saveChoice = sc.nextInt();
                    switch(saveChoice)
                    {
                        case 1:
                            TreeIO.writeBST(tree, strInput("Enter the name of the save file: "));
                            break;
                        case 2:
                            TreeIO.serBST(tree, strInput("Enter the name of the save file: "));
                            break;
                        case 3:
                            TreeIO.saveBST(tree, strInput("Enter the name of the save file: "));
                            break;
                        case 0:
                            System.out.println("...Returning to previous menu...");
                            break;
                        default:
                            System.out.println("Wrong output file type. Returning to menu....");
                            break;
                    }
                    break;
                case 0:
                    System.out.println("Returning to previous menu!...");
                    break;
                default:    
                    System.out.println("Choose one of the options given!");
                    break;
            }
        }while(choice != 0);
    }

    /**************************************************************************
    *Submodule: mainMenuBT
    *Import: none   
    *Export: none
    *Assertion: Interactive environment for Block-Tree, contains all of the 
    *           options to interact with the tree.        
    **************************************************************************/
    public static void mainMenuBT ()
    {
        DSAQueue load = null;
        DecimalFormat dp2 = new DecimalFormat("#0.00");
        BlockTree tree = null;
        int choice, saveChoice, loadChoice, order;
        Object val = null;
        Scanner sc = new Scanner (System.in);
        do
        {
            System.out.println("(1) -Load new data\n(2) -Tree find\n(3) -Tree insert\n(4) -Tree delete\n(5) -Tree statistic\n(6) -Save tree\n(0) -Quit");    
            System.out.print(">Choice: ");
            choice = sc.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("Loading function\n(1) -CSV\n(2) -Serialized File\n(3) -Binary Import\n(0) -Return");
                    System.out.print(">Choice: ");
                    loadChoice = sc.nextInt();
                    switch(loadChoice)
                    {
                        case 1:
                            System.out.println("Enter the order [e.g. > 2]");
                            order = sc.nextInt();
                            if(order < 2)
                            {
                                throw new IllegalArgumentException ("Order is too low!");
                            }
                            tree = new BlockTree(order, strInput("Enter a file to load to the tree: "));
                            break;
                        case 2:
                            tree = (BlockTree)TreeIO.load(strInput("Enter the load file: "));
                            break;
                        case 3:
                            System.out.println("Enter the order [e.g. > 2]");
                            order = sc.nextInt();
                            tree = new BlockTree(order);
                            load = TreeIO.open(strInput("Enter the name of the binary file: "));
                            while(!load.isEmpty())
                            {
                                StockClass stock = (StockClass)load.dequeue();   
                                tree.insert(stock.getTicker(), stock); 
                            }
                            break;
                        case 0:
                            System.out.println("...Returning to previous menu...");
                            break;
                        default:
                            System.out.println("Wrong output file type. Returning to menu....");
                            break;
                    }
                    break;
                case 2:
                    val = tree.find(strInput("Enter a key to find on the tree: "));
                    System.out.println(((StockClass)val).status());
                    break;
                case 3:
                    String key = strInput("Enter a valid ticker [e.g. AAA]: ");    
                    String ticker = key;    
                    System.out.println("Enter a valid date [e.g. YYYYMMDD]:");
                    int date = sc.nextInt();    
                    System.out.println("Enter a valid open [e.g. >= 0]:");
                    double open = sc.nextDouble();    
                    System.out.println("Enter a valid high [e.g. >= 0]:");
                    double high = sc.nextDouble();   
                    System.out.println("Enter a valid low [e.g. >= 0]:");
                    double low = sc.nextDouble();   
                    System.out.println("Enter a valid close [e.g. >= 0]:");
                    double close = sc.nextDouble(); 
                    System.out.println("Enter a valid volume [e.g. >= 0]:");
                    int volume = sc.nextInt();  
                    StockClass stock = new StockClass (ticker, date, open, high, low, close, volume);
                    tree.insert(key, stock);
                    break;
                case 4:
                    tree.delete(strInput("Enter a key to delete on the tree: "));
                    break;
                case 5:
                    System.out.println("Displaying tree statistic");
                    System.out.println("Tree size: " + tree.getSize());
                    System.out.println("Tree height: " + tree.getHeight());
                    int actualHeight = tree.getHeight();
                    int idealHeight = tree.getIdealHeight();
                    double balance = ((double)idealHeight / (double)actualHeight) * 100.0;
                    System.out.println("Tree balance: " + dp2.format(balance) + "%");
                    break;
                case 6:
                    System.out.println("Save as?\n(1) -Text output\n(2) -Serialized File\n(3) -Binary Export\n(0) -Return");
                    System.out.print(">Choice: ");
                    saveChoice = sc.nextInt();
                    switch(saveChoice)
                    {
                        case 1:
                            TreeIO.writeBT(tree, strInput("Enter the name of the save file: "));
                            break;
                        case 2:
                            TreeIO.serBT(tree, strInput("Enter the name of the save file: "));
                            break;
                        case 3:
                            TreeIO.saveBT(tree, strInput("Enter the name of the save file: "));
                            break;
                        case 0:
                            System.out.println("...Returning to previous menu...");
                            break;
                        default:
                            System.out.println("Wrong output file type. Returning to menu....");
                            break;
                    }
                    break;
                case 0:
                    System.out.println("Returning to previous menu!...");
                    break;
                default:    
                    System.out.println("Choose one of the options given!");
                    break;
            }
        }while(choice != 0);
    }

    /**************************************************************************
    *Submodule: mainMenuTTFT
    *Import: none   
    *Export: none
    *Assertion: Interactive environment for 234-Tree, contains all of the 
    *           options to interact with the tree.        
    **************************************************************************/
    public static void mainMenuTTFT ()
    {
        DSAQueue load = null;
        DecimalFormat dp2 = new DecimalFormat("#0.00");
        TwoThreeFourTree tree = null;
        int choice, saveChoice, loadChoice;
        Object val = null;
        Scanner sc = new Scanner (System.in);
        do
        {
            System.out.println("(1) -Load new data\n(2) -Tree find\n(3) -Tree insert\n(4) -Tree delete\n(5) -Tree statistic\n(6) -Save tree\n(0) -Quit");    
            System.out.print(">Choice: ");
            choice = sc.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("Loading function\n(1) -CSV\n(2) -Serialized File\n(3) -Binary Import\n(0) -Return");
                    System.out.print(">Choice: ");
                    loadChoice = sc.nextInt();
                    switch(loadChoice)
                    {
                        case 1:
                            tree = new TwoThreeFourTree(strInput("Enter a file to load to the tree: "));
                            break;
                        case 2:
                            tree = (TwoThreeFourTree)TreeIO.load(strInput("Enter the load file: "));
                            break;
                        case 3:
                            tree = new TwoThreeFourTree();
                            load = TreeIO.open(strInput("Enter the name of the binary file: "));
                            while(!load.isEmpty())
                            {
                                StockClass stock = (StockClass)load.dequeue();   
                                tree.insert(stock.getTicker(), stock); 
                            }
                            break;
                        case 0:
                            System.out.println("...Returning to previous menu...");
                            break;
                        default:
                            System.out.println("Wrong output file type. Returning to menu....");
                            break;
                    }
                    break;
                case 2:
                    val = tree.find(strInput("Enter a key to find on the tree: "));
                    System.out.println(((StockClass)val).status());
                    break;
                case 3:
                    String key = strInput("Enter a valid ticker [e.g. AAA]: ");    
                    String ticker = key;    
                    System.out.println("Enter a valid date [e.g. YYYYMMDD]:");
                    int date = sc.nextInt();    
                    System.out.println("Enter a valid open [e.g. >= 0]:");
                    double open = sc.nextDouble();    
                    System.out.println("Enter a valid high [e.g. >= 0]:");
                    double high = sc.nextDouble();   
                    System.out.println("Enter a valid low [e.g. >= 0]:");
                    double low = sc.nextDouble();   
                    System.out.println("Enter a valid close [e.g. >= 0]:");
                    double close = sc.nextDouble(); 
                    System.out.println("Enter a valid volume [e.g. >= 0]:");
                    int volume = sc.nextInt();  
                    StockClass stock = new StockClass (ticker, date, open, high, low, close, volume);
                    tree.insert(key, stock);
                    break;
                case 4:
                    tree.delete(strInput("Enter a key to delete on the tree: "));
                    break;
                case 5:
                    System.out.println("Displaying tree statistic");
                    System.out.println("Tree size: " + tree.getSize());
                    System.out.println("Tree height: " + tree.getHeight());
                    int actualHeight = tree.getHeight();
                    int idealHeight = tree.getIdealHeight();
                    double balance = ((double)idealHeight / (double)actualHeight) * 100.0;
                    System.out.println("Tree balance: " + dp2.format(balance) + "%");
                    break;
                case 6:
                    System.out.println("Save as?\n(1) -Text output\n(2) -Serialized File\n(3) -Binary Export\n(0) -Return");
                    System.out.print(">Choice: ");
                    saveChoice = sc.nextInt();
                    switch(saveChoice)
                    {
                        case 1:
                            TreeIO.writeTTFT(tree, strInput("Enter the name of the save file: "));
                            break;
                        case 2:
                            TreeIO.serTTFT(tree, strInput("Enter the name of the save file: "));
                            break;
                        case 3:
                            TreeIO.saveTTFT(tree, strInput("Enter the name of the save file: "));
                            break;
                        case 0:
                            System.out.println("...Returning to previous menu...");
                            break;
                        default:
                            System.out.println("Wrong output file type. Returning to menu....");
                            break;
                    }
                    break;
                case 0:
                    System.out.println("Returning to previous menu!...");
                    break;
                default:    
                    System.out.println("Choose one of the options given!");
                    break;
            }
        }while(choice != 0);
    }
    
    /**************************************************************************
    *Submodule: profiler
    *Import: type (String), size (int), filename (String)   
    *Export: none
    *Assertion: Profiling mode, will only display statistic based on command
    *           line argument.        
    **************************************************************************/
    public static void profiler (String type, int size, String filename)
    {
        DecimalFormat dp2 = new DecimalFormat("#0.00");
        if(type.equals("BST"))
        {
            long startTime = System.nanoTime();
            BinarySearchTree tree = new BinarySearchTree (filename);
            long estimatedTime = (System.nanoTime() - startTime)/tree.getSize();
            System.out.println("Tree size: " + tree.getSize());
            System.out.println("Tree height: " + tree.getHeight());
            int actualHeight = tree.getHeight();
            int idealHeight = tree.getIdealHeight();
            double balance = ((double)idealHeight / (double)actualHeight) * 100.0;
            System.out.println("Tree balance: " + dp2.format(balance) + "%");
            System.out.println("Average time insertion per node: " + estimatedTime + " nanoseconds");
            System.out.println("Profiling mode ends!");
        }
        else if(type.equals("BT"))
        {
            long startTime = System.nanoTime();
            BlockTree tree = new BlockTree (size, filename);
            long estimatedTime = (System.nanoTime() - startTime)/tree.getSize();
            System.out.println("Tree size: " + tree.getSize());
            System.out.println("Tree height: " + tree.getHeight());
            int actualHeight = tree.getHeight();
            int idealHeight = tree.getIdealHeight();
            double balance = ((double)idealHeight / (double)actualHeight) * 100.0;
            System.out.println("Tree balance: " + dp2.format(balance) + "%");
            System.out.println("Average time insertion per node: " + estimatedTime + " nanoseconds");
            System.out.println("Profiling mode ends!");
        }
        else if(type.equals("TTFT"))
        {
            long startTime = System.nanoTime();
            TwoThreeFourTree tree = new TwoThreeFourTree (filename);
            long estimatedTime = (System.nanoTime() - startTime)/tree.getSize();
            System.out.println("Tree size: " + tree.getSize());
            System.out.println("Tree height: " + tree.getHeight());
            int actualHeight = tree.getHeight();
            int idealHeight = tree.getIdealHeight();
            double balance = ((double)idealHeight / (double)actualHeight) * 100.0;
            System.out.println("Tree balance: " + dp2.format(balance) + "%");
            System.out.println("Average time insertion per node: " + estimatedTime + " nanoseconds");
            System.out.println("Profiling mode ends!");
        }
        else
        {
            System.out.println("Invalid tree selection!");
        }
    }

    /**************************************************************************
    *Submodule: strInput
    *Import: prompt (String)
    *Export: str (String)
    *Assertion: Allows user to input string in peace        
    **************************************************************************/
    public static String strInput (String prompt)
    {
        String str;
        Scanner sc = new Scanner (System.in);
        System.out.print(prompt);
        str = sc.nextLine();
        return str;
    }
}
