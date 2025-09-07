package org.example.template;
import java.io.*;
import java.util.*;

/**
 * Java Coding Test Cheatsheet (Backend Dev Style)
 * - Java 17, 빠른 I/O, 실전 템플릿, edge-case 체크리스트 포함
 */
public class Main {
    // =============================
    // 0) Fast I/O + Utils
    // =============================
    static FastScanner fs = new FastScanner(System.in);
    static FastPrinter out = new FastPrinter(System.out);

    public static void main(String[] args) throws Exception {
        // Demo: 입력 N 후 N개의 합
        int N = fs.nextInt();
        long sum = 0;
        for (int i = 0; i < N; i++) sum += fs.nextLong();
        out.println(sum);
        out.flush();
    }

    // ---- FastScanner ----
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        FastScanner(InputStream in) { br = new BufferedReader(new InputStreamReader(in)); }
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        String nextLine() throws IOException { return br.readLine(); }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
        long nextLong() throws IOException { return Long.parseLong(next()); }
        double nextDouble() throws IOException { return Double.parseDouble(next()); }
    }

    // ---- FastPrinter ----
    static class FastPrinter {
        private final StringBuilder sb = new StringBuilder(1 << 20);
        private final OutputStream os;
        FastPrinter(OutputStream os) { this.os = os; }
        void print(Object o) { sb.append(o); }
        void println(Object o) { sb.append(o).append('\n'); }
        void println() { sb.append('\n'); }
        void flush() throws IOException { os.write(sb.toString().getBytes()); sb.setLength(0); }
    }

    // ---- Small data structs ----
    static class Pair<A, B> { A a; B b; Pair(A a, B b){this.a=a;this.b=b;} }
    static class IntPair { int x,y; IntPair(int x,int y){this.x=x;this.y=y;} }
    static class LongPair { long x,y; LongPair(long x,long y){this.x=x;this.y=y;} }

    // ---- Common Utils ----
    static final long INF64 = (long)9e18;   // Long.MAX_SAFE-ish
    static final int INF32 = (int)1e9;
    static int[] dx4 = {1,-1,0,0};
    static int[] dy4 = {0,0,1,-1};
    static int[] dx8 = {1,1,1,0,0,-1,-1,-1};
    static int[] dy8 = {1,0,-1,1,-1,1,0,-1};

    static boolean inRange(int x,int y,int n,int m){ return (0<=x && x<n && 0<=y && y<m); }
    static long clampedAdd(long a,long b){
        if (b>0 && a>Long.MAX_VALUE-b) return Long.MAX_VALUE;
        if (b<0 && a<Long.MIN_VALUE-b) return Long.MIN_VALUE;
        return a+b;
    }

    // =============================
    // 1) 정렬 & 비교자 패턴
    // =============================
    static class Node {
        int a,b,c;
        Node(int a,int b,int c){this.a=a;this.b=b;this.c=c;}
    }
    static void sortExample(List<Node> list){
        // 1차 a 오름차순, 2차 b 내림차순, 3차 c 오름차순
        list.sort((u,v)->{
            if(u.a!=v.a) return Integer.compare(u.a,v.a);
            if(u.b!=v.b) return -Integer.compare(u.b,v.b);
            return Integer.compare(u.c,v.c);
        });
    }

    // =============================
    // 2) 투 포인터 & 슬라이딩 윈도우
    // =============================
    static int twoPointersCountPairsWithSumSorted(int[] arr,int target){
        Arrays.sort(arr);
        int n=arr.length, l=0, r=n-1, cnt=0;
        while(l<r){
            int s=arr[l]+arr[r];
            if(s==target){ cnt++; l++; r--; /*중복 처리시 while(l<r && arr[l]==arr[l-1]) l++; ...*/ }
            else if(s<target) l++; else r--;
        }
        return cnt;
    }

    static int slidingWindowMaxLenAtMostKDistinct(int[] a, int K){
        Map<Integer,Integer> freq=new HashMap<>();
        int n=a.length, l=0, best=0;
        for(int r=0;r<n;r++){
            freq.merge(a[r],1,Integer::sum);
            while(freq.size()>K){
                freq.compute(a[l],(k,v)-> v==1?null:v-1); l++;
            }
            best=Math.max(best,r-l+1);
        }
        return best;
    }

    // =============================
    // 3) 이분 탐색 (인덱스/값) + Parametric Search
    // =============================
    static int lowerBound(int[] a,int x){
        int l=0,r=a.length; // [l,r)
        while(l<r){
            int m=(l+r)>>>1;
            if(a[m]>=x) r=m; else l=m+1;
        }
        return l;
    }
    static int upperBound(int[] a,int x){
        int l=0,r=a.length;
        while(l<r){
            int m=(l+r)>>>1;
            if(a[m]>x) r=m; else l=m+1;
        }
        return l;
    }

    // Parametric: 최소 가능한 정답을 찾는 패턴
    static long parametricSearch(long lo,long hi){
        while(lo<hi){
            long mid = (lo+hi)>>>1;
            if(ok(mid)) hi=mid; else lo=mid+1;
        }
        return lo;
    }
    static boolean ok(long x){
        // 단조성 predicate 구현 (x가 크면 조건 만족 여부가 변하지 않아야 함)
        return true;
    }

    // =============================
    // 4) 누적합 / 차분 / 구간 질의
    // =============================
    static long[] prefixSum(long[] a){
        int n=a.length; long[] ps=new long[n+1];
        for(int i=0;i<n;i++) ps[i+1]=ps[i]+a[i];
        return ps;
    }
    static long rangeSum(long[] ps,int l,int r){ // [l,r]
        return ps[r+1]-ps[l];
    }
    static void rangeAddDiff(long[] diff,int l,int r,long v){ // diff 배열로 구간 가산
        diff[l]+=v; if(r+1<diff.length) diff[r+1]-=v;
    }

    // =============================
    // 5) 그래프: BFS/DFS (격자 & 일반)
    // =============================
    static int bfsGrid(char[][] map){
        int n=map.length, m=map[0].length; boolean[][] vis=new boolean[n][m];
        ArrayDeque<int[]> q=new ArrayDeque<>();
        // 시작 좌표 예시 (0,0)
        q.add(new int[]{0,0}); vis[0][0]=true;
        int dist=0;
        while(!q.isEmpty()){
            for(int sz=q.size(); sz>0; sz--){
                int[] cur=q.poll();
                for(int d=0; d<4; d++){
                    int nx=cur[0]+dx4[d], ny=cur[1]+dy4[d];
                    if(!inRange(nx,ny,n,m) || vis[nx][ny]) continue;
                    if(map[nx][ny]=='#') continue; // 벽
                    vis[nx][ny]=true; q.add(new int[]{nx,ny});
                }
            }
            dist++;
        }
        return dist; // 필요에 따라 반환 변경
    }

    static void dfsGraphRec(List<Integer>[] g, int s, boolean[] vis){
        vis[s]=true;
        for(int to: g[s]) if(!vis[to]) dfsGraphRec(g,to,vis);
    }

    // =============================
    // 6) 최단경로: 다익스트라 (long dist, 음수 간선 X)
    // =============================
    static long dijkstra(List<int[]>[] g, int s, int t){
        int n=g.length; long[] dist=new long[n]; boolean[] done=new boolean[n];
        Arrays.fill(dist, INF64); dist[s]=0;
        PriorityQueue<long[]> pq=new PriorityQueue<>(Comparator.comparingLong(a->a[0])); // {dist,node}
        pq.add(new long[]{0,s});
        while(!pq.isEmpty()){
            long[] cur=pq.poll(); long d=cur[0]; int v=(int)cur[1];
            if(done[v]) continue; done[v]=true; if(v==t) break;
            for(int[] e:g[v]){ int to=e[0], w=e[1];
                long nd=d+w; if(nd<dist[to]){ dist[to]=nd; pq.add(new long[]{nd,to}); }
            }
        }
        return dist[t];
    }

    // =============================
    // 7) 유니온파인드 (DSU) + MST
    // =============================
    static class DSU{
        int[] p, r;
        DSU(int n){ p=new int[n]; r=new int[n]; for(int i=0;i<n;i++) p[i]=i; }
        int f(int x){ return p[x]==x?x:(p[x]=f(p[x])); }
        boolean u(int a,int b){ a=f(a); b=f(b); if(a==b) return false; if(r[a]<r[b]){int t=a;a=b;b=t;} p[b]=a; if(r[a]==r[b]) r[a]++; return true; }
    }

    // Kruskal 예시
    static long kruskal(int n, int[][] edges){ // edges: {w,u,v}
        Arrays.sort(edges, Comparator.comparingInt(e->e[0]));
        DSU dsu=new DSU(n); long cost=0; int cnt=0;
        for(int[] e: edges){ if(dsu.u(e[1], e[2])){ cost+=e[0]; cnt++; if(cnt==n-1) break; } }
        return cost;
    }

    // =============================
    // 8) 위상정렬 (Kahn) + DAG 최장/최단 거리
    // =============================
    static int[] topoOrder(List<Integer>[] g){
        int n=g.length; int[] indeg=new int[n]; for(int v=0;v<n;v++) for(int to:g[v]) indeg[to]++;
        ArrayDeque<Integer> q=new ArrayDeque<>(); for(int i=0;i<n;i++) if(indeg[i]==0) q.add(i);
        int[] order=new int[n]; int idx=0;
        while(!q.isEmpty()){
            int v=q.poll(); order[idx++]=v;
            for(int to:g[v]) if(--indeg[to]==0) q.add(to);
        }
        return (idx==n)?order:new int[0]; // 사이클 있으면 빈 배열
    }

    // =============================
    // 9) DP 패턴 모음
    // =============================
    // 0/1 Knapsack (1D 압축)
    static int knapsack01(int[] w,int[] val,int W){
        int n=w.length; int[] dp=new int[W+1];
        for(int i=0;i<n;i++) for(int cap=W; cap>=w[i]; cap--) dp[cap]=Math.max(dp[cap], dp[cap-w[i]]+val[i]);
        return dp[W];
    }

    // LIS (O(n log n))
    static int lisLength(int[] a){
        int[] d=new int[a.length]; int len=0;
        for(int x: a){
            int i=Arrays.binarySearch(d,0,len,x);
            if(i<0) i=-(i+1);
            d[i]=x; if(i==len) len++;
        }
        return len;
    }

    // =============================
    // 10) 세그트리 / 펜윅트리
    // =============================
    static class Fenwick{
        int n; long[] bit; Fenwick(int n){ this.n=n; bit=new long[n+1]; }
        void add(int idx,long delta){ for(; idx<=n; idx+=idx&-idx) bit[idx]+=delta; }
        long sum(int idx){ long s=0; for(; idx>0; idx-=idx&-idx) s+=bit[idx]; return s; }
        long rangeSum(int l,int r){ return sum(r)-sum(l-1); }
    }

    static class SegTree { // Range Sum 예시
        int n; long[] t;
        SegTree(int n){ this.n=1; while(this.n<n) this.n<<=1; t=new long[this.n<<1]; }
        void build(long[] a){ for(int i=0;i<a.length;i++) t[n+i]=a[i]; for(int i=n-1;i>0;i--) t[i]=t[i<<1]+t[i<<1|1]; }
        void pointUpdate(int idx,long v){ int i=idx+n; t[i]=v; for(i>>=1; i>0; i>>=1) t[i]=t[i<<1]+t[i<<1|1]; }
        long rangeQuery(int l,int r){ long L=0,R=0; for(l+=n,r+=n; l<=r; l>>=1, r>>=1){ if((l&1)==1) L+=t[l++]; if((r&1)==0) R+=t[r--]; } return L+R; }
    }

    // =============================
    // 11) 문자열: KMP
    // =============================
    static int[] kmpPi(char[] p){
        int m=p.length; int[] pi=new int[m];
        for(int i=1,j=0;i<m;i++){
            while(j>0 && p[i]!=p[j]) j=pi[j-1];
            if(p[i]==p[j]) pi[i]=++j;
        }
        return pi;
    }
    static List<Integer> kmpSearch(String s,String p){
        char[] a=s.toCharArray(), b=p.toCharArray(); int[] pi=kmpPi(b); List<Integer> res=new ArrayList<>();
        for(int i=0,j=0;i<a.length;i++){
            while(j>0 && a[i]!=b[j]) j=pi[j-1];
            if(a[i]==b[j]){
                if(j==b.length-1){ res.add(i-j); j=pi[j]; }
                else j++;
            }
        }
        return res;
    }

    // =============================
    // 12) 수학: GCD/LCM, 빠른 거듭제곱, 모듈러 역원
    // =============================
    static long gcd(long a,long b){ while(b!=0){ long t=a%b; a=b; b=t; } return Math.abs(a); }
    static long lcm(long a,long b){ return a/gcd(a,b)*b; }
    static long modPow(long a,long e,long mod){ long r=1%mod; a%=mod; while(e>0){ if((e&1)==1) r=(r*a)%mod; a=(a*a)%mod; e>>=1; } return r; }
    static long modInvPrime(long a,long mod){ return modPow(a,mod-2,mod); } // mod은 소수

    static class CombMod { // nCk mod (소수)
        int N; long MOD; long[] fact, invFact;
        CombMod(int N,long MOD){ this.N=N; this.MOD=MOD; fact=new long[N+1]; invFact=new long[N+1];
            fact[0]=1; for(int i=1;i<=N;i++) fact[i]=fact[i-1]*i%MOD;
            invFact[N]=modInvPrime(fact[N],MOD); for(int i=N;i>0;i--) invFact[i-1]=invFact[i]*i%MOD;
        }
        long nCk(int n,int k){ if(k<0||k>n) return 0; return (((fact[n]*invFact[k])%MOD)*invFact[n-k])%MOD; }
    }

}
