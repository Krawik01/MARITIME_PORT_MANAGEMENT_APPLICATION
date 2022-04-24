import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public
    class magazineCont {

    int maxCapacity;
    int dateOfload;


    public magazineCont(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    static void magazineList(List<basicCont> magazine, int day) {

        if (magazine.size() > 0) {
            System.out.println("List: ");
            for (basicCont basicCont : magazine) {
                if (Objects.equals(basicCont.type, "Explosive") || Objects.equals(basicCont.type, "ToxicBulk") || Objects.equals(basicCont.type, "ToxicLiquid")) {
                    System.out.println(basicCont.toString() + "\ndate of load: *" + basicCont.dateOfLoadToShow + "*, remaining time: " + (basicCont.maxAmountOfTime - (day - basicCont.dateOfLoad)));

                } else {
                    System.out.println(basicCont.toString() + "\ndate of load: *" + basicCont.dateOfLoadToShow + "*");

                }
            }
        } else {
            System.out.println("\nmagazine is empty.");
        }

    }

    public static void saveMagazine(ArrayList<basicCont> magazine) throws IOException {

        File magazineSave = new File("magazine.txt");
        FileWriter magazineWriter = new FileWriter(magazineSave);

        int[] magazineSortedTab = new int[magazine.size()];
        String[] names = new String[magazineSortedTab.length];
        ArrayList<basicCont> magazineSortedList = new ArrayList<>();

        for (int i = 0; i < magazineSortedTab.length; i++) {
            magazineSortedTab[i] = magazine.get(i).dateOfLoad;
        }
        Arrays.sort(magazineSortedTab);

        for (int i = 0; i < magazineSortedTab.length; i++) {
            if (magazineSortedTab[i] == magazine.get(i).dateOfLoad)
                magazineSortedList.add(magazine.get(i));
        }

//        for (int i = 0; i < magazineSortedList.size(); i++) {
//            for (int j = 0; j < magazineSortedList.size(); j++) {
//                if (magazineSortedList.get(i).dateOfLoad == magazineSortedList.get(j).dateOfLoad) {
//                    names[0] = magazineSortedList.get(i).sender;
//                    names[1] = magazineSortedList.get(j).sender;
//                    Arrays.sort(names);
//                    for (int k = 0; k < magazineSortedList.size(); k++) {
//                        if ((magazineSortedList.get(k).sender == names[0]) && (magazineSortedList.get(k).dateOfLoad == magazineSortedList.get(i).dateOfLoad)) {
//                            magazineSortedList.add(i, magazineSortedList.get(k));
//                            magazineSortedList.add(i + 1, magazineSortedList.get(k));
//                        }
//                        if ((magazineSortedList.get(k).sender == names[1]) && (magazineSortedList.get(k).dateOfLoad == magazineSortedList.get(i).dateOfLoad)) {
//                            magazineSortedList.add(i, magazineSortedList.get(k));
//                            magazineSortedList.add(i + 1, magazineSortedList.get(k));
//                        }
//                    }
//                }
//            }
//        }
        for (int k = 0; k < magazineSortedList.size(); k++) {
        magazineWriter.write(magazineSortedList.get(k).toString() +"\n");
        }
        magazineWriter.close();
    }



}


