package transmetteurs;

import destinations.DestinationInterface;
import information.Information;
import information.InformationNonConforme;

public class TransmetteurParfait extends Transmetteur <Boolean,Boolean> {

	
	/**
	    * reeoit une information. 
		 * Cette methode, en fin d'execution, appelle la methode emettre.
	    * @param information  l'information  reeue
	    */
	       public void recevoir(Information <Boolean> info) throws InformationNonConforme{
	    	  
	    	   this.informationRecue = info;
	    	   this.emettre();
	       }
	      
	   
	    /**
	    * emet l'information construite par le transmetteur  
	    */
	      public void emettre() throws InformationNonConforme{  
	    	  
	          for (DestinationInterface <Boolean> destinationConnectee : destinationsConnectees) { //Boolean ??
	        	  
	             destinationConnectee.recevoir(informationRecue);
	             
	          }
	          
	          this.informationEmise = informationRecue; 
	          
	      }

}

