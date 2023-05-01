package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forproject2.forproject2ipc2_2023.modelo.Consultas.Consultas;
import com.forproject2.forproject2ipc2_2023.modelo.Especialidades.Especialidades;
import com.forproject2.forproject2ipc2_2023.modelo.Examenes.Examenes;
import com.forproject2.forproject2ipc2_2023.modelo.Horarios.Horarios;
import com.forproject2.forproject2ipc2_2023.modelo.Solicitudes.Solicitudes;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Administrador.Administrador;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Laboratorios.Laboratorios;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Medicos.Medicos;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Paciente.Pacientes;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Personas;
import com.forproject2.forproject2ipc2_2023.modelo.cobroApp.CobroApicacion;
import com.forproject2.forproject2ipc2_2023.servicios.ServicioCargaDatos;
import com.forproject2.forproject2ipc2_2023.servicios.ServicioCobroApp;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.math.BigInteger;
import java.security.MessageDigest;


@MultipartConfig
@WebServlet(name = "cargaDatos", urlPatterns = {"/cargaDatos"})
public class ControladorCargaDatos extends HttpServlet {
    private Conexion conexion = new Conexion();
    private ServicioCargaDatos servicioCargaDatos = new ServicioCargaDatos();
    private UtilidadesJSON utilidad = new UtilidadesJSON();
    private ServicioCobroApp servicioCobroApp = new ServicioCobroApp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        conexion.conectar();
        PrintWriter out = response.getWriter();
        //StringBuilder bufer = new StringBuilder();
        BufferedReader in = request.getReader();
        Object obj = null;

        JSONParser jparser = new JSONParser();
        try {
            obj = jparser.parse(in);
        } catch (ParseException ex) {
            Logger.getLogger(ControladorCargaDatos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido cargar los datos");
        }
        JSONObject general = (JSONObject) obj;
        JSONObject administrador = (JSONObject) general.get("admin");
        List<JSONObject> listaEspecialidades = (List<JSONObject>) general.get("especialidades");
        List<JSONObject> listaExamenes = (List<JSONObject>) general.get("tipos_examenes");
        List<JSONObject> listaMedicos = (List<JSONObject>) general.get("medicos");
        List<JSONObject> listaLaboratorios = (List<JSONObject>) general.get("laboratorios");
        List<JSONObject> listaPacientes = (List<JSONObject>) general.get("pacientes");
        List<JSONObject> listaConsultas = (List<JSONObject>) general.get("consultas");
        List<JSONObject> listaSolicitudes = (List<JSONObject>) general.get("solicitudes");

        guardarAdministradores(administrador);
        guardarEspecialidad(listaEspecialidades);
        guardarExamenes(listaExamenes);
        guardarMedicos(listaMedicos);
        guardarLaboratorios(listaLaboratorios);
        guardarPacientes(listaPacientes);
        guardarConsultas(listaConsultas);
        guardarSolicitudes(listaSolicitudes);


        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        out.print("{\"message\": \"Archivo guardado\"}");
        conexion.cerrarConexion();
    }

   /* private void doPost2(){
        conexion.conectar();
        PrintWriter out = response.getWriter();
        //out.print("Hola mundo");
        //Conexion.errores = new ArrayList<>();

        Part filePart = request.getPart("file");
        InputStream fileStream = filePart.getInputStream();
        Object obj = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(fileStream))) {

            JSONParser jparser = new JSONParser();
            try {
                obj = jparser.parse(in);
            } catch (ParseException ex) {
                Logger.getLogger(ControladorCargaDatos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("No se ha podido cargar los datos");
            }
            JSONObject general = (JSONObject) obj;
            JSONObject administrador = (JSONObject) general.get("admin");
            List<JSONObject> listaEspecialidades = (List<JSONObject>) general.get("especialidades");
            List<JSONObject> listaExamenes = (List<JSONObject>) general.get("tipos_examenes");
            List<JSONObject> listaMedicos = (List<JSONObject>) general.get("medicos");
            List<JSONObject> listaLaboratorios = (List<JSONObject>) general.get("laboratorios");
            List<JSONObject> listaPacientes = (List<JSONObject>) general.get("pacientes");
            List<JSONObject> listaConsultas = (List<JSONObject>) general.get("consultas");
            List<JSONObject> listaSolicitudes = (List<JSONObject>) general.get("solicitudes");

            guardarAdministradores(administrador);
            guardarEspecialidad(listaEspecialidades);
            guardarExamenes(listaExamenes);
            guardarMedicos(listaMedicos);
            guardarLaboratorios(listaLaboratorios);
            guardarPacientes(listaPacientes);
            guardarConsultas(listaConsultas);
            guardarSolicitudes(listaSolicitudes);
        }
        conexion.cerrarConexion();
    }*/


