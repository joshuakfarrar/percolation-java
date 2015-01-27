public class TestPercolation {
    private final int T;
    private final int N;
    private double[] results;

    public TestPercolation(int N, int T) {
        if (N <= 0) throw new IllegalArgumentException();
        if (T <= 0) throw new IllegalArgumentException();

        this.N = N;
        this.T = T;

        results = new double[T];

        for (int i = 0; i < T; i++) {
            runPercolationExperiment();
        }
    }

    private void runPercolationExperiment() {
        Percolation p = new Percolation(N);
        double steps = 0;
        double openSites;

        p.open(1, 6);
        System.out.println("derp: " + p.isFull(1, 6));
        p.open(5, 1);
        System.out.println("derp: " + p.isFull(5, 1));
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

        TestPercolation p = new TestPercolation(N, T);
    }
}