import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'load-image-and-reactiv-form';
  myFormGroup!: FormGroup;
  name: any;
  private selectedFile: any;
 public avatar: any;
 fileValue:any;

  SERVER_URL = "http://localhost:8080/api/saveUpload"
message!: string;
         public constructor(public fb:FormBuilder , private http: HttpClient)
         {
           this.myFormGroup=this.fb.group(
             {
               name: [''],
               avatar: [null],
             }
           );
         }

  ngOnInit(): void
  {
  // this.myFormGroup = new FormGroup(
  //   {
  //   name: new FormControl()
  //   });

  }
  uploadFile(event:any)
  {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.selectedFile = file;

      console.log(this.selectedFile);
      // @ts-ignore
    this.myFormGroup.get('avatar').setValue(file);


    }
    // @ts-ignore
    // uploadFile(event:any) {
    //   // @ts-ignore
      //   const file = (event.target as HTMLInputElement).files[0];
    //   this.myFormGroup.patchValue({
    //     avatar: file,
    //   });
    //   // @ts-ignore
    //   this.myFormGroup.get('avatar').updateValueAndValidity();
    //   console.log(this.avatar)
    // }

//   uploadFile(event:any)
//   {
//     this.selectedFile =event.target.files[0];
//    // console.log(this.selectedFile)
// this.myFormGroup=new FormGroup(
//   {
//     avatar : new FormControl(this.selectedFile)
//   }
// )
//   //   console.log(this.avatar)
//
//     //console.log(this.avatar)
//     this.fileValue=this.avatar;
//     console.log(this.fileValue)

  }

  submitForm() {
    this.name = this.myFormGroup.value.name;

    //this.user.title = this.egitimBilgileriForm.get("title").value;
    // @ts-ignore
    //console.log( this.myFormGroup.get('name').value)
    console.log(this.name)
    //console.log(this.selectedFile)
    // @ts-ignore
    console.log(this.myFormGroup.get('avatar').value)
    var formData: any = new FormData();
    formData.append('name', this.name);
    formData.append('avatar', this.selectedFile)

    this.http.post(this.SERVER_URL, formData,{ observe: 'response' })
      .subscribe((response) => {
          if (response.status === 200) {
            this.message = 'Image uploaded successfully';
          } else {
            this.message = 'Image not uploaded successfully';
          }
        }
      );
    // // @ts-ignore
    // formData.append('name', this.myFormGroup.get('name').value);
    // // @ts-ignore
    // formData.append('avatar', this.myFormGroup.get('avatar').value);




  }
}
