import com.github.javafaker.Faker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

/*
update_type|source_system_id|source_system_name|sidmod_id|first_name|last_name|middle_name|name_suffix|gender|dob|ssn|alien_number|date_of_death|address_1|address_2|city|state|zip|phone_primary|phone_secondary|email|source_system_create_date|source_system_update_date|transmission_id|status
A|1234|TS||PERSON1|ADD|TO|I|M|1972-06-04|123456789|987654321||12345 TEST ST|UNIT 5|DENVER|CO|80202|551231234|553214321|TEST@COMPUSERVE.NET|2021-04-08|2021-04-09|TEST_DATA_20210410.txt|PROCESSING
A|2234|TS||PERSON2|ADD|TO|II|M|1972-06-04|123456789|987654321||12345 TEST ST|UNIT 5|DENVER|CO|80202|551231234|553214321|TEST@COMPUSERVE.NET|2021-04-08|2021-04-09|TEST_DATA_20210410.txt|PROCESSING
A|3234|TS||DONT|ADD||I|M|1972-06-04|123456789|987654321||12345 TEST ST|UNIT 5|DENVER|CO|80202|551231234|553214321|TEST@COMPUSERVE.NET|2021-04-08|2021-04-09|DIFFERENT_20210410.txt|DONE


 */



public class SourceSystemPersonData {
    private static final String UPDATE_TYPE = "A";
    private static final String SOURCE_SYSTEM_NAME = "TS";
    private static final String TRANSMISSION_ID = "SSPS_10_20210423.txt";
    private static final String STATUS = "NEW";
    private static final String SOURCE_SYS_CREATE_DATE_LOW = "2001-01-01";
    private static final String SOURCE_SYS_CREATE_DATE_HIGH = "2015-12-31";
    private static final String SOURCE_SYS_UPDATE_DATE_LOW = "2016-01-01";
    private static final String SOURCE_SYS_UPDATE_DATE_HIGH = "2020-12-31";
    private Integer sourceSystemId = 1;

    Faker faker = new Faker();
    DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
    DateTimeFormatter dateTimeFormat =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



    public String getHeader() {
        return "update_type|source_system_id|source_system_name|sidmod_id|first_name|last_name|middle_name|name_suffix|gender|dob|ssn|alien_number|date_of_death|address_1|address_2|city|state|zip|phone_primary|phone_secondary|email|source_system_create_date|source_system_update_date|effective_start_date|transmission_id|status";
    }


    public String getSourceSystemPersonLine() {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s",
                getUpdateType(),
                getSourceSystemId(),
                getSourceSystemName(),
                getSidmodId(),
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
                getSourceSystemCreateDate(),
                getSourceSystemUpdateDate(),
                getEffectiveStartDate(),
                getTransmissionId(),
                getStatus());

    }



    protected String getUpdateType() {
        return UPDATE_TYPE;
    }

    protected String getSourceSystemId() {
        sourceSystemId++;
        return sourceSystemId.toString();
    }

    protected String getSourceSystemName() {
        return SOURCE_SYSTEM_NAME;
    }

    protected String getSidmodId() {
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        String id = faker.number().digits(6);
        return valueOrBlank(Character.toString(c).concat(id).toUpperCase());
    }

    protected String getFirstName() {
        return valueOrBlank(faker.name().firstName().toUpperCase());
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
        int randomInt = randomGenerator.nextInt(3);
        //System.out.println(randomInt);
        if (randomInt == 1) {
            suffix = "II";
        } else if (randomInt == 2) {
            suffix = "III";
        }
        return suffix;
    }

    protected String getGender() {
        String gender = "O";
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

    private String valueOrBlank(String value) {
        String valOrBlank = "";

        Random random = new Random();
        int randomInt = random.nextInt(2);

        if (randomInt == 1) {
            valOrBlank = value;
        }

        return valOrBlank;
    }

}
