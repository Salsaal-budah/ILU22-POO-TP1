package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nbEtalMarche) {
		this.nom = nom;
		marche = new Marche(nbEtalMarche);
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	//les methodes de class Englobante
	//a
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder sortiConsole = new StringBuilder();
		int indiceEtal = marche.trouverEtalLibre();
		if(indiceEtal != -1) {
			marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
			sortiConsole.append(vendeur.getNom() + " cherche un endroit pour vendre "+ nbProduit+ " "+ produit + "Le vendeur" + vendeur.getNom()  + "vend des"+ produit +" à l'étal n°"+ indiceEtal);
			return sortiConsole.toString();
		}else {
			return "Aucun étal disponible pour installer le vendeur "+vendeur.getNom();
			}
	}
	//b
	 public String rechercherVendeursProduit(String produit) {
		 StringBuilder result = new StringBuilder();
		 
			 
			 Etal[] etalsAvecProd;
			 etalsAvecProd =marche.trouverEtals(produit);
			 if(etalsAvecProd.length==0) {
				 result.append("Il n'y a pas de vendeur qui propose des"+ produit+ " au marché.");
			 }else {
				 result.append(" Les vendeurs qui proposent des fleurs sont : \n");
				 for(int i=0;i< etalsAvecProd.length;i++) {
					result.append(etalsAvecProd[i].getVendeur()+"_ \n"); 
					 
				 }
			 }
			 
			 
			 
		 
			result.append("un erreur est produit \n");
		
		 return result.toString();
	 }
	
	//Class interne Marche
	private static class Marche{
		Etal[] etals ;
		
		private Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for(int i=0;i<etals.length;i++) {
				etals[i]= new Etal();
			}
		}
		//3
		void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		//4
		private int trouverEtalLibre() {
			for(int i =0 ;i< etals.length;i++) {
				if(!etals[i].isEtalOccupe())
					return i;
				}
			return -1;
			}
		
	// 5
	private Etal[] trouverEtals(String produit) {
		int nbEtalVendeur = 0;
		for(int i=0;i<etals.length ; i++) {
			if(etals[i].contientProduit(produit)){
				nbEtalVendeur ++;
			}
		}
		Etal[] etalsVendProduit = new Etal[nbEtalVendeur] ;
		for(int i=0;i< etals.length ; i++) {
			if(etals[i].contientProduit(produit)) {
				etalsVendProduit[i] = etals[i];
			}
		}
		return etalsVendProduit;
	}
	
	//6
	 private Etal trouverVendeur(Gaulois gaulois) {
		 
		 for(int i =0; i<etals.length;i++) {
			 if(etals[i]!= null && etals[i].getVendeur()==gaulois) {
				 return etals[i];
			 }
		 }
		 return null;
	 }
	 //7
	 
	private String afficherMarche(){
		int nbEtalVide =0;
		StringBuilder theString = new StringBuilder();
		for(int i=0;i<etals.length;i++) {
			if(!etals[i].isEtalOccupe()) {
				theString.append(etals[i].afficherEtal());
			}
			else {
				nbEtalVide ++;
			}
			
		}
		if(nbEtalVide > 0) {
			theString.append("Il reste" + nbEtalVide+ " étals non utilisés dans le marché.\n");
		}
		
		return  theString.toString();
	 }
	 
	}//Marche
	
} //Village