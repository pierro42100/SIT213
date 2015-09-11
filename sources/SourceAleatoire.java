package sources;
import java.util.Random;

import information.Information;

public class SourceAleatoire extends Source <Boolean> {

	public SourceAleatoire (String messageString, Integer seed){
		
		super();	
		int i;
		
		Random random = new Random(seed);
	    
		int nbBoolean = Integer.parseInt(messageString);
		
		Boolean tabMessage [] = new Boolean [nbBoolean];
		
		for(i = 0 ; i < nbBoolean ; i++){

		tabMessage[i] = random.nextBoolean();

		}

		informationGeneree = new Information<Boolean>(tabMessage) ;
		
	}
	
}
