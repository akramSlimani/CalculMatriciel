package AlgLin;

public class SysTriangInf extends SysLin {

	public SysTriangInf(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
		if(!estTrianglInf(matriceSystem)) {
			throw new IrregularSysLinException("la matrice n'est pas triangulaire inf");
		}
	}
	
	public boolean estTrianglInf(Matrice matrice) {
		int ordre = getOrdre();
		for(int i = 0; i < ordre; i++) {
			for(int j = i+1; j < ordre; j++) {
			    if( getMatriceSystem().getCoef(i, j) != 0)  {	
			    		return false;
			}
		  }
		}
		return true;
	}
	
	@Override
	public Vecteur resolution() throws IrregularSysLinException {
		if (!estTrianglInf(matriceSystem)) {
            throw new IrregularSysLinException("Le système n'est pas triangulaire inf.");
        }
		int ordre = getOrdre();
		Vecteur solution = new Vecteur(ordre);
		for (int i = 0; i < ordre; i++) {
            double somme = 0;
            for (int j = 0; j < i; j++) {
                somme += getMatriceSystem().getCoef(i, j) * solution.getCoef(j);
            }
            double coefficientDiagonal = getMatriceSystem().getCoef(i, i);
            if (coefficientDiagonal == 0) {
                throw new IrregularSysLinException("Le système est singulier.");
            }
            solution.remplaceCoef(i, 0, somme / coefficientDiagonal);
        }

        return solution;
		
	}

}
