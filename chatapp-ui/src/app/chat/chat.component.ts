import {Component, OnInit} from '@angular/core';
import {ChatService} from './chat.service';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  value = 'elo';

  constructor(private chatService: ChatService) {
  }

  ngOnInit() {
    this.chatService.getData().subscribe(value1 => this.value = value1);
  }

}
