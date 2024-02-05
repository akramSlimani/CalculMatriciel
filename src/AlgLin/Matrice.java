package AlgLin;

import java.io.*;
import java.util.*;

public class Matrice {
	
    public final static double EPSILON = 0.000006;

	/** Définir ici les attributs de la classe **/
	protected double coefficient[][];

	/** Définir ici les constructeur de la classe **/
	Matrice(int nbligne, int nbcolonne) {
		this.coefficient = new double[nbligne][nbcolonne];
	}

	Matrice(double[][] tableau) {
		coefficient = tableau;
	}

	Matrice(String fichier) {
		try {
			Scanner sc = new Scanner(new File(fichier));
			int ligne = sc.nextInt();
			int colonne = sc.nextInt();
			this.coefficient = new double[ligne][colonne];
			for (int i = 0; i < ligne; i++)
				for (int j = 0; j < colonne; j++)
					this.coefficient[i][j] = sc.nextDouble();
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier absent");
		}
	}

	/** Definir ici les autres methodes */

	public void recopie(Matrice arecopier) {
		int ligne, colonne;
		ligne = arecopier.nbLigne();
		colonne = arecopier.nbColonne();
		this.coefficient = new double[ligne][colonne];
		for (int i = 0; i < ligne; i++)
			for (int j = 0; j < colonne; j++)
				this.coefficient[i][j] = arecopier.coefficient[i][j];
	}

	public int nbLigne() {
		return this.coefficient.length;
	}

	public int nbColonne() {
		return this.coefficient[0].length;
	}

	public double getCoef(int ligne, int colonne) {
		return this.coefficient[ligne][colonne];
	}

	public void remplacecoef(int ligne, int colonne, double value) {
		this.coefficient[ligne][colonne] = value;
	}

	public String toString() {
		int ligne = this.nbLigne();
		int colonne = this.nbColonne();
		String matr = "";
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (j == 0) {
					matr += this.getCoef(i, j);
				} else {
					matr += " " + this.getCoef(i, j);
				}
			}
			matr += "\n";
		}
		return matr;
	}

	public Matrice produit(double scalaire) {
		int ligne = this.nbLigne();
		int colonne = this.nbColonne();
		for (int i = 0; i < ligne; i++)
			for (int j = 0; j < colonne; j++)
				this.coefficient[i][j] *= scalaire;
		return this;
	}

	static Matrice addition(Matrice a, Matrice b) {
		int ligne = a.nbLigne();
		int colonne = a.nbColonne();
		Matrice mat = new Matrice(ligne, colonne);
		for (int i = 0; i < ligne; i++)
			for (int j = 0; j < colonne; j++)
				mat.coefficient[i][j] = a.coefficient[i][j] + b.coefficient[i][j];
		return mat;
	}

	static Matrice verif_addition(Matrice a, Matrice b) throws Exception {
		if ((a.nbLigne() == b.nbLigne()) && (a.nbColonne() == b.nbColonne())) {
			int ligne = a.nbLigne();
			int colonne = a.nbColonne();
			Matrice mat = new Matrice(ligne, colonne);
			for (int i = 0; i < ligne; i++)
				for (int j = 0; j < colonne; j++)
					mat.coefficient[i][j] = a.coefficient[i][j] + b.coefficient[i][j];
			return mat;
		} else {
			throw new Exception("Les deux matrices n'ont pas les mêmes dimensions !!!");
		}
	}

	static Matrice produit(Matrice a, Matrice b) {
		int ligne, colonne;
		ligne = a.nbLigne();
		colonne = b.nbColonne();
		Matrice mat = new Matrice(ligne, colonne);
		for (int i = 0; i < ligne; i++)
			for (int j = 0; j < colonne; j++) {
				mat.coefficient[i][j] = 0;
				for (int k = 0; k < a.nbColonne(); k++)
					mat.coefficient[i][j] += a.coefficient[i][k] * b.coefficient[k][j];
			}
		return mat;
	}

	static Matrice verif_produit(Matrice a, Matrice b) throws Exception {
		int ligne = 0;
		int colonne = 0;
		if (a.nbColonne() == b.nbLigne()) {
			ligne = a.nbLigne();
			colonne = b.nbColonne();
		} else {
			throw new Exception("Dimensions des matrices à multiplier incorrectes");
		}

		Matrice mat = new Matrice(ligne, colonne);
		for (int i = 0; i < ligne; i++)
			for (int j = 0; j < colonne; j++) {
				mat.coefficient[i][j] = 0;
				for (int k = 0; k < a.nbColonne(); k++)
					mat.coefficient[i][j] += a.coefficient[i][k] * b.coefficient[k][j];
			}
		return mat;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("**********************       EXEMPLE1       ********************************\n");
		double mat[][] = { { 2, 1 , 3}, { 0, 1 , 4}, { 5, 1 , 3} };
		Matrice a = new Matrice(mat);
		System.out.println("construction d'une matrice par affectation d'un tableau :\n" + a);
		
		System.out.println("**********************       EXEMPLE2       ********************************\n");
		Matrice b = new Matrice("C:\\Users\\akram\\eclipse-workspace\\Calcul_Matriciel\\src\\AlgLin\\matrice1.txt");
		System.out.println("Construction d'une matrice par lecture d'un fichier :\n" + b);
		
		System.out.println("**********************       EXEMPLE3       ********************************\n");
		Matrice c = new Matrice(2, 2);
		c.recopie(b);
		System.out.println("Recopie de la matrice b :\n" + c);
		
		System.out.println("**********************       EXEMPLE4       ********************************\n");
		System.out.println("Nombre de lignes et colonnes de la matrice c :\n " + c.nbLigne() + ", " + c.nbColonne());
		System.out.println("Coefficient (2,2) de la matrice b : \n" + b.getCoef(1, 1));
		System.out.println("Nouvelle valeur de ce coefficient : 8");
		b.remplacecoef(1, 1, 8);
		System.out.println("Vérification de la modification du coefficient");
		System.out.println("Coefficient (2,2) de la matrice b :\n " + b.getCoef(1, 1));
		System.out.println("Addition de 2 matrices : affichage des 2 matrices " + "puis de leur addition");
		System.out.println(" matrice 1 :\n" + a + "\n matrice 2 :\n" + b + "\n somme :\n" + Matrice.addition(a, b));
		
		System.out.println("**********************       EXEMPLE5       ********************************\n");
		System.out.println("Produit de 2 matrices : affichage des 2 matrices " + "puis de leur produit");
		System.out.println(" matrice 1 :\n" + a + "\n matrice 2 :\n" + b + "\n produit :\n" + produit(a, b));
	}
}
