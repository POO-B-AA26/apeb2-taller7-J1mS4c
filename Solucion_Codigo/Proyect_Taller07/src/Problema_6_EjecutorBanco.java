/**
 * Problema 06: El banco UN BANCO mantiene las cuentas de varios clientes. Los datos que describen a cada una de las cuentas consisten en el número de cuenta, 
 * el nombre del cliente y el balance actual. Escriba una clase para implementar dicha cuenta bancaria. El método constructor debe aceptar como parámetros el número de cuenta
 * y el nombre. Debe proporcionarse métodos para depositar o retirar una cantidad de dinero y obtener el balance actual.

El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS. Una cuenta de cheques puede sobregirarse (el balance puede ser menor que cero), 
* pero una cuenta de ahorros no. Al final de cada mes, se calcula el interés sobre la cantidad que tenga la cuenta de ahorros. Este interés se suma al balance. 
* Escriba clases para describir cada uno de estos tipos de cuentas, haciendo un máximo uso de la herencia. La clase de la cuenta de ahorros debe proporcionar un método que sea
* invocado para calcular el interés. Además, el banco está pensando en implementar una cuenta PLATINO que viene siendo similar a los otros dos tipos anteriores de cuentas bancarias,
* ésta tiene el interés del 10%, sin cargos ni castigos por sobregiro.
 *
 * @author Joan Salinas
 *
 * @version 1.0
 */
/**
 * Problema 06: Sistema Un Banco con cuentas de cheques, ahorros y platino.
 *
 * @author Joan Salinas
 *
 * @version 1.0
 */
class CuentaBancaria{
    public String numeroCuenta;
    public String nombreCliente;
    public double balance;

    public CuentaBancaria(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
    }

    public void depositar(double cantidad){
        this.balance = this.balance + cantidad;
    }

    public boolean retirar(double cantidad){
        if(cantidad <= this.balance){
            this.balance = this.balance - cantidad;
            return true;
        }
        return false;
    }

    public double obtenerBalance(){
        return this.balance;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "numeroCuenta=" + numeroCuenta + ", nombreCliente=" + nombreCliente + ", balance=" + balance + '}';
    }
}

class CuentaCheques extends CuentaBancaria{
    public double limiteSobregiro;

    public CuentaCheques(double limiteSobregiro, String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.limiteSobregiro = limiteSobregiro;
    }

    public boolean retirarConSobregiro(double cantidad){
        if((this.balance - cantidad) >= (this.limiteSobregiro * -1)){
            this.balance = this.balance - cantidad;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "CuentaCheques{" + "limiteSobregiro=" + limiteSobregiro + '}' + super.toString();
    }
}

class CuentaAhorros extends CuentaBancaria{
    public double porcentajeInteres;

    public CuentaAhorros(double porcentajeInteres, String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.porcentajeInteres = porcentajeInteres;
    }

    public double calcularInteresAhorros(){
        double interes = this.balance * (this.porcentajeInteres / 100);
        this.balance = this.balance + interes;
        return interes;
    }

    @Override
    public String toString() {
        return "CuentaAhorros{" + "porcentajeInteres=" + porcentajeInteres + '}' + super.toString();
    }
}

class CuentaPlatino extends CuentaBancaria{
    public double porcentajeInteres;

    public CuentaPlatino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.porcentajeInteres = 10;
    }

    public boolean retirarPlatino(double cantidad){
        this.balance = this.balance - cantidad;
        return true;
    }

    public double calcularInteresPlatino(){
        double interes = this.balance * (this.porcentajeInteres / 100);
        this.balance = this.balance + interes;
        return interes;
    }

    @Override
    public String toString() {
        return "CuentaPlatino{" + "porcentajeInteres=" + porcentajeInteres + '}' + super.toString();
    }
}

public class Problema_6_EjecutorBanco {
    public static void main(String[] args) {
        CuentaCheques cuenta1 = new CuentaCheques(200, "001-100", "Juan Perez");
        cuenta1.depositar(100);
        cuenta1.retirarConSobregiro(250);
        System.out.println("Estado de cuenta: " + cuenta1);

        CuentaAhorros cuenta2 = new CuentaAhorros(5, "001-200", "Maria Loja");
        cuenta2.depositar(800);
        cuenta2.retirar(100);
        cuenta2.calcularInteresAhorros();
        System.out.println("Estado de cuenta: " + cuenta2);

        CuentaPlatino cuenta3 = new CuentaPlatino("001-300", "Carlos Mora");
        cuenta3.depositar(1000);
        cuenta3.retirarPlatino(1200);
        cuenta3.calcularInteresPlatino();
        System.out.println("Estado de cuenta: " + cuenta3);
    }
}
/**
 * run:
Estado de cuenta: CuentaCheques{limiteSobregiro=200.0}CuentaBancaria{numeroCuenta=001-100, nombreCliente=Juan Perez, balance=-150.0}
Estado de cuenta: CuentaAhorros{porcentajeInteres=5.0}CuentaBancaria{numeroCuenta=001-200, nombreCliente=Maria Loja, balance=735.0}
Estado de cuenta: CuentaPlatino{porcentajeInteres=10.0}CuentaBancaria{numeroCuenta=001-300, nombreCliente=Carlos Mora, balance=-220.0}
BUILD SUCCESSFUL (total time: 0 seconds)

 */