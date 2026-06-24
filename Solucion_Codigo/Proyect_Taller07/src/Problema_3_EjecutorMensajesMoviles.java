/**
 * Problema 03: Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes que se pueden enviar entre móviles, 
 * mensajes de texto (SMS) y mensajes que contienen imágenes (MMS). Por un lado, los mensajes de texto contienen un mensaje en caracteres que se desea enviar de un móvil a otro.
 * Por otro lado, los mensajes que contienen imágenes almacenan información sobre la imagen a enviar, la cual se representará por el nombre del fichero que la contiene. 
 * Independientemente del tipo de mensaje, cada mensaje tendrá asociado un remitente de dicho mensaje y un destinatario. 
 * Ambos estarán definidos obligatoriamente por un número de móvil, y opcionalmente se podrá guardar información sobre su nombre. 
 * Además, los métodos enviarMensaje y visualizarMensaje deben estar definidos.
 *
 * @author Joan Salinas
 *
 * @version 1.0
 */
class ContactoMovil{
    public String numeroMovil;
    public String nombre;

    public ContactoMovil(String numeroMovil, String nombre) {
        this.numeroMovil = numeroMovil;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ContactoMovil{" + "numeroMovil=" + numeroMovil + ", nombre=" + nombre + '}';
    }
}

class MensajeMovil{
    public ContactoMovil remitente;
    public ContactoMovil destinatario;
    public String estado;

    public MensajeMovil(ContactoMovil remitente, ContactoMovil destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.estado = "Pendiente";
    }

    public void enviarMensaje(){
        this.estado = "Enviado";
    }

    @Override
    public String toString() {
        return "MensajeMovil{" + "remitente=" + remitente + ", destinatario=" + destinatario + ", estado=" + estado + '}';
    }
}

class Sms extends MensajeMovil{
    public String texto;

    public Sms(String texto, ContactoMovil remitente, ContactoMovil destinatario) {
        super(remitente, destinatario);
        this.texto = texto;
    }
    
    @Override
    public String toString() {
        return "Sms{" + "texto=" + texto + '}' + super.toString();
    }
}

class Mms extends MensajeMovil{
    public String nombreImg;

    public Mms(String nombreImg, ContactoMovil remitente, ContactoMovil destinatario) {
        super(remitente, destinatario);
        this.nombreImg = nombreImg;
    }

    @Override
    public String toString() {
        return "Mms{" + "nombreFichero=" + nombreImg + '}' + super.toString();
    }
}

public class Problema_3_EjecutorMensajesMoviles {
    public static void main(String[] args) {
        ContactoMovil remitente = new ContactoMovil("0991234567", "Carlos");
        ContactoMovil destinatario = new ContactoMovil("0987654321", "Maria");

        Sms mensajeTexto = new Sms("Hola, nos vemos en clase", remitente, destinatario);
        mensajeTexto.enviarMensaje();
        System.out.println(mensajeTexto);

        Mms mensajeImagen = new Mms("foto.png", destinatario, remitente);
        mensajeImagen.enviarMensaje();
        System.out.println(mensajeImagen);
    }
}
/**
run:
Sms{texto=Hola, nos vemos en clase}MensajeMovil{remitente=ContactoMovil{numeroMovil=0991234567, nombre=Carlos}, destinatario=ContactoMovil{numeroMovil=0987654321, nombre=Maria}, estado=Enviado}
Mms{nombreFichero=foto.png}MensajeMovil{remitente=ContactoMovil{numeroMovil=0987654321, nombre=Maria}, destinatario=ContactoMovil{numeroMovil=0991234567, nombre=Carlos}, estado=Enviado}
BUILD SUCCESSFUL (total time: 0 seconds)
 */