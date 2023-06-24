import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

/*


 */


public class SSPFileDataUniqueSidmod {
    private static final String UPDATE_TYPE = "";
    private static final String SOURCE_SYSTEM_NAME = "TS";
    private String TRANSMISSION_ID;
    private static final String STATUS = "NEW";
    private static final String SOURCE_SYS_CREATE_DATE_LOW = "2001-01-01";
    private static final String SOURCE_SYS_CREATE_DATE_HIGH = "2015-12-31";
    private static final String SOURCE_SYS_UPDATE_DATE_LOW = "2016-01-01";
    private static final String SOURCE_SYS_UPDATE_DATE_HIGH = "2020-12-31";
    private Integer sourceSystemId = 2000000;
    private String sidmodId = "B0000000";

    Faker faker = new Faker();
    DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
    DateTimeFormatter dateTimeFormat =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //DateTimeFormatter dateTimeFormat =  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); // for SSP API load test file.

    //DateTimeFormatter dateTimeFormat =  DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public SSPFileDataUniqueSidmod(String transmissionId) {
        this.TRANSMISSION_ID = transmissionId;
    }


    public String getHeader() {
        return "SOURCE_SYSTEM_ID|FIRST_NAME|LAST_NAME|MIDDLE_NAME|NAME_SUFFIX|GENDER|DOB|SSN|ALIEN_NUMBER|DATE_OF_DEATH|ADDRESS_1|ADDRESS_2|CITY|STATE|ZIP|PHONE_PRIMARY|PHONE_SECONDARY|EMAIL|SIDMOD_ID|SOURCE_SYSTEM_CREATE_DATE|SOURCE_SYSTEM_UPDATE_DATE|SOURCE_SYSTEM_UPDATE_TYPE";
    }


    public String getSourceSystemPersonLine() {
        this.incrementSidmodId();
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s",

                getSourceSystemId(),
                //getSourceSystemName(),
                getFirstName(),
                getLastName(),
                getMiddleName(),
                getNameSuffix(),
                getGender(),
                getDob(),
                getSsn(),
                getAlienNumber(),
                getDateOfDeath(),
                getAddress1(),
                getAddress2(),
                getCity(),
                getState(),
                getZip(),
                getPhonePrimary(),
                getPhoneSecondary(),
                getEmail(),
                getSidmodId(),
                getSourceSystemCreateDate(),
                getSourceSystemUpdateDate(),
                //getEffectiveStartDate(),
                //getTransmissionId(),
                getUpdateType());


    }
    /**
     * Generate sequential SidmodIds
     * @return
     */
    protected void incrementSidmodId() {
        char sidmodLetter = sidmodId.charAt(0);  //  sidmodId.substring(0, 0);
        Integer sidmodNum = Integer.parseInt(sidmodId.substring(1, 7));
        if (sidmodNum < 999999) {
            sidmodNum++;
        } else {
            sidmodNum = 0;
            if(sidmodLetter < 'Z') {
                sidmodLetter++;
            } else {
                sidmodLetter = 'A';
            }
        }
        this.sidmodId = Character.toString(sidmodLetter).concat(String.format("%06d", sidmodNum));
    }

    protected String getUpdateType() {
        String updateType = "";
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(4);
        //System.out.println(randomInt);
        if (randomInt == 1) {
            updateType = "A";
        } else if (randomInt == 2) {
            updateType = "U";
        } else if (randomInt == 3) {
            updateType = "R";
        }
        //return updateType;
        return "";
    }

    protected String getSourceSystemId() {
        // sourceSystemId++;
        // return sourceSystemId.toString();
        return sidmodId;
    }

    protected String getSourceSystemName() {
        return SOURCE_SYSTEM_NAME;
    }



    protected String getSidmodId() {
        return sidmodId;

    }

    protected String getFirstName() {
        return faker.name().firstName().toUpperCase();
    }

    protected String getLastName() {
        return faker.name().lastName().toUpperCase();
    }

    protected String getMiddleName() {
        return valueOrBlank(faker.name().firstName().toUpperCase());
    }

    protected String getNameSuffix() {
        String suffix = "";
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(2);
        //System.out.println(randomInt);
        if (randomInt == 0) {
            suffix = "II";
        } else if (randomInt == 1) {
            suffix = "III";
        }
        return valueOrBlank(suffix);
    }

    protected String getGender() {
        String gender = "X";
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(4);
        //System.out.println(randomInt);
        if (randomInt == 1) {
            gender = "U";
        } else if (randomInt == 2) {
            gender = "F";
        } else if (randomInt == 3) {
            gender = "M";
        }
        return gender;
    }

    protected String getDob() {
        return dateFormat.format(faker.date()
                .birthday(18,90)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    protected String getSsn() {

        return valueOrBlank(faker.number().digits(9));
        //return "111111111";
    }

    protected String getAlienNumber() {
        String id = faker.number().digits(8);
        return valueOrBlank("A".concat(id));
    }

    protected String getDateOfDeath() {
        LocalDate localDate = LocalDate.now();
        return valueOrBlank(dateFormat.format(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
    }

    protected String getAddress1() {
        return valueOrBlank(faker.address().streetAddress());
    }

    protected String getAddress2() {
        return valueOrBlank(faker.address().buildingNumber());
    }

    protected String getCity() {
        return valueOrBlank(faker.address().city());
    }

    protected String getState() {
        return valueOrBlank(faker.address().stateAbbr());
    }

    protected String getZip() {
        return faker.address().zipCode().substring(0,5);
    }

    protected String getPhonePrimary() {
        return valueOrBlank(faker.phoneNumber().subscriberNumber(10));
    }

    protected String getPhoneSecondary() {
        return valueOrBlank(faker.phoneNumber().subscriberNumber(10));
    }

    protected String getEmail() {
        return valueOrBlank(faker.internet().emailAddress());
    }

    protected String getSourceSystemCreateDate() {
        LocalDate low = LocalDate.parse(SOURCE_SYS_CREATE_DATE_LOW, dateFormat);

        Date lowDate = Date.from(low.atStartOfDay(ZoneId.systemDefault()).toInstant());

        LocalDate high = LocalDate.parse(SOURCE_SYS_CREATE_DATE_HIGH, dateFormat);

        Date highDate = Date.from(high.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return valueOrBlank(dateTimeFormat.format(faker.date().between(lowDate, highDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
    }

    protected String getSourceSystemUpdateDate() {
        LocalDate low = LocalDate.parse(SOURCE_SYS_UPDATE_DATE_LOW, dateFormat);

        Date lowDate = Date.from(low.atStartOfDay(ZoneId.systemDefault()).toInstant());

        LocalDate high = LocalDate.parse(SOURCE_SYS_UPDATE_DATE_HIGH, dateFormat);

        Date highDate = Date.from(high.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return valueOrBlank(dateTimeFormat.format(faker.date().between(lowDate, highDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
    }

    protected String getEffectiveStartDate() {
        LocalDate localDate = LocalDate.now();
        return dateTimeFormat.format(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    protected String getTransmissionId() {
        return TRANSMISSION_ID;
    }

    protected String getStatus() {
        return STATUS;
    }

//    private String valueOrBlank(String value) {
//        String valOrBlank = "";

//        Random random = new Random();
//        int randomInt = random.nextInt(2);

//        if (randomInt == 1) {
//            valOrBlank = value;
//        }

//        return valOrBlank;
//    }

    private String valueOrBlank(String value) {
        // Wanted a file with no blanks

        return value;
    }
}
