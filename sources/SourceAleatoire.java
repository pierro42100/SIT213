package sources;
import java.util.Random;

import information.Information;

/** 
 * Classe public d'un composant source d'informations aléatoires 
 * @author P. Chovelon, M. Corre
 */
public class SourceAleatoire extends Source <Boolean> {

	/**
	 * génËre un tableau tabMessage de booléens grâce à messageString et au seed
	 * 
	 * @param messageString	le message
	 * @param seed	le nombre qui génère pseudo-aléatoirement le message
	 */
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
