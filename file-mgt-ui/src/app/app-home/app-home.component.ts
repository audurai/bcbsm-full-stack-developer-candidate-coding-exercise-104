import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-app-home',
  templateUrl: './app-home.component.html',
  styleUrls: ['./app-home.component.css']
})
export class AppHomeComponent {
  title = 'BCBS File Management System';
  isAuthenticated: boolean = false;
  userObj: any;

  constructor(private http: HttpClient) {
  }

  login() {
    this.http.get('api/file-mgt').subscribe(
      data => {
        this.userObj = data;
        this.isAuthenticated = true;
        console.info(this.userObj);
      },
      error => {
        console.error(error);
      }
    );
  }

  compressFiles(){
    let fileDtls = {
      "folderLocation" : "F:/bcbs/test_folder"
    };
    this.http.post('api/file-mgt', fileDtls).subscribe(
      data => {
        this.userObj = data;
        this.isAuthenticated = true;
        console.info(this.userObj);
      },
      error => {
        console.error(error);
      }
    );
  }
}
