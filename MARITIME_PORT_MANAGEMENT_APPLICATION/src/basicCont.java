import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public
    class basicCont{

    int toDelete;
    String sender;
    String security;
    String certificates;
    String type;
    String content;
    int tare;
    int netWeight;
    int grossWeight;
    int id;
    private static int number = 20;
    int placeId;
    int dateOfLoad;
    String dateOfLoadToShow;
    int maxAmountOfTime;
    int dateOfDelete;
    String dateOfDeleteToShow;
    int valueOfContainer;


    public basicCont(String type, String sender, int tare, int netWeight, int grossWeight, String security, String certificates, String content, int placeId, int dateOfLoad, String dateOfLoadToShow, int maxAmountOfTime, int toDelete, int dateOfDelete,String dateOfDeleteToShow, int valueOfContainer) {
        this.sender = sender;
        this.tare = tare;
        this.security = security;
        this.netWeight = netWeight;
        this.grossWeight = grossWeight;
        this.certificates = certificates;
        this.type = type;
        this.content = content;
        this.placeId = placeId;
        this.dateOfLoad = dateOfLoad;
        this.dateOfLoadToShow = dateOfLoadToShow;

        this.maxAmountOfTime = maxAmountOfTime;
        this.toDelete = toDelete;
        this.dateOfDelete = dateOfDelete;
        this.dateOfDeleteToShow = dateOfDeleteToShow;
        this.valueOfContainer = valueOfContainer;

        id = ++number;


    }


    public static void addContainer(List<basicCont> containers, int placeId, int x, List<Sender> senders) {
        Scanner in = new Scanner(System.in);

        System.out.println("select sender: ");
        for (int i = 0; i < senders.size(); i++) {
            System.out.println("(" + (i + 1) + ")" + senders.get(i));
            if(senders.get(i).breach >= 2){
                System.out.println(" *sender not available due to too many policy violations*  ");
            }
        }
        int selectSender = in.nextInt();
        selectSender = selectSender - 1;

        try {
            if (senders.get(selectSender).breach >= 2) {
            throw new toManyWarningForSender();
        }
    } catch (toManyWarningForSender e) {
            System.out.println("you cannot select this container the sender is unavailable due to too many policy violations");
            addContainer(containers, placeId, x, senders);
        }

            System.out.println("tare(kg): ");
            int tare = in.nextInt();
            System.out.println("net weight(kg): ");
            int netWeight = in.nextInt();
            System.out.println("gross weight: ");
            int grossWeight = in.nextInt();
            System.out.println("security/");
            String security = in.nextLine();
            System.out.println("certificates: ");
            String certificates = in.nextLine();
            System.out.println("content: ");
            String content = in.nextLine();


            if (x == 1) {
                String type = "Basic";

                int dateOfLoadBasic = 0;

                int maxAmountOfTimeBasic = 10000000;

                containers.add(new basicCont(type,senders.get(selectSender).name, tare, netWeight, grossWeight, security, certificates, content, placeId, dateOfLoadBasic, "", maxAmountOfTimeBasic, 0, 0, "", 0));
            } else if (x == 2) {
                int dateOfLoadHeavy = 0;

                int maxAmountOfTimeHeavy = 100000;

                String type = "Heavy";

                containers.add(new heavyCont(type,senders.get(selectSender).name, tare, netWeight, grossWeight, security, certificates, content, placeId, dateOfLoadHeavy, "", maxAmountOfTimeHeavy, 1, 0, "", 0));
            } else if (x == 3) {
                int dateOfLoadCooling = 10000000;

                int maxAmountOfTimeCooling = 0;

                System.out.println("temperature: ");
                int temperature = in.nextInt();
                String type = "Cooling";

                containers.add(new coolingCont(type, senders.get(selectSender).name, tare, netWeight, grossWeight, security, certificates, content, placeId, dateOfLoadCooling, "", maxAmountOfTimeCooling, 0, 0, "", 0, temperature));
            } else if (x == 4) {
                int dateOfLoadLiquid = 1000000;

                int maxAmountOfTimeHeavy = 0;

                System.out.println("tightness(0-10): ");
                int tightness = in.nextInt();
                String type = "Liquid";

                containers.add(new liquidCont(type, senders.get(selectSender).name, tare, netWeight, grossWeight, security, certificates, content, placeId, dateOfLoadLiquid, "", maxAmountOfTimeHeavy, 0, 0, "", 0, tightness));
            } else if (x == 5) {
                System.out.println("durability(0-10): ");
                int durability = in.nextInt();

                int dateOfLoadExplosive = 0;


                int maxAmountOfTime = 5;
                String type = "Explosive";

                containers.add(new explosiveCont(type, senders.get(selectSender).name, tare, netWeight, grossWeight, security, certificates, content, placeId, dateOfLoadExplosive, "", maxAmountOfTime, 0, 0, "", 1, durability));
            } else if (x == 6) {
                System.out.println("covers: ");
                String covers = in.nextLine();

                int dateOfLoadToxicBulk = 0;

                int maxAmountOfTime = 14;

                String type = "ToxicBulk";

                containers.add(new toxicBulkCont(type, senders.get(selectSender).name, tare, netWeight, grossWeight, security, certificates, content, placeId, dateOfLoadToxicBulk, "", maxAmountOfTime, 0, 0, "", 1, covers));
            } else if (x == 7) {
                System.out.println("covers: ");
                int tightness = in.nextInt();

                int dateOfLoadToxicLiquid = 0;

                int maxAmountOfTime = 10;

                String type = "ToxicLiquid";

                containers.add(new toxicLiquidCont(type, senders.get(selectSender).name, tare, netWeight, grossWeight, security, certificates, content, placeId, dateOfLoadToxicLiquid, "", maxAmountOfTime, 0, 0, "", 1, tightness));
            }
        System.out.println("the container has been created");
        System.out.println("You are in the - main menu");
        }

    public static void addLaunchObjects(List<basicCont> containers,List<Sender> senders,List<ship> shipList){
        senders.add(new Sender("Michal", "Tulej", "983541275831", "Wiejska 3, Goleniow", 0));
        senders.add(new Sender("Maciej", "Bonkowski", "01280506118", "kwiatowa 2, Gdansk", 0));
        senders.add(new Sender("Krystain", "Leszczynski", "97312331235", "Powstancow 4, Gdynia", 0));
        senders.add(new Sender("Marek", "Towarek", "82583517632", "Lokietka 4a, Sopot", 0));


        containers.add(new coolingCont("Cooling",(senders.get(1).name), 319, 231, 5123, "stowage bags", "PN-EN ISO 9001:2015-10", "ice", 0, 0, "", 10000, 0, 0, "", 0, -2));
        containers.add(new basicCont("Basic",(senders.get(2).name), 732, 600, 31251, "airbags", "PN-EN ISO 14001:2015-09", "pencils", 0, 0, "", 10000, 0, 0, "", 0));
        containers.add(new heavyCont("Heavy",(senders.get(3).name), 231, 190, 83, "stowage bags", "AQAP 2110:2016", "car parts", 0, 0, "", 10000, 1, 0, "", 0));
        containers.add(new liquidCont("Liquid",(senders.get(1).name), 312, 311, 412, "stowage bags", "PN-EN ISO 14001:2015-09", "alcohol", 0, 0, "", 10000, 0, 0, "", 0, 8));
        containers.add(new coolingCont("Cooling",(senders.get(2).name), 671, 324, 3131, "airbags", "AQAP 2110:2016", "meat", 0, 0, "", 10000, 0, 0, "", 0, -1));
        containers.add(new explosiveCont("Explosive",(senders.get(0).name), 512, 312, 31221, "airbags", "PN-EN ISO 14001:2015-09", "fireworks", 2, 0, "", 5, 0, 0, "", 1, 4));

        shipList.add(new ship("sea eye", "Hel", "Alegracias", "Rotterdam", 250000));
        shipList.add(new ship("siren", "Gdansk", "Valencia", "Gdansk", 133111));
        shipList.add(new ship("bob", "Sopot", "Hamburg", "Antwerp", 20000));




    }

    public int getMass() {
        return grossWeight;
    }


    public static void showContainersList(List<basicCont> containers, int id) {
        int number = 1;

        for (int i = 0; i < containers.size(); i++) {
            if (containers.get(i).placeId == id) {
                System.out.println("(" + number + ")" + containers.get(i).toString());
            }
            number++;
        }
    }
    public static void saveContainers(ArrayList<basicCont> containers, ArrayList<basicCont> utilizedContainers) throws IOException {
        File containersSave = new File("containers.txt");
        FileWriter containersWriter = new FileWriter(containersSave);
        for (basicCont basicCont : containers) {
            containersWriter.write(basicCont.toStringtoFile() + "\n");
        }
        containersWriter.close();

        File utilizedContainersSave = new File("utilizedContainers.txt");
        FileWriter utilizedContainersWriter = new FileWriter(utilizedContainersSave);
        for (basicCont basicCont : utilizedContainers) {
            utilizedContainersWriter.write(basicCont.toStringtoFile() + "\n");
        }
        utilizedContainersWriter.write(utilizedContainers.toString());
        utilizedContainersWriter.close();
    }

    public static void showContainersListWithSenders(Map<basicCont, List<Sender>> sender_container) {

        for (Map.Entry<basicCont, List<Sender>> entry : sender_container.entrySet()) {
            System.out.println(entry.getKey() +" place id: "+ entry.getKey().placeId + " -> \n " + entry.getValue().toString().replaceAll("[\\[\\]]", "") + "\n");
        }
    }

    public static ArrayList<basicCont> loadContainer(String filename,int placeIdd,boolean magazine) throws IOException {

        File file = new File("containers.txt");
        Scanner s = new Scanner(file);


        ArrayList<basicCont> containersFromFile = new ArrayList<>();

        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] items = line.split("[|]");
            items[2] = "312";
            items[5] = "none";
            items[10] = "2022-05-16";
            items[14] = "0";

            //int placeid = Integer.parseInt(items[8]);
            if (magazine) {
                if (Integer.parseInt(items[8]) == placeIdd) {
                    if (Objects.equals(items[0], "Basic")) {
                        containersFromFile.add(new basicCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[15])));

                    }
                    if (Objects.equals(items[0], "Cooling")) {
                        containersFromFile.add(new coolingCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[16]), Integer.parseInt(items[16])));
                    }
                    if (Objects.equals(items[0], "Heavy")) {
                        containersFromFile.add(new heavyCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[15])));
                    }
                    if (Objects.equals(items[0], "Liquid")) {
                        containersFromFile.add(new liquidCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[16]), Integer.parseInt(items[16])));
                    }
                    if (Objects.equals(items[0], "Explosive")) {
                        containersFromFile.add(new explosiveCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8].trim()), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[16]), Integer.parseInt(items[16])));
                    }
                    if (Objects.equals(items[0], "ToxicBulk")) {
                        containersFromFile.add(new toxicBulkCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[16]), items[16]));
                    }
                    if (Objects.equals(items[0], "ToxicLiquid")) {
                        containersFromFile.add(new toxicLiquidCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[16]), Integer.parseInt(items[16])));
                    }

                }
            } else {
                if (Objects.equals(items[0], "Basic")) {
                    containersFromFile.add(new basicCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[15])));

                }
                if (Objects.equals(items[0], "Cooling")) {
                    containersFromFile.add(new coolingCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[16]), Integer.parseInt(items[16])));
                }
                if (Objects.equals(items[0], "Heavy")) {
                    containersFromFile.add(new heavyCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[15])));
                }
                if (Objects.equals(items[0], "Liquid")) {
                    containersFromFile.add(new liquidCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[16]), Integer.parseInt(items[16])));
                }
                if (Objects.equals(items[0], "Explosive")) {
                    containersFromFile.add(new explosiveCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8].trim()), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[16]), Integer.parseInt(items[16])));
                }
                if (Objects.equals(items[0], "ToxicBulk")) {
                    containersFromFile.add(new toxicBulkCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[16]), items[16]));
                }
                if (Objects.equals(items[0], "ToxicLiquid")) {
                    containersFromFile.add(new toxicLiquidCont(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5], items[6], items[7], Integer.parseInt(items[8]), Integer.parseInt(items[9]), items[10], Integer.parseInt(items[11]), Integer.parseInt(items[12]), Integer.parseInt(items[14]), items[15], Integer.parseInt(items[16]), Integer.parseInt(items[16])));
                }

            }
        }



        return containersFromFile;
    }


    public String toStringtoFile() {
        return type + '|' + sender + '|' + tare + '|' + netWeight +'|' + grossWeight +'|' + security +'|' + certificates +'|'+content + '|' + placeId + '|' + dateOfLoad + '|' + dateOfLoadToShow + '|' + maxAmountOfTime + '|' + toDelete + '|' +dateOfDelete + '|' + 0 + '|' + valueOfContainer;
    }

    @Override
    public String toString() {
        return "basicCont{" +
                "sender='" + sender + '\'' +
                ", security='" + security + '\'' +
                ", certificates='" + certificates + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", tare=" + tare +
                ", netWeight=" + netWeight +
                ", grossWeight=" + grossWeight +
                ", id=" + id +
                ", placeId=" + placeId +
                '}';
    }
}




