public class TestPercolation {
    private final int T;
    private final int N;

    public TestPercolation(int N, int T) {
        if (N <= 0) throw new IllegalArgumentException();
        if (T <= 0) throw new IllegalArgumentException();

        this.N = N;
        this.T = T;

        for (int i = 0; i < T; i++) {
            runPercolationExperiment();
        }
    }

    private void runPercolationExperiment() {
        Percolation p = new Percolation(N);

        p.open(1, 6);
        System.out.println("isFull: " + p.isFull(1, 6));
        p.open(5, 1);
        System.out.println("isFull: " + p.isFull(5, 1));
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        TestPercolation p = new TestPercolation(N, T);
    }
}