package destinations;

import information.Information;
import information.InformationNonConforme;

public class DestinationFinale extends Destination <Boolean>{

	/**
	    * re�oit une information 
	    * @param information l'information � recevoir
	    */
	       public void recevoir(Information info) throws InformationNonConforme{
	    	   informationRecue = info;
	       }
	    
	
}
