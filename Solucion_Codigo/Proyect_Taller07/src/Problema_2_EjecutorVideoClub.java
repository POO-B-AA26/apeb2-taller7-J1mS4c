import java.util.Arrays;

/**
 * Problema 02: Un videoclub dispone de una serie de películas que pueden estar en DVD (con capacidad en Gb.)
 * o en VHS (una sola cinta por película y con cierto tipo de cinta magnetica). De las películas interesa guardar el título, 
 * el autor, el año de edición y el idioma (o los idiomas, en caso de DVD). El precio de alquiler de las películas varía en función del tipo de película. 
 * Las DVD siempre son 10% mas caras que las de VHS.
 * 
 * @author Joan Salinas
 * 
 * @version 1.0
 */
class Pelicula{
    public String tituloPeli;
    public String autor;
    public int anio;

    public Pelicula(String tituloPeli, String autor, int anio) {
        this.tituloPeli = tituloPeli;
        this.autor = autor;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "tituloPeli=" + tituloPeli + ", autor=" + autor + ", anio=" + anio + '}';
    }
    
}

class Soporte{
    public Pelicula pelicula;
    public int cantidad;
    public double precio;
    public double costoAlquiler;

    public Soporte(Pelicula pelicula, int cantidad, double precio) {
        this.pelicula = pelicula;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    public double calcularCostoAlquiler(){
        return this.costoAlquiler = this.cantidad * this.precio;
    }

    @Override
    public String toString() {
        return "Soporte{" + "pelicula=" + pelicula + ", cantidad=" + cantidad + ", precio=" + precio + ", costoAlquiler=" + costoAlquiler + '}';
    }
}

class Vhs extends Soporte{
    public String idioma;

    public Vhs(String idioma, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Vhs{" + "idioma=" + idioma + '}'+super.toString();
    }
    
}

class DvD extends Soporte{
    public String idiomas[];
    public double porcentajeRecargo;

    public DvD(String[] idiomas, double porcentajeRecargo, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.idiomas = idiomas;
        this.porcentajeRecargo = porcentajeRecargo;
    }
    
   public double calcularCostoAlquiler(){
       return this.costoAlquiler = super.calcularCostoAlquiler() + (this.costoAlquiler*(this.porcentajeRecargo/100));
   }

    @Override
    public String toString() {
        return "DvD{" + "idiomas=" + Arrays.toString(idiomas) + ", porcentajeRecargo=" + porcentajeRecargo + '}'+super.toString();
    }
   
}
public class Problema_2_EjecutorVideoClub {
    public static void main(String[] args) {
        String idiomas[] = {"ES", "ENG"};
        Pelicula peli = new Pelicula("Harry Potter", "J.K Rowling", 2002);
        DvD dvd1 = new DvD(idiomas, 10, peli, 2, 10);
        dvd1.calcularCostoAlquiler();
        System.out.println(dvd1);
        Vhs vhs1 = new Vhs(idiomas[0], peli, 2, 10);
        vhs1.calcularCostoAlquiler();
        System.out.println(vhs1);
    }
}
/**
 * DvD{idiomas=[ES, ENG], porcentajeRecargo=10.0}Soporte{pelicula=Pelicula{tituloPeli=Harry Potter, autor=J.K Rowling, anio=2002}, cantidad=2, precio=10.0, costoAlquiler=22.0}
 * Vhs{idioma=ES}Soporte{pelicula=Pelicula{tituloPeli=Harry Potter, autor=J.K Rowling, anio=2002}, cantidad=2, precio=10.0, costoAlquiler=20.0}
 * BUILD SUCCESSFUL (total time: 0 seconds)
 */