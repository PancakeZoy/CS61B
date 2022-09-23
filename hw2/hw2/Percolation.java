package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int opensites;
    private int top;
    private int bottom;
    private int openroot;
    private int mapsize;
    private WeightedQuickUnionUF AdjHoles;
    private WeightedQuickUnionUF OpenHoles;
    private WeightedQuickUnionUF AdjHoles_wo_bottom;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        if ( N < 1){
            throw new java.lang.IllegalArgumentException("Need a positive integer");
        }
        mapsize = N;
        top = N*N;
        bottom = N*N + 1;
        openroot = N*N;
        opensites = 0;
        OpenHoles = new WeightedQuickUnionUF(N*N + 1);
        AdjHoles = new WeightedQuickUnionUF(N*N + 2);
        AdjHoles_wo_bottom = new WeightedQuickUnionUF(N*N + 1);
        for (int i=0; i<N; i++){
            AdjHoles.union(top, i);
            AdjHoles.union(bottom,N*(N-1)+i);
            AdjHoles_wo_bottom.union(top, i);
        }
    }
    private int xyTo1D(int row, int col){
        return row * mapsize + col;
    }

    private boolean isValid(int row, int col){
        return (row < mapsize && row >= 0 && col < mapsize && col >= 0);
    }
    private void validate(int row, int col){
        if (!isValid(row, col)){
            throw new java.lang.IndexOutOfBoundsException("Out of bound");
        }
    }

    private void connect(int row, int col){
        int myloc = xyTo1D(row, col);
        int[][] neighbors = {{row-1, col}, {row+1, col}, {row, col-1},{row, col+1}};
        for (int[] n:neighbors){
            if(isValid(n[0],n[1])){
                if(isOpen(n[0],n[1])){
                    AdjHoles.union(myloc, xyTo1D(n[0],n[1]));
                    AdjHoles_wo_bottom.union(myloc, xyTo1D(n[0],n[1]));
                }
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        validate(row, col);
        if (!isOpen(row, col)){
            OpenHoles.union(openroot, xyTo1D(row, col));
            connect(row, col);
            opensites += 1;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return OpenHoles.connected(xyTo1D(row, col), openroot);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        return AdjHoles_wo_bottom.connected(xyTo1D(row, col), top) && OpenHoles.connected(xyTo1D(row, col), openroot);
    }

    // number of open sites
    public int numberOfOpenSites(){
        return opensites;
    }

    // does the system percolate?
    public boolean percolates(){
        return AdjHoles.connected(top, bottom);
    }
}
