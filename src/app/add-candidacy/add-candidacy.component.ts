import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Candidacy } from '../class/candidacy';
import { CandidacyService } from '../shared/candidacy.service';
import { OfferService } from '../shared/offer.service';
import { Offer } from '../class/offer';
@Component({
  selector: 'app-add-candidacy',
  templateUrl: './add-candidacy.component.html',
  styleUrls: ['./add-candidacy.component.css']
})
export class AddCandidacyComponent implements OnInit {
  form : boolean = true;
  candidacy! : Candidacy;
  offer!: Offer;
  listCandidacy:any;
  offerId: any | null = null;
  attachments!: File;
  B2Fr!: File;
  B2Eng!: File;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private candidacyService: CandidacyService,
    private offerService: OfferService) {}

  ngOnInit(): void {
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

  this.route.params.subscribe(params => {
    this.offerId = ['id'];
  });
  const offerId = this.route.snapshot.params['offerId'];
  this.offerService.getOfferById(offerId).subscribe((offer) => {
    this.offer = offer;
  });
  }
  onFileSelected(event: any,fileType :any) {

    const file: File = event.files[0];
    if (fileType === 'attachments') {
      this.attachments = file;
    } else if (fileType === 'B2Fr') {
      this.B2Fr = file;
    } else if (fileType === 'B2Eng') {
      this.B2Eng = file;
    }
  }

  // addCandidacy(c: any){
  //   console.log(c.offerId); // add this line to check the value of offerId
  //   this.candidacyService.addCandidacy(c).subscribe(() => {
  //     this.getAllCandidacy();
  //     this.form = false;
  //   });
  // }
  // addCandidacy() {
  //   let c: Candidacy
  //   console.log("attachments",this.attachments)
  //   console.log("B2Fr",this.B2Fr)
  //   console.log("B2Eng",this.B2Eng)
  //   const formData: FormData = new FormData();
  //   formData.append('attachments', this.attachments);
  //   formData.append('B2Fr', this.B2Fr);
  //   formData.append('B2Eng', this.B2Eng);
  //   let id = Number(this.route.snapshot.paramMap.get('id'));
  //   this.candidacyService.addCandidacy(this.candidacy,id).subscribe((res) => {
  //     console.log('Candidacy added successfully.',res);
  //     this.router.navigate(['/']);
  //   },);
  // }
  addCandidacy() {
    console.log("attachments",this.attachments)
    console.log("B2Fr",this.B2Fr)
    console.log("B2Eng",this.B2Eng)
    const attachments = this.attachments;
    const B2Fr = this.B2Fr;
    const B2Eng = this.B2Eng;
    let id = Number(this.route.snapshot.paramMap.get('id'));
    this.candidacyService.addCandidacy(id, attachments, B2Fr, B2Eng).subscribe(
      (response) => {
        console.log('Upload success');
      },
      (error) => {
        console.error('Upload error',error);
      }
    );
this.router.navigate(['/dashboard']);
}
}
