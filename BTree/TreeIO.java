import java.util.*;
import java.io.*;

public class TreeIO implements java.io.Serializable
{
    /**************************************************************************
    *Submodule: writeBST
    *Import: tree (BinarySearchTree), save (String)
    *Export: none
    *Assertion: Writes down all of the nodes into a text output given by a
    *           Binary-Tree       
    **************************************************************************/
    public static void writeBST (BinarySearchTree tree, String save)
    {
        FileOutputStream strm = null;                                           
        PrintWriter pw;                                                         
        int i; 
        try
        {
            strm = new FileOutputStream (save);                        
            pw = new PrintWriter (strm);   
            pw.println(tree.toString()); 
            pw.close();                                                         
            System.out.println("...File successfully written...");
        }                                                                       
        catch (IOException e)//Catches io exception                             
        {                                                                       
            if (strm != null)//If file is not at the end of line                
                {//Start of if-then-else statement                                  
                try                                                             
                {                                                               
                    strm.close();//closes the stream                            
                }                                                               
                catch (IOException ex)//Catches io exception                    
                {                                                               
                    //Nothing else to do here....                               
                }                                                               
            }//End of if-then-else statement   
        }                                 
    }

    /**************************************************************************
    *Submodule: writeBT
    *Import: tree (BlockTree), save (String)
    *Export: none
    *Assertion: Writes down all of the nodes into a text output given by a
    *           B-Tree       
    **************************************************************************/
    public static void writeBT (BlockTree tree, String save)
    {
        FileOutputStream strm = null;                                           
        PrintWriter pw;                                                         
        int i; 
        try
        {
            strm = new FileOutputStream (save);                        
            pw = new PrintWriter (strm);   
            pw.println(tree.toString()); 
            pw.close();                                                         
            System.out.println("...File successfully written...");
        }                                                                       
        catch (IOException e)//Catches io exception                             
        {                                                                       
            if (strm != null)//If file is not at the end of line                
                {//Start of if-then-else statement                                  
                try                                                             
                {                                                               
                    strm.close();//closes the stream                            
                }                                                               
                catch (IOException ex)//Catches io exception                    
                {                                                               
                    //Nothing else to do here....                               
                }                                                               
            }//End of if-then-else statement   
        }                                 
    }

    /**************************************************************************
    *Submodule: writeTTFT
    *Import: tree (TwoThreeFourTree), save (String)
    *Export: none
    *Assertion: Writes down all of the nodes into a text output given by a
    *           234-Tree       
    **************************************************************************/
    public static void writeTTFT (TwoThreeFourTree tree, String save)
    {
        FileOutputStream strm = null;                                           
        PrintWriter pw;                                                         
        int i; 
        try
        {
            strm = new FileOutputStream (save);                        
            pw = new PrintWriter (strm);   
            pw.println(tree.toString()); 
            pw.close();                                                         
            System.out.println("...File successfully written...");
        }                                                                       
        catch (IOException e)//Catches io exception                             
        {                                                                       
            if (strm != null)//If file is not at the end of line                
                {//Start of if-then-else statement                                  
                try                                                             
                {                                                               
                    strm.close();//closes the stream                            
                }                                                               
                catch (IOException ex)//Catches io exception                    
                {                                                               
                    //Nothing else to do here....                               
                }                                                               
            }//End of if-then-else statement   
        }                                 
    }

    /**************************************************************************
    *Submodule: serBST
    *Import: tree (BinarySearchTree), save (String)
    *Export: none
    *Assertion: Serializes a BinarySearchTree class      
    **************************************************************************/
    public static void serBST (BinarySearchTree tree, String save)
    {
        FileOutputStream outFile = null;
        ObjectOutputStream outObj = null;       
        try
        {
            outFile = new FileOutputStream(save);
            outObj = new ObjectOutputStream(outFile);
            outObj.writeObject(tree);
            
            //Closing
            outObj.close();
            outFile.close();
            System.out.println("...Serialization Complete...");
        }
        catch(IOException e)
        {
            if(outFile != null)
            {
                try
                {
                    outFile.close();
                }
                catch(IOException e2)
                {
                    //Nothing to do here
                }
            }
        }
    }

