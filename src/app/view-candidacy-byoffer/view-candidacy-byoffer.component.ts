import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CandidacyService } from '../shared/candidacy.service';

@Component({
  selector: 'app-view-candidacy-byoffer',
  templateUrl: './view-candidacy-byoffer.component.html',
  styleUrls: ['./view-candidacy-byoffer.component.css']
})
export class ViewCandidacyByofferComponent implements OnInit {
  role:string | undefined;
  id: number | undefined;
  listCandidacy:any;
  constructor(private route: ActivatedRoute,
    private candidacyService: CandidacyService) {}

  ngOnInit(): void {
    this.role = ""+sessionStorage.getItem("Role");
    this.id = this.route.snapshot.params["id"];
    console.log(this.id);
    console.log(this.role);
    this.candidacyService.getCandidacyByOfferid(this.id).subscribe((res:any) =>this.listCandidacy = res);}

}
