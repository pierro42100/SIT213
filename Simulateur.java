
import sources.*;
import destinations.*;
import transmetteurs.*;

import information.*;

import visualisations.*;

import java.util.regex.*;
import java.util.*;
import java.lang.Math;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/** La classe Simulateur permet de construire et simuler une chaine de transmission composee d'une Source, d'un nombre variable de Transmetteur(s) et d'une Destination.  
 * @author corre melanie
 * @author chovelon pierrick
 *
 */
public class Simulateur {

	/** indique si le Simulateur utilise des sondes d'affichage */
	private          boolean affichage = false;
	/** indique si le Simulateur utilise un message genere de maniere aleatoire */
	private          boolean messageAleatoire = true;
	/** indique si le Simulateur utilise un germe pour initialiser les generateurs aleatoires */
	private          boolean aleatoireAvecGerme = false;
	/** la valeur de la semence utilisee pour les generateurs aleatoires */
	private          Integer seed = null;
	/** la longueur du message aleatoire à transmettre si un message n'est pas impose */
	private          int nbBitsMess = 100; 
	/** la chaine de caracteres correspondant à m dans l'argument -mess m */
	private          String messageString = "100";



	/** le  composant Source de la chaine de transmission */
	private			  Source <Boolean>  source = null;
	/** le  composant Transmetteur parfait logique de la chaine de transmission */
	private			  Transmetteur <Boolean, Boolean>  transmetteurLogique = null;
	/** le  composant Destination de la chaine de transmission */
	private			  Destination <Boolean>  destination = null;
	/** le  composant Sonde1 de la chaine de transmission */
	private			  Sonde <Boolean>  sonde1 = null;
	/** le  composant Sonde2 de la chaine de transmission */
	private			  Sonde <Boolean>  sonde2 = null;


	/** Le constructeur de Simulateur construit une chaine de transmission composee d'une Source <Boolean>, d'une Destination <Boolean> et de Transmetteur(s) [voir la methode analyseArguments]...  
	 * <br> Les differents composants de la chaene de transmission (Source, Transmetteur(s), Destination, Sonde(s) de visualisation) sont crees et connectes.
	 * @param args le tableau des differents arguments.
	 *
	 * @throws ArgumentsException si un des arguments est incorrect
	 *
	 */   
	public  Simulateur(String [] args) throws ArgumentsException {

		// analyser et recuperer les arguments

		analyseArguments(args);

		////////////////////////////////////////////////////////////////////////////////////////////
		//Instanciation de tous les objets utiles à la simulation de la chaine de transmission//
		////////////////////////////////////////////////////////////////////////////////////////////

		//Source
		if (seed == null)//donnees fixes
		{
			source = new SourceFixe(messageString);
		}
		else//donnees aleatoires
		{
			source = new SourceAleatoire(messageString, seed);
		}

		//Transmetteur
		transmetteurLogique = new TransmetteurParfait();

		//Destination
		destination = new DestinationFinale();

		//Sondes logiques
		sonde1 = new SondeLogique("Sonde 1 : Source", nbBitsMess);
		sonde2 = new SondeLogique("Sonde 2 : Source", nbBitsMess);

		///////////////////////////////////////////////////////////////////
		//Connexions des differents elements de la chaine de transmission//
		///////////////////////////////////////////////////////////////////

		//Source --> Transmetteur 
		source.connecter(transmetteurLogique);

		//Source --> Sondes
		source.connecter(sonde1);

		//Transmetteur --> Destination
		transmetteurLogique.connecter(destination);
		
		//Source --> Sondes
		transmetteurLogique.connecter(sonde2);


	}


