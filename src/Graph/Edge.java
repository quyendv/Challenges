package Graph;

// Edge for EdgeWeightedGraph
public class Edge implements Comparable<Edge> {
    private final int v, w;
    private final double weight;

    public Edge(int v, int w, double weight) { // create a weighted edge v-w
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {                   // lấy đỉnh bất kì. (return v vì chưa phân biệt được v hay w truyền vào trước)
        return v;
    }

    public int other(int vertex) {          // lấy đỉnh còn lại, khác đỉnh truyền vào.
        return vertex == v ? w : v;
    }

    public double weight() {
        return weight;
    }

    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(weight, that.weight);
    }
    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67);
        System.out.println(e);
    }
}
