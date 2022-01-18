import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppearanceService {

  constructor() {
  }

  public customAlert(message: string): void {
    let popup = document.getElementById("popup");
    let popup_text = document.getElementById("popup_text");
    popup_text!.textContent = message;
    popup!.style.display = "block";
  }

  public customQuestion(question: string): void {
    let popup = document.getElementById("question");
    let popup_text = document.getElementById("question_text");
    popup_text!.textContent = question;
    popup!.style.display = "block";
  }
}
