package Methods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Faker {

    private static final String DEFAULT_CSV_FILE_NAME = "fakerUser.csv";
    private static final String DEFAULT_FAKER_LOCALE = "tr";
    private static final String DEFAULT_EMAIL = "testuserkeepnet@gmail.com";
    private final com.github.javafaker.Faker faker;


    public Faker() {
        this.faker = new com.github.javafaker.Faker(new Locale.Builder().setLanguage(DEFAULT_FAKER_LOCALE).setRegion(DEFAULT_FAKER_LOCALE).build());
    }

    public static void main(String[] args){

        try {
            Faker generator = new Faker();
            generator.generateUserCSV();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public Map<String, String> generateUser() {
        Map<String, String> user = new HashMap<>();

        user.put("Name", faker.name().firstName());
        user.put("Surname", faker.name().lastName());
        user.put("randomEmail", generateRandomEmail());
        user.put("CompanyTitle", faker.company().profession());
        user.put("GroupName", faker.animal().name());
        user.put("CampaignName", faker.rickAndMorty().character());

        return user;
    }

    private String generateRandomEmail() {
        return DEFAULT_EMAIL.replace("@gmail.com", "") + "+" + System.currentTimeMillis() + "@gmail.com";
    }

    public void generateUserCSV() throws IOException {
        Map<String, String> user = generateUser();

        try (FileWriter writer = new FileWriter(DEFAULT_CSV_FILE_NAME)) {
            writer.append(String.join(",", user.keySet())).append("\n");
            writer.append(String.join(",", user.values())).append("\n");
        }
    }
}