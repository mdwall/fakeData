
/*
CBMS csv header:
"CWIN","CIN","FIRST_NM","LAST_NM","MI","DOB","GENDER","ADR_TYP_CD","HOME_ADDR_L1","HOME_ADDR_L2","CTY","STA_CD","ZIP"
*/

import com.github.javafaker.Faker;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class CbmsData {
    Set<String> cwins;
    Faker faker = new Faker();
    DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public CbmsData() {
        cwins = new HashSet<>();
    }

    public String getCbmsHeader() {
        return "\"CWIN\",\"CIN\",\"FIRST_NM\",\"LAST_NM\",\"MI\",\"DOB\",\"GENDER\",\"ADR_TYP_CD\",\"HOME_ADDR_L1\",\"HOME_ADDR_L2\",\"CTY\",\"STA_CD\",\"ZIP\"";
    }

    public String getCbmsLine(String cinId) {
        return String.format("%s,\"%s  \",\"%s\",\"%s\",\"%s\",%s,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                getCwin(),
                cinId,
                getFirstNm(),
                getLastNm(),
                getMi(),
                getDob(),
                getGender(),
                getAdrTypCd(),
                getHomeAddrLn1(),
                getHomeAddrLn2(),
                getCty(),
                getStaCd(),
                getZip());
    }

//    protected Integer getCwin() {
//        Integer cwin = 0;
//        do {
//            cwin = Integer.valueOf(faker.number().digits(7));
//        } while (cwins.add(cwin)==false);
//
//        return cwin;
//    }

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

    protected String getDob() {
        return dateFormat.format(faker.date()
                .birthday(18,90)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).concat(" 00.00.00");
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
}
