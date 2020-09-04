import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

  currentIndex;

  constructor() {
    this.currentIndex = 0;
  }

  onPrev() {
    this.currentIndex--;
  }

  onNext() {
    this.currentIndex++;
  }
}
