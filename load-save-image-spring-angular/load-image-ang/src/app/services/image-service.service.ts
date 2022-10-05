import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../product";

@Injectable({
  providedIn: 'root'
})
export class ImageServiceService {
  public host_ssl:string="https://localhost:8443";
  public host:string="http://localhost:8080";

  constructor(private http: HttpClient) {}
//---------------------Methode Youssoufi

  uploadPhotoProduct(file: File, product:Product): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
    formdata.append('file', file);
    const req = new HttpRequest('POST', this.host+'/uploadPhoto/', formdata,
      {
      reportProgress: true,
      responseType: 'text'

    });
    return this.http.request(req);

  }}
