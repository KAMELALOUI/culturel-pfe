import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSitesComponent } from './create-sites.component';

describe('CreateSitesComponent', () => {
  let component: CreateSitesComponent;
  let fixture: ComponentFixture<CreateSitesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateSitesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateSitesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
