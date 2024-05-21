export interface DrugPair {
    id: number;
    drug_name: string;
    AE_name: string;
    contexts: string[];
    ADR_possibilities: number;
    Remarks: string;
}