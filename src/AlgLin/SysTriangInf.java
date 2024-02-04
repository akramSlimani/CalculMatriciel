package AlgLin;

public class SysTriangInf extends SysLin {

	public SysTriangInf(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
	}
	
	
	@Override
	public Vecteur resolution() throws IrregularSysLinException {
		
		int taille = this.getMatriceSystem().nbLigne();
		Vecteur solution = new Vecteur(taille);
		
		for (int i = 0; i < solution.getTaille(); i++) {
           double s=0;
           
           for(int j = 0; j < i; j++) {
        	   s += this.getMatriceSystem().getCoef(i, j) * solution.getCoef(j);
           }
           
           solution.remplaceCoef(i, 0, (this.getSecondMembre().getCoef(i) - s)/this.getMatriceSystem().getCoef(i, i));

        }
		
        return solution;
	}
	
	public static void main(String[] args) throws IrregularSysLinException {
		double[][] tab1 = {{16.0,0.0,0.0},{3.0,4.0,0.0},{2.0,4.0,16}};
		Matrice matriceSys = new Matrice(tab1);
		System.out.println("la matrice A :\n" + matriceSys.toString());
		System.out.println("******************************************************\n");

		double[] tab2 = {4.0,16.0,9.0};
		Vecteur secondMembre = new Vecteur(tab2);
		System.out.println("le vecteur B :\n" + secondMembre.toString());
		System.out.println("******************************************************\n");

		SysTriangInf s = new SysTriangInf(matriceSys, secondMembre); 
		System.out.println("la solution de ce système (vecteur x) :\n" + s.resolution());	
	}

}
