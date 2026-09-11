import java.util.*;

public class orSegmentTree {
    static int[] tree;
    static int[] arr;
    static int n;

    public static void build(int idx, int s, int e) {
        if (s == e) {
            tree[idx] = arr[s];
            return;
        }

        int mid = (s + e) / 2;
        int l = 2 * idx + 1;
        int r = 2 * idx + 2;

        build(l, s, mid);
        build(r, mid + 1, e);

        tree[idx] = tree[l] | tree[r];
    }

    public static void update(int idx, int s, int e, int pos, int val) {
        if (s == e) {
            arr[pos] = val;
            tree[idx] = val;
            return;
        }

        int mid = (s + e) / 2;
        int l = 2 * idx + 1;
        int r = 2 * idx + 2;

        if (pos <= mid) {
            update(l, s, mid, pos, val);
        } else {
            update(r, mid + 1, e, pos, val);
        }

        tree[idx] = tree[l] | tree[r];
    }

    public static int query(int idx, int s, int e, int ql, int qr) {
        if (e < ql || s > qr) {
            return 0;
        }

        if (s >= ql && e <= qr) {
            return tree[idx];
        }

        int mid = (s + e) / 2;

        int left = query(2 * idx + 1, s, mid, ql, qr);
        int right = query(2 * idx + 2, mid + 1, e, ql, qr);

        return left | right;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i]=sc.nextInt();
        }

        tree = new int[4 * n];
        build(0, 0, n - 1);
        int q = sc.nextInt();
        while (q-->0) {
            int type = sc.nextInt();
            if (type == 1) {
                int pos = sc.nextInt();
                int val = sc.nextInt();
                update(0, 0, n - 1, pos, val);
            } 
            else if (type == 2) {
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(query(0, 0, n - 1, l, r));
            }
        }
    }
}