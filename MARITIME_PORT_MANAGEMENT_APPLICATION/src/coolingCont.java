public class coolingCont extends heavyCont {
    //rodzaj kontenera ciezkiego wymagajacy podlaczenia do sieci elektrycznej statku.
    int id;
    int number = 30;
    int temperature;


    public coolingCont(String type, String sender, int tare, int netWeight, int grossWeight, String security, String certificates, String content, int placeId, int dateOfLoad, String dateOfLoadToShow, int maxAmountOfTime, int toDelete, int dateOfDelete, String dateOfDeleteToShow, int valueOfContainer, int temperature) {
        super(type, sender, tare, netWeight, grossWeight, security, certificates, content, placeId, dateOfLoad, dateOfLoadToShow, maxAmountOfTime, toDelete, dateOfDelete, dateOfDeleteToShow, valueOfContainer);
        this.temperature = temperature;

        id = number;
        number++;
    }

    @Override
    public String toString() {
        return "Cooling Container {" +
                "sender='" + sender + '\'' +
                ", security='" + security + '\'' +
                ", certificates='" + certificates + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", tare=" + tare +
                ", netWeight=" + netWeight +
                ", grossWeight=" + grossWeight +
                ", id=" + id +
                ", temperature=" + temperature +
                '}';

    }

    @Override
    public String toStringtoFile() {
        return type + '|' + sender + '|' + tare + '|' + netWeight + '|' + grossWeight + '|' + security + '|' + certificates + '|' + content + '|' + placeId + '|' + dateOfLoad + '|' + dateOfLoadToShow + '|' +
                maxAmountOfTime + '|' + toDelete + '|' + dateOfDelete + '|' + dateOfLoadToShow + '|' + valueOfContainer + '|' + temperature;
    }
}
