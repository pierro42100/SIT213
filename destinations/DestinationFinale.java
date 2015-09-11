package destinations;

import information.Information;
import information.InformationNonConforme;

public class DestinationFinale extends Destination <Boolean>{

	/**
	    * reçoit une information 
	    * @param information l'information à recevoir
	    */
	       public void recevoir(Information info) throws InformationNonConforme{
	    	   informationRecue = info;
	       }
	    
	
}
