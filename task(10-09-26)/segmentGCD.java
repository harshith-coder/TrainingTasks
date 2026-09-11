import java.util.*;

public class segmentGCD {

    static int tree[],n,arr[];
    
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void build(int idx, int s, int e){
        if(s==e){
            tree[idx] = arr[s];
            return;
        }
        int mid = (s + e) / 2;
        int l=idx*2+1;
        int r=idx*2+2;
        build(l,s,mid);
        build(r,mid+1,e);
        tree[idx] = gcd(tree[l],tree[r]);
    }

    public static void update(int idx,int s,int e,int pos,int val){
        if(s==e){
            tree[idx] = val;
            return;
        }
        int mid = (s + e) / 2;
        int l=idx*2+1;
        int r=idx*2+2;
        if(pos<=mid){
            update(l,s,mid,pos,val);
        }else{
            update(r,mid+1,e,pos,val);
        }
        tree[idx] = gcd(tree[l],tree[r]);
    }

    public static int query(int idx, int s, int e, int l, int r){
        if(s>r || e<l){
            return 0;
        }
        if(s>=l && e<=r){
            return tree[idx];
        }
        int mid = (s + e) / 2;
        int left = query(idx*2+1,s,mid,l,r);
        int right = query(idx*2+2,mid+1,e,l,r);
        return gcd(left,right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        tree = new int[4*n];
        build(0,0,n-1);
        int q = sc.nextInt();
        while(q-->0){
            int type = sc.nextInt();
            if(type==1){
                int pos = sc.nextInt();
                int val = sc.nextInt();
                update(0,0,n-1,pos,val);
            }else{
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(query(0,0,n-1,l,r));
            }
        }
    }
}
