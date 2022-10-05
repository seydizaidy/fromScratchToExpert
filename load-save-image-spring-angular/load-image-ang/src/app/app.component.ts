import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'load-image-ang';
  public selectedFiles!: File;
  public selectedFile!: File;
  public message: any;
  public imageName: any;
  public retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  constructor(private httpClient: HttpClient) { }

  // onSelectedFile(event: any)
  // {
  //   this.selectedFiles=event.target.files;
  //   console.log(this.selectedFiles)
  //   console.log("\n\n\n");
  //   console.log(this.selectedFiles.item(0))
  // }
  // uploadPhoto()
  // {
  // }
  // //-----------------------------------Form 2---
  // processFile(imageInput: HTMLInputElement)
  // {
  // }

//Gets called when the user selects an image
  onFileChanged(event:any)
  {
    //Select File
    this.selectedFile = event.target.files[0];
    console.log(this.selectedFile)
  }

  onUpload()
  {

    console.log(this.selectedFile);
    //FormData API provides methods and properties to allow us easily prepare form data
    // to be sent with POST HTTP requests.
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile',this.selectedFile, this.selectedFile.name)
    this.httpClient.post('http://localhost:8080/image/upload', uploadImageData,
      { observe: 'response' })
      .subscribe((response) => {
          if (response.status === 200) {
            this.message = 'Image uploaded successfully';
          } else {
            this.message = 'Image not uploaded successfully';
          }
        }
      );
  }
  host:string= "http://localhost:8080";

  //Gets called when the user clicks on retieve image button to get the image from back end
  getImage() {
    //Make a call to Sprinf Boot to get the Image Bytes.
    this.httpClient.get('http://localhost:8080/image/get/' + this.imageName)
      .subscribe(
        res => {
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        }
      );
  }
}
