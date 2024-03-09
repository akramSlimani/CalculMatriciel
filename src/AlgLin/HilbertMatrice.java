package AlgLin;

public class HilbertMatrice extends Matrice {
    public HilbertMatrice(int ordre) {
        super(ordre, ordre);
        for (int i = 0; i < nbLigne(); i++) {
            for (int j = 0; j < nbLigne(); j++) {
                remplacecoef(i, j, (double) 1 / (double) (i + j + 1));

            }
        }
    }

    public static void main(String[] args) throws Exception {
        int ordre = 3;
        HilbertMatrice hilbert = new HilbertMatrice(ordre);
        System.out.println(hilbert);
        Matrice inverse = hilbert.inverse();
        System.out.println(inverse);

        Matrice produit = Matrice.produit(hilbert, inverse);
        System.out.println(produit);

        System.out.println("Cond(Id) = ||id|| = " + hilbert.identite().cond_1());

        System.out.println("Cond1(A) = ||A * A^-1||1 " + produit.norme_1());

        System.out.println("CondINF(A) = ||A * A^-1||inf  = " + produit.norme_inf());
        System.out.println(
                "Résultat :  Le conditionnement de cette matrice est relativement trés faible et indique qu'elle est trés bien conditionnée. Les solutions aux systèmes linéaires Ax = b associés à cette matrice sont stables et précises, quelles que soient les valeurs de b.");

        for (int i = 3; i <= 15; i++) {
            
            hilbert = new HilbertMatrice(i);
            inverse = hilbert.inverse();
            produit = Matrice.produit(hilbert, inverse);
            System.out.println("Ordre = "+i);
            System.out.println("Cond1(A) = ||A * A^-1||1 " + produit.norme_1());
            System.out.println("CondINF(A) = ||A * A^-1||inf  = " + produit.norme_inf());
            System.out.println("******");
        }

    }

}
