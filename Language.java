public class Language
{
	String name;
	String googleCode;
	String bingCode;
	String artist;
	String destinationText;

	public Language(String googleCode)
	{
		this.googleCode = googleCode;
		switch (googleCode)
		{
			case "fr":
				artist = "(fr-FR, Julie, Apollo)";
				destinationText = "Vous avez atteint votre destination.";
				break;
			case "de":
				artist = "(de-DE, Hedda)";
				destinationText = "Sie haben ihr Ziel erreicht.";
				break;
			case "it":
				artist = "(it-IT, Cosimo, Apollo)";
				destinationText = "Hai raggiunto la tua destinazione.";
				break;
			case "es":
				artist = "(es-ES, Laura, Apollo)";
				destinationText = "Ha llegado a su destino.";
				break;
			default:
				artist = "(en-GB, Susan, Apollo)";
				destinationText = "You have reached your destination.";
				break;
		}
		bingCode = artist.substring(1,6);
		name = "This is a bug";
	}
	public Language(String name, String googleCode)
	{
		this(googleCode);
		this.name  = name;
	}

	public String getName()
	{
		return name;
	}

	public String getGoogleCode()
	{
		return googleCode;
	}

	public String getBingCode()
	{
		return bingCode;
	}

	public String getArtist()
	{
		return artist;
	}

	public String getDestinationText()
	{
		return destinationText;
	}

}
