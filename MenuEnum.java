/**
* Author: Darya Shyroka
* An Enumeration class to encode the state of the device
* while in the Menu (or main) frame. 
**/

public enum MenuEnum {
    MAPS, WHERETO, TRIPCOMP, MENU,
    SPEECH, ABOUT, SATELLITE, ONOFF;
    // a method to convert the enums to a string,
    // required by certain functions. 
    @Override 
    public String toString() {
    switch(this) {
      case MAPS: return "Maps";
      case WHERETO: return "WhereTo";
      case TRIPCOMP: return "TripComp";
      case MENU: return "Menu";
      case SPEECH: return "Speech";
      case ABOUT: return "About";
      case SATELLITE: return "Satellite";
      case ONOFF: return "OnOff";
      default: throw new IllegalArgumentException();
    }
  }
}