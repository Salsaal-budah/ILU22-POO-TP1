package villagegaulois;

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
	
	//Class interne
	public static class Marche{
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
		int trouverEtalLibre() {
			for(int i =0 ;i< etals.length;i++) {
				if(!etals[i].isEtalOccupe())
					return i;
				}
			return -1;
			}
		
	// 5
	Etal[] trouverEtals(String produit) {
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
	 Etal trouverVendeur(Gaulois gaulois) {
		 
		 for(int i =0; i<etals.length;i++) {
			 if(etals[i]!= null && etals[i].getVendeur()==gaulois) {
				 return etals[i];
			 }
		 }
		 return null;
	 }
	public String afficherMarche(){
		
		
		return "";
	 }
	 
	}//Marche
}