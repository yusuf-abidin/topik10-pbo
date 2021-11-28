package com.company;

public class Customer extends Member{

    public Customer(String nik, String nama, String noTelp, double saldo) {
        super(nik, nama, noTelp, saldo);
    }

    @Override
    public void display() {
        System.out.println("Status Member : Customer");
        System.out.println("NIK : " + this.nik);
        System.out.println("Nama : " + this.nama);
        System.out.println("No Telepon : " + this.noTelp);
        System.out.printf("Saldo : %.2f\n" , this.saldo);
        System.out.println();
    }
}
