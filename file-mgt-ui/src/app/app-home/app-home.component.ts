import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-app-home',
  templateUrl: './app-home.component.html',
  styleUrls: ['./app-home.component.css']
})
export class AppHomeComponent {
  title = 'BCBS File Management System';
  filesToUpload: File[] = [];
  isAuthenticated: boolean = false;
  isDownloaded: boolean = false;
  userObj: any;
  compressedFileName: string = "FileCompressed.zip";

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

  loadFile(event: any) {
    this.filesToUpload.push(event.target.files[0]);
    this.isDownloaded = false;
    console.info(this.filesToUpload);
  }

  uploadCompressAndDownload() {
    console.info(this.filesToUpload);

    const formData = new FormData();
    for (let i = 0; i < this.filesToUpload.length; i++) {
      let file: File = this.filesToUpload[i];
      formData.append("files", file, file.name);
    }

    const httpOptions = {
      responseType: 'blob' as 'json'
    };

    this.http.post('api/file-mgt/download', formData, httpOptions).subscribe(
      data => {

        const blob = new Blob([data as BlobPart], { type: 'application/octet-stream' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = this.compressedFileName;
        a.click();

        this.isDownloaded = true;
        this.filesToUpload = [];
        console.info(data);
      },
      error => {
        console.error(error);
      }
    );

  }

  canShowFiles() {
    return this.isDownloaded === false && this.filesToUpload.length > 0;
  }

  canDownloadZipFile() {
    return this.filesToUpload.length > 0;
  }

}