    public void guardarAdministradores(JSONObject administrador) {
        int id;
        String nombre;
        String userName;
        String password;
        String gmail;
        String nacimiento;
        double saldo;

        long codigo = (Long) administrador.get("id");
        id = (int) codigo;

        nombre = (String) administrador.get("nombre");
        userName = (String) administrador.get("username");
        password = getMD5((String) administrador.get("password"));
        gmail = (String) administrador.get("email");
        nacimiento = (String) administrador.get("fecha_nacimiento");
        try {
            saldo = (double) administrador.get("saldo");
        } catch (Exception e) {
            long cost = (Long) administrador.get("saldo");
            saldo = (double) cost;
        }

        var admin = new Administrador(id, nombre, userName, password, gmail, nacimiento, saldo);
        System.out.println(admin.getIdUsuario() + "\n" + admin.getNombre() + "\n"
                + admin.getUserName() + "\n" + admin.getPassword() + "\n" + admin.getEmail() + "\n"
                + admin.getFechaNacimiento() + "\n" + admin.getSaldo());

        servicioCargaDatos.guardarAdmin(admin, conexion);

    }

    public void guardarEspecialidad(List<JSONObject> listaEspecialidades) {
        for (JSONObject especialidad : listaEspecialidades) {
            long codigo = (Long) especialidad.get("id");
            int id = (int) codigo;
            String nombre = (String) especialidad.get("nombre");
            String descripcion = (String) especialidad.get("descripcion");

            var nuevaEspecialidad = new Especialidades(id, nombre, descripcion);
            System.out.println(nuevaEspecialidad.getId() + "\n" + nuevaEspecialidad.getNombre() + "\n"
                    + nuevaEspecialidad.getDescripcion());
            servicioCargaDatos.guardarEspecialidad(nuevaEspecialidad, conexion);
        }
    }

    public void guardarExamenes(List<JSONObject> listaExamenes) {
        for (JSONObject examen : listaExamenes) {
            long codigo = (Long) examen.get("id");
            int id = (int) codigo;
            var nuevoExamen = new Examenes(id, (String) examen.get("nombre"), (String) examen.get("descripcion"));
            System.out.println(nuevoExamen.getId() + "\n" + nuevoExamen.getNombre() + "\n" + nuevoExamen.getDescripcion());
            servicioCargaDatos.guardarTiposExamenes(nuevoExamen, conexion);
        }
    }

    public void guardarMedicos(List<JSONObject> listaMedicos) {
        for (JSONObject medico : listaMedicos) {
            List<JSONObject> horarios = (List<JSONObject>) medico.get("horarios");
            Personas m = desglosarPersonas(medico, false);
            var newMedic = new Medicos(m.getIdUsuario(), m.getNombre(), m.getUserName(), getMD5(m.getPassword()), m.getEmail(),
                    m.getFechaNacimiento(), m.getSaldo(), m.getDireccion(), m.getCui(), m.getTelefono());
            System.out.println(newMedic.getIdUsuario() + "\n" + newMedic.getNombre() + "\n" + newMedic.getUserName() + "\n" + getMD5(newMedic.getPassword()) + "\n" + newMedic.getEmail() + "\n" + newMedic.getFechaNacimiento() + "\n" + newMedic.getSaldo() +
                    "\n" + newMedic.getDireccion() + "\n" + newMedic.getCui() + "\n" + newMedic.getTelefono());
            System.out.println("Horarios:");
            for (Horarios horario : horariosMedico(horarios, newMedic.getIdUsuario())) {
                System.out.println(horario.getIdMedico() + ", " + horario.getHoraEntrada() + " a " + horario.getHoraSalida());
            }
            System.out.println("Especialidades:");
            for (Especialidades esp : especialidadesMedicas((List<JSONObject>) medico.get("especialidades"))) {
                System.out.println(newMedic.getIdUsuario() + ", " + esp.getId() + ", " + esp.getPrecio() + ", " + "ACEPTADA");
            }
            //agregar a medicos
            servicioCargaDatos.guardarMedicos(newMedic, conexion, horariosMedico(horarios, newMedic.getIdUsuario()), especialidadesMedicas((List<JSONObject>) medico.get("especialidades")));
        }
    }

    public List<Horarios> horariosMedico(List<JSONObject> horarios, int idMedico) {
        List<Horarios> nuevosHorarrios = new ArrayList<>();
        Object[] h = horarios.toArray();
        String[] horas;
        for (int i = 0; i < h.length; i++) {
            horas = (h[i].toString()).split("-");
            var horario = new Horarios(idMedico, horas[0], horas[1]);
            nuevosHorarrios.add(horario);
        }
        return nuevosHorarrios;
    }

