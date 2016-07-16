import { Component } from '@angular/core';

@Component({
    selector: 'demo',
    template: '<button (click)="onButtonClick()">demo :)</button>'
})
export class DemoComponent {
    public onButtonClick() {
        console.log('hello click!');
    }
}