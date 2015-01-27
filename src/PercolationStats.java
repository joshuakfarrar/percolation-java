public class PercolationStats {
    private final int T;
    private final int N;
    private double[] results;

    public PercolationStats(int N, int T) {
        if (N <= 0) throw new IllegalArgumentException();
        if (T <= 0) throw new IllegalArgumentException();

        this.N = N;
        this.T = T;

        results = new double[T];

        for (int i = 0; i < T; i++) {
            double result = runPercolationExperiment();
            results[i] = result;
        }
    }

    private double runPercolationExperiment() {
        Percolation p = new Percolation(N);
        double steps = 0;
        double openSites;

        while (!p.percolates()) {
            int i = StdRandom.uniform(1, N + 1);
            int j = StdRandom.uniform(1, N + 1);
            if (!p.isOpen(i, j)) {
                p.open(i, j);
                steps++;
            }
        }

        openSites = (steps / (N * N));
        return openSites;
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();

        double confidenceLo = mean - ((1.96 * stddev) / Math.sqrt(T));
        return confidenceLo;
    }

    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();

        double confidenceHi = mean + ((1.96 * stddev) / Math.sqrt(T));
        return confidenceHi;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats p = new PercolationStats(N, T);
        System.out.println("mean                    = " + p.mean());
        System.out.println("stddev                  = " + p.stddev());
        System.out.println("95% confidence interval = " + p.confidenceLo() + " " + p.confidenceHi());
    }
}