    public List<Especialidades> especialidadesMedicas(List<JSONObject> listaEspe) {
        List<Especialidades> listaEspecialidades = new ArrayList<>();
        for (JSONObject especialidad : listaEspe) {
            long codigo = (Long) especialidad.get("id");
            double precio;
            try {
                precio = (double) especialidad.get("precio");
            } catch (Exception e) {
                long cost = (Long) especialidad.get("precio");
                precio = (double) cost;
            }
            var nuevaEspecialidad = new Especialidades((int) codigo, precio);
            listaEspecialidades.add(nuevaEspecialidad);
        }
        return listaEspecialidades;
    }


    public Personas desglosarPersonas(JSONObject persona, boolean esLab) {
        int idUsuario;
        long codigo = (Long) persona.get("id");
        idUsuario = (int) codigo;
        String nombre = (String) persona.get("nombre");
        String userName = (String) persona.get("username");
        String password = (String) persona.get("password");
        String gmail = (String) persona.get("email");
        String fechaNacimiento;
        if (esLab) {
            fechaNacimiento = (String) persona.get("fecha_fundacion");
        } else {
            fechaNacimiento = (String) persona.get("fecha_nacimiento");
        }
        double saldo;
        try {
            saldo = (double) persona.get("saldo");
        } catch (Exception e) {
            long cost = (Long) persona.get("saldo");
            saldo = (double) cost;
        }
        String direccion = (String) persona.get("direccion");

        String cui = (String) persona.get("cui");
        String telefono = (String) persona.get("telefono");
        var nuevapersona = new Personas(idUsuario, nombre, userName, password, gmail, fechaNacimiento, saldo, direccion, cui, telefono);
        return nuevapersona;
    }

    public void guardarLaboratorios(List<JSONObject> listaLabs) {
        for (JSONObject laboratorio : listaLabs) {
            List<JSONObject> examenes = (List<JSONObject>) laboratorio.get("examenes");
            Personas m = desglosarPersonas(laboratorio, true);
            var newLab = new Laboratorios(m.getIdUsuario(), m.getNombre(), m.getUserName(), getMD5(m.getPassword()), m.getEmail(),
                    m.getFechaNacimiento(), m.getSaldo(), m.getDireccion(), m.getCui(), m.getTelefono());
            System.out.println(newLab.getIdUsuario() + "\n" + newLab.getNombre() + "\n" + newLab.getUserName() + "\n" + getMD5(newLab.getPassword()) + "\n" + newLab.getEmail() + "\n" + newLab.getFechaNacimiento() + "\n" + newLab.getSaldo() +
                    "\n" + newLab.getDireccion() + "\n" + newLab.getCui() + "\n" + newLab.getTelefono());
            System.out.println("Examenes:");
            for (Examenes ex : examenesLaboratorio(examenes)) {
                System.out.println(newLab.getIdUsuario() + ", " + ex.getId() + ", " + ex.getPrecio() + ", ACEPTADA");
            }
            servicioCargaDatos.guardarLaboratorios(newLab, conexion, examenesLaboratorio(examenes));
        }
    }

    public List<Examenes> examenesLaboratorio(List<JSONObject> examenes) {
        List<Examenes> listaExamenes = new ArrayList<>();
        for (JSONObject examen : examenes) {
            long codigo = (Long) examen.get("id");
            double precio;
            try {
                precio = (double) examen.get("precio");
            } catch (Exception e) {
                long cost = (Long) examen.get("precio");
                precio = (double) cost;
            }
            var newExamen = new Examenes((int) codigo, precio);
            listaExamenes.add(newExamen);
        }
        return listaExamenes;
    }

    public void guardarPacientes(List<JSONObject> listaPacientes) {
        for (JSONObject paciente : listaPacientes) {
            System.out.println("Pacientes:");
            Personas m = desglosarPersonas(paciente, false);
            var newPaciente = new Pacientes(m.getIdUsuario(), m.getNombre(), m.getUserName(), getMD5(m.getPassword()), m.getEmail(),
                    m.getFechaNacimiento(), m.getSaldo(), m.getDireccion(), m.getCui(), m.getTelefono());
            System.out.println(newPaciente.getIdUsuario() + "\n" + newPaciente.getNombre() + "\n" + newPaciente.getUserName() + "\n" + getMD5(newPaciente.getPassword()) + "\n" + newPaciente.getEmail() + "\n" + newPaciente.getFechaNacimiento() + "\n" + newPaciente.getSaldo() +
                    "\n" + newPaciente.getDireccion() + "\n" + newPaciente.getCui() + "\n" + newPaciente.getTelefono());

            servicioCargaDatos.guardarPacientes(newPaciente, conexion);
        }
    }

