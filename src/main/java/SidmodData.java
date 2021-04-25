import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/*
Example row of fake data - no header
D806087|       |504838007|A05890116|FAKEUID |20150825|0553351|F|SNDL|SNDF|19491030| |WINDLER              |DEVON     |CLIFTON   |    |7|00000000|

#%RAML 1.0 DataType
type: object
properties:
  STATE-ID:
    type: string
  CURRENT-STATE-ID:
    type: string
  SOCIAL-SECURITY-NUMBER:
    type: string
  ALIEN-REGISTRATION-NUMBER:
    type: string
  USER-ID:
    type: string
  CREATION-DATE:
    type: string
  CREATION-TIME:
    type: string
  SEX:
    type: string
  SOUNDEX-LAST-NAME:
    type: string
  SOUNDEX-FIRST-NAME:
    type: string
  BIRTHDATE:
    type: string
  BIRTHDATE-VERIFIED:
    type: string
  LAST-NAME:
    type: string
  FIRST-NAME:
    type: string
  MIDDLE-NAME:
    type: string
  TITLE:
    type: string
  SSN-VERIFIED:
    type: string
  DATE-OF-DEATH:
    type: string
  SIDMOD-CHANGE:
    type: string
 */
public class SidmodData {
    Faker faker = new Faker();
    DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("yyyyMMdd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

    public String getSidmodLine() {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|",
                getStateId(),
                getCurrentStateId(),
                getSsn(),
                getAlienNumber(),
                getUserId(),
                getCreateDate(),
                getTime(),
                getSex(),
                getSndLast(),
                getSndFirst(),
                getBirthdate(),
                getBirthdateVerified(),
                getLastName(),
                getFirstName(),
                getMiddleName(),
                getTitle(),
                getSsnVerified(),
                getDateOfDeath());
    }

    public String getSidmodLine(String sidmodIdOverride) {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|",
                sidmodIdOverride,
                getCurrentStateId(),
                getSsn(),
                getAlienNumber(),
                getUserId(),
                getCreateDate(),
                getTime(),
                getSex(),
                getSndLast(),
                getSndFirst(),
                getBirthdate(),
                getBirthdateVerified(),
                getLastName(),
                getFirstName(),
                getMiddleName(),
                getTitle(),
                getSsnVerified(),
                getDateOfDeath());
    }

    protected String getStateId() {
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        String id = faker.number().digits(6);
        return Character.toString(c).concat(id).toUpperCase();
    }

    protected String getCurrentStateId() {
        return "       ";
    }

    protected String getSsn() {
        return faker.number().digits(9);
        //return faker.idNumber().ssnValid().replace("-", "");
    }

    protected String getAlienNumber() {
        String id = faker.number().digits(8);
        return "A".concat(id);
    }

    protected String getUserId() {
        return "FAKEUID ";
    }

    protected String getCreateDate() {
        return dateFormat.format(faker.date().between(createDateUtil(2002, 1,1), new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        //return dateFormat.format(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    protected String getTime() {
        //seems the time format has an extra digit on the end - not sure what that is.
        return timeFormatter.format(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalTime()).concat("1");
    }

    protected String getSex() {
        String sex = "F";
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(2);
        //System.out.println(randomInt);
        if (randomInt == 1) {
            sex = "M";
        }
        return sex;
    }

    protected String getSndLast() {
        return "LAST";
    }

    protected String getSndFirst() {
        return "FST";
    }

    protected String getBirthdate() {
        return dateFormat.format(faker.date()
                .birthday(18,90)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    protected String getBirthdateVerified() {
        return " ";
    }

    //21 chars
    protected String getLastName() {
        String lname = faker.name().lastName().toUpperCase();
        if (lname.length() > 21) {lname = lname.substring(0,20);}
        return String.format("%0$-21s", lname);
    }

    protected String getFirstName() {
        String fname = faker.name().firstName().toUpperCase();
        if (fname.length() > 14) {fname = fname.substring(0,14);}
        return String.format("%0$-15s", fname);
    }

    protected String getTitle() {
        return "    ";
    }

    protected String getMiddleName() {
        String mname = faker.name().firstName().toUpperCase();
        if (mname.length() > 10) {mname = mname.substring(0,9);}
        return String.format("%0$-10s", mname);
    }

    protected String getSsnVerified() {
        return "7";
    }

    protected String getDateOfDeath() {
        return "00000000";
    }

    protected Date createDateUtil(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month,day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
