import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double mean, stddev, coLo95, coHi95;

    public PercolationStats(int n, int trials) { // perform trials independent
                                                 // experiments on an n-by-n
                                                 // grid
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        double[] stats = new double[trials];
        for (int trail = 0; trail < trials; trail++) {
            Percolation percolation = new Percolation(n);
            int iterations = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1, col = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    iterations++;
                }
            }
            stats[trail] = new Double(iterations) / new Double(n * n);
        }
        mean = StdStats.mean(stats);
        stddev = StdStats.stddev(stats);
        coLo95 = mean - 1.96*(stddev/Math.sqrt(new Double(trials)));
        coHi95 = mean + 1.96*(stddev/Math.sqrt(new Double(trials)));
    }

    public double mean() { // sample mean of percolation threshold
        return mean;
    }

    public double stddev() { // sample standard deviation of percolation
                             // threshold
        return stddev;
    }

    public double confidenceLo() { // low endpoint of 95% confidence interval
        return coLo95;
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        return coHi95;
    }

    public static void main(String[] args) { // test client (described below)
        int n = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.coLo95+", "+stats.confidenceHi());
    }
}
