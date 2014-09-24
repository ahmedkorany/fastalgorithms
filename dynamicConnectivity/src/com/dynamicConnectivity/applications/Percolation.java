package com.dynamicConnectivity.applications;

/*****************************************************************************
 * Percolation class is used to simulate percolation model for experimental uses
 *  @author ahmedkorany <ahmedkorany@gmail.com>
 ****************************************************************************/
import com.dynamicConnectivity.algorithms.WeightedQuickUnion;

/**************************************************************************
 * Percolation class.
 *
 * @author ahmedkorany
 *
 */
public class Percolation {
    /**
     * wqu weighted Quick Union Object used to fix connectivity problem status.
     */
    private WeightedQuickUnion wqu = null;
    /**
     * array of booleans shows if site is open = true or blocked = false.
     */
    private boolean[] status = null;
    /**
     * nodesNumer integer number of elements in each row.
     */
    private int nodesNumer = 0;

    /**
     * Initialize Relocation Object.
     *
     * @param iN
     *            number of nodes in each row of percolation model.
     * @throws IllegalArgumentException
     *             if iN less than 1.
     **/
    public Percolation(final int iN) throws IllegalArgumentException {
        if (iN < 1) {
            throw new java.lang.IllegalArgumentException(
                    "Number of nodes should be greater than or equal 1");
        }
        // We add 2 nodes for virtual top and virtual nodes
        this.nodesNumer = iN;
        int nodesCount = nodesNumer * nodesNumer + 2;
        wqu = new WeightedQuickUnion();
        wqu.Init(nodesCount);
        status = new boolean[nodesCount];
        for (int i = 0; i < nodesCount; i++) {
            status[i] = false;
        }
    }

    /**
     * Function takes one bases 2 dimensional index and return 0 based one
     * dimensional index taking into account existence of 2 virtual nodes.
     * 
     * @param row
     *            number of required row.
     * @param col
     *            number of the required column.
     * @return the equivalent index for 2 d coord -1 for invalid data.
     */
    private int getIndex(final int row, final int col) {
        int index = (row - 1) * nodesNumer + col;
        if (index > 0 && index <= nodesNumer * nodesNumer) {
            return index;
        } else {
            return -1;
        }
    }

    /**
     * @param row
     *            number of required row.
     * @param col
     *            number of the required column.
     * @return true = open , false = blocked.
     */
    public final boolean isOpen(final int row, final int col)
            throws IndexOutOfBoundsException {
        int cell = getIndex(row, col);
        if (cell == -1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return status[cell];
    }

    /**
     * @param row
     *            number of required row.
     * @param col
     *            number of the required column.
     * @throws java.lang.IndexOutOfBoundsException
     *             if rows or col out of model.
     */
    public final void open(final int row, final int col)
            throws java.lang.IndexOutOfBoundsException {
        if (isOpen(row, col)) {
            return;
        }
        int cell = getIndex(row, col);
        if (cell == -1) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        status[cell] = true;
        if (getIndex(row, col - 1) > 0 && isOpen(row, col - 1)) {
            wqu.union(getIndex(row, col - 1), cell);
        }
        if (getIndex(row, col + 1) > 0 && isOpen(row, col + 1)) {
            wqu.union(getIndex(row, col + 1), cell);
        }
        if (getIndex(row - 1, col) > 0 && isOpen(row - 1, col)) {
            wqu.union(getIndex(row - 1, col), cell);
        }
        if (getIndex(row + 1, col) > 0 && isOpen(row + 1, col)) {
            wqu.union(getIndex(row + 1, col), cell);
        }

        if (row == 1) {
            wqu.union(0, cell);
        } else if (row == nodesNumer) {
            wqu.union(nodesNumer * nodesNumer + 1, cell);
        }
    }

    /**
     * node is full if it's connected to top virtual site.
     *
     * @param row
     *            number of required row.
     * @param col
     *            number of the required column.
     * @return true if site is full , false site is open
     */
    public final boolean isFull(final int row, final int col)
            throws IndexOutOfBoundsException {
        int cell = getIndex(row, col);
        if (cell == -1) {
            throw new IndexOutOfBoundsException();
        }
        return wqu.connected(cell, 0);
    }

    /**
     * System percolates if any cell in the bottom row is connected with to
     * virtual cell.
     *
     * @return true if system percolates else false
     */
    public final boolean percolates() {
        return wqu.connected(0, nodesNumer * nodesNumer + 1);
    }

    /**
     * @param args
     *            command line arguments.
     */
    public static void main(final String[] args) {
        Percolation per = new Percolation(9);
        per.open(3, 3);
        System.out.println("Opened 3,3");
        per.open(2, 3);
        System.out.println("Opened 2,3");
        System.out.println("isFull 3,3 =" + per.isFull(3, 3));
        per.open(1, 3);
        System.out.println("Opened 1,3");
        System.out.println("isFull 3,3 =" + per.isFull(3, 3));
        System.out.println("percolates returns " + per.percolates());
    }
}
