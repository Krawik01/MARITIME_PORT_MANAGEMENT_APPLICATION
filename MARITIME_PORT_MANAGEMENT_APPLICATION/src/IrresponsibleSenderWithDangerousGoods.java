import java.util.List;
import java.util.Map;

public class IrresponsibleSenderWithDangerousGoods extends Exception{

    public IrresponsibleSenderWithDangerousGoods(List<basicCont> utilizedContainers, Map<basicCont,List<Sender>> sender_container,List<basicCont> magazine,String incrementedDate, String date,int day) {

        for (int i = 0; i < utilizedContainers.size(); i++) {

            System.out.println(utilizedContainers.get(i).toString() + "\n-date of load/delete: *"+utilizedContainers.get(i).dateOfLoadToShow +"*/*" + utilizedContainers.get(i).dateOfDeleteToShow );


        }

    }
}

//        for (int i = 0; i < utilizedContainers.size(); i++) {
//            utilizedContainers.get(i).dateOfDelete = dateOfDelete;
//            System.out.println(utilizedContainers.get(i).toString()+", Date of delete: " + utilizedContainers.get(i).dateOfDelete);
//            magazine.get(i).toDelete = 2;
//        }
//super("container removed due to too much time in storage: \n");

// magazine.remove(select);