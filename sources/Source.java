
   package sources;

   import information.*;
   import destinations.DestinationInterface;
   import java.util.*;

/** 
 * Classe Abstraite d'un composant source d'informations dont les �l�ments sont de type T 
 * @author prou
 */
   public  abstract class Source <T> implements  SourceInterface <T> {
   
   
   /** 
   * la liste des composants destination connect�s 
   */
      protected LinkedList <DestinationInterface <T>> destinationsConnectees;
   
   /** 
   * l'information g�n�r�e par la source 
   */
      protected Information <T>  informationGeneree;
   	
   /** 
   * l'information �mise par la source 
   */
      protected Information <T>  informationEmise;
   	
   
   /** 
   * un constructeur factorisant les initialisations communes aux r�alisations de la classe abstraite Source 
   */
      public Source () {
         destinationsConnectees = new LinkedList <DestinationInterface <T>> ();
         informationGeneree = null;
         informationEmise = null;
      }
   
    
   /**
    * retourne la derni�re information �mise par la source
    * @return une information   
    */
      public Information <T>  getInformationEmise() {
         return this.informationEmise;
      }
   
   
   /**
    * connecte une  destination � la source 
    * @param destination  la destination � connecter
    */
      public void connecter (DestinationInterface <T> destination) {
         destinationsConnectees.add(destination); 
      }
   
   
   /**
    * d�connecte une  destination de la source 
    * @param destination  la destination � d�connecter
    */
      public void deconnecter (DestinationInterface <T> destination) {
         destinationsConnectees.remove(destination); 
      }
   
   /**
    * �met l'information  g�n�r�e  
    */
      public void emettre() throws InformationNonConforme {
       	// �mission vers les composants connect�s  
         for (DestinationInterface <T> destinationConnectee : destinationsConnectees) {
            destinationConnectee.recevoir(informationGeneree);
         }
         this.informationEmise = informationGeneree;   			 			      
      
      }
     
   }