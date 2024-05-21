import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DrugPairComponent } from './drug-pair.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, DrugPairComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'drug-pairing-frontend';
}
