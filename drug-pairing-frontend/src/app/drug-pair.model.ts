export interface DrugPair {
    id: number;
    drug_name: string;
    ae_name: string;
    contexts: string[];
    adr_possibilities: number;
    remarks: string;
}