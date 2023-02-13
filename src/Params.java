/*****************/
public class Params
{
    public int clmn , row , sheep , wolf;
    /*****************/
    public Params(int pClmn, int pRow, int pSheep, int pWolf)
    {
        clmn = pClmn;
        row = pRow;
        sheep = pSheep;
        wolf = pWolf;
    }
    /*****************/
    @Override public boolean equals(Object pO)
    {
        if(pO instanceof Params pP) {
            return (pP.clmn*pP.row == clmn*row && pP.sheep == sheep && pP.wolf == wolf);
        }
        return false;
    }
}

