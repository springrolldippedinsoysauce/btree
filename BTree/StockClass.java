import java.util.*;
import java.io.*;

public class StockClass implements java.io.Serializable
{
    private String ticker; //Comma separated ticker
    private double open, high, low, close;
    private int date, volume;

    /**************************************************************************
    *DEFAULT CONSTRUCTOR
    *IMPORT: none
    *EXPORT: object of StockClass
    *ASSERTION: Exports an object of default values.
    * ************************************************************************/
    public StockClass()
    {
        ticker = "";
        date = 00000000;
        open = 0;
        high = 0;
        low = 0;
        close = 0;
        volume = 0;
    }

    /**************************************************************************
    *ALTERNATE CONSTRUCTOR
    *IMPORT: inTicker (String), inDate (Integer), inOpen (Real), inHigh (Real),
    *        inLow (Real), inClose (Real), inVolume (Integer)
    *EXPORT: object of StockClass
    *ASSERTION: Exports an object of changed default values.
    * ************************************************************************/
    public StockClass(String inTicker, int inDate, double inOpen, double inHigh,
                        double inLow, double inClose, int inVolume)
    {
        setTicker(inTicker);
        setDate(inDate);
        setOpen(inOpen);
        setHigh(inHigh);
        setLow(inLow);
        setClose(inClose);
        setVolume(inVolume);
    }
    /**************************************************************************
    *NAME: toString
    *IMPORT: none
    *EXPORT: String
    *ASSERTION: Exports a list of string.
    * ************************************************************************/
    public String toString()
    {
        return (getTicker() + "," + getDate() + "," + getOpen() + "," + 
                getClose() + "," + getHigh() + "," + getLow() 
                + "," + getVolume());            
    }

    /**************************************************************************
    *NAME: status
    *IMPORT: none
    *EXPORT: String
    *ASSERTION: Exports a list of string.
    * ************************************************************************/
    public String status()
    {
        return ("....Stock Found....\nTicker: " + getTicker() + "\n" + "Date: " 
                + getDate() + "\n" + "Open: " + getOpen() + "\n" + "Close: " 
                + getClose() + "\n" + "High: " + getHigh() + "\n" + "Low: " 
                + getLow() + "\n" + "Volume: " + getVolume());            
    }

    /**************************************************************************
    *NAME: getTicker
    *IMPORT: none
    *EXPORT: ticker (String)
    *ASSERTION: -
    * ************************************************************************/
    public String getTicker()
    {
        return ticker;
    }
    
    /**************************************************************************
    *NAME: getDate
    *IMPORT: none 
    *EXPORT: date (Integer)
    *ASSERTION: -
    * ************************************************************************/
    public int getDate()
    {
        return date;
    }
    
    /**************************************************************************
    *NAME: getOpen
    *IMPORT: none
    *EXPORT: open (Real)
    *ASSERTION: -
    * ************************************************************************/
    public double getOpen()
    {
        return open;
    }

    /**************************************************************************
    *NAME: getHigh
    *IMPORT: none
    *EXPORT: high (Real)
    *ASSERTION: -
    * ************************************************************************/
    public double getHigh()
    {
        return high;
    }

    /**************************************************************************
    *NAME: getLow
    *IMPORT: none
    *EXPORT: low (Real)
    *ASSERTION: -
    * ************************************************************************/
    public double getLow()
    {
        return low;
    }
    
    /**************************************************************************
    *NAME: getClose
    *IMPORT: none
    *EXPORT: close (Real)
    *ASSERTION: -
    * ************************************************************************/
    public double getClose()
    {
        return close;
    }

    /**************************************************************************
    *NAME: getVolume
    *IMPORT: none
    *EXPORT: volume (Integer)
    *ASSERTION: -
    * ************************************************************************/
    public int getVolume()
    {
        return volume;
    }
    
    /**************************************************************************
    *NAME: setTicker
    *IMPORT: inTicker (String)
    *EXPORT: none
    *ASSERTION: -
    * ************************************************************************/
    public void setTicker(String inTicker)
    {
        if (validateTicker(inTicker))
        {
            ticker = inTicker;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Ticker!");
        }
    }    
    
