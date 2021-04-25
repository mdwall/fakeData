import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FakeDataApp {
    //static String sidmodFileName = "SIDMOD_TEST_dev_file_fake_5000000.txt";

    //static String sidmodFileName = "SIDMOD_HOURLY_FILE_20201026140015.txt";

    static String cbmsFileName = "CBMS_TEST_dev_file_fake_5000000.csv";
    //static String corhioFileName = "CORHIO_TEST_dev_file_fake_10.txt";

    static String sspsFileName = "SSPS_10_20210423.txt";

    static int numRecords = 10;

    public static void main(String[] args) throws IOException {
        SidmodData sidmodData = new SidmodData();
        NewCbmsData cbmsData = new NewCbmsData();
        CorhioReturnData corhioData = new CorhioReturnData();
        SourceSystemPersonData sspsData = new SourceSystemPersonData();


//        for(int i = 0; i<10;i++) {
//            System.out.println(sidmodData.getSidmodLine());
//        }

        SidmodIds sidmodIds = new SidmodIds(numRecords);

//        FileWriter fileWriter = new FileWriter(sidmodFileName);
//        PrintWriter printWriter = new PrintWriter(fileWriter);
//        for (int i = 0; i < numRecords; i++) {
//            //System.out.println(sidmodData.getSidmodLine());
//            printWriter.println(sidmodData.getSidmodLine(sidmodIds.getSidmodIdFromList(i)));
//        }
//        printWriter.close();

//        FileWriter fileWriter2 = new FileWriter(cbmsFileName);
//        PrintWriter printWriter2 = new PrintWriter(fileWriter2);
//        printWriter2.println(cbmsData.getCbmsHeader());
//        for (int i = 0; i < numRecords; i++) {
//            //System.out.println(sidmodData.getSidmodLine());
//            printWriter2.println(cbmsData.getCbmsLine(sidmodIds.getSidmodIdFromList(i)));
//        }
//        printWriter2.close();

        FileWriter fileWriter3 = new FileWriter(sspsFileName);
        PrintWriter printWriter3 = new PrintWriter(fileWriter3);
        printWriter3.println(sspsData.getHeader());
        for (int i = 0; i < numRecords; i++) {
            printWriter3.println(sspsData.getSourceSystemPersonLine());
        }
        printWriter3.close();
//
//
//
//        FileWriter fileWriter3 = new FileWriter(corhioFileName);
//        PrintWriter printWriter3 = new PrintWriter(fileWriter3);
//        printWriter3.println(corhioData.getCorhioHeader());
//        for (int i = 0; i < numRecords; i++) {
//            //System.out.println(sidmodData.getSidmodLine());
//            printWriter3.println(corhioData.getCorhioLine(sidmodIds.getSidmodIdFromList(i)));
//        }
//        printWriter3.close();


    }
}
