public class Main {

    public static void main(String[] args) {
	    int[] data;
        data = new int[100];
        int[][] data2;
        data2 = new int[10][20];
        System.out.println(data.length);
        System.out.println("\n" + data2.length);

        /*Interface: everything is abstract in an interface
        interface Cell{
            boolean isVisible();
            int getNeighborMines();
            boolean isMine();
        }
        class MyCell implements Cell{
            //right click and copy the stuff in the interface, then write all of the functions in the interface
            the class uses the function in the interface
        }
         */
    }
}
