import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError, retry} from "rxjs/operators";
import {PhoneState} from "./phone-status/phone-status.component";

@Injectable({
  providedIn: 'root'
})
export class PhoneStatusService {

  constructor(private http: HttpClient) {
  }

  getPhoneNumbers(): Observable<PhoneState[]> {
    console.log(' <<<<<< getPhoneNumbers called >>>>>>');
    return this.http.get<PhoneState[]>(`http://localhost:9081/validator/status`)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
