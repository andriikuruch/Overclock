import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class DataSharingService {
    public curUser : BehaviorSubject<string> = new BehaviorSubject<string>('');
    public isLoggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
    public isAdmin: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
}
