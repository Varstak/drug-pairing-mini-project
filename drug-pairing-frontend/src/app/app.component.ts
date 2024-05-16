import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { OnInit } from '@angular/core';
import { DrugPair } from '../models/drug-pair.model';
import { DrugPairService } from '../services/drug-pair.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit {
  title = 'drug-pairing-frontend';
  public drugPairs: DrugPair[] = [];

  constructor(private drugPairService: DrugPairService) { }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  public getDrugPairs(): void {
    this.drugPairService.getDrugPairList().subscribe(
      (response: DrugPair[]) => {
        this.drugPairs = response;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }
}
