package bancojava;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Conta {  
    private String nome;
    private int numeroConta;
    private double saldo;
    private double limiteDiario=0;
    public static List<Conta> contas = new ArrayList();
    static Random numero = new Random();
    
    public Conta(){  
        this.numeroConta = 1 + numero.nextInt(9999);
    } 
    
    public Conta(String nome, double saldo){
        this.nome = nome;
        this.saldo = saldo;
        this.numeroConta = 1 + numero.nextInt(9999);
    }
    
    public int getNumeroConta(){
        return numeroConta;
    }
    
     public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean saca(double valor) {
        if (this.saldo < valor) {
            return false;
        } else {
            this.saldo = this.saldo - valor;
            this.limiteDiario = this.limiteDiario + valor;
            return true;
        }
    }    
    
    public void deposita(double valor){
        this.saldo = this.saldo + valor;        
    }
    
    public double getLimiteDiario(){
        return limiteDiario;
    }
}
