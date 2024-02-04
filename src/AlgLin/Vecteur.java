package AlgLin;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles.Lookup.ClassOption;
import java.util.Scanner;

public class Vecteur extends Matrice {


	public Vecteur(int taille) {
		super(taille, 1);
	}

	public Vecteur(double[] tab) {
		super(tab.length, 1);
		for (int i = 0; i < tab.length; i++) {
			this.coefficient[i][0] = tab[i];
		}
	}

	public Vecteur(String fichier) {
		super(fichier);
	}

	public int getTaille() {
		return nbLigne();
	}

	public double getCoef(int x) {
		return getCoef(x, 0);
	}

	public void remplaceCoef(int ligne,int colonne, double valeur) {
		remplacecoef(ligne, 0, valeur);
	}

	public String toString() {
		int ligne = this.nbLigne();
		String vect = "";
		for (int i = 0; i < ligne; i++) {
			vect += this.getCoef(i, 0);
			vect += "\n";
		}
		return vect;
	}
	
	public static double produitNaif(Vecteur v1, Vecteur v2) {
		int res = 0;
		for(int i = 0; i < v1.getTaille(); i++) {
			res += v1.getCoef(i) * v2.getCoef(i);
		}
		return res;
	}
	
	public static int produit(Vecteur v1, Vecteur v2) throws Exception{
		int res = 0;
		if(v1.getTaille() == v2.getTaille()) {
			for(int i = 0; i < v1.getTaille(); i++) {
				res += v1.getCoef(i) * v2.getCoef(i);
			}
			return res;
		}else {
			throw new Exception("v1 et v2 n'ont pas la meme taille");
		}
	}
	
	public static void main(String[] args) throws Exception{
		int taille = 2;
		Vecteur v1 = new Vecteur(taille);
		System.out.println("construction d'un vecteur par affectation d'une taille :\n"+v1);
		System.out.println("******************************************************\n");
		
		double[] tab = {20, 1.12, 5.6};
		Vecteur v2 = new Vecteur(tab);
		System.out.println("construction d'un vecteur à partir d'un tableau :\n"+v2);
		System.out.println("******************************************************\n");
		
		Vecteur v3 = new Vecteur("C:\\Users\\akram\\eclipse-workspace\\Calcul_Matriciel\\src\\AlgLin\\vecteur1.txt");
		System.out.println("construction d'un vecteur à partir d'un fichier texte :\n"+v3);
		System.out.println("******************************************************\n");
		 
		v2.remplaceCoef(0, 0, 8);
	    v2.remplaceCoef(1, 0, 3);
	    System.out.println("nouvelle valeur de v2 :\n" +v2);
		System.out.println("******************************************************\n");

		double[] t = {1, 2, 3};
		Vecteur v4 = new Vecteur(t);
		System.out.println("valeur de v4 :\n" +v4);
	    v3.recopie(v2);
	    System.out.println("nouvelle valeur de v4 après recopiage de v2:\n" +v4);
		System.out.println("******************************************************\n");

		v3.remplaceCoef(0, 0, 99.99);
	    System.out.println("modification du coefficient 1 de v3 :\n" +v3);
		System.out.println("******************************************************\n");
	    
	    double p = produit(v2, v3);
	    System.out.println("produit scalaire du vecteur v2*v3 : " + p);
		
	}
}