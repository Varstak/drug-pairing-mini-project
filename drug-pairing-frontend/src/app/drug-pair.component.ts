import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DrugPair } from './drug-pair.model';
import { DrugPairService } from './drug-pair.service';

@Component({
  selector: 'app-drug-pair',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './drug-pair.component.html',
  styleUrls: ['./drug-pair.component.css'] // Note: it's `styleUrls` not `styleUrl`
})
export class DrugPairComponent implements OnInit {
  drugPairs: DrugPair[] = [];

  constructor(private drugPairService: DrugPairService) {}

  ngOnInit(): void {
    this.loadDrugPairs();
  }

  loadDrugPairs(): void {
    this.drugPairService.getDrugPairs().subscribe(pairs => this.drugPairs = pairs);
  }

  addDrugPair(): void {
    const newPair: DrugPair = {
      id: 0, // Assuming the backend assigns the ID
      drug_name: '',
      AE_name: '',
      contexts: [''],
      ADR_possibilities: 0,
      Remarks: ''
    };
    this.drugPairs.push(newPair);
  }

  addContext(pair: DrugPair): void {
    pair.contexts.push('');
  }

  deleteDrugPair(id: number): void {
    this.drugPairService.deleteDrugPair(id).subscribe(() => {
      this.drugPairs = this.drugPairs.filter(pair => pair.id !== id);
    });
  }
}
