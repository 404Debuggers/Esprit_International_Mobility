import { Component, OnInit } from '@angular/core';
import { CandidacyService } from '../shared/candidacy.service';
import { Candidacy } from '../class/candidacy';
import { NgModel } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-candidacy',
  templateUrl: './candidacy.component.html',
  styleUrls: ['./candidacy.component.css']
})
export class CandidacyComponent implements OnInit  {
  listCandidacy:any;
  archivedlistCandidacy: any;
  candidacies: any;
  form : boolean = true;
  candidacy! : Candidacy;
  role:string | undefined;
  constructor(private candidacyService: CandidacyService) { }
  ngOnInit(): void {
    let x=localStorage.getItem("candidacy");
    if(x=="1"){
      localStorage.setItem('candidacy', "0");
      window.location.reload();
    }
    this.getAllCandidacy();
    this.getAllArchivedCandidancy();
    this.getCurrentUserCandidacy();
      this.candidacy = {
      candidatureId:null,
      CoverLettre:null,
      attachements:null,
      B2Eng:null,
      B2Fr:null,
      option:null,
      levelEng:null,
      levelFr:null,
      status:null,
      marks:null,
      archive:null,
      title:null,
      offerId:null,
      firstName:null,
      lastName:null,
      email:null,

    }
    this.role = ""+sessionStorage.getItem("Role");
  }
  getAllCandidacy() {
    this.candidacyService.getAllCandidacy().subscribe((res:any) => this.listCandidacy = res)
  }
  getAllArchivedCandidancy(){
    this.candidacyService.getAllArchivedCandidancy().subscribe((res:any) => {this.archivedlistCandidacy = res;
    });
  }
  getCurrentUserCandidacy(){
    this.candidacyService.getCurrentUserCandidacy().subscribe((res:any) => this.candidacies = res)
  }



  deleteCandidacyFromDb(id:any){
    console.log(id);
    this.candidacyService.deleteCandidacyFromDb(id).subscribe((res:any) => this.getAllCandidacy());
  }
  deleteCandidancy(id:any){
    this.candidacyService.deleteCandidancy(id).subscribe((res:any) => this.getAllCandidacy());
    window.location.reload();
  }
  RestoreCandidancy(id:any){
    this.candidacyService.RestoreCandidancy(id).subscribe((res:any) => this.getAllCandidacy());
    window.location.reload();
  }


}
