public enum MenuEnum {
    MAPS, WHERETO, TRIPCOMP, MENU,
    SPEECH, ABOUT, SATELLITE, ONOFF;
    @Override 
    public String toString() {
    switch(this) {
      case MAPS:	return "Maps";
      case WHERETO:	return "WhereTo";
      case TRIP:	return "TripComp";
      case MENU:	return "Menu";
      case SPEECH:	return "Speech";
      case ABOUT:	return "About";
      case SATELLITE:	return "Satellite";
      case ONOFF:	return "OnOff";
      default: throw new IllegalArgumentException();
    }
  }
}