    public void guardarConsultas(List<JSONObject> listaConsultas) {
        double porcentaje1 = 0;
        String fecha="";
        for (JSONObject consulta : listaConsultas) {
            List<JSONObject> listaExamenes = (List<JSONObject>) consulta.get("examenes_solicitados");
            long codigo = (Long) consulta.get("id");
            long cPaciente = (Long) consulta.get("paciente");
            long cMedico = (Long) consulta.get("medico");
            long cEspecialidad = (Long) consulta.get("especialidad");
            double porcentaje;
            try {
                porcentaje = (double) consulta.get("porcentaje_aplicacion");
            } catch (Exception e) {
                long cost = (Long) consulta.get("porcentaje_aplicacion");
                porcentaje = (double) cost;
            }
            double precio;
            try {
                precio = (double) consulta.get("precio");
            } catch (Exception e) {
                long cost = (Long) consulta.get("precio");
                precio = (double) cost;
            }
            var nCons = new Consultas((int) codigo, (int) cPaciente, (int) cMedico,
                    (int) cEspecialidad, porcentaje, (String) consulta.get("fecha_creacion"),
                    (String) consulta.get("fecha_agendada"), precio,
                    (String) consulta.get("informe_finalizacion"), (String) consulta.get("estado"));

            System.out.println(nCons.getIdConsulta() + "\n" + nCons.getIdPaciente() + "\n" + nCons.getIdMedico() + "\n"
                    + nCons.getIdEspecialidad() + "\n" + nCons.getPorcentajeCobroApp() + "\n" + nCons.getFechaCreacion() + "\n"
                    + nCons.getFechaAgendada() + "\n" + nCons.getPrecio() + "\n" + nCons.getInforme() + "\n" + nCons.getEstado());
            System.out.println("Examenes:");
            for (Examenes examen : examenesConsulta(listaExamenes)) {
                System.out.println(examen.getId());
            }
            fecha = (String) consulta.get("fecha_creacion"); porcentaje1=porcentaje;
            servicioCargaDatos.guardarConsultas(nCons,conexion,examenesConsulta(listaExamenes));
        }
        CobroApicacion cobro=new CobroApicacion(fecha,porcentaje1);
        servicioCobroApp.crearCobroApp(conexion,cobro);
    }

    public List<Examenes> examenesConsulta(List<JSONObject> examenes) {
        List<Examenes> listaExamenes = new ArrayList<>();
        for (JSONObject examen : examenes) {
            long codigo = (Long) examen.get("id");
            var examenNuevo = new Examenes((int) codigo);
            listaExamenes.add(examenNuevo);
        }
        return listaExamenes;
    }

    public void guardarSolicitudes(List<JSONObject> listaSolicitudes){
        for(JSONObject solicitud: listaSolicitudes){
            List<JSONObject> listaExamenes = (List<JSONObject>) solicitud.get("examenes");
            long codigo =(Long)solicitud.get("id");
            long cPaciente = (Long)solicitud.get("paciente");
            long cLab = (Long)solicitud.get("laboratorio");
            double porcentaje;
            try {
                porcentaje = (double) solicitud.get("porcentaje_aplicacion");
            } catch (Exception e) {
                long cost = (Long) solicitud.get("porcentaje_aplicacion");
                porcentaje = (double) cost;
            }
            String fechaSolicitado = (String) solicitud.get("fecha_solicitado");
            String fechaFinalizado = (String) solicitud.get("fecha_finalizado");
            String estado = (String) solicitud.get("estado_solicitud");
            var newSolic = new Solicitudes((int)codigo,(int)cPaciente,(int)cLab,porcentaje,fechaSolicitado,fechaFinalizado,estado);
            System.out.println(newSolic.getIdSolicitud() + "\n"+ newSolic.getIdPaciente()+ "\n"+newSolic.getIdLaboratorio() + "\n"+newSolic.getPorcentajeCobroApp()+ "\n"
                    + newSolic.getFechaSolicitada()+ "\n"+newSolic.getFechaFinalizada()+ "\n"+newSolic.getEstado());
            System.out.println("Examenes:");
            for(Examenes examen: examenesSolicitud(listaExamenes)){
                System.out.println(examen.getId() + ", " + examen.getPrecio());
            }
            servicioCargaDatos.guardarSolicitud(newSolic,conexion, examenesSolicitud(listaExamenes));
        }
    }
    public List<Examenes> examenesSolicitud(List<JSONObject> examenes){
        List<Examenes> listaExamenes = new ArrayList<>();
        for(JSONObject examen: examenes){
            long id = (Long)examen.get("id");
            double precio;
            try {
                precio = (double) examen.get("precio");
            } catch (Exception e) {
                long cost = (Long) examen.get("precio");
                precio = (double) cost;
            }
            var newExamen = new Examenes((int)id,precio);
            listaExamenes.add(newExamen);
        }
        return listaExamenes;
    }
    public String getMD5(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encBytes = md.digest(texto.getBytes());
            BigInteger numero = new BigInteger(1, encBytes);
            String encString = numero.toString(16);
            while (encString.length() < 32) {
                encString = "0" + encString;
            }
            return encString;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