    /**************************************************************************
    *Submodule: serBT
    *Import: tree (BlockTree), save (String)
    *Export: none
    *Assertion: Serializes a BlockTree class      
    **************************************************************************/
    public static void serBT (BlockTree tree, String save)
    {
        FileOutputStream outFile = null;
        ObjectOutputStream outObj = null;       
        try
        {
            outFile = new FileOutputStream(save);
            outObj = new ObjectOutputStream(outFile);
            outObj.writeObject(tree);
            
            //Closing
            outObj.close();
            outFile.close();
            System.out.println("...Serialization Complete...");
        }
        catch(IOException e)
        {
            if(outFile != null)
            {
                try
                {
                    outFile.close();
                }
                catch(IOException e2)
                {
                    //Nothing to do here
                }
            }
        }
    }

    /**************************************************************************
    *Submodule: serTTFT
    *Import: tree (TwoThreeFourTree), save (String)
    *Export: none
    *Assertion: Serializes a TwoThreeFourTree class      
    **************************************************************************/
    public static void serTTFT (TwoThreeFourTree tree, String save)
    {
        FileOutputStream outFile = null;
        ObjectOutputStream outObj = null;       
        try
        {
            outFile = new FileOutputStream(save);
            outObj = new ObjectOutputStream(outFile);
            outObj.writeObject(tree);
            
            //Closing
            outObj.close();
            outFile.close();
            System.out.println("...Serialization Complete...");
        }
        catch(IOException e)
        {
            if(outFile != null)
            {
                try
                {
                    outFile.close();
                }
                catch(IOException e2)
                {
                    //Nothing to do here
                }
            }
        }
    }

    /**************************************************************************
    *Submodule: load
    *Import: file (String)
    *Export: loaded (Object)
    *Assertion: Deserialize any serialized file and returns a class object      
    **************************************************************************/
    public static Object load (String file)
    {
        Object loaded = null;
        FileInputStream inFile = null;
        ObjectInputStream inObj = null;       
        try
        {
            inFile = new FileInputStream(file);
            inObj = new ObjectInputStream(inFile);
            loaded = inObj.readObject();
            
            //Closing
            inObj.close();
            inFile.close();
            System.out.println("...File has been deserialized successfully...");
        }
        catch (ClassNotFoundException x)
        {
            System.out.println("Error in processing file: " + x.getMessage());
        }
        catch(IOException e)
        {
            if(inFile != null)
            {
                try
                {
                    inFile.close();
                }
                catch(IOException e2)
                {
                    //Nothing to do here
                }
            }
        }
        return loaded;
    }

