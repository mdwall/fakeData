import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ExperimentWithJavaFaker {

    @org.junit.jupiter.api.Test
    void testAddress() {
        Faker faker = new Faker(new Locale("en-US"));

        Address address = faker.address();

        System.out.println(address.fullAddress());

        System.out.println(address.cityName());

        System.out.println(address.stateAbbr());

        System.out.println(faker.address().stateAbbr());

        

        System.out.println(faker.address().countyByZipCode("80110"));

        System.out.println(faker.address().zipCodeByState("CO"));

    }
}


