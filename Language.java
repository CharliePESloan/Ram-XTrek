public class Language
{
	String name;
	String googleCode;
	String bingCode;
	String artist;

	public Language(String name, String langCode)
	{
		this.name  = name;
		this.bingCode = langCode;
		switch (langCode)
		{
			case "fr-FR":
				artist = "(fr-FR, Julie, Apollo)";
				this.googleCode = "fr";
				break;
			case "de-DE":
				artist = "(de-DE, Hedda)";
				this.googleCode = "de";
				break;
			case "it-IT":
				artist = "(it-IT, Cosimo, Apollo)";
				this.googleCode = "it";
				break;
			case "es-ES":
				artist = "(es-ES, Laura, Apollo)";
				this.googleCode = "es";
				break;
			default:
				artist = "(en-GB, Susan, Apollo)";
				this.googleCode = "en";
				break;
		}
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

}
