import { Observable } from "rxjs";
import { DrugPair } from "./drug-pair.model";
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})

export class DrugPairService {
    private apiserverurl = "http://localhost:8080/api/drugpairs";

    constructor(private http: HttpClient) {}

    public getDrugPairs(): Observable<DrugPair[]> {
        return this.http.get<DrugPair[]>(`${this.apiserverurl}`);
    }

    public addDrugPair(drugPair: DrugPair): Observable<DrugPair> {
        return this.http.post<DrugPair>(`${this.apiserverurl}`, drugPair);
    }

    public updateDrugPair(drugPair: DrugPair): Observable<DrugPair> {
        return this.http.put<DrugPair>(`${this.apiserverurl}/${drugPair.id}`, drugPair);
    }

    public deleteDrugPair(drugPairId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiserverurl}/${drugPairId}`);
    }
}