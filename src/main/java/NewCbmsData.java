
/*
CBMS csv header:
First Name,Last Name,Middle Name,Name Suffix,GENDER,DOB,SSN,ALIEN_NUM,Date of Death,Address 1,Address 2,CITY,STATE,ZIP,Phone Mobile,Phone Home,EMAIL,SIDMODID,Source ID
*/

import com.github.javafaker.Faker;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class NewCbmsData {
    Set<String> cwins;
    Faker faker = new Faker();
    DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public NewCbmsData() {
        cwins = new HashSet<>();
    }

    public String getCbmsHeader() {
        return "First Name,Last Name,Middle Name,Name Suffix,GENDER,DOB,SSN,ALIEN_NUM,Date of Death,Address 1,Address 2,CITY,STATE,ZIP,Phone Mobile,Phone Home,EMAIL,SIDMODID,Source ID";
    }

    public String getCbmsLine(String cinId) {
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                getFirstNm(),
                getLastNm(),
                getMi(),
                getNameSuffix(),
                getGender(),
                getDob(),
                getSsn(),
                getAlienNumber(),
                "", //Date of Death
                getHomeAddrLn1(),
                getHomeAddrLn2(),
                getCty(),
                getStaCd(),
                getZip(),
                getPhone(), //mobile phone
                getPhone(), //home phone
                faker.internet().safeEmailAddress(),
                cinId,
                getCwin());
    }

//    protected Integer getCwin() {
//        Integer cwin = 0;
//        do {
//            cwin = Integer.valueOf(faker.number().digits(7));
//        } while (cwins.add(cwin)==false);
//
//        return cwin;
//    }

    // sourceid
    protected String getCwin() {
        String cwin = "0000000";
        do {
            cwin = faker.number().digits(7);
        } while (cwins.add(cwin)==false);

        return cwin;
    }

    protected String getCin() {
        return "";
    }

    protected String getFirstNm() {
        return faker.name().firstName();
    }

    protected String getLastNm() {
        return faker.name().lastName();
    }

    protected String getMi() {
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        return Character.toString(c).toUpperCase();
    }

    protected String getNameSuffix() {
        return "";
    }

    protected String getDob() {
        return dateFormat.format(faker.date()
                .birthday(18,90)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    protected String getGender() {
        String sex = "Female";
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(2);
        //System.out.println(randomInt);
        if (randomInt == 1) {
            sex = "Male";
        }
        return sex;
    }

    protected String getAdrTypCd() {
        return "";
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

    protected String getSsn() {
        return faker.number().digits(9);
        //return faker.idNumber().ssnValid().replace("-", "");
    }

    protected String getAlienNumber() {
        String id = faker.number().digits(8);
        return "A".concat(id);
    }

    protected String getPhone() {
        return faker.phoneNumber().subscriberNumber(10);
    }
}
