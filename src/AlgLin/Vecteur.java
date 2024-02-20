package AlgLin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

	public void remplaceCoef(int ligne, int colonne, double valeur) {
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

	/* sans vérifier la taille */
	public static double produitNaif(Vecteur v1, Vecteur v2) {
		int res = 0;
		for (int i = 0; i < v1.getTaille(); i++) {
			res += v1.getCoef(i) * v2.getCoef(i);
		}
		return res;
	}

	/* vérifier d'abord si les deux vecteurs ont la meme taille */
	public static double produit(Vecteur v1, Vecteur v2) throws IOException {

		int result = 0;

		if (v1.getTaille() != v2.getTaille())
			throw new IOException();

		for (int i = 0; i < v1.getTaille(); ++i) {

			result += v1.getCoef(i) * v2.getCoef(i);
		}

		return result;
	}

	public static Vecteur soustraction(Vecteur v1, Vecteur v2) {

		assert v1.getTaille() == v2.getTaille();

		Vecteur result = new Vecteur((int) v1.getTaille());

		for (int i = 0; i < v1.getTaille(); ++i) {

			result.remplaceCoef(i, 0, v1.getCoef(i) - v2.getCoef(i));
		}

		return result;
	}

	/*norme L1 = somme des valeurs absolues des coefs*/
	public double normeL1() {

		double norme = 0;

		for (int i = 0; i < getTaille(); ++i) {

			norme += Math.abs(getCoef(i));
		}

		return norme;
	}

	/*norme L2 = la racine carrée de la somme des carrés des coefs*/
	public double normeL2() {

		double norme = 0;

		for (int i = 0; i < getTaille(); ++i) {

			norme += Math.pow(getCoef(i), 2);
		}

		return Math.sqrt(norme);
	}

	/*à l'infinie = valeur absolue du plus grand coef*/
	public double normeLInfini() {

		double max = getCoef(0);

		for (int i = 0; i < getTaille(); ++i) {

			if (max < Math.abs(getCoef(i))) {

				max = Math.abs(getCoef(i));
			}
		}

		return max;
	}

	public static void main(String[] args) throws Exception {
		int taille = 2;
		Vecteur v1 = new Vecteur(taille);
		System.out.println("construction d'un vecteur par affectation d'une taille :\n" + v1);
		System.out.println("******************************************************\n");

		double[] tab = { 20, 1.12, 5.6 };
		Vecteur v2 = new Vecteur(tab);
		System.out.println("construction d'un vecteur à partir d'un tableau :\n" + v2);
		System.out.println("******************************************************\n");

		Vecteur v3 = new Vecteur("C:\\Users\\akram\\eclipse-workspace\\Calcul_Matriciel\\src\\AlgLin\\vecteur1.txt");
		System.out.println("construction d'un vecteur à partir d'un fichier texte :\n" + v3);
		System.out.println("******************************************************\n");

		v2.remplaceCoef(0, 0, 8);
		v2.remplaceCoef(1, 0, 3);
		System.out.println("nouvelle valeur de v2 :\n" + v2);
		System.out.println("******************************************************\n");

		double[] t = { 1, 2, 3 };
		Vecteur v4 = new Vecteur(t);
		System.out.println("valeur de v4 :\n" + v4);
		v3.recopie(v2);
		System.out.println("nouvelle valeur de v4 après recopiage de v2:\n" + v4);
		System.out.println("******************************************************\n");

		v3.remplaceCoef(0, 0, 99.99);
		System.out.println("modification du coefficient 1 de v3 :\n" + v3);
		System.out.println("******************************************************\n");

		double p = produit(v2, v3);
		System.out.println("produit scalaire du vecteur v2*v3 : " + p);

		double normeL1 = v2.normeL1();
		System.out.println("norme L1 du vecteur v2 : " + normeL1);

		double normeL2 = v2.normeL2();
		System.out.println("norme L2 du vecteur v2 : " + normeL2);

		double normeLInfini = v2.normeLInfini();
		System.out.println("norme Linfini du vecteur v2 : " + normeLInfini);

	}
}
