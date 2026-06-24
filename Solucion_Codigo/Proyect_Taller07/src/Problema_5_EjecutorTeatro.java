import java.util.Arrays;

/**
 * Problema 05: Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:

Se desea gestionar la venta de entradas para un espectáculo en un teatro. El patio de butacas del teatro se divide en varias zonas, cada una identificada por su nombre. 
*  Los datos de las zonas son los mostrados en la siguiente tabla:

NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
Principal	200	25$	17.5$
PalcoB	40	70$	40$
Central	400	20$	14$
Lateral	100	15.5$	10$
Para realizar la compra de una entrada, un espectador debe indicar la zona que desea y presentar al vendedor el documento que justifique que tiene algún tipo de descuento 
(estudiante, abonado o pensionista). El vendedor sacará la entrada del tipo apropiado y de la zona indicada, en el momento de la compra se asignará a la entrada un identificador 
(un número entero) que permitirá la identificación de la entrada en todas las operaciones que posteriormente se desee realizar con ella.

Una entrada tiene como datos asociados su identificador, la zona a la que pertenece y el nombre del comprador.

Los precios de las entradas dependen de la zona y del tipo de entrada según lo explicado a continuación:

Entradas normales: su precio es el precio normal de la zona elegida sin ningún tipo de descuento.
Entradas reducidas (para estudiantes o pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de la zona elegida.
Entradas abonado: su precio es el precio para abonados de la zona elegida.
La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos.
 *
 * @author Joan Salinas
 *
 * @version 1.0
 */
class ZonaTeatro{
    public String nombreZona;
    public int numeroLocalidades;
    public double precioNormal;
    public double precioAbonado;
    public int entradasVendidas;

    public ZonaTeatro(String nombreZona, int numeroLocalidades, double precioNormal, double precioAbonado) {
        this.nombreZona = nombreZona;
        this.numeroLocalidades = numeroLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }

    public boolean hayLocalidad(){
        return this.entradasVendidas < this.numeroLocalidades;
    }

    public void venderLocalidad(){
        this.entradasVendidas++;
    }

    @Override
    public String toString() {
        return "ZonaTeatro{" + "nombreZona=" + nombreZona + ", numeroLocalidades=" + numeroLocalidades + ", precioNormal=" + precioNormal + ", precioAbonado=" + precioAbonado + ", entradasVendidas=" + entradasVendidas + '}';
    }
}

class Entrada{
    public int identificador;
    public ZonaTeatro zona;
    public String nombreComprador;
    public double precio;

    public Entrada(int identificador, ZonaTeatro zona, String nombreComprador) {
        this.identificador = identificador;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
    }

    public double calcularPrecio(){
        return this.precio;
    }

    @Override
    public String toString() {
        return "Entrada{" + "identificador=" + identificador + ", zona=" + zona.nombreZona + ", nombreComprador=" + nombreComprador + ", precio=" + precio + '}';
    }
}

class EntradaNormal extends Entrada{

    public EntradaNormal(int identificador, ZonaTeatro zona, String nombreComprador) {
        super(identificador, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio(){
        return this.precio = this.zona.precioNormal;
    }

    @Override
    public String toString() {
        return "EntradaNormal{" + '}' + super.toString();
    }
}

class EntradaReducida extends Entrada{
    public double porcentajeDescuento;

    public EntradaReducida(double porcentajeDescuento, int identificador, ZonaTeatro zona, String nombreComprador) {
        super(identificador, zona, nombreComprador);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double calcularPrecio(){
        return this.precio = this.zona.precioNormal - (this.zona.precioNormal * (this.porcentajeDescuento / 100));
    }

    @Override
    public String toString() {
        return "EntradaReducida{" + "porcentajeDescuento=" + porcentajeDescuento + '}' + super.toString();
    }
}

class EntradaAbonado extends Entrada{

    public EntradaAbonado(int identificador, ZonaTeatro zona, String nombreComprador) {
        super(identificador, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio(){
        return this.precio = this.zona.precioAbonado;
    }

    @Override
    public String toString() {
        return "EntradaAbonado{" + '}' + super.toString();
    }
}

class VentaTeatro{
    public ZonaTeatro zonas[];
    public Entrada entradas[];
    public int contadorEntradas;

    public VentaTeatro(ZonaTeatro[] zonas) {
        this.zonas = zonas;
        this.entradas = new Entrada[20];
    }

    public ZonaTeatro buscarZona(String nombreZona){
        for (int i = 0; i < zonas.length; i++) {
            if(zonas[i].nombreZona.equalsIgnoreCase(nombreZona)){
                return zonas[i];
            }
        }
        return null;
    }

    public Entrada venderEntrada(String nombreZona, String nombreComprador, String tipo){
        ZonaTeatro zona = buscarZona(nombreZona);
        if(zona == null || !zona.hayLocalidad()){
            return null;
        }

        Entrada entrada;
        int identificador = contadorEntradas + 1;
        if(tipo.equalsIgnoreCase("abonado")){
            entrada = new EntradaAbonado(identificador, zona, nombreComprador);
        }else{
            if(tipo.equalsIgnoreCase("reducida")){
                entrada = new EntradaReducida(15, identificador, zona, nombreComprador);
            }else{
                entrada = new EntradaNormal(identificador, zona, nombreComprador);
            }
        }
        entrada.calcularPrecio();
        zona.venderLocalidad();
        entradas[contadorEntradas] = entrada;
        contadorEntradas++;
        return entrada;
    }

    public Entrada consultarEntrada(int identificador){
        for (int i = 0; i < contadorEntradas; i++) {
            if(entradas[i].identificador == identificador){
                return entradas[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "VentaTeatro{" + "zonas=" + Arrays.toString(zonas) + ", entradas=" + Arrays.toString(entradas) + ", contadorEntradas=" + contadorEntradas + '}';
    }
}

public class Problema_5_EjecutorTeatro {
    public static void main(String[] args) {
        ZonaTeatro zonas[] = {
            new ZonaTeatro("Principal", 200, 25, 17.5),
            new ZonaTeatro("PalcoB", 40, 70, 40),
            new ZonaTeatro("Central", 400, 20, 14),
            new ZonaTeatro("Lateral", 100, 15.5, 10)
        };

        VentaTeatro venta = new VentaTeatro(zonas);
        Entrada entrada1 = venta.venderEntrada("Principal", "Carlos Ruiz", "normal");
        Entrada entrada2 = venta.venderEntrada("Central", "Maria Torres", "reducida");
        Entrada entrada3 = venta.venderEntrada("PalcoB", "Ana Jimenez", "abonado");

        System.out.println(entrada1);
        System.out.println(entrada2);
        System.out.println(entrada3);
        System.out.println("Consulta: " + venta.consultarEntrada(2));
    }
}

/**
 * run:
EntradaNormal{}Entrada{identificador=1, zona=Principal, nombreComprador=Carlos Ruiz, precio=25.0}
EntradaReducida{porcentajeDescuento=15.0}Entrada{identificador=2, zona=Central, nombreComprador=Maria Torres, precio=17.0}
EntradaAbonado{}Entrada{identificador=3, zona=PalcoB, nombreComprador=Ana Jimenez, precio=40.0}
Consulta: EntradaReducida{porcentajeDescuento=15.0}Entrada{identificador=2, zona=Central, nombreComprador=Maria Torres, precio=17.0}
BUILD SUCCESSFUL (total time: 0 seconds)
 */