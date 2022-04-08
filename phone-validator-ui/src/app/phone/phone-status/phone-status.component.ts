import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {PhoneStatusService} from "../phone-status.service";

@Component({
  selector: 'app-phone-status',
  templateUrl: './phone-status.component.html',
  styleUrls: ['./phone-status.component.css']
})
export class PhoneStatusComponent implements OnInit {

  title = 'phone-validator-ui';
  dataSource = new MatTableDataSource<PhoneState>();

  constructor(private phoneService: PhoneStatusService) {
  }

  ngOnInit() {
    console.log("<<<<<< Initializing AppComponent >>>>>>>>");
    // @ts-ignore
    this.phoneService.getPhoneNumbers().subscribe(data => {
      console.log("Response: >>>> " + data);
      this.dataSource.data = data;
    });
  }

  columnsToDisplay = ['phone', 'country', 'code', 'status'];
}



export interface PhoneState {
  id: number;
  status: string;
  country: {
    id: number;
    code: string;
    name: string;
    phonePattern: string;
  };
  customer: {
    id: number;
    name: string;
    phone: string;
  };
}

const phoneNumbers: any[] = [
  {
    "id": 124,
    "status": "INVALID",
    "country": {
      "id": 3,
      "code": "+212",
      "name": "Morocco",
      "phonePattern": "\\(212\\)\\ ?[5-9]\\d{8}$"
    },
    "customer": {
      "id": 0,
      "name": "Walid Hammadi",
      "phone": "(212) 6007989253"
    }
  },
  {
    "id": 125,
    "status": "VALID",
    "country": {
      "id": 3,
      "code": "+212",
      "name": "Morocco",
      "phonePattern": "\\(212\\)\\ ?[5-9]\\d{8}$"
    },
    "customer": {
      "id": 1,
      "name": "Yosaf Karrouch",
      "phone": "(212) 698054317"
    }
  },
  {
    "id": 126,
    "status": "INVALID",
    "country": {
      "id": 3,
      "code": "+212",
      "name": "Morocco",
      "phonePattern": "\\(212\\)\\ ?[5-9]\\d{8}$"
    },
    "customer": {
      "id": 2,
      "name": "Younes Boutikyad",
      "phone": "(212) 6546545369"
    }
  },
  {
    "id": 127,
    "status": "INVALID",
    "country": {
      "id": 3,
      "code": "+212",
      "name": "Morocco",
      "phonePattern": "\\(212\\)\\ ?[5-9]\\d{8}$"
    },
    "customer": {
      "id": 3,
      "name": "Houda Houda",
      "phone": "(212) 6617344445"
    }
  }
]