	/** La methode analyseArguments extrait d'un tableau de chaines de caracteres les differentes options de la simulation. 
	 * Elle met à jour les attributs du Simulateur.
	 *
	 * @param args le tableau des differents arguments.
	 * <br>
	 * <br>Les arguments autorises sont : 
	 * <br> 
	 * <dl>
	 * <dt> -mess m  </dt><dd> m (String) constitue de 7 ou plus digits 0 | 1, le message à transmettre</dd>
	 * <dt> -mess m  </dt><dd> m (int) constitue de 1 à 6 digits, le nombre de bits du message "aleatoire" à transmettre</dd> 
	 * <dt> -s </dt><dd> utilisation des sondes d'affichage</dd>
	 * <dt> -seed v </dt><dd> v (int) d'initialisation pour les generateurs aleatoires</dd> 
	 * <br>
	 * <dt> -form f </dt><dd>  codage (String) RZ, NRZR, NRZT, la forme d'onde du signal à transmettre (RZ par défaut)</dd>
	 * <dt> -nbEch ne </dt><dd> ne (int) le nombre d'echantillons par bit (ne >= 6 pour du RZ, ne >= 9 pour du NRZT, ne >= 18 pour du RZ,  30 par defaut))</dd>
	 * <dt> -ampl min max </dt><dd>  min (float) et max (float), les amplitudes min et max du signal analogique e transmettre ( min < max, 0.0 et 1.0 par defaut))</dd> 
	 * <br>
	 * <dt> -snr s </dt><dd> s (float) le rapport signal/bruit en dB</dd>
	 * <br>
	 * <dt> -ti i dt ar </dt><dd> i (int) numero du trajet indirect (de 1 e 5), dt (int) valeur du decalage temporel du ieme trajet indirect 
	 * en nombre d'echantillons par bit, ar (float) amplitude relative au signal initial du signal ayant effectue le ieme trajet indirect</dd>
	 * <br>
	 * <dt> -transducteur </dt><dd> utilisation de transducteur</dd>
	 * <br>
	 * <dt> -aveugle </dt><dd> les recepteurs ne connaissent ni l'amplitude min et max du signal, ni les differents trajets indirects (s'il y en a).</dd>
	 * <br>
	 * </dl>
	 * <br> <b>Contraintes</b> :
	 * Il y a des interdependances sur les parametres effectifs. 
	 *
	 * @throws ArgumentsException si un des arguments est incorrect.
	 *
	 */   
	public  void analyseArguments(String[] args)  throws  ArgumentsException {

		for (int i=0;i<args.length;i++){ 


			if (args[i].matches("-s")){
				affichage = true;
			}
			else if (args[i].matches("-seed")) {
				aleatoireAvecGerme = true;
				i++; 
				// traiter la valeur associee
				try { 
					seed =new Integer(args[i]);
				}
				catch (Exception e) {
					throw new ArgumentsException("Valeur du parametre -seed  invalide :" + args[i]);
				}           		
			}

			else if (args[i].matches("-mess")){
				i++; 
				// traiter la valeur associee
				messageString = args[i];
				if (args[i].matches("[0,1]{7,}")) {
					messageAleatoire = false;
					nbBitsMess = args[i].length();
				} 
				else if (args[i].matches("[0-9]{1,6}")) {
					messageAleatoire = true;
					nbBitsMess = new Integer(args[i]);
					if (nbBitsMess < 1) 
						throw new ArgumentsException ("Valeur du parametre -mess invalide : " + nbBitsMess);
				}
				else 
					throw new ArgumentsException("Valeur du parametre -mess invalide : " + args[i]);
			}

			else throw new ArgumentsException("Option invalide :"+ args[i]);
		}

	}



	/** La methode execute effectue un envoi de message par la source de la chaene de transmission du Simulateur. 
	 * @return les options explicites de simulation.
	 *
	 * @throws Exception si un probleme survient lors de l'execution
	 *
	 */ 
	public void execute() throws Exception {      

		source.emettre(); 

	}



	/** La methode qui calcule le taux d'erreur binaire en comparant les bits du message emis avec ceux du message reeu.
	 *
	 * @return  La valeur du Taux dErreur Binaire.
	 */   	   
	public float  calculTauxErreurBinaire() {
		
		//Recuperation de l'information emise par la source
		Information infoEmiseSource = source.getInformationEmise();	
		
		//Recuperation de l'information reeue par la destination
		Information infoRecueDestination = destination.getInformationRecue();
		
		int nbBits = infoEmiseSource.nbElements();
		int nbBitsErrones = 0;
		
		for (int i = 0; i < nbBits; i++)
		{
			if(infoEmiseSource.iemeElement(i) != infoRecueDestination.iemeElement(i))
			{
				nbBitsErrones++;
			}			
			
		}
		
		return  nbBitsErrones/nbBits;
	}




	/** La fonction main instancie un Simulateur e l'aide des arguments parametres et affiche le resultat de l'execution d'une transmission.
	 *  @param args les differents arguments qui serviront e l'instanciation du Simulateur.
	 */
	public static void main(String [] args) { 

		Simulateur simulateur = null;

		try {
			simulateur = new Simulateur(args);

		}
		catch (Exception e) {
			System.out.println(e); 
			System.exit(-1);
		} 

		try {

			simulateur.execute();
			float tauxErreurBinaire = simulateur.calculTauxErreurBinaire();
			String s = "java  Simulateur  ";
			for (int i = 0; i < args.length; i++) {
				s += args[i] + "  ";
			}
			System.out.println(s + "  =>   TEB : " + tauxErreurBinaire);
		}
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.exit(-2);
		}              	
	}

}

