import java.util.Arrays;

/**
 * Problema 01: Dibuje un diagrama de clases que muestre la estructura de un capítulo de libro; un capítulo está compuesto por varias secciones, 
 * cada una de las cuales comprende varios párrafos y figuras. Un párrafo incluye varias sentencias, cada una de las cuales contiene varias palabras.
 * @author Joan Salinas
 *
 * @version 1.0
 */
class PalabraLibro{
    public String texto;

    public PalabraLibro(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "PalabraLibro{" + "texto=" + texto + '}';
    }
}

class SentenciaLibro{
    public PalabraLibro palabras[];

    public SentenciaLibro(PalabraLibro[] palabras) {
        this.palabras = palabras;
    }

    @Override
    public String toString() {
        return "SentenciaLibro{" + "palabras=" + Arrays.toString(palabras) + '}';
    }
}

class ComponenteSeccion{
    public String titulo;

    public ComponenteSeccion(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "ComponenteSeccion{" + "titulo=" + titulo + '}';
    }
}

class ParrafoLibro extends ComponenteSeccion{
    public SentenciaLibro sentencias[];

    public ParrafoLibro(SentenciaLibro[] sentencias, String titulo) {
        super(titulo);
        this.sentencias = sentencias;
    }

    @Override
    public String toString() {
        return "ParrafoLibro{" + "sentencias=" + Arrays.toString(sentencias) + '}' + super.toString();
    }
}

class FiguraLibro extends ComponenteSeccion{
    public String nombreArchivo;
    public String descripcion;

    public FiguraLibro(String nombreArchivo, String descripcion, String titulo) {
        super(titulo);
        this.nombreArchivo = nombreArchivo;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "FiguraLibro{" + "nombreArchivo=" + nombreArchivo + ", descripcion=" + descripcion + '}' + super.toString();
    }
}

class TablaLibro extends ComponenteSeccion{
    public int filas;
    public int columnas;

    public TablaLibro(int filas, int columnas, String titulo) {
        super(titulo);
        this.filas = filas;
        this.columnas = columnas;
    }

    @Override
    public String toString() {
        return "TablaLibro{" + "filas=" + filas + ", columnas=" + columnas + '}' + super.toString();
    }
}

class SeccionLibro{
    public String titulo;
    public ComponenteSeccion componentes[];

    public SeccionLibro(String titulo, ComponenteSeccion[] componentes) {
        this.titulo = titulo;
        this.componentes = componentes;
    }

    @Override
    public String toString() {
        return "SeccionLibro{" + "titulo=" + titulo + ", componentes=" + Arrays.toString(componentes) + '}';
    }
}

class CapituloLibro{
    public String titulo;
    public SeccionLibro secciones[];

    public CapituloLibro(String titulo, SeccionLibro[] secciones) {
        this.titulo = titulo;
        this.secciones = secciones;
    }

    @Override
    public String toString() {
        return "CapituloLibro{" + "titulo=" + titulo + ", secciones=" + Arrays.toString(secciones) + '}';
    }
}

public class Problema_1_EjecutorCapituloLibro {
    public static void main(String[] args) {
        PalabraLibro palabra1 = new PalabraLibro("capitulo");
        PalabraLibro palabra2 = new PalabraLibro("libro");
        PalabraLibro palabra3 = new PalabraLibro("seccion");
        PalabraLibro palabra4 = new PalabraLibro("figura");

        PalabraLibro palabrasSentencia1[] = {palabra1, palabra2};
        PalabraLibro palabrasSentencia2[] = {palabra1, palabra3};
        PalabraLibro palabrasSentencia3[] = {palabra4, palabra2};

        SentenciaLibro sentencia1 = new SentenciaLibro(palabrasSentencia1);
        SentenciaLibro sentencia2 = new SentenciaLibro(palabrasSentencia2);
        SentenciaLibro sentencia3 = new SentenciaLibro(palabrasSentencia3);

        SentenciaLibro sentenciasParrafo1[] = {sentencia1, sentencia2};
        SentenciaLibro sentenciasParrafo2[] = {sentencia3};

        ParrafoLibro parrafo1 = new ParrafoLibro(sentenciasParrafo1, "Parrafo 1");
        ParrafoLibro parrafo2 = new ParrafoLibro(sentenciasParrafo2, "Parrafo 2");
        FiguraLibro figura1 = new FiguraLibro("figura1.png", "Estructura del capitulo", "Figura 1");
        TablaLibro tabla1 = new TablaLibro(3, 4, "Tabla ejemplo");

        ComponenteSeccion componentesSeccion1[] = {parrafo1, figura1};
        ComponenteSeccion componentesSeccion2[] = {parrafo2, tabla1};

        SeccionLibro seccion1 = new SeccionLibro("Introduccion", componentesSeccion1);
        SeccionLibro seccion2 = new SeccionLibro("Desarrollo", componentesSeccion2);

        SeccionLibro secciones[] = {seccion1, seccion2};
        CapituloLibro capitulo = new CapituloLibro("Capitulo 1", secciones);

        System.out.println(capitulo);
    }
}
