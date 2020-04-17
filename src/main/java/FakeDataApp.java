import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FakeDataApp {
    static String sidmodFileName = "SIDMOD_TEST_dev_file_fake_1000.txt";
    static String cbmsFileName = "CBMS_TEST_dev_file_fake_1000.csv";
    static int numRecords = 1000;

    public static void main(String[] args) throws IOException {
        SidmodData sidmodData = new SidmodData();
        CbmsData cbmsData = new CbmsData();

//        for(int i = 0; i<10;i++) {
//            System.out.println(sidmodData.getSidmodLine());
//        }

        SidmodIds sidmodIds = new SidmodIds(numRecords);

        FileWriter fileWriter = new FileWriter(sidmodFileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < numRecords; i++) {
            //System.out.println(sidmodData.getSidmodLine());
            printWriter.println(sidmodData.getSidmodLine(sidmodIds.getSidmodIdFromList(i)));
        }
        printWriter.close();

        FileWriter fileWriter2 = new FileWriter(cbmsFileName);
        PrintWriter printWriter2 = new PrintWriter(fileWriter2);
        printWriter2.println(cbmsData.getCbmsHeader());
        for (int i = 0; i < numRecords; i++) {
            //System.out.println(sidmodData.getSidmodLine());
            printWriter2.println(cbmsData.getCbmsLine(sidmodIds.getSidmodIdFromList(i)));
        }
        printWriter2.close();
    }
}
