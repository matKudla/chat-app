import {Component, OnDestroy, OnInit} from '@angular/core';
import {ChatService} from './chat.service';
import {WebSocketAPI} from '../helpers/websocketapi';
import {Message} from '../model/message';
import {AuthenticationService} from '../service/authentication.service';


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {


  webSocketAPI: WebSocketAPI;
  name: string;

  message: string;
  messages: Message[] = [];


  send() {
    const m = new Message();
    m.content = this.message;
    m.sender = this.name;
    m.type = 'MESSAGE';
    this.webSocketAPI.send(m);
    this.message = '';
  }


  constructor(private chatService: ChatService,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.name = this.authenticationService.currentUserValue.username;
    this.webSocketAPI = new WebSocketAPI();
    this.handleMessage();
    this.connect();
  }

  connect() {
    this.webSocketAPI.connect();
  }

  disconnect() {
    this.webSocketAPI.disconnect();
  }

  handleMessage() {
    this.webSocketAPI.messageReceived.pipe().subscribe(value => {
      this.messages.push(value);
    });
  }

  ngOnDestroy(): void {
    this.disconnect();
  }
}
