import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {environment} from '../../environments/environment';
import {EventEmitter} from '@angular/core';
import {Message} from '../model/message';


export class WebSocketAPI {
  webSocketEndPoint = environment.apiUrl + '/ws';
  topic = '/topic/chat';
  stompClient: any;
  token: string;
  messageReceived: EventEmitter<Message> = new EventEmitter();

  constructor() {
    this.token = (JSON.parse(localStorage.getItem('currentUser'))).token;
  }

  connect() {
    console.log('Initialize WebSocket Connection');
    const ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);

    this.stompClient.connect({token: this.token}, frame => {
      this.stompClient.subscribe(this.topic, sdkEvent => {
        this.onMessageReceived(sdkEvent.body);
      });

    }, this.errorCallBack);
  }

  disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log('Disconnected');
  }


  errorCallBack(error) {
    console.log('errorCallBack -> ' + error);
    setTimeout(() => {
      this.connect();
    }, 5000);
  }


  send(message) {
    this.stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(message));
  }

  onMessageReceived(message) {
    console.log('Message Recieved from Server :: ' + message);
    this.messageReceived.emit(JSON.parse(message) as Message);
  }
}
