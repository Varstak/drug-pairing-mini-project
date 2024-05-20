package sg.synapxe.drugpairingsystem;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DrugPair {
    private int id;
    private String drug_name;
    private String ae_name;
    private List<String> contexts;
    @JsonProperty("adr-possibilities")
    private int adr_posibilities;
    private String remarks;

    // Default constructor
    public DrugPair() {
    }

    // Parameterized constructor
    public DrugPair(int id, String drug_name, String ae_name, List<String> contexts, int adr_posibilities,
            String remarks) {
        this.id = id;
        this.drug_name = drug_name;
        this.ae_name = ae_name;
        this.contexts = contexts;
        this.adr_posibilities = adr_posibilities;
        this.remarks = remarks;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getAe_name() {
        return ae_name;
    }

    public void setAe_name(String ae_name) {
        this.ae_name = ae_name;
    }

    public List<String> getContexts() {
        return contexts;
    }

    public void setContexts(List<String> contexts) {
        this.contexts = contexts;
    }

    public int getAdr_posibilities() {
        return adr_posibilities;
    }

    public void setAdr_posibilities(int adr_posibilities) {
        this.adr_posibilities = adr_posibilities;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
