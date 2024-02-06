package AlgLin;

public class Mat3Diag extends Matrice {

	public Mat3Diag(int dim1, int dim2) throws Exception {
		super(dim1, dim2);
		if (dim1 != 3) {
			throw new Exception("La première dimension doit être égale à 3 !");
		}
	}

	public Mat3Diag(double tableau[][]) throws Exception {
		super(tableau);
		if (tableau.length != 3) {
			throw new Exception("Le tableau doit etre de 3 lignes !");
		}
	}

	public Mat3Diag(int dim) {
		super(3, dim);
	}

	public static Vecteur multVect(Mat3Diag matrice, Vecteur vecteur) throws IrregularSysLinException {
	    int dim = matrice.nbColonne();
	    if (dim != vecteur.getTaille()) {
	        throw new IrregularSysLinException("Les dimensions de la matrice et du vecteur ne sont pas compatibles.");
	    }

	    Vecteur result = new Vecteur(dim);
	    double somme;

	    for (int i = 0; i < dim; ++i) {
	        somme = 0;
	        if (i > 0) {
	            somme += vecteur.getCoef(i - 1) * matrice.getCoef(0, i);
	        }
	        somme += vecteur.getCoef(i) * matrice.getCoef(1, i);
	        if (i < dim - 1) {
	            somme += vecteur.getCoef(i + 1) * matrice.getCoef(2, i);
	        }
	        result.remplaceCoef(i, 0, somme);
	    }

	    return result;
	}

	public static void main(String[] args) throws Exception {
		
		 try {
			    System.out.println("**********************       EXEMPLE1 : premier constructeur      ********************************\n");
	            Mat3Diag mat1 = new Mat3Diag(3, 4);
	            System.out.println(mat1);

			    System.out.println("**********************       EXEMPLE1 : deuxième constructeur      ********************************\n");
	            double[][] tab1 = {  {2.0, 1.0, 0.0, 0.0}, {4.0, 4.0, 2.0, 0.0}, {0.0, 1.0, 1.0, 3.0} };
	            Mat3Diag mat2 = new Mat3Diag(tab1);
	            System.out.println(mat2);

			    System.out.println("**********************       EXEMPLE1 : troisième constructeur      ********************************\n");
	            Mat3Diag mat3 = new Mat3Diag(4);
	            System.out.println(mat3);

			    System.out.println("**********************       EXEMPLE1 : Test de la méthode multVect      ********************************\n");
	            double[] vecteurArray = {1.0, 2.0, 3.0, 4.0};
	            Vecteur vecteur = new Vecteur(vecteurArray);
	            System.out.println("Matrice :\n" + mat2);
	            
	            System.out.println("Vecteur :\n"+ vecteur);
	            
	            Vecteur resultat = Mat3Diag.multVect(mat2, vecteur);
	            System.out.println("Résultat du produit (matrice * vecteur):\n" + resultat);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}

}
