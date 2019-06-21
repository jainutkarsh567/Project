import {Injectable, NgModule} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse} from '@angular/common/http';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import { filter } from 'rxjs/operators';
@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor{
	intercept (req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
	const dupReq = req.clone({headers: req.headers.set('ads','http://localhost:8080/WILD1/Add')});
	return next.handle(dupReq);
	}
	};
	@NgModule({
	providers: [{ provide: HTTP_INTERCEPTORS, useClass:HttpRequestInterceptor, multi: true}]
	})
	export class InterceptorModule{}
