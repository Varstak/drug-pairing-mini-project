import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DrugPair } from './drug-pair.model';
import { DrugPairService } from './drug-pair.service';
import { ChangeDetectorRef } from '@angular/core';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-drug-pair',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './drug-pair.component.html',
  styleUrls: ['./drug-pair.component.css']
})
export class DrugPairComponent implements OnInit {
  drugPairs: DrugPair[] = [];

  constructor(
    private drugPairService: DrugPairService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadDrugPairs();
  }

  loadDrugPairs(): void {
    this.drugPairService.getDrugPairs().subscribe(pairs => {
      this.drugPairs = pairs;
      this.cdr.detectChanges();
    });
  }

  addDrugPair(): void {
    const newPair: DrugPair = {
      id: 0, // Assuming the backend assigns the ID
      drug_name: '',
      ae_name: '',
      contexts: [''],
      adr_possibilities: 0,
      remarks: ''
    };
    this.drugPairs.push(newPair);
    this.cdr.detectChanges();
  }

  addContext(pair: DrugPair): void {
    pair.contexts.push('');
    this.cdr.detectChanges();
  }

  removeContext(pair: DrugPair, index: number): void {
    pair.contexts.splice(index, 1);
    this.cdr.detectChanges();
  }

  updateAdrPossibilities(pair: DrugPair, value: number): void {
    pair.adr_possibilities = value;
    this.cdr.detectChanges();
  }  

  deleteDrugPair(pair: DrugPair): void {
    if (pair.id === 0) {
      this.drugPairs = this.drugPairs.filter(p => p !== pair);
      return;
    }
  
    this.drugPairService.deleteDrugPair(pair.id).subscribe(() => {
      this.drugPairs = this.drugPairs.filter(p => p !== pair);
      this.cdr.detectChanges();
    });
  }
  

  saveDrugPairs(): void {
    // Check if any required fields are empty
    // context field should not be empty if there is a context field
    let hasEmptyFields = false;
    this.drugPairs.forEach(pair => {
      if (pair.drug_name === '' || pair.ae_name === '') {
        hasEmptyFields = true;
      }
      if (pair.contexts.length > 0) {
        pair.contexts.forEach(context => {
          if (context === '') {
            hasEmptyFields = true;
          }
        });
      }
    });
  
    if (!hasEmptyFields) {
      const saveObservables = this.drugPairs.map(pair => {
        if (pair.id === 0) {
          return this.drugPairService.addDrugPair(pair);
        } else {
          return this.drugPairService.updateDrugPair(pair);
        }
      });
  
      forkJoin(saveObservables).subscribe(() => {
        this.loadDrugPairs(); // Reload to get updated IDs and data
        alert('Saved successfully'); // Show alert after successfully saving
      });
    } else {
      alert('Please fill in all required fields.');
    }
  }
  

  trackByPair(index: number, pair: DrugPair): number {
    return pair.id;
  }

  trackByIndex(index: number): number {
    return index;
  }
}
