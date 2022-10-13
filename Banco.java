package bancojava;

import java.util.Scanner;

public class Banco {

    public static Scanner ler = new Scanner(System.in);
    static int numeroContaMov;

    public static void main(String[] args) {
        int opcao = 0;
        do {
            menu1();
            opcao = Integer.parseInt(ler.nextLine());
            if (opcao == 1) {
                criarConta();
            }
            if (opcao == 2) {
                System.out.println("Digite o número da conta: ");
                numeroContaMov = Integer.parseInt(ler.nextLine());
                for (Conta variavel : Conta.contas) {
                    if (variavel.getNumeroConta() == numeroContaMov) {
                        movimentar();
                    }else{
                        System.out.println("Número de conta inválida!");
                        System.out.println("Tente nvamente!");
                    }
                }
            }
            if (opcao == 3) {
                listar();

            }
        } while (opcao != 4);
    }

    public static void menu1() {
        System.out.println("***Banco Java***");
        System.out.println("Menu de Opções");
        System.out.println("1 - Criar conta corrente");
        System.out.println("2 - Movimentar conta");
        System.out.println("3 - Listar contas");
        System.out.println("4 - Sair");
        System.out.print("Opção:");
    }

    public static void menu2() {
        System.out.println("***Movimentação de Conta***");
        System.out.println("1 - Sacar");
        System.out.println("2 - Depositar");
        System.out.println("3 - Extrato");
        System.out.println("4 - Sair");
        System.out.print("Opção:");
    }

    public static void criarConta() {
        Conta nova = new Conta();
        System.out.print("Digite o nome do cliente: ");
        nova.setNome(ler.nextLine());
        System.out.print("Digite o valor do depósito inicial: R$ ");
        nova.setSaldo(Double.parseDouble(ler.nextLine()));
        Conta.contas.add(nova);

        System.out.println("A conta de " + nova.getNome()
                + " numero " + nova.getNumeroConta() + " tem saldo de R$ " + nova.getSaldo() + " criada com sucesso!");
    }

    public static void movimentar() {
        int opcao2;
        do {
            menu2();
            opcao2 = Integer.parseInt(ler.nextLine());
            if (opcao2 == 1) {
                sacar();
            }
            if (opcao2 == 2) {
                depositar();
            }
            if (opcao2 == 3) {
                solicitarExtrato();
            }
        } while (opcao2 != 4);
    }

    public static void listar() {
        System.out.println("***Listagem das Contas***");
        if (Conta.contas.isEmpty()) {
            System.out.println("\nNão existem contas cadastradas!");
        } else {
            double saldoGlobal = 0;
            int qtdContas = 0;
            for (Conta variavel : Conta.contas) {
                System.out.println("Cliente " + variavel.getNome() + " Saldo " + variavel.getSaldo());
                saldoGlobal = saldoGlobal + variavel.getSaldo();
                qtdContas++;
            }
            System.out.println(qtdContas + " contas no total de R$ " + saldoGlobal);
        }
    }

    public static void sacar() {
        double valor;
        System.out.println("\n***Movimentação***");
        if (Conta.contas.isEmpty()) {
            System.out.println("\nNão existem contas cadastradas!");
        } else {
            for (Conta variavel : Conta.contas) {
                if (variavel.getNumeroConta() == numeroContaMov) {
                    System.out.print("Digite o valor a sacar: R$ ");
                    valor = Double.parseDouble(ler.nextLine());
                    if (valor > 1000 || (valor + variavel.getLimiteDiario()) > 1000) {
                        System.out.println("Limite diário excedido!");
                        System.out.println("Limite diário R$ 1000,00");
                    } else {
                        if (variavel.saca(valor)) {
                            System.out.println("Saque realizado com sucesso!");
                            System.out.println("Saldo atual R$ " + variavel.getSaldo());
                        } else {
                            System.out.println("Saldo insuficiente!");
                        }
                    }

                }
            }
        }
    }

    public static void depositar() {
        double valor;
        if (Conta.contas.isEmpty()) {
            System.out.println("\nNão existem contas cadastradas!");
        } else {
            for (Conta variavel : Conta.contas) {
                if (variavel.getNumeroConta() == numeroContaMov) {
                    System.out.println("****Depósito****");
                    System.out.print("Informe o valor do depósito: ");
                    valor = Double.parseDouble(ler.nextLine());
                    variavel.deposita(valor);
                    System.out.println("Seu novo saldo é de R$ " + variavel.getSaldo());
                }
            }
        }
    }

    public static void solicitarExtrato() {
        System.out.println("\n*****Extrato*****");
        if (Conta.contas.isEmpty()) {
            System.out.println("\nNão existem contas cadastradas!");
        } else {
            for (Conta variavel : Conta.contas) {
                if (variavel.getNumeroConta() == numeroContaMov) {
                    System.out.println("Cliente : " + variavel.getNome());
                    System.out.println("Número da conta : " + variavel.getNumeroConta());
                    System.out.println("Saldo : R$ " + variavel.getSaldo());
                    System.out.println("Limite de saque diário : R$ 1.000,00");
                    System.out.println("Limite de saque disponível : " + (1000 - variavel.getLimiteDiario()));
                }
            }
        }
    }
}
