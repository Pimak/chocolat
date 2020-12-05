package pimak;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
	// write your code here

        HashMap<String,Tablet> newTablets = new HashMap<>();
        Tablet pos24 = new Tablet();
        HashMap<String,Tablet> oldTablets = new HashMap<>();
        oldTablets.put(pos24.getHash(),pos24);
        long now = System.currentTimeMillis();
        long alreadyPrinted;
        long current;
        double size;
        for (int nombre=23; nombre>0; nombre-- ){
            System.out.println("On place le nombre :"+nombre);
            System.out.println("Il y a "+oldTablets.size()+" configurations différentes");
            alreadyPrinted = 0;
            current = 0;
            size=oldTablets.size();
            for (Tablet oldTablet: oldTablets.values()){
                for (int column = 0; column<=Tablet.HORIZONTAL_SIZE; column++){
                    for (int row=0; row<=Tablet.VERTICAL_SIZE;row++){
                        //System.out.println(column+","+row);
                        if (oldTablet.isValid(column,row)){
                            boolean[][] tableau = oldTablet.addSquare(column,row);
                            Tablet tablet = new Tablet(tableau,oldTablet.getWays());
                            if (newTablets.containsKey(tablet.getHash())){
                                newTablets.get(tablet.getHash()).addWays(oldTablet.getWays());
                            } else {
                                newTablets.put(tablet.getHash(),tablet);
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
            newTablets = new HashMap<>();
            System.out.println("Temps nécessaire :"+(System.currentTimeMillis()-now) + "ms");
            now = System.currentTimeMillis();
        }
        for (Tablet tablet : oldTablets.values()){
            System.out.println(" Résultat : "+tablet.getWays());
        }


    }
}
