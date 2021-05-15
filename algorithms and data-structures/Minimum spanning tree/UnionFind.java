public class UnionFind {
    private int[] data;

    /**
     * Create a UnionFind data structure for a specialized use case
     *
     * @param n
     */
    public UnionFind(int n) {
        this.data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;
        }
    }

    /**
     * Unifies i and j
     *
     * @param i
     * @param j
     */
    public void union(int i, int j) {
        this.data[i] = j;
    }

    /**
     * Finds the root of node i
     *
     * @param i
     * @return Root node of i
     */
    public int find(int i) {
        if (i == this.data[i]) {
            return i;
        } else {
            int j = find(this.data[i]);
            this.data[i] = j;
            return j;
        }
    }

    /**
     * Vertices are unified if the find operation return the same result.
     *
     * @return true if unified, false else
     */
    public boolean isUnified(int i, int j) {
        return this.find(i) == this.find(j);
    }

    /**
     * The graph is unified if all vertices are unified.
     *
     * @return true if all vertices are unified, false else
     */
    public boolean isAllUnified() {
        for (int i = 0; i < this.data.length; i++) {
            for (int j = i; j < this.data.length; j++) {
                if (!isUnified(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
