public
    class heavyCont extends basicCont{
    int id;
    int number = 20;


    public heavyCont(String type, String sender, int tare, int netWeight, int grossWeight, String security, String certificates, String content, int placeId, int dateOfLoad, String dateOfLoadToShow ,int maxAmountOfTime, int toDelete, int dateOfDelete,String dateOfDeleteToShow, int valueOfContainer) {
        super(type, sender, tare, netWeight, grossWeight, security, certificates,content, placeId,dateOfLoad,dateOfLoadToShow,maxAmountOfTime,toDelete,dateOfDelete,dateOfDeleteToShow,valueOfContainer);
        id = number;
        number++;
    }
}
