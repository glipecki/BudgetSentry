import { inject, addProviders } from '@angular/core/testing';
import { AppComponent } from './app.component';

describe('App', () => {
    beforeEach(() => {
        addProviders([AppComponent]);
    });
    it ('should inject app component', inject([AppComponent], (app: AppComponent) => {
        expect(app).not.toBeNull();
    }));
});