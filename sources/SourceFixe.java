package sources;

import information.*;

/** 
 * Classe public d'un composant source d'informations fixes 
 * @author P. Chovelon, M. Corre
 */
public class SourceFixe extends Source <Boolean> {
	
	/**
	 * transforme le message messageString en tableau de booléens
	 * 
	 * @param messageString le message
	 */

	public SourceFixe (String messageString){
		super();	
		
		Boolean tabMessage [] = new Boolean [messageString.length()];
		int i;
		
		for(i = 0 ; i < messageString.length() ; i++){
		
			if(messageString.charAt(i) == '1')
			{
				tabMessage[i] = true;
			}
			if(messageString.charAt(i) == '0')
			{
				tabMessage[i] = false;
			}

		}

		informationGeneree= new Information<Boolean>(tabMessage) ;
		
	}
	
}
