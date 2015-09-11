package sources;

import information.*;

public class SourceFixe extends Source <Boolean> {

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
