public class explosiveCont extends heavyCont{

    int id;
    int number = 3000;
    int toDelete = 0;



    int durability;

    public explosiveCont(String type, String sender, int tare, int netWeight, int grossWeight, String security, String certificates, String content, int placeId, int dateOfLoad, String dateOfLoadToShow, int maxAmountOfTime, int toDelete, int dateOfDelete,String dateOfDeleteToShow, int valueOfContainer,int durability) {
        super(type,sender, tare, netWeight, grossWeight, security, certificates,content, placeId,dateOfLoad,dateOfLoadToShow,maxAmountOfTime,toDelete, dateOfDelete,dateOfDeleteToShow, valueOfContainer);
        this.durability = durability;
        this.toDelete = toDelete;

        id = number;
        number++;
    }

    @Override
    public String toString() {
        return "Explosive Container {" +
                "sender='" + sender + '\'' +
                ", security='" + security + '\'' +
                ", certificates='" + certificates + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", tare=" + tare +
                ", netWeight=" + netWeight +
                ", grossWeight=" + grossWeight +
                ", id=" + id +", durability=" + durability +
                '}';
    }
    @Override
    public String toStringtoFile() {
       return type + '|' + sender + '|' + tare + '|' + netWeight + '|' + grossWeight + '|' + security + '|' + certificates + '|' + content + '|' + placeId + '|' + dateOfLoad + '|' + dateOfLoadToShow + '|' + maxAmountOfTime + '|' + toDelete + '|' + dateOfDelete + '|' + dateOfDeleteToShow + '|' + valueOfContainer + '|' + durability;
    }

}
