import com.github.javafaker.Faker;

import java.util.*;

public class SidmodIds {
    private int sidmodListSize = 0;
    private List<String> sidmodIdList;

    Faker faker = new Faker();

    public SidmodIds(int sidmodListSize) {
        this.sidmodListSize = sidmodListSize;
        this.sidmodIdList = generateSidmodList(sidmodListSize);
    }

    public String getSidmodIdFromList(int sidmodListIndex) {
        return sidmodIdList.get(sidmodListIndex);
    }

    public int getSidmodListSize() {
        return sidmodListSize;
    }

    List<String> generateSidmodList(int sidmodListSize) {
        Set<String> sidmodIdSet = new HashSet<String>();
        List<String> sidmodIdList = new ArrayList<String>();
        while (sidmodIdSet.size() < sidmodListSize) {
            sidmodIdSet.add(generateRandomSidmodId());
        }
        sidmodIdList.addAll(sidmodIdSet);
        return sidmodIdList;
    }

    String generateRandomSidmodId() {
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        String id = faker.number().digits(6);
        return Character.toString(c).concat(id).toUpperCase();
    }
}
