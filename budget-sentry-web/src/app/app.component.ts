import {Component} from '@angular/core';
import {DemoComponent} from 'sample/demo/demo.component';

@Component({
    selector: 'my-app',
    template: require('./app.component.html'),
    styles: [require('./app.component.scss')],
    directives: [DemoComponent]
})
export class AppComponent {
    public onHeyClicked() {
        console.log('onHeyClicked');
    }
}
