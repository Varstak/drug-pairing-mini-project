package sg.synapxe.drugpairingsystem;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DrugPair {
    private int id;
    private String drug_name;
    private String ae_name;
    private List<String> contexts;
    @JsonProperty("adr_possibilities")
    private int adr_possibilities;
    private String remarks;

    // Default constructor
    public DrugPair() {
    }

    // Parameterized constructor
    public DrugPair(int id, String drug_name, String ae_name, List<String> contexts, int adr_possibilities,
            String remarks) {
        this.id = id;
        this.drug_name = drug_name;
        this.ae_name = ae_name;
        this.contexts = contexts;
        this.adr_possibilities = adr_possibilities;
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

    public int getAdr_possibilities() {
        return adr_possibilities;
    }

    public void setAdr_possibilities(int adr_possibilities) {
        this.adr_possibilities = adr_possibilities;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
