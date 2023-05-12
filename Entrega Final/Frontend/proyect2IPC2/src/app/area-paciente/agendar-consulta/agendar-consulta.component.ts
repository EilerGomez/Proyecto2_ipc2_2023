import { Component, OnInit } from '@angular/core';
import { Especialidades } from 'src/app/Modelo/Especialidades';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceBuscarMedicosService } from 'src/app/Service/service-buscar-medicos.service';
import{ServiceService} from 'src/app/Service/service.EspecialidadesMedicas'

@Component({
  selector: 'app-agendar-consulta',
  templateUrl: './agendar-consulta.component.html',
  styleUrls: ['./agendar-consulta.component.css']
})
export class AgendarConsultaComponent implements OnInit{
  especialidades!:Especialidades[]
  constructor(private service: ServiceService, private sercicioBuscarMedico:ServiceBuscarMedicosService){}
  ngOnInit(): void {
    this.service.getEspecialidadesDeLaAPP()
    .subscribe(data=>{
      this.especialidades=data;

    }, error=>{
      console.log(error)
    });
  }
  mostrarguardarConsulta : boolean = false;
  medicos!:Medicos[]
  medicoEdit!:Medicos
  idEsp!:number

  buscar(nombre:String, idEsp:string){
    this.idEsp=parseInt(idEsp);
    if(nombre==null||nombre==""){
      this.sercicioBuscarMedico.getMedicoPorEspecialidad(idEsp)
      .subscribe(data=>{
        this.medicos=data;
      }, error=>{
        console.log(error)
        var medico:Medicos[]=[]
        this.medicos=medico;
      });
    }else{
      this.sercicioBuscarMedico.getMedicoPorNombre(nombre)
      .subscribe(data=>{
        this.medicos=data
      },error=>{        
        console.log(error)
        var medico:Medicos[]=[]
        this.medicos=medico;
      })
    }
  }

  SeleccionarMedico(medico:Medicos){
    this.medicoEdit=medico
    this.mostrarguardarConsulta=true;
  }
}
