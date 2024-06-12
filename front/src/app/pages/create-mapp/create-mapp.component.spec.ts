import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMappComponent } from './create-mapp.component';

describe('CreateMappComponent', () => {
  let component: CreateMappComponent;
  let fixture: ComponentFixture<CreateMappComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateMappComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateMappComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