    /**************************************************************************
    *NAME: setDate
    *IMPORT: inDate (Integer)
    *EXPORT: none
    *ASSERTION: -
    * ************************************************************************/
    public void setDate(int inDate)
    {
        if (validateDate(inDate))
        {
            date = inDate;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Date!");
        }
    }    
    
    /**************************************************************************
    *NAME: setOpen
    *IMPORT: inOpen (Real)
    *EXPORT: none
    *ASSERTION: -
    * ************************************************************************/
    public void setOpen(double inOpen)
    {
        if (validateOpen(inOpen))
        {
            open = inOpen;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Open!");
        }
    }

    /**************************************************************************
    *NAME: setHigh
    *IMPORT: inHigh (Real)
    *EXPORT: none
    *ASSERTION: -
    * ************************************************************************/
    public void setHigh(double inHigh)
    {
        if (validateHigh(inHigh))
        {
            high = inHigh;
        }
        else
        {
            throw new IllegalArgumentException("Invalid High!");
        }
    }
    
    /**************************************************************************
    *NAME: setLow
    *IMPORT: inLow (Real)
    *EXPORT: none
    *ASSERTION: -
    * ************************************************************************/
    public void setLow(double inLow)
    {
        if (validateLow(inLow))
        {
            low = inLow;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Low!");
        }
    }

    /**************************************************************************
    *NAME: setClose
    *IMPORT: inClose (Real)
    *EXPORT: none
    *ASSERTION: -
    * ************************************************************************/
    public void setClose(double inClose)
    {
        if (validateClose(inClose))
        {
            close = inClose;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Close!");
        }
    }
    
    /**************************************************************************
    *NAME: setVolume
    *IMPORT: inVolume (Integer)
    *EXPORT: none
    *ASSERTION: -
    * ************************************************************************/
    public void setVolume(int inVolume)
    {
        if (validateVolume(inVolume))
        {
            volume = inVolume;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Volume!");
        }
    }

    /**************************************************************************
    *NAME: validateTicker
    *IMPORT: inTicker (String)
    *EXPORT: valid (Boolean)
    *ASSERTION: -
    * ************************************************************************/
    private boolean validateTicker(String inTicker)
    {
        boolean valid = true;
        int str = inTicker.length();
        if (inTicker.equals(null))
        {
            valid = false;
        }
        return valid;
    }   
    
    /**************************************************************************
    *NAME: validateDate
    *IMPORT: inDate (Integer)
    *EXPORT: valid (Boolean)
    *ASSERTION: -
    * ************************************************************************/
    private boolean validateDate(int inDate)
    {
        boolean valid = false;
        int y, m, d;
        y = inDate / 10000;
        m = inDate / 100 % 100;
        d = inDate % 100;
        
        if (y == 2019)
        {
            if ((m > 0)&&(m <= 12))
            {
                if ((d > 0)&&(d <= 31))
                {
                    valid = true;
                }
            }
        }
        return valid;
    }   
        
    /**************************************************************************
    *NAME: validateOpen
    *IMPORT: inOpen (Real)
    *EXPORT: boolean
    *ASSERTION: -
    * ************************************************************************/
    private boolean validateOpen(double inOpen)
    {
        return (inOpen >= 0 ); 
    }
   
    /**************************************************************************
    *NAME: validateHigh
    *IMPORT: inHigh (Real)
    *EXPORT: boolean
    *ASSERTION: -
    * ************************************************************************/
    private boolean validateHigh(double inHigh)
    {
        return (inHigh >= 0 );
    }   

    /**************************************************************************
    *NAME: validateLow
    *IMPORT: inLow (Real)
    *EXPORT: boolean
    *ASSERTION: -
    * ************************************************************************/
    private boolean validateLow(double inLow)
    {
        return (inLow >= 0 );
    }
   
    /**************************************************************************
    *NAME: validateClose 
    *IMPORT: inClose (Real)
    *EXPORT: boolean 
    *ASSERTION: -
    * ************************************************************************/
    private boolean validateClose(double inClose)
    {
        return (inClose >= 0 );
    }
   
    /**************************************************************************
    *NAME: validateVolume 
    *IMPORT: inVolume (Integer)
    *EXPORT: boolean
    *ASSERTION: -
    * ************************************************************************/
    private boolean validateVolume(int inVolume)
    {
        return (inVolume >= 0 );
    }   
}
