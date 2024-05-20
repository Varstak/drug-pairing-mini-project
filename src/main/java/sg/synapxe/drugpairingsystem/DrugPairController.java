package sg.synapxe.drugpairingsystem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/drugpairs")
public class DrugPairController {

    private final DrugPairService drugPairService;

    public DrugPairController(DrugPairService drugPairService) {
        this.drugPairService = drugPairService;
    }

    @GetMapping
    public ResponseEntity<List<DrugPair>> getAll() {
        try {
            List<DrugPair> drugPairs = drugPairService.getAll();
            return ResponseEntity.ok(drugPairs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<DrugPair> addDrugPair(@RequestBody DrugPair newDrugPair) {
        try {
            DrugPair addedDrugPair = drugPairService.addDrugPair(newDrugPair);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedDrugPair);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
