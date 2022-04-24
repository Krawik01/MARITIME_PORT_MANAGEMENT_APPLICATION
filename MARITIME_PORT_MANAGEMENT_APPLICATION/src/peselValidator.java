//public class peselValidator {
//    public class PeselValidator {
//
//        private byte PESEL[] = new byte[11];
//        private boolean valid = false;
//
//        public PeselValidator(String PESELNumber) {
//            if (PESELNumber.length() != 11){
//                valid = false;
//            }
//            else {
//                for (int i = 0; i < 11; i++){
//                    PESEL[i] = Byte.parseByte(PESELNumber.substring(i, i+1));
//                }
//                if (checkSum() && checkMonth() && checkDay()) {
//                    valid = true;
//                }
//                else {
//                    valid = false;
//                }
//            }
//        }
//
//        public boolean isValid() {
//            return valid;
//        }
//}
