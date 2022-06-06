import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { throwError, Observable, BehaviorSubject } from 'rxjs';
import { shareReplay, tap, catchError } from 'rxjs/operators';
import { UserInterface } from '../interfaces/user.model';
import { PermissionTypes } from '../interfaces/permission-types.enum';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable()
export class AuthService {
  public $loggedSubject!: BehaviorSubject<boolean>;
  public $logged!: Observable<boolean>;
  public $permittedSubject!: BehaviorSubject<boolean>;
  public $permitted!: Observable<boolean>;

  constructor(private router: Router, private http: HttpClient, private helper: JwtHelperService) {
    this.$loggedSubject = new BehaviorSubject<boolean>(this.isLoggedIn());
    this.$logged = this.$loggedSubject.asObservable();
  }

  public login(user: any): Observable<any> {
    const params: URLSearchParams = new URLSearchParams();
    params.set('username', user.username);
    params.set('password', user.password);
    return this.SendAuthentication(params);
  }

  private SendAuthentication(params: URLSearchParams): Observable<any> {
    const options = {
      headers: new HttpHeaders().set(
        'Content-Type',
        'application/x-www-form-urlencoded'
      ),
    };
    return this.http
      .post<any>(
        `${environment.baseUrl}/user/login`,
        params.toString(),
        options
      )
      .pipe(
        catchError((error) => {
          this.router.navigateByUrl('/');
          return throwError(error);
        }),
        shareReplay(),
        tap((res) => this.setSession(res))
      );
  }

  private setSession(authResult: { access_token: string; refresh_token: string; }): void {
    sessionStorage.setItem('access_token', authResult.access_token);
    sessionStorage.setItem('refresh_token', authResult.refresh_token);

    const tokenInfo = this.helper.decodeToken(authResult.access_token);
    const user: UserInterface = {
      username: tokenInfo.sub,
      roles: tokenInfo.roles
    };

    sessionStorage.setItem('user', btoa(JSON.stringify(user)));
    this.$loggedSubject.next(true);
    this.getPermissions();
  }

  public isLoggedIn(): any {
    let logged = this.helper.isTokenExpired(sessionStorage.getItem('access_token')!);
    return logged;
  }

  public logout(): void {
    sessionStorage.clear();
    this.$loggedSubject.next(false);
    this.router.navigate(['/']);
  }

  public getToken(): string {
    const token = sessionStorage.getItem('access_token');
    return token!;
  }

  public getDataUser(): UserInterface {
    const user = JSON.parse(atob(sessionStorage.getItem('user')!));
    return user;
  }

  public refreshToken() {

    const options = {
      headers: new HttpHeaders().set(
        'Content-Type',
        'application/x-www-form-urlencoded'
      ),
    };
    return this.http
      .post<any>(
        `${environment.baseUrl}/user/login`,
        params.toString(),
        options
      )
      .pipe(
        catchError((error) => {
          this.router.navigateByUrl('/');
          return throwError(error);
        }),
        shareReplay(),
        tap((res) => this.setSession(res))
      );
      
    const params: URLSearchParams = new URLSearchParams();
        params.set('grant_type', 'refresh_token');
        params.set('refresh_token', this.getRefreshToken()!);
        params.set('client_id', environment.clientId);
        params.set('client_secret', environment.clientSecret);
        params.set('scope', '');
        return this.SendAuthentication(params);
  }

  private getRefreshToken() {
    return sessionStorage.getItem('refresh_token');
  }

  public isPermitted(rota: string, child: string): boolean {
    if (!this.isLoggedIn()) {
      this.router.navigate(['/inicial/login']);
    }

    if (this.getDataUser().role == 'Sistema') {
      return true;
    }

    const route = this.getDataPermissions().find(
      (element) => element.route == rota
    );
    
    if (route == undefined || route == null) {
      return false;
    } else {
      return route.children.findIndex((item: string) => item == child) > -1;
    }
  }

  public getPermissions() {
    return this.http
      .get<any[]>('assets/config/permissions.json?v=' + environment.version)
      .subscribe(
        (response) => {
          const role = this.getDataUser().role;
          let userPermissions: any = [];
          response.forEach((element) => {
            if (element.roles.findIndex((item: any) => item == role) > -1) {
              let elementTemp: any = {};
              elementTemp.children = [];
              elementTemp.route = element.route;
              element.children.forEach((sub: { roles: any[]; route: any; }) => {
                if (sub.roles.findIndex((item: any) => item == role) > -1) {
                  elementTemp.children.push(sub.route);
                }
              });
              userPermissions.push(elementTemp);
            }
          });
          sessionStorage.setItem(
            'permissions',
            btoa(JSON.stringify(userPermissions))
          );
        },
        () => {
          sessionStorage.setItem('permissions', btoa(JSON.stringify([])));
        }
      );
  }

  public getDataPermissions(): any[] {
    const permission = sessionStorage.getItem('permissions');
    if (permission !== null) {
      const permissions = JSON.parse(
        atob(sessionStorage.getItem('permissions'))
      );
      return permissions;
    }
  }

  public getMenu() {
    return this.http.get<[]>(
      'assets/config/menu.json?v=' + environment.version
    );
  }

  public hasPermission(permissions: string[]): boolean {
    const role = this.getDataUser().role;
    if (role == PermissionTypes.SISTEMA) {
      return true;
    }
    return permissions.indexOf(role) > -1;
  }

  public getUsuarios(): Observable<any> {
    const url = '/api/v1/usuarios?PageSize=500';
    return this.http.get<any>(environment.urlServices + `${url}`);
  }
}
