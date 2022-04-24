
import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.DoubleToIntFunction;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public
    class main {
    public static void main(String[] args) throws Exception {

     //   pjatk();

        Timer Timer = new Timer();
        Timer.start();


        String date = LocalDate.now().toString();
        final String[] incrementedDate = {addOneDay(date, Timer.days)};
        System.out.println(startMsg(date));

        ArrayList<Sender> senders = new ArrayList<>();

        magazineCont magazineCont = new magazineCont(10);

        ArrayList<basicCont> magazine = new ArrayList<>();
        ArrayList<basicCont> utilizedContainers = new ArrayList<>();
        ArrayList<basicCont> containers = new ArrayList<>();
        ArrayList<basicCont> wagon = new ArrayList<>();

        ArrayList<ship> shipList = new ArrayList<>();
        ArrayList<ship> shipCruise = new ArrayList<>();

        Map<basicCont, List<Sender>> sender_container = new HashMap<>();


        loadDataFromFiles(senders,containers,magazine,shipList);


        Thread checks = new Thread(new Runnable() {
            @Override
            public void run() {
                int when = (Timer.days - 1) + 1000;
                while (true) {
                    try {
                        sleep(when);
                    } catch (InterruptedException exc) {
                        System.out.println("stoped");
                        return;
                    }
                    for (int i = 0; i < containers.size(); i++) {
                        for (int j = 0; j < senders.size(); j++) {
                            if (Objects.equals(containers.get(i).sender, senders.get(j).name)) {
                                sender_container.put(containers.get(i), new ArrayList<>());
                                sender_container.get(containers.get(i)).add(senders.get(j));
                            }
                        }
                    }
                    try {
                        for (basicCont basicCont : magazine) {
                            if (Timer.days - basicCont.dateOfLoad >= basicCont.maxAmountOfTime) {
                                incrementedDate[0] = addOneDay(date, Timer.days);
                                basicCont.dateOfDeleteToShow = incrementedDate[0];
                                basicCont.dateOfDelete = Timer.days;
                                utilizedContainers.add(basicCont);


                                for (int j = 0; j < senders.size(); j++) {
                                    if (basicCont.sender == senders.get(j).name) {
                                        senders.get(j).breach++;
                                    }
                                }
                                if (magazine.size() > 0)
                                    magazine.remove(basicCont);
                            }
                        } //wait
                    } catch (ConcurrentModificationException e) {
                        System.out.println("");
                    }
                }
            }
        });



        checks.start();

        Scanner in = new Scanner(System.in);

        boolean end = false;


        while (!end) {

            String user = in.nextLine();

            if (Objects.equals(user, "end")) {
                System.out.println("do you want to save changes?(yes or no)");
                String doYouSave = in.nextLine();
                if (Objects.equals(doYouSave,"yes")){
                    save(shipList, containers, utilizedContainers, magazine, wagon, senders);
                    end = true;
                } else {
                    end = true;
                }
                System.out.println("program has been stoped by user");
            }

            if (Objects.equals(user, "clear")) {
                clearScreen();
            }

            if(Objects.equals(user,"launch")){
                basicCont.addLaunchObjects(containers,senders,shipList);
                save(shipList, containers, utilizedContainers, magazine, wagon, senders);
            }

            if (Objects.equals(user, "help")) {
                System.out.println(help());
            }

            if (Objects.equals(user, "sender")) {
                System.out.println("You are in - senders manager\ncommands: sign, list");
                user = in.nextLine();

                if (Objects.equals(user, "sign")) {
                    System.out.println("name: ");
                    String name = in.nextLine();
                    System.out.println("surname: ");
                    String surname = in.nextLine();
                    System.out.println("pesel: ");
                    String pesel = in.nextLine();
                    System.out.println("adres: ");
                    String adres = in.nextLine();

                    senders.add(new Sender(name, surname, pesel, adres, 0));
                    System.out.println("sender has been added\nYou are in the - main menu");
                }

                if (Objects.equals(user, "list")) {
                    System.out.println("senders: ");
                    for (Sender value : senders) {
                        System.out.print(value + "Birth date: ");
                        value.getBirthDate();
                        System.out.println();
                    }
                }
            }

            if (Objects.equals(user, "ship")) {

                System.out.println("You are in - ship manager.\nComands: new, list, unload, send cruise");

                String shipManager = in.nextLine();

                if (Objects.equals(shipManager, "new")) {

                    ship.addShip(shipList);

                }

                if (Objects.equals(shipManager, "list")) {

                    System.out.println("which list to display\nport,cruise");
                    String whatList = in.nextLine();

                    if (Objects.equals(whatList, "port")) {
                        System.out.println("Do you want me to show the containers assigned to the ships?(yes or no)");
                        shipManager = in.nextLine();
                        if (Objects.equals(shipManager, "no")) {
                            ship.showShipsList(shipList);
                        }
                        if (Objects.equals(shipManager, "yes")) {
                            ship.showShipsListWithContainers(shipList, containers);
                        }
                    }

                    if (Objects.equals(whatList, "cruise")) {
                        ship.showShipsList(shipCruise);
                    }
                }
                if (Objects.equals(shipManager, "unload")) {
                    System.out.println("Where u want to unload:\nmagazine, wagon");

                    String selectPlace = in.nextLine();

                    if (Objects.equals(selectPlace, "magazine")) {
                        ship.unloadShip(shipList, containers, magazine, 100, Timer.days, incrementedDate, date);
                    }

                    if (Objects.equals(selectPlace, "wagon")) {
                        if ((wagon.size() % 10 == 0) && (wagon.size() > 0)) {
                            System.out.println("The wagon is full. You must wait for the current wagon to leave and for the next wagon to arrive");
                            long current = System.currentTimeMillis();
                            int i = 30;
                            while (i >= 0) {
                                if (System.currentTimeMillis() - current > 1000) {
                                    System.out.println(i--);
                                    current = System.currentTimeMillis();

                                }
                                wagon.removeAll(containers);
                            }
                        }

                        ship.unloadShip(shipList, containers, wagon, 8000, Timer.days, incrementedDate, date);
                    }
                }
                if (Objects.equals(shipManager, "send cruise")) {
                    ship.showShipsList(shipList);
                    System.out.println("select ship");
                    int selectShip = in.nextInt();
                    selectShip = selectShip - 1;
                    System.out.println("The ship:\n" + "(" + (selectShip + 1) + ")" + shipList.get(selectShip).toString() + "\nhas been sent on a cruise");
                    shipCruise.add(shipList.get(selectShip));
                    shipList.remove(selectShip);

                }
                System.out.println("You are in the - main menu");
            }

            if (Objects.equals(user, "cont")) {

                System.out.println("You are in - containers manager.\nComands: new, list, load ship, load magazine");
                user = in.nextLine();

                if (Objects.equals(user, "new")) {
                    System.out.println("Select type:\nbasic, heavy, cooling, liquid, explosive, toxic bulk, toxic liquid.");
                    user = in.nextLine();
                    if (Objects.equals(user, "basic")) {
                        basicCont.addContainer(containers, 0, 1, senders);
                    }
                    if (Objects.equals(user, "heavy")) {
                        basicCont.addContainer(containers, 0, 2, senders);
                    }
                    if (Objects.equals(user, "cooling")) {
                        basicCont.addContainer(containers, 0, 3, senders);
                    }
                    if (Objects.equals(user, "liquid")) {
                        basicCont.addContainer(containers, 0, 4, senders);
                    }
                    if (Objects.equals(user, "explosive")) {
                        basicCont.addContainer(containers, 0, 5, senders);
                    }
                    if (Objects.equals(user, "toxic bulk")) {
                        basicCont.addContainer(containers, 0, 6, senders);
                    }
                    if (Objects.equals(user, "toxic liquid")) {
                        basicCont.addContainer(containers, 0, 7, senders);
                    }
                }
                if (Objects.equals(user, "list")) {
                    basicCont.showContainersListWithSenders(sender_container);
                }

                if (Objects.equals(user, "load ship")) {
                    ship.loadShip(shipList, containers, false, 0);
                }

                if (Objects.equals(user, "load magazine")) {
                    System.out.println("select what container you want to load(use numbers)");
                    basicCont.showContainersList(containers, 0);

                    try {
                        boolean notAvaible = false;
                        int contSelect = in.nextInt();
                        contSelect = contSelect - 1;

                        if (!main.numberIsAvaible(contSelect, containers, 0)) {
                            System.out.println("wrong number!\nlook at numbers in brackets.");

                        } else {
                            ArrayList<Sender> sendersWithBreach = new ArrayList<>();
                            for (int i = 0; i < senders.size(); i++) {
                                if(senders.get(i).breach >2){
                                    sendersWithBreach.add(senders.get(i));
                                }
                            }
                            for (int i = 0; i < sendersWithBreach.size(); i++) {
                                if(containers.get(contSelect).sender == sendersWithBreach.get(i).name){
                                    notAvaible = true;
                                }
                            }
                            if(!notAvaible) {
                                if (magazine.size() >= magazineCont.maxCapacity) {
                                    System.out.println("Cannot add container. No more storage space available");
                                } else {
                                    incrementedDate[0] = addOneDay(date, Timer.days);
                                    containers.get(contSelect).dateOfLoadToShow = incrementedDate[0];
                                    containers.get(contSelect).dateOfLoad = Timer.days;
                                    magazine.add(containers.get(contSelect));
                                    //containers.remove(contSelect);
                                    containers.get(contSelect).placeId = 100;
                                    System.out.println("container has been loaded");
                                }
                            } else {
                                System.out.println("sender is not avaible");
                            }
                            notAvaible = false;
                        }
                        System.out.println("You are in the - main menu");
                    }catch (InputMismatchException e){
                        System.out.println("use numbers!\nYou are in the - main menu");
                    }
                }
            }

            if (Objects.equals(user, "magazine")) {
                System.out.println("You are in - magazine manager\ncommands: list, utilize, load ship");

                user = in.nextLine();

                try {
                    if (utilizedContainers.size() > 0) {
                        throw new IrresponsibleSenderWithDangerousGoods(utilizedContainers, sender_container, magazine, incrementedDate[0], date, Timer.days);
                    }
                } catch (IrresponsibleSenderWithDangerousGoods e) {
                    System.out.print("*Sender gets first warning and containers has been utilized.*\n");
                }

                if (Objects.equals(user, "list")) {
                    magazineCont.magazineList(magazine, Timer.days);
                }
                System.out.println("You are in the - main menu");

                if ((Objects.equals(user, "utilize"))) {
                    try {
                        for (int i = 0; i < magazine.size(); i++) {
                            System.out.println("(" + (i + 1) + ")" + magazine.get(i).toString());
                            int selectContainerToUtlize = in.nextInt();
                            selectContainerToUtlize = selectContainerToUtlize - 1;

                            incrementedDate[0] = addOneDay(date, Timer.days);
                            magazine.get(selectContainerToUtlize).dateOfDelete = Timer.days;
                            magazine.get(selectContainerToUtlize).dateOfDeleteToShow = incrementedDate[0];

                            System.out.println(
                                    "Container: " + magazine.get(selectContainerToUtlize).toString() + "\nhas been removed\nDate: " + magazine.get(selectContainerToUtlize).dateOfDeleteToShow
                            );
                            magazine.remove(selectContainerToUtlize);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("wrong number!\nlook at numbers in brackets.");

                    }
                }
                if ((Objects.equals(user, "state"))) {
                    System.out.println(magazine.size());
                }
                if ((Objects.equals(user, "load ship"))) {

                    ship.loadShip(shipList, magazine, true, 100);
                    System.out.println("you can load ship - magazine is empty");

                }
            }

            if(Objects.equals(user, "save")){
                save(shipList, containers, utilizedContainers, magazine, wagon, senders);
            }
        }

        deleteCacheCont(containers);
        deleteCacheShips(shipList);
        deleteCacheSenders(senders);


    }

    public static void loadDataFromFiles(List<Sender> senders, List<basicCont> containers, List<basicCont> magazine, List<ship> shipList) throws IOException {
        ArrayList<Sender> sendersReturFromFile= new ArrayList<>();
        sendersReturFromFile = Sender.loadSender("senders.txt");

        for (int i = 0; i < sendersReturFromFile.size(); i++) {
            senders.add(sendersReturFromFile.get(i));
        }

        ArrayList<basicCont> containersReturnFromFile = new ArrayList<>();
        containersReturnFromFile = basicCont.loadContainer("containers.txt",0,false);

        for (int i = 0; i < containersReturnFromFile.size(); i++) {
            containers.add(containersReturnFromFile.get(i));
        }

        ArrayList<basicCont> magazineReturnFromFile = new ArrayList<>();
        magazineReturnFromFile = basicCont.loadContainer("magazine.txt",100,true);

        for (int i = 0; i < magazineReturnFromFile.size(); i++) {
            magazine.add(magazineReturnFromFile.get(i));
        }

        ArrayList<ship> shipReturnFromFile = new ArrayList<>();
        shipReturnFromFile = ship.loadShipFromFile("ships.txt");

            for (int i = 0; i < shipReturnFromFile.size(); i++) {
                shipList.add(shipReturnFromFile.get(i));
            }

    }

    public static void deleteCacheCont(List<basicCont> list){
        for (int i = 0; i < list.size(); i++) {
            list.remove(list.get(i));
        }

    }

    public static void deleteCacheShips(List<ship> list){
        for (int i = 0; i < list.size(); i++) {
            list.remove(list.get(i));
        }
    }

    public static void deleteCacheSenders(List<Sender> list){
        for (int i = 0; i < list.size(); i++) {
            list.remove(list.get(i));
        }
    }

    public static String help() {
        return """
                ship - ships manager
                cont - containers manager
                magazine - magazine manager
                sender - senders manager
                save - save changes in the files
                clear - clear screen
                res - restore to initial state
              
                end - terminate the program
                """;

    }

    public static String startMsg(String date) {
        return """ 
                =====================================================
                  WELCOME IN MARITIME PORT MANAGEMENT APPLICATION
                                       Tymoteusz Krawiecki s25611
                Today is:""" + date +
                "\nhelp - to see all commands. \n" +
                "=====================================================";

    }

    public static String addOneDay(String date, int day) {
        return LocalDate.parse(date).plusDays(day).toString();
    }

    public static void save(ArrayList<ship> list1, ArrayList<basicCont> list2, ArrayList<basicCont> list3, ArrayList<basicCont> list4,ArrayList<basicCont> list5,ArrayList<Sender> list6) throws IOException {

        ship.saveShips(list1,list2);

        basicCont.saveContainers(list2,list3);

        magazineCont.saveMagazine(list4);

        File wagonSave = new File("wagon.txt");
        FileWriter wagon = new FileWriter(wagonSave);
        for (basicCont basicCont : list5) {
            wagon.write(basicCont.toString() + "\n");
        }
        wagon.close();

        Sender.saveSender(list6);
        System.out.println("changes have been saved in the files");

    }

    public static boolean numberIsAvaible(int selected, List<basicCont> list, int id) {
        if ((list.get(selected).placeId) == id) {
            return true;
        } else {
            return false;
        }
    }

    private static void clearScreen() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }

    }




}


//maksymalna liczba kontenerow podalczonych do sieci elektrycznej
//dodac pozostaly czas do utylizacji kontenera
//z magazynu na statek