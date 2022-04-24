import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public
    class Sender {

    String name;
    String surname;
    String adres;
    private String pesel;
    public int breach;


//    public Sender(String name, String surnamem, int pesel

    public Sender(String name, String surname, String pesel,String adres,int breach){
        this.name = name;
        this.surname = surname;
        this.breach = breach;

        this.pesel = pesel;
        this.adres = adres;
    }

    public void getBirthDate() {
        char[] numbersOfPesel = new char[11];


        String str = pesel;
        for (int i = 0; i < 11; i++) {
            char number = str.charAt(i);
            numbersOfPesel[i] = number;
        }

        String year = "";

        if ((numbersOfPesel[2] == '8') || (numbersOfPesel[2] == '9')) {
            year = "18" + numbersOfPesel[0] + numbersOfPesel[1];

        } else if ((numbersOfPesel[2] == '0') || (numbersOfPesel[2] == '1')) {
            year = "19" + numbersOfPesel[0] + numbersOfPesel[1];

        } else if ((numbersOfPesel[2] == '2') || (numbersOfPesel[2] == '3')) {
            year = "20" + numbersOfPesel[0] + numbersOfPesel[1];

        }
        //01280506118
        String mouth = "";
        if (numbersOfPesel[2] == '8' || numbersOfPesel[2] == '0' || numbersOfPesel[2] == '2') {
            mouth = "0" + numbersOfPesel[3];
        } else {
            mouth = "1" + numbersOfPesel[3];
        }
        String day = numbersOfPesel[4] + "" + numbersOfPesel[5];

        System.out.print(day+'-'+ mouth+'-'+year);

//        LocalDate date = LocalDate.parse(pesel);
//        String text = date.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
//        LocalDate parsedDate = LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-YYYY"));
//        System.out.println(parsedDate);

    }


    public static ArrayList<Sender> loadSender(String filename) throws IOException {

        File file = new File("senders.txt");
        Scanner s = new Scanner(file);

        ArrayList<Sender> sendersFromFile = new ArrayList<Sender>();
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] items = line.split("\\|");
           // System.out.print(items[0] + ", " + items[1] + ", " + items[2] + ", " + items[3]);

            sendersFromFile.add(new Sender(items[0], items[1], items[2], items[3], 0));


        }
        return sendersFromFile;
        }
        

    public static void saveSender(ArrayList<Sender> senders) throws IOException {
        File sandersSave = new File("senders.txt");
        FileWriter sendersWriter = new FileWriter(sandersSave);
        for (Sender sender : senders) {
            sendersWriter.write(sender.toStringToFile() + "\n");
        }

        sendersWriter.close();
    }

    public String toStringToFile() {
        return name + "|" + surname +"|" + pesel + "|" + adres ;
    }
    @Override
    public String toString() {
        return  "Sender{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", adres='" + adres + '\'' +
                '}';


    }
}

