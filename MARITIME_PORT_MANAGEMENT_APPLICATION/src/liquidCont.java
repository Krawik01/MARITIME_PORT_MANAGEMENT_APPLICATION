public class liquidCont extends basicCont {

    int id;
    int number = 20;
    int tightness;
    int dateOfDelete;


    public liquidCont(String type, String sender, int tare, int netWeight, int grossWeight, String security, String certificates, String content, int placeId, int dateOfLoad, String dateOfLoadToShow, int maxAmountOfTime, int toDelete, int dateOfDelete, String dateOfDeleteToShow, int valueOfContainer, int tightness) {
        super(type, sender, tare, netWeight, grossWeight, security, certificates, content, placeId, dateOfLoad, dateOfLoadToShow, maxAmountOfTime, toDelete, dateOfDelete, dateOfDeleteToShow, valueOfContainer);
        this.tightness = tightness;

        id = number;
        number++;
    }


    @Override
    public String toString() {
        return "Liquid Container {" +
                "sender='" + sender + '\'' +
                ", security='" + security + '\'' +
                ", certificates='" + certificates + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", tare=" + tare +
                ", netWeight=" + netWeight +
                ", grossWeight=" + grossWeight +
                ", id=" + id + ", tightness=" + tightness +
                '}';
    }

    @Override
    public String toStringtoFile() {
        return type + '|' + sender + '|' + tare + '|' + netWeight + '|' + grossWeight + '|' + security + '|' + certificates + '|' + content + '|' + placeId + '|' + dateOfLoad + '|' + dateOfLoadToShow + '|' + maxAmountOfTime + '|' + toDelete + '|' + dateOfDelete + '|' + dateOfDeleteToShow + '|' + valueOfContainer + '|' + tightness;

    }
}
