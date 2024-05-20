package sg.synapxe.drugpairingsystem;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
// import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class DrugPairService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath = "src/main/resources/data.json";
    private List<DrugPair> drugPairs;

    public DrugPairService() throws Exception {
        loadDrugPairs();
    }

    private void loadDrugPairs() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        this.drugPairs = objectMapper.readValue(content, new TypeReference<List<DrugPair>>() {
        });
    }

    public List<DrugPair> getAll() {
        return drugPairs;
    }

    public Optional<DrugPair> getById(int id) {
        return drugPairs.stream().filter(pair -> pair.getId() == id).findFirst();
    }

    public void addContext(int id, String context) throws Exception {
        Optional<DrugPair> optionalDrugPair = getById(id);
        if (optionalDrugPair.isPresent()) {
            DrugPair drugPair = optionalDrugPair.get();
            drugPair.getContexts().add(context);
            saveDrugPairs();
        } else {
            throw new IllegalArgumentException("DrugPair with id " + id + " not found.");
        }
    }

    public DrugPair addDrugPair(DrugPair newDrugPair) throws Exception {
        int nextId = getNextId();
        newDrugPair.setId(nextId);
        drugPairs.add(newDrugPair);
        saveDrugPairs();
        return newDrugPair;
    }

    private int getNextId() {
        return drugPairs.stream().mapToInt(DrugPair::getId).max().orElse(0) + 1;
    }

    private void saveDrugPairs() throws Exception {
        String updatedContent = objectMapper.writeValueAsString(drugPairs);
        Files.write(Paths.get(filePath), updatedContent.getBytes());
    }
}
