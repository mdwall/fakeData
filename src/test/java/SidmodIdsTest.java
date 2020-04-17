import static org.junit.jupiter.api.Assertions.*;

class SidmodIdsTest {

    @org.junit.jupiter.api.Test
    void getSidmodIdFromList() {
        SidmodIds ids = new SidmodIds(10000);
        for (int i = 0; i < 10000; i++) {
            System.out.println(ids.getSidmodIdFromList(i));
        }
    }
}