    /**************************************************************************
    *Submodule: saveBST
    *Import: tree (BinarySearchTree), save (String)
    *Export: none
    *Assertion: Save data as a binary export     
    **************************************************************************/
    public static void saveBST(BinarySearchTree tree, String save)
    {
        StockClass stock = null;
        DSAQueue stored = tree.storeObjBST(tree.getSize());
        FileOutputStream fileStrm = null;
        DataOutputStream dataStrm = null;
        try
        {
            fileStrm = new FileOutputStream(save);
            dataStrm = new DataOutputStream(fileStrm);
            //Gets the number of elements
            dataStrm.writeInt(stored.getCount());
            while(!stored.isEmpty())
            {
                stock = (StockClass)stored.dequeue();       
                dataStrm.writeUTF(stock.getTicker());
                dataStrm.writeInt(stock.getDate());
                dataStrm.writeDouble(stock.getOpen());
                dataStrm.writeDouble(stock.getHigh());
                dataStrm.writeDouble(stock.getLow());
                dataStrm.writeDouble(stock.getClose());
                dataStrm.writeInt(stock.getVolume());
            }
            fileStrm.close();
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try { fileStrm.close(); }
                catch(IOException e2) {}
            }
            System.out.println("File processing: " + e.getMessage());
        }
    }   

    /**************************************************************************
    *Submodule: saveBT
    *Import: tree (BlockTree), save (String)
    *Export: none
    *Assertion: Save data as a binary export     
    **************************************************************************/
    public static void saveBT(BlockTree tree, String save)
    {
        StockClass stock = null;
        DSAQueue newQ = new DSAQueue (tree.getSize());
        DSAQueue stored = tree.storeObj(newQ);
        FileOutputStream fileStrm = null;
        DataOutputStream dataStrm = null;
        try
        {
            fileStrm = new FileOutputStream(save);
            dataStrm = new DataOutputStream(fileStrm);
            //Gets the number of elements
            dataStrm.writeInt(stored.getCount());
            while(!stored.isEmpty())
            {
                stock = (StockClass)stored.dequeue();       
                dataStrm.writeUTF(stock.getTicker());
                dataStrm.writeInt(stock.getDate());
                dataStrm.writeDouble(stock.getOpen());
                dataStrm.writeDouble(stock.getHigh());
                dataStrm.writeDouble(stock.getLow());
                dataStrm.writeDouble(stock.getClose());
                dataStrm.writeInt(stock.getVolume());
            }
            fileStrm.close();
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try { fileStrm.close(); }
                catch(IOException e2) {}
            }
            System.out.println("File processing: " + e.getMessage());
        }
    }
   
    /**************************************************************************
    *Submodule: saveTTFT
    *Import: tree (TwoThreeFourTree), save (String)
    *Export: none
    *Assertion: Save data as a binary export     
    **************************************************************************/
    public static void saveTTFT(TwoThreeFourTree tree, String save)
    {
        StockClass stock = null;
        DSAQueue newQ = new DSAQueue (tree.getSize());
        DSAQueue stored = tree.storeObj(newQ);
        FileOutputStream fileStrm = null;
        DataOutputStream dataStrm = null;
        try
        {
            fileStrm = new FileOutputStream(save);
            dataStrm = new DataOutputStream(fileStrm);
            //Gets the number of elements
            dataStrm.writeInt(stored.getCount());
            while(!stored.isEmpty())
            {
                stock = (StockClass)stored.dequeue();       
                dataStrm.writeUTF(stock.getTicker());
                dataStrm.writeInt(stock.getDate());
                dataStrm.writeDouble(stock.getOpen());
                dataStrm.writeDouble(stock.getHigh());
                dataStrm.writeDouble(stock.getLow());
                dataStrm.writeDouble(stock.getClose());
                dataStrm.writeInt(stock.getVolume());
            }
            fileStrm.close();
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try { fileStrm.close(); }
                catch(IOException e2) {}
            }
            System.out.println("File processing: " + e.getMessage());
        }
    }  

    /**************************************************************************
    *Submodule: open
    *Import: open (String)
    *Export: newQ (DSAQueue)
    *Assertion: Save data as a binary export     
    **************************************************************************/
    public static DSAQueue open (String open)
    {
        StockClass stock = new StockClass();
        DSAQueue newQ = new DSAQueue (1000000);
        FileInputStream fileStrm = null;
        DataInputStream dataStrm = null;
        try
        {
            fileStrm = new FileInputStream (open);
            dataStrm = new DataInputStream (fileStrm);
            for(int ii = 0; ii < dataStrm.readInt();)
            {
                stock.setTicker(dataStrm.readUTF());
                stock.setDate(dataStrm.readInt());
                stock.setOpen(dataStrm.readDouble());
                stock.setHigh(dataStrm.readDouble());
                stock.setLow(dataStrm.readDouble());
                stock.setClose(dataStrm.readDouble());
                stock.setVolume(dataStrm.readInt());
                newQ.enqueue(stock);
            }
            fileStrm.close();
        }
        
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try { fileStrm.close(); }
                catch(IOException e2) {}
            }
        }
        return newQ;
    }
}
