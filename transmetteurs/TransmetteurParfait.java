package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

public class TransmetteurParfait extends Transmetteur <Boolean,Boolean> {

	
	/**
	    * re�oit une information. 
		 * Cette m�thode, en fin d'ex�cution, appelle la m�thode �mettre.
	    * @param information  l'information  re�ue
	    */
	       public void recevoir(Information <Boolean> info) throws InformationNonConforme{
	    	  
	    	   this.informationRecue = info;
	    	   this.emettre();
	       }
	      
	   
	    /**
	    * �met l'information construite par le transmetteur  
	    */
	      public void emettre() throws InformationNonConforme{  
	    	  
	          for (DestinationInterface <Boolean> destinationConnectee : destinationsConnectees) { //Boolean ??
	        	  
	             destinationConnectee.recevoir(informationRecue);
	             
	          }
	          
	          this.informationEmise = informationRecue; 
	          
	      }

}

