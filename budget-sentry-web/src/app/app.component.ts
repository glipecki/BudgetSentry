import {Component} from '@angular/core';
import {DemoComponent} from 'sample/demo/demo.component';

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    directives: [DemoComponent]
})
export class AppComponent { }