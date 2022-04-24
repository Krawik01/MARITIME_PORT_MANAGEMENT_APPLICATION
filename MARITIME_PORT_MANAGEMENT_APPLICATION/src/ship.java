import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public
    class ship {
        String name;
        String homePort;
        String startingLocation;
        String finishLocation;
        int acctualWeight;
        private static int number = 0; //automatycznie
        int id;
        int sumValueContainers;
        List<Integer> numbersforcontainers;

        int MaxDangerousCont; //maks niebezpiecznych konterow
        int maxHeavyCont;    //maks ciezkich konterow
        int maxElectricCont; //maks wymagających podlaczenia kontenerow
        int maxAllCont;      //maks wszystkich konterow
        int maxCapacity;     //maksymalna ladownosc

        public ship(String name, String homePort, String startingLocation, String finishLocation, int maxCapacity){
            this.name = name;
            this.homePort = homePort;
            this.startingLocation = startingLocation;
            this.finishLocation = finishLocation;
            this.maxCapacity = maxCapacity;
            id = ++number;
        }

    public static void addShip(List<ship> shipList) {
      try {
          Scanner in = new Scanner(System.in);

          System.out.println("Name: ");
          String nameIn = in.nextLine();
          System.out.println("Home port: ");
          String homePortIn = in.nextLine();
          System.out.println("Starting Location: ");
          String startingLocationIn = in.nextLine();
          System.out.println("Finish Location: ");
          String finishLocationIn = in.nextLine();
          System.out.println("Max capacity");
          int maxCapacity = in.nextInt();

          shipList.add(new ship(nameIn, homePortIn, startingLocationIn, finishLocationIn, maxCapacity));
          System.out.println("Ship has been added.");
      } catch (InputMismatchException x ){
          System.out.println("wrong format of data!");
      }

    }

    public static void unloadShip(List<ship> shipList, List<basicCont> containers, List<basicCont> where, int id, int day, String[] incrementedDate, String date) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("select ship: ");
        ship.showShipsListWithContainers(shipList, containers);
        int selectShip = in.nextInt();
        System.out.println("select container: ");

        for (int i = 0; i < containers.size(); i++) {
            if (containers.get(i).placeId == selectShip) {
                containers.get(i).dateOfDelete = day;
                System.out.println("(" + (i + 1) + ")" + containers.get(i).toString());
            }
        }
        int selectContainer = in.nextInt();
        selectContainer = selectContainer - 1;
        containers.get(selectContainer).placeId = id;
        containers.get(selectContainer).dateOfLoad = day;
        incrementedDate[0] = main.addOneDay(date, day);
        containers.get(selectContainer).dateOfLoadToShow = incrementedDate[0];

        where.add(containers.get(selectContainer));

        System.out.println("container has been unloaded");
    }

    public static ArrayList<ship> loadShipFromFile(String filename) throws IOException {

        File file = new File("ships.txt");
        Scanner s = new Scanner(file);

        ArrayList<ship> shipsFromFIle = new ArrayList<ship>();
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] items = line.split("\\|");
            // System.out.print(items[0] + ", " + items[1] + ", " + items[2] + ", " + items[3]);

            shipsFromFIle.add(new ship(items[0], items[1], items[2], items[3], Integer.parseInt(items[4])));


        }
        return shipsFromFIle;
    }

    public static void showShipsList(List<ship> shipList) {
        System.out.println("The list of your ships: ");
        int j = 1;
        for (ship ship : shipList) {
            System.out.println("(" + j + ")" + ship);
            j++;
        }
    }
    public static void showShipsListWithContainers(List<ship> shipList, List<basicCont> containers) {
        for (int i = 0; i < shipList.size(); i++) {
            System.out.println("(" + (i + 1) + ")" + shipList.get(i));
            for (int j = 0; j < containers.size(); j++) {
                if (containers.get(j).placeId == i + 1) {
                    System.out.println("(" + (i + 1) + ") " + containers.get(j).toString());
                }
            }
            System.out.println();
        }
    }

    public static void loadShip(List <ship> shipList, List<basicCont> from,boolean inMagazine,int id) {
        java.util.Scanner in = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("select what ship u want to load(use numbers)");
            ship.showShipsList(shipList);
            int shipSelect = in.nextInt();

            shipSelect = shipSelect - 1;


            System.out.println("select what container you want to load(use numbers)");
            basicCont.showContainersList(from, id);
            try{
            int contSelect = in.nextInt();
            contSelect = contSelect - 1;
            if (!main.numberIsAvaible(contSelect, from, id)) {
                System.out.println("wrong number!\nlook at numbers in brackets.");
                break;
            }

            try {
                if ((shipList.get(shipSelect).acctualWeight + from.get(contSelect).grossWeight < shipList.get(shipSelect).maxCapacity) && (shipList.get(shipSelect).sumValueContainers + from.get(contSelect).valueOfContainer <= 3)) {
                    shipList.get(shipSelect).acctualWeight = shipList.get(shipSelect).acctualWeight + from.get(contSelect).grossWeight;
                    from.get(contSelect).placeId = shipSelect + 1;
                    shipList.get(shipSelect).sumValueContainers = shipList.get(shipSelect).sumValueContainers + from.get(contSelect).valueOfContainer;
                    System.out.println("the container has been loaded");
                } else {
                    System.out.println("you can't load this container, check max capacity or number of dangerous containers ");
                }
            } catch (NullPointerException e) {
                System.out.println("");
            }
            if (inMagazine) {
                from.remove(contSelect);
            }
            }catch (IndexOutOfBoundsException e){
            System.out.println("wrong number!");
        }
            break;
        }
    }
    public static void saveShips(ArrayList<ship> ships, ArrayList<basicCont> containers) throws IOException {

        String[] shipsSortedTab = new String[ships.size()];

        for (int i = 0; i <shipsSortedTab.length; i++) {
            shipsSortedTab[i] = ships.get(i).name;
        }

        Arrays.sort(shipsSortedTab);

        List<ship> sortedShipsList = new ArrayList<>();
        for (int i = 0; i < shipsSortedTab.length; i++) {
            for (int j = 0; j < shipsSortedTab.length; j++) {
                if(shipsSortedTab[i] == ships.get(j).name){
                    sortedShipsList.add(ships.get(j));
                }
            }
        }

        File shipSave = new File("ships.txt");
        FileWriter shipsWriter = new FileWriter(shipSave);
        for (ship ship : sortedShipsList) {
            shipsWriter.write(ship.toStringToFile() + "\n");
        }
        shipsWriter.close();
//======================================================================================
        int[] containersWithShipsSortedTab = new int[containers.size()];
        ArrayList<basicCont> containersWithShipsSortedList = new ArrayList<>();


        for (int i = 0; i < containersWithShipsSortedTab.length; i++) {
            if((containers.get(i).placeId<100)&&(containers.get(i).placeId !=0))
                containersWithShipsSortedTab[i] = containers.get(i).grossWeight;
        }

        Arrays.sort(containersWithShipsSortedTab);

        for (int i = 0; i < containers.size(); i++) {
            for (int j = 0; j < containersWithShipsSortedTab.length; j++) {
                if (containersWithShipsSortedTab[i]==containers.get(j).grossWeight) {
                    containersWithShipsSortedList.add(containers.get(j));
                }
            }
        }
        File shipWithContainersSave = new File("shipsWithContainers.txt");
        FileWriter shipsWithCotainers = new FileWriter(shipWithContainersSave);
        for (int j = 0; j <ships.size() ; j++) {
            shipsWithCotainers.write(ships.get(j).toString() + "\n");
            for (int k = 0; k < containersWithShipsSortedList.size(); k++) {
                if(ships.get(j).id == containersWithShipsSortedList.get(k).placeId){
                    shipsWithCotainers.write(containersWithShipsSortedList.get(k).toString() + "\n");
                }
            }
            shipsWithCotainers.write("\n");
        }
        shipsWithCotainers.close();

    }


    public String toString() {
        return " {name: " + name + ", " +
                "homePort: " + homePort + ", " +
                "startingLocation: " + startingLocation + ", " +
                "finishLocation: " + finishLocation + ", " +
                "id: " + id + '}';
    }
    public String toStringToFile() {
        return name + '|' + homePort + '|' + startingLocation + '|' + finishLocation + '|' +maxCapacity;
    }

}
