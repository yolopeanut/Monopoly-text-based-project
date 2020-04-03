//DONE BY BRANDON
public class Cards {
	public final static int COMMUNITY_CARD = 0; //Numbers to define each card in text file.
	public final static int CHANCE_CARD = 1;

	private Cards[] communityChestCard; //Cards array which stores card objects.
	private Cards[] chanceCard; 		//Cards array which stores card objects.
	private String specialInfo;			//Variable to store the contents of the cards
	
	//Constructor to initialize the array sizes.
	public Cards()
	{
		communityChestCard = new Cards[16];
		chanceCard = new Cards[16];
	}
	//Overloaded Constructor which takes in the special info of the card and store it in private variable in this class.
	public Cards(String specialInfo)
	{
		this.specialInfo = specialInfo;
	}
	
	//===================================================GETTER METHODS=====================================================//
	public Cards[] GetCommCardArray()
	{
		return this.communityChestCard;
	}
	public Cards[] GetChanceCardArray()
	{
		return this.chanceCard;
	}
	public String GetSpecialInfo()
	{
		return this.specialInfo;
	}
	//=====================================================================================================================//

	
	//Method to take in the text filename and create an instance of FileAccessor to import the cards from
	public void AddCard(String filename)
	{
		FileAccessor fileAccessor = new FileAccessor(filename);
		fileAccessor.ImportCardFromFile(communityChestCard, chanceCard);
	}
}
