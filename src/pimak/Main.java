package pimak;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here

        ArrayList<Tablet> newTablets = new ArrayList<>();
        Tablet pos24 = new Tablet();
        ArrayList<Tablet> oldTablets = new ArrayList<>();
        oldTablets.add(pos24);
        long now = System.currentTimeMillis();
        long alreadyPrinted = 0;
        long current = 0;
        double size = oldTablets.size();
        for (int nombre=23; nombre>0; nombre-- ){
            System.out.println("On place le nombre :"+nombre);
            System.out.println("Il y a "+oldTablets.size()+" configurations différentes");
            alreadyPrinted = 0;
            current = 0;
            size=oldTablets.size();
            for (Tablet oldTablet: oldTablets){
                for (int column = 0; column<=Tablet.HORIZONTAL_SIZE; column++){
                    for (int row=0; row<=Tablet.VERTICAL_SIZE;row++){
                        //System.out.println(column+","+row);
                        if (oldTablet.isValid(column,row)){
                            boolean[][] tableau = oldTablet.addSquare(column,row);
                            boolean found = false;
                            for (Tablet tab23:newTablets){
                                if (tab23.isEqual(tableau)){
                                    tab23.addWays(oldTablet.getWays());
                                    found = true;
                                }
                            }
                            if (!found){
                                Tablet tablet = new Tablet(tableau, oldTablet.getWays());
                                newTablets.add(tablet);
                            }
                        }
                    }
                }
                current++;
                while (alreadyPrinted<(current*100)/size){
                    alreadyPrinted++;
                    System.out.print('=');
                }
            }
            oldTablets = newTablets;
            newTablets = new ArrayList<>();
            System.out.println("Temps nécessaire :"+(System.currentTimeMillis()-now)/1000 + "s");
            now = System.currentTimeMillis();
        }
        System.out.println(oldTablets.get(0).getWays());


    }
}
