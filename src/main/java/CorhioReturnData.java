
/*
Corhio csv header:
"Sending_organization_name","Duplicate_Count","CORHIO_MPI_ID","PayerMemberID","Member_Last_Name","Member_First_Name","Member_Gender","Member_Date_of_Birth","Member_Address_1","Member_Address_2","Member_City","Member_State","Member_Zip_Code","Member_Phone_Number","Member_Social Security_Number","Member_County"
*/

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class CorhioReturnData {
    Set<String> corhioMpiIds;
    Faker faker = new Faker();
    DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("yyyyMMdd");


    public CorhioReturnData() {

        corhioMpiIds = new HashSet<>();
    }

    public String getCorhioHeader() {
        return "CORHIO_ID|Sending_organization_name|Payer_Member_ID_Number|Member_Last_Name|Member_First_Name|Member_Gender|Member_Date_of_Birth|Member_ID|Member_Address_1|Member_Address_2|Member_City|Member_State|Member_Zip_Code|Member_Phone_Number|Member_Social Security_Number";
    }

    public String getCorhioLine(String sidmodId) {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s",
                getCorhioId(),
                "OIT",
                "", //Payer_Member_ID_Number
                getLastNm(),
                getFirstNm(),
                getGender(),
                getDob(),
                sidmodId,
                getHomeAddrLn1(),
                getHomeAddrLn2(),
                getCty(),
                getStaCd(),
                getZip(),
                getPhone(),
                getSsn());
    }


    protected String getCorhioId() {

        return RandomStringUtils.randomAlphanumeric(24);
    }


    protected String getFirstNm() {
        return faker.name().firstName();
    }

    protected String getLastNm() {
        return faker.name().lastName();
    }

    protected String getDob() {
        return dateFormat.format(faker.date()
                .birthday(18,90)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    protected String getGender() {
        String sex = "F";
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(2);
        //System.out.println(randomInt);
        if (randomInt == 1) {
            sex = "M";
        }
        return sex;
    }


    protected String getHomeAddrLn1() {
        return faker.address().streetAddress();
    }

    protected String getHomeAddrLn2() {
        return "";
    }

    protected String getCty() {
        return faker.address().city();
    }

    protected String getStaCd() {
        return faker.address().stateAbbr();
    }

    protected String getZip() {
        return faker.address().zipCode().substring(0,5);
    }

    protected String getPhone() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    protected String getSsn() {
        return faker.number().digits(9);
        //return faker.idNumber().ssnValid().replace("-", "");
    }

    protected String getCounty() {

        return "Arapahoe";


    }

}


