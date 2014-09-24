package com.dynamicConnectivity.applications;

import java.util.Random;

/*****************************************************************************
 * PercolationStats class is used to make a mont carlo simulation over
 * percolation model for experimental uses.
 *
 * @author ahmedkorany <ahmedkorany@gmail.com>
 ****************************************************************************/
public class PercolationStats {
    /**
     * variable x to hold x coordinates.
     */
    private double[] x;
    /**
     * variable T to hold T variable entered as command line parameters to the
     * main method.
     */
    private int tValue = 0;
    /**
     * variable per to hold percolation object.
     */
    private static Percolation per;
    /**
     * static coefficient variable for HI and LOW confidence calculations.
     */
    private static final double CONFIDENCE_COEF = 1.96;

    /**
     * @param iN
     *            number of nodes in percolation model row.
     * @param iT
     *            number of experiments.
     */
    public PercolationStats(final int iN, final int iT) {
        if (iN <= 0 || iT <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        x = new double[iT];
        this.tValue = iT;
        for (int i = 0; i < tValue; i++) {
            Percolation pre = new Percolation(iN);
            int opened = 0;
            Random randogen = new Random();
            while (!pre.percolates()) {
                int x1 = 1 + randogen.nextInt(iN);
                int y1 = 1 + randogen.nextInt(iN);
                if (!pre.isOpen(x1, y1)) {
                    pre.open(x1, y1);
                    opened++;
                }
            }
            x[i] = (double) opened / (double) (iN * iN);
        }
    }

    /**
     * @return mean of the percolate threshold probabilities.
     */
    public final double mean() {
        double sum = 0;
        for (int i = 0; i < tValue; i++) {
            sum += x[i];
        }
        double mean = sum / tValue;
        return mean;
    }

    /**
     * @return standard deviation of the percolate threshold probabilities.
     */
    public final double stddev() {
        double mean = mean();
        double sum = 0.0;
        for (int i = 0; i < tValue; i++) {
            sum += Math.pow(x[i] - mean, 2.0);
        }
        sum /= (double) (tValue - 1);
        return sum;
    }

    /**
     * @return confidenceLo of the percolate threshold probabilities
     */
    public final double confidenceLo() {
        double mean = mean();
        double stddev1 = this.stddev();
        double confLo = mean
                - ((CONFIDENCE_COEF * stddev1) / Math.sqrt(tValue));
        return confLo;
    }

    /**
     * @return confidenceHi of the percolate threshold probabilities
     */
    public final double confidenceHi() {
        double mean = mean();
        double stddev = this.stddev();
        double confHi = mean + ((CONFIDENCE_COEF * stddev)
                / Math.sqrt(tValue));
        return confHi;
    }

    /**
     * @param args
     *            command line argument to exe.
     */
    public static void main(final String[] args) {
        PercolationStats stat = new PercolationStats(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));
        System.out.printf("mean=%f\n", stat.mean());
        System.out.printf("stddev=%f\n", stat.stddev());
        System.out.printf("95%% confidence interval=%f,%f",
                stat.confidenceLo(), stat.confidenceHi());
    }
}
