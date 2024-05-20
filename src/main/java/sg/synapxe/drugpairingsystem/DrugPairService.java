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

    // delete drugpair and update the id of the rest of the drugpairs
    public void deleteDrugPair(int id) throws Exception {
        Optional<DrugPair> optionalDrugPair = getById(id);
        if (optionalDrugPair.isPresent()) {
            drugPairs.removeIf(pair -> pair.getId() == id);
            drugPairs.forEach(pair -> {
                if (pair.getId() > id) {
                    pair.setId(pair.getId() - 1);
                }
            });
            saveDrugPairs();
        } else {
            throw new IllegalArgumentException("DrugPair with id " + id + " not found.");
        }
    }

    // edit drugpair and update the drugpair
    public void editDrugPair(int id, DrugPair editedDrugPair) throws Exception {
        Optional<DrugPair> optionalDrugPair = getById(id);
        if (optionalDrugPair.isPresent()) {
            DrugPair drugPair = optionalDrugPair.get();
            drugPair.setDrug_name(editedDrugPair.getDrug_name());
            drugPair.setAe_name(editedDrugPair.getAe_name());
            drugPair.setContexts(editedDrugPair.getContexts());
            drugPair.setAdr_posibilities(editedDrugPair.getAdr_posibilities());
            drugPair.setRemarks(editedDrugPair.getRemarks());
            saveDrugPairs();
        } else {
            throw new IllegalArgumentException("DrugPair with id " + id + " not found.");
        }
    }
}
