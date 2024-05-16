package sg.synapxe.drugpairingsystem.models;

import java.util.ArrayList;
import java.io.Serializable;

@Entity
public class Drug_pair implements Serializable {
    public long id;
    public String drug_name;
    public String AE_name;
    public ArrayList<String> contexts;
    public ArrayList<String> ADR_posibilities = new ArrayList<String>("True", "False", "Uncertain");
    public String Remarks;

    public Drug_pair(long id, String drug_name, String AE_name, ArrayList<String> contexts, String Remarks) {
        this.id = id;
        this.drug_name = drug_name;
        this.AE_name = AE_name;
        this.contexts = contexts;
        this.Remarks = Remarks;
    }

    public long getId() {
        return id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public String getAE_name() {
        return AE_name;
    }

    public ArrayList<String> getContexts() {
        return contexts;
    }


    public String getRemarks() {
        return Remarks;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public void setAE_name(String AE_name) {
        this.AE_name = AE_name;
    }

    public void setContexts(ArrayList<String> contexts) {
        this.contexts = contexts;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public void addContext(String context) {
        this.contexts.add(context);
    }
}