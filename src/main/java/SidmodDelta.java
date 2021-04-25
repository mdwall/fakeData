import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class SidmodDelta {

    public static void main(String[] args) throws Exception {
        //createLastNameUpdates();
        //createMerges();

        String inputFileName = "SIDMOD_TEST_dev_file_fake_20.txt";
        String outputFileName = "SIDMOD_TEST_dev_file_fake_20_delta.txt";

        int inputFileTotalRecords = 1000;
        int numUpdatesToCreate = 3;
        int numMergesToCreate = 3;

        createDeltaFile(inputFileName, outputFileName, numUpdatesToCreate, numMergesToCreate);
    }

    private static void createDeltaFile(String inputFileName, String outputFileName, int numUpdatesToCreate, int numMergesToCreate) throws Exception {
        BufferedReader reader;
        try {

            FileWriter fileWriter = new FileWriter(outputFileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            reader = new BufferedReader(new FileReader(inputFileName));


            //create merges
            for(int i=0; i < numMergesToCreate; i++) {
                String mergeIntoLineStr = reader.readLine();
                String mergeLineStr = reader.readLine();
                String mergeIntoLineSidmodId = mergeIntoLineStr.substring(0,7);
                String mergeLineSidmodId = mergeLineStr.substring(0,7);

                printWriter.println(mergeIntoRecord(mergeIntoLineStr, mergeLineSidmodId));
                printWriter.println(nullOutRecordForMerge(mergeLineStr, mergeIntoLineSidmodId));
            }

            //create updates
            for(int i=0; i < numUpdatesToCreate; i++) {
                printWriter.println(updatedRecord(reader.readLine()));
            }

            reader.close();
            printWriter.close();

        } catch (IIOException e) {
            e.printStackTrace();
        }
    }


    //change last name to the word "CHANGED"
    private static StringBuilder updatedRecord(String recordToUpdate) {
        StringBuilder updatedRecord = new StringBuilder(recordToUpdate);
        updatedRecord.replace(84,105, "CHANGED              ");
        return updatedRecord;
    }

    private static StringBuilder mergeIntoRecord(String mergeIntoRecordStr, String mergeSidmodId) {
        StringBuilder mergeIntoRecord = new StringBuilder(mergeIntoRecordStr);
        mergeIntoRecord.replace(8,15, mergeSidmodId);
        return mergeIntoRecord;
    }

    private static StringBuilder nullOutRecordForMerge(String recordToNull, String mergeIntoSidmodId) {
        StringBuilder nulledOutRecord = new StringBuilder(recordToNull);
        nulledOutRecord.replace(8,15, mergeIntoSidmodId);
        nulledOutRecord.replace(16,25, "000000000"); //SSN
        nulledOutRecord.replace(26,35, "         "); //Alien Number
        nulledOutRecord.replace(62,63, " "); //Gender
        nulledOutRecord.replace(64,68, "    "); //soundex last
        nulledOutRecord.replace(69,72, "   "); //soundex first
        nulledOutRecord.replace(73,81, "00000000"); //birthday
        nulledOutRecord.replace(82,83, " "); //birthday verified
        nulledOutRecord.replace(84,105, "                     "); //last name
        nulledOutRecord.replace(106,121, "               "); // first name
        nulledOutRecord.replace(122,132, "          "); // middle name
        nulledOutRecord.replace(133,137, "    "); //title
        nulledOutRecord.replace(138,139, " "); //ssn-verified
        nulledOutRecord.replace(140,148, "00000000"); //date of death
        return nulledOutRecord;
    }



}
