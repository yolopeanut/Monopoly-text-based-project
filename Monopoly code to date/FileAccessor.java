//DONE BY BRANDON
import java.util.Scanner;
import java.io.File;            
import java.io.IOException;    

/* Textfile accessing class */
public class FileAccessor
{
   private String filename;  // Keeps track of the filename of the text file
   
   /* Constructor: Instantiate the object by providing the 
    * filename of the text file */
   public FileAccessor(String filename)
   {
      this.filename = filename;  // Keeps track of the filename of the text file
   }
   
   
   // HELPER METHOD
   //Used for checking counter if its equals to 11, return true, which resets it to 0. - USED FOR LOCATION IMPORT.
   public boolean IfChecking11(int counter)//DONE DO NOT CHANGE
   {
	   if (counter == 11)
	   {
		  return true;
	   }
	   else 
		   return false;
   }
   
   //Used for checking counter if its equals to 9, return true, which resets it to 0. - USED FOR LOCATION IMPORT.
   public boolean IfChecking9(int counter)//DONE DO NOT CHANGE
   {
	   if (counter == 9)
	   {
		  return true;
	   }
	   else 
		   return false;
   }
   
   //Used for checking counter if its equals to 16, return true, which resets it to 0. - USED FOR CARD IMPORT.
   public boolean IfChecking16(int counter)//DONE DO NOT CHANGE
   {
	   if (counter == 16)
	   {
		  return true;
	   }
	   else 
		   return false;
   }
   /*============== Public Methods =================*/
   //Import locations from text file into north,east,south,west array, in that order.
   public void ImportLocationFromFile(Location[] locationNorth, Location[] locationEast, Location[] locationSouth, Location[] locationWest)//DONE DO NOT CHANGE
   {
      String record;
      String [] LocationInfo;
      
      try
      {
         File file = new File(this.filename); 
         Scanner input = new Scanner(file); 

         int counter = 0;
         // While the file has a line of record
         while (input.hasNextLine()) 
         {
            // Look for the  number
            record = input.nextLine();
            LocationInfo = record.split("\t");
            
            Location newLocation = null;
            
            //If the fourth position of the text is equal to north, then make a new location and add it to the array.
            if (LocationInfo[4].equals("NORTH"))
            {
            	if( Integer.parseInt(LocationInfo[0]) == Location.COLOUR_LOCATION  )
            	{
            		newLocation = new ColourLocation(LocationInfo[1],LocationInfo[2], Integer.valueOf(LocationInfo[3]), Integer.valueOf(LocationInfo[5]), Integer.valueOf(LocationInfo[6]),
            				Integer.valueOf(LocationInfo[7]),Integer.valueOf(LocationInfo[8]),Integer.valueOf(LocationInfo[9]),Integer.valueOf(LocationInfo[10]),Integer.valueOf(LocationInfo[11]), 
            				Integer.valueOf(LocationInfo[12]), Integer.valueOf(LocationInfo[13]), Integer.valueOf(LocationInfo[14]) );
            	}
            	else if( Integer.parseInt(LocationInfo[0]) == Location.BLANK_LOCATION )
            	{
            		newLocation = new BlankLocation(LocationInfo[1], LocationInfo[2], Integer.valueOf(LocationInfo[13]));

            	}

            	else if( Integer.parseInt(LocationInfo[0]) == Location.SPECIAL_LOCATION)
            	{
            		newLocation = new SpecialLocation(LocationInfo[1],Integer.valueOf(LocationInfo[2]),Integer.valueOf(LocationInfo[3]),Integer.valueOf(LocationInfo[13]), Integer.valueOf(LocationInfo[5]),Integer.valueOf(LocationInfo[6]),Integer.valueOf(LocationInfo[7]),Integer.valueOf(LocationInfo[8]));

            	}
            	locationNorth[counter] = newLocation;
            	counter++;
            	
            	if(this.IfChecking11(counter) == true)
            		counter = 0;
            	
            	
            }
            
            //If the fourth position of the text is equal to EAST, then make a new location and add it to the array.
            else if (LocationInfo[4].equals("EAST"))
            {
            	if( Integer.parseInt(LocationInfo[0]) == Location.COLOUR_LOCATION  )
            	{
            		newLocation = new ColourLocation(LocationInfo[1],LocationInfo[2], Integer.valueOf(LocationInfo[3]), Integer.valueOf(LocationInfo[5]), Integer.valueOf(LocationInfo[6]),
            				Integer.valueOf(LocationInfo[7]),Integer.valueOf(LocationInfo[8]),Integer.valueOf(LocationInfo[9]),Integer.valueOf(LocationInfo[10]),Integer.valueOf(LocationInfo[11]), 
            				Integer.valueOf(LocationInfo[12]), Integer.valueOf(LocationInfo[13]), Integer.valueOf(LocationInfo[14]) );
            	}
            	else if( Integer.parseInt(LocationInfo[0]) == Location.BLANK_LOCATION )
            	{
            		newLocation = new BlankLocation(LocationInfo[1],LocationInfo[2], Integer.valueOf(LocationInfo[13]));
            	}

            	else if( Integer.parseInt(LocationInfo[0]) == Location.SPECIAL_LOCATION)
            	{
            		newLocation = new SpecialLocation(LocationInfo[1],Integer.valueOf(LocationInfo[2]),Integer.valueOf(LocationInfo[3]),Integer.valueOf(LocationInfo[13]), Integer.valueOf(LocationInfo[5]),Integer.valueOf(LocationInfo[6]),Integer.valueOf(LocationInfo[7]),Integer.valueOf(LocationInfo[8]));
            	}
            	locationEast[counter] = newLocation;
            	counter++;
            	
            	if(this.IfChecking9(counter) == true)
            		counter = 0;

            }
            
            //If the fourth position of the text is equal to SOUTH, then make a new location and add it to the array.
            else if (LocationInfo[4].equals("SOUTH"))
            {
            	if( Integer.parseInt(LocationInfo[0]) == Location.COLOUR_LOCATION  )
            	{
            		newLocation = new ColourLocation(LocationInfo[1],LocationInfo[2], Integer.valueOf(LocationInfo[3]), Integer.valueOf(LocationInfo[5]), Integer.valueOf(LocationInfo[6]),
            				Integer.valueOf(LocationInfo[7]),Integer.valueOf(LocationInfo[8]),Integer.valueOf(LocationInfo[9]),Integer.valueOf(LocationInfo[10]),Integer.valueOf(LocationInfo[11]), 
            				Integer.valueOf(LocationInfo[12]), Integer.valueOf(LocationInfo[13]), Integer.valueOf(LocationInfo[14]));
            	}
            	else if( Integer.parseInt(LocationInfo[0]) == Location.BLANK_LOCATION )
            	{
            		newLocation = new BlankLocation(LocationInfo[1],LocationInfo[2], Integer.valueOf(LocationInfo[13]));
            	}

            	else if( Integer.parseInt(LocationInfo[0]) == Location.SPECIAL_LOCATION)
            	{
            		newLocation = new SpecialLocation(LocationInfo[1],Integer.valueOf(LocationInfo[2]),Integer.valueOf(LocationInfo[3]),Integer.valueOf(LocationInfo[13]), Integer.valueOf(LocationInfo[5]),Integer.valueOf(LocationInfo[6]),Integer.valueOf(LocationInfo[7]),Integer.valueOf(LocationInfo[8]));
            	}
            	locationSouth[counter] = newLocation;
            	counter++;
            	
            	if(this.IfChecking11(counter) == true)
            		counter = 0;

            }
            
            //If the fourth position of the text is equal to WEST, then make a new location and add it to the array.
            else if (LocationInfo[4].equals("WEST"))
            {
            	if( Integer.parseInt(LocationInfo[0]) == Location.COLOUR_LOCATION  )
            	{
            		newLocation = new ColourLocation(LocationInfo[1],LocationInfo[2], Integer.valueOf(LocationInfo[3]), Integer.valueOf(LocationInfo[5]), Integer.valueOf(LocationInfo[6]),
            				Integer.valueOf(LocationInfo[7]),Integer.valueOf(LocationInfo[8]),Integer.valueOf(LocationInfo[9]),Integer.valueOf(LocationInfo[10]),Integer.valueOf(LocationInfo[11]), 
            				Integer.valueOf(LocationInfo[12]), Integer.valueOf(LocationInfo[13]), Integer.valueOf(LocationInfo[14]));
            	}
            	else if( Integer.parseInt(LocationInfo[0]) == Location.BLANK_LOCATION )
            	{
            		newLocation = new BlankLocation(LocationInfo[1],LocationInfo[2], Integer.valueOf(LocationInfo[13]));
            	}

            	else if( Integer.parseInt(LocationInfo[0]) == Location.SPECIAL_LOCATION)
            	{
            		newLocation = new SpecialLocation(LocationInfo[1],Integer.valueOf(LocationInfo[2]),Integer.valueOf(LocationInfo[3]),Integer.valueOf(LocationInfo[13]), Integer.valueOf(LocationInfo[5]),Integer.valueOf(LocationInfo[6]),Integer.valueOf(LocationInfo[7]),Integer.valueOf(LocationInfo[8]));
            	}
            	locationWest[counter] = newLocation;
            	counter++;
            	if(this.IfChecking9(counter) == true)
            		counter = 0;

            }
            
         }
         
         
         input.close();
      }
      catch (IOException ex)
      {
      } 
      
      
   }
   
   //Method to import card from file, takes in community cest and chance arrays.
   public void ImportCardFromFile(Cards[] communityChest, Cards[] chance)//DONE DO NOT CHANGE
   {
	   String record;
	   String [] CardsInfo;

	   try
	   {
		   int counter= 0;
		   File file = new File(this.filename); 
		   Scanner input = new Scanner(file); 

		   // While the file has a line of record
		   while (input.hasNextLine()) 
		   {
			   // Look for the  number
			   record = input.nextLine();
			   CardsInfo = record.split("\t");

			   Cards newCards = null;
			   
			   if( Integer.parseInt(CardsInfo[0]) == Cards.COMMUNITY_CARD )
			   {
				   newCards = new CommunityChestCard(CardsInfo[1]);
				   communityChest[counter] = newCards;
				   counter++;
			   }
			   else if( Integer.parseInt(CardsInfo[0]) == Cards.CHANCE_CARD )
			   {
				   newCards = new ChanceCard(CardsInfo[1]);
				   chance[counter] = newCards;
				   counter++;
			   }
			   
			   if (this.IfChecking16(counter) == true)
				   counter = 0;

		   }

		   input.close();
	   }
	   catch (IOException ex)
	   {
	   } 
	      
	   
   }
   

}