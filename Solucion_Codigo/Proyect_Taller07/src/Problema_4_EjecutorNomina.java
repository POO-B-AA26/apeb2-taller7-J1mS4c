
/**
 * Problema 04: Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. 
 * Los datos personales de los trabajadores son nombre y apellidos, dirección y DNI. Además, existen diferentes tipos de trabajadores:
    - Fijos Mensuales: que cobran una cantidad fija al mes.
    - Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
    - Por Horas: cobran un precio por cada una de las horas que han realizado durante el mes. El precio es fijo para las primeras 40 horas y es otro para las horas realizadas a partir de la 40 hora mensual.
    - Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene obligatoriamente un jefe (exceptuando los jefes que no tienen ninguno). El programa debe permitir dar de alta a trabajadores, así como fijar horas o ventas realizadas e imprimir la nómina correspondiente al final de mes.
 *
 * @author Joan Salinas
 *
 * @version 1.0
 */
import java.util.Random;

class Trabajador {
    public String nombres;
    public String apellidos;
    public String direccion;
    public String dni;
    public Jefe jefe;
    public double sueldo;

    public Trabajador(String nombres, String apellidos, String direccion, String dni, Jefe jefe) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }

    public String toString() { 
        return "Trabajador{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", sueldo=" + String.format("%.2f", sueldo) + '}'; 
    }
}

class Jefe extends Trabajador {
    public String departamento;

    public Jefe(String departamento, double sueldo, String nombres, String apellidos, String direccion, String dni) {
        super(nombres, apellidos, direccion, dni, null);
        this.departamento = departamento;
        this.sueldo = sueldo;
    }

    public String toString() {
        return "Jefe{" + "departamento=" + departamento + "} " + super.toString();
    }
}

class FijoMensual extends Trabajador {

    public FijoMensual(double sueldoMensual, String nombres, String apellidos, String direccion, String dni, Jefe jefe) {
        super(nombres, apellidos, direccion, dni, jefe);
        this.sueldo = sueldoMensual;
    }

    public String toString() { 
        return "FijoMensual{" + super.toString() + "}"; 
    }
}

class Comisionista extends Trabajador {
    public double ventasRealizadas;
    public double porcentajeComision;

    public Comisionista(double ventasRealizadas, double porcentajeComision, String nombres, String apellidos, String direccion, String dni, Jefe jefe) {
        super(nombres, apellidos, direccion, dni, jefe);
        this.ventasRealizadas = ventasRealizadas;
        this.porcentajeComision = porcentajeComision;
        this.sueldo = ventasRealizadas * (porcentajeComision / 100);
    }

    public String toString() { 
        return "Comisionista{" + "ventasRealizadas=" + ventasRealizadas + ", porcentajeComision=" + porcentajeComision + "} " + super.toString(); 
    }
}

class PorHoras extends Trabajador {
    public int horasTrabajadas;
    public double precioHoraNormal;
    public double precioHoraExtra;

    public PorHoras(int horasTrabajadas, double precioHoraNormal, double precioHoraExtra, String nombres, String apellidos, String direccion, String dni, Jefe jefe) {
        super(nombres, apellidos, direccion, dni, jefe);
        this.horasTrabajadas = horasTrabajadas;
        this.precioHoraNormal = precioHoraNormal;
        this.precioHoraExtra = precioHoraExtra;

        if (horasTrabajadas <= 40) this.sueldo = horasTrabajadas * precioHoraNormal;
        else this.sueldo = (40 * precioHoraNormal) + ((horasTrabajadas - 40) * precioHoraExtra);
    }

    public String toString() { 
        return "PorHoras{" + "horasTrabajadas=" + horasTrabajadas + "} " + super.toString(); 
    }
}

public class Problema_4_EjecutorNomina {
    public static void main(String[] args) {

        Random r = new Random();

        double sueldoJefe = 1200 + r.nextInt(1000);
        int horas = 20 + r.nextInt(41);
        double ventas = 1000 + r.nextInt(5001);
        double porcentaje = 5 + r.nextInt(11);
        double precioNormal = 4 + r.nextInt(7);
        double precioExtra = 8 + r.nextInt(9);

        Jefe jefe1 = new Jefe("Sistemas", sueldoJefe, "Ana", "Lopez", "Loja", "1101234567");
        FijoMensual t1 = new FijoMensual(650, "Luis", "Perez", "Catamayo", "1107654321", jefe1);
        Comisionista t2 = new Comisionista(ventas, porcentaje, "Sofia", "Mora", "Loja", "1105555555", jefe1);
        PorHoras t3 = new PorHoras(horas, precioNormal, precioExtra, "Mario", "Castillo", "Zamora", "1109999999", jefe1);

        System.out.println(jefe1);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
    }
}
/**
Jefe{departamento=Sistemas} Trabajador{nombres=Ana, apellidos=Lopez, sueldo=2183.00}
FijoMensual{Trabajador{nombres=Luis, apellidos=Perez, sueldo=650.00}}
Comisionista{ventasRealizadas=2054.0, porcentajeComision=8.0} Trabajador{nombres=Sofia, apellidos=Mora, sueldo=164.32}
PorHoras{horasTrabajadas=22} Trabajador{nombres=Mario, apellidos=Castillo, sueldo=110.00}
BUILD SUCCESSFUL (total time: 0 seconds)

 */