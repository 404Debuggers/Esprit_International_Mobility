import { Component, OnInit } from '@angular/core';
import { DormitoriesService } from '../shared/dormitories.service';

@Component({
  selector: 'app-dormitories',
  templateUrl: './dormitories.component.html',
  styleUrls: ['./dormitories.component.css']
})
export class DormitoriesComponent implements OnInit {
dorms : any;
  constructor(private DormitoriesService : DormitoriesService) { }

getalldorm(){
  this.DormitoriesService.GetAllDorm().subscribe(data =>{
    this.dorms = data
  });
}

  ngOnInit(): void {
    this.getalldorm();
  }

}
