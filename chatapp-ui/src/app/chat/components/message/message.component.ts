import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {Message} from '../../../model/message';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit, AfterViewInit {
  @Input() message: Message;
  @Input() username: string;

  isMessageFromMe: boolean;
  isMessageType: boolean;
  isLeaveType: boolean;
  isConnectType: boolean;

  container: HTMLElement;

  constructor() {
  }

  ngOnInit() {
    this.isMessageFromMe =  this.message.sender === this.username;
    this.isMessageType = this.message.type === 'MESSAGE';
    this.isLeaveType = this.message.type === 'Leave';
    this.isConnectType = this.message.type === 'Connect';
  }

  ngAfterViewInit() {
    this.container = document.getElementById('scroll');
    this.container.scrollTop = this.container.scrollHeight;
  }

}
