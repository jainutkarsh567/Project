import {ANIMAL} from './animals/animal';
import {MANIMAL} from './animals/mock-animal';
import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {User} from './User';

import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';





@Injectable({
  providedIn: 'root'
})
export class Animal1Service {
user: any
s:any
  constructor(public http : HttpClient) {
   }
  getAnimals() :Observable <ANIMAL[]>
  {
  return of(MANIMAL);
  }
  getAnimal(id: number): Observable<ANIMAL>{
  return of(MANIMAL.find(i => i.id === id));
  }
  getDetails():Observable <any>
  {
  return this.http.get('http://localhost:8080/WILD1/Add').pipe(map(res => res));
  }
  addUser(user): Observable <any>{
   
  return this.http.post('http://localhost:8080/WILD1/Add',user).pipe(map(res => res));
  
  }
  onCheck(user): Observable<any>{
  return this.http.post('http://localhost:8080/WILD1/Add',user).pipe(map(res => res));
  }
  onSign_Up(user) : Observable<any>
  {
   return this.http.post('http://localhost:8080/WILD1/Add',user).pipe(map(res => res));
  }
}
