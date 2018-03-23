/* Language
 * Represents a language
 */
public class Language
{
	/* Variables */
	String name;
	String googleCode;
	String bingCode;
	String artist;
	final String destinationText;
	final String kilometerText;
	final String meterText;

	/* Constructor */
	public Language(String googleCode)
	{
		this.googleCode = googleCode;
		switch (googleCode)
		{
			case "fr":
				artist = "(fr-FR, Julie, Apollo)";
				destinationText = "Vous avez atteint votre destination.";
				kilometerText = "Dans %.1f kilomètres,";
				meterText = "Dans %d mètres,";
				break;
			case "de":
				artist = "(de-DE, Hedda)";
				destinationText = "Sie haben ihr Ziel erreicht.";
				kilometerText = "In %.1f Kilometern,";
				meterText = "In %d Metern,";
				break;
			case "it":
				artist = "(it-IT, Cosimo, Apollo)";
				destinationText = "Hai raggiunto la tua destinazione.";
				kilometerText = "In %.1f metri,";
				meterText = "In %d chilometri,";
				break;
			case "es":
				artist = "(es-ES, Laura, Apollo)";
				destinationText = "Ha llegado a su destino.";
				kilometerText = "En %.1f kilómetros,";
				meterText = "En %d metros,";
				break;
			default:
				artist = "(en-GB, Susan, Apollo)";
				destinationText = "You have reached your destination.";
				kilometerText = "In %.1f kilometers,";
				meterText = "In %d meters,";
				break;
		}
		bingCode = artist.substring(1,6);
		name = "Unknown";
	}
	public Language(String name, String googleCode)
	{
		this(googleCode);
		this.name  = name;
	}

	/* Getters */
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

	public String getKilometerText()
	{
		return kilometerText;
	}

	public String getMeterText()
	{
		return meterText;
	}

}
