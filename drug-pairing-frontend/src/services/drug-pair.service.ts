import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DrugPair } from '../models/drug-pair.model';

@Injectable({
  providedIn: 'root'
})
export class DrugPairService {

  private baseUrl = 'http://localhost:4200';

  constructor(private http: HttpClient) { }

  getDrugPair(id: number): Observable<DrugPair> {
    return this.http.get<DrugPair>(`${this.baseUrl}/${id}`);
  }

  createDrugPair(drugPair: Object): Observable<DrugPair> {
    return this.http.post<DrugPair>(`${this.baseUrl}`, drugPair);
  }

  updateDrugPair(id: number, value: any): Observable<DrugPair> {
    return this.http.put<DrugPair>(`${this.baseUrl}/${id}`, value);
  }

  deleteDrugPair(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  getDrugPairList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }
}