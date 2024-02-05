package AlgLin;

public class SysTriangSup extends SysLin {

	public SysTriangSup(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException  {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {
		
		int taille = this.getMatriceSystem().nbLigne();
		Vecteur solution = new Vecteur(taille);
		
		 for(int i = 0; i < this.getMatriceSystem().nbLigne(); i++) {
		      
		      for(int j = 0; j < this.getMatriceSystem().nbColonne(); j++) {
		        
		        if(i > j && this.getMatriceSystem().getCoef(i, j) != 0) {
		          
		          throw new IrregularSysLinException("La matrice n'est pas triangulaire supérieure !");
		        }
		      }
		    }
		 
		for (int i = solution.getTaille() - 1; i >= 0; i--) {
           double s=0;
           
           for(int j = i + 1; j < taille; j++) {
        	   s += this.getMatriceSystem().getCoef(i, j) * solution.getCoef(j);
           }
           solution.remplaceCoef(i, 0, (this.getSecondMembre().getCoef(i) - s) / this.getMatriceSystem().getCoef(i, i));

        }
		
        return solution;
	}
	
	
	public static void main(String[] args) throws IrregularSysLinException {
		double[][] tab1 = {{1.0, 1.0, -2.0},{0.0, 1.0 , -1.5},{0.0, 0.0, 1.0}};
		Matrice matriceSys = new Matrice(tab1);
		System.out.println("la matrice A :\n" + matriceSys.toString());
		System.out.println("******************************************************\n");

		double[] tab2 = {3.0, 7.0/6.0, 11.0/9.0};
		Vecteur secondMembre = new Vecteur(tab2);
		System.out.println("le vecteur B :\n" + secondMembre.toString());
		System.out.println("******************************************************\n");

		SysTriangSup s = new SysTriangSup(matriceSys, secondMembre); 
		System.out.println("la solution de ce système (vecteur x) :\n" + s.resolution());	
	}
}
