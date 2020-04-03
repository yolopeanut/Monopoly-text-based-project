//DONE BY ANSELM
import java.util.Random;
import java.util.Scanner;

public class RandomObject {
	
	Random rand = new Random();
	Scanner input = new Scanner(System.in);
	
	private int dice1;
	private int dice2;
	private Cards chance;
	private Cards community;
	
	//Blank constructor
	public RandomObject()
	{
		
	}
	
	//Method to get and random dice roll
	public int RandomDiceRoll()
	{
		this.dice1 = rand.nextInt(6);
		return this.dice1 + 1; 
		
	}
	//Method to get a random integer for a chance card
	public Cards RandomChanceCard(Cards[] card)
	{
		this.chance = card[rand.nextInt(15)+1];
		return this.chance;
	}
	//Method to get a random integer for a comm card
	public Cards RandomCommCard(Cards[] card)
	{
		this.community = card[rand.nextInt(15)+1];
		return this.community; 
	}
}
