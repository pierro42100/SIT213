   package visualisations;
	
	
   import information.Information;


/** 
 * Classe r�alisant l'affichage d'information compos�e d'�l�ments bool�ens
 * @author prou
 */
   public class SondeLogique extends Sonde <Boolean> {
   
   /** le nombre de pixels en largeur pour un �l�ment d'information Boolean � afficher dans la fen�tre */
      private int nbPixels;
   
   /**
    * pour construire une sonde logique  
    * @param nom  le nom de la fen�tre d'affichage
    * @param nbPixels  le nombre pixels en largeur pour un �l�ment d'information Boolean � afficher dans la fen�tre
    */
      public SondeLogique(String nom, int nbPixels) {
         super(nom);
         this.nbPixels = nbPixels;
      }
   
   	 
      public void recevoir (Information <Boolean> information) { 
         informationRecue = information;
         int nbElements = information.nbElements();
         boolean [] table = new boolean[nbElements];
         int i = 0;
         for (boolean b : information) {
            table[i] = b;
            i++;
         }
      
         new VueCourbe (table,  nbPixels, nom); 
      }
   	 
   	
   
   
   }