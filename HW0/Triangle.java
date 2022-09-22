public class Triangle {
	public static void drawTriangle(int N){
    	int row = 0;
    	while (row < N){
    		int star = 0;
    		while (star <= row){
    			System.out.print('*');
    			star = star + 1;
    		}
			System.out.println();
    		row = row + 1;
    	}
	}


    public static void main(String[] args) {
    	drawTriangle(10);
    }
}