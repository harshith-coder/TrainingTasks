import java.util.Scanner;
public class KthOneSegmentTree {

    static int[] tree;
    static int[] arr;
    static int n;

    public static void build(int idx, int s, int e) {
        if (s == e) {
            tree[idx] = arr[s];
            return;
        }

        int mid = (s + e) / 2;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        build(left, s, mid);
        build(right, mid + 1, e);
        tree[idx] = tree[left] + tree[right];
    }

    public static void update(int idx, int s, int e, int pos, int val) {
        if (s == e) {
            arr[pos] = val;
            tree[idx] = val;
            return;
        }

        int mid = (s + e) / 2;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        if (pos <= mid) update(left, s, mid, pos, val);
        else update(right, mid + 1, e, pos, val);
        
        tree[idx] = tree[left] + tree[right];
    }

    public static int kthOne(int idx, int s, int e, int k) {
        if (s > e) return -1; 
        if (s == e) return s;

        int mid = (s + e) / 2;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        int leftOnes = tree[left];

        if (k <= leftOnes) return kthOne(left, s, mid, k);
        else return kthOne(right, mid + 1, e, k - leftOnes);
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = scan.nextInt();
        
        tree = new int[4 * n];
        build(0, 0, n - 1);
        int q = scan.nextInt();
        while (q-- > 0) {
            int type = scan.nextInt();
            if (type == 1) {
                int index = scan.nextInt();
                int value = scan.nextInt();
                update(0, 0, n - 1, index, value);
            }else if (type == 2) {
                int k = scan.nextInt();
                if (tree[0] < k) System.out.println(-1);
                else System.out.println(kthOne(0, 0, n - 1, k));
                
            }
        }
    }
}