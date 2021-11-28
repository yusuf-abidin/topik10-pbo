package com.company;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Member> members = new ArrayList<>();

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("-- MENU APLIKASI --");
            System.out.println("1. Input Driver");
            System.out.println("2. Input Customer");
            System.out.println("3. Show Member");
            System.out.println("4. Top-up Saldo");
            System.out.println("5. Antar Penumpang");
            System.out.println("0. Keluar");

            System.out.print("\nPilih menu : ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan){
                case 0 :
                    System.out.println("\nAplikasi Berhenti, Sampai Jumpa Kembali :)");
                    break;
                case 1 :
                    inputDriver();
                    break;
                case 2 :
                    inputCustomer();
                    break;
                case 3 :
                    showMember();
                    break;
                case 4 :
                    transaksi("topup");
                    break;
                case 5 :
                    transaksi("antar");
                    break;
                default :
                    System.out.println("\nPilih menu yang sesuai\n");
            }

        }while (pilihan != 0);


    }

    private static void inputDriver(){
        System.out.println("\n-- Input Driver --");
        System.out.print("NIK : ");
        String nik = scanner.nextLine();
        System.out.print("Nama : ");
        String nama = scanner.nextLine();
        System.out.print("No Telepon : ");
        String noTelp = scanner.nextLine();
        System.out.print("Saldo : ");
        double saldo = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("No Plat : ");
        String noPlat = scanner.nextLine();
        System.out.print("Jenis : ");
        String jenis = scanner.nextLine();

        Member d = searchMember(nik);

        if (d == null){
            Driver driver = new Driver(nik, nama, noTelp, saldo, noPlat, jenis);
            members.add(driver);
            System.out.println("\nDriver Berhasil Ditambahkan!\n");
        }else {
            System.out.println("\nNIK Sudah terdaftar\n");
        }

    }

    private static void inputCustomer(){
        System.out.println("\n-- Input Customer --");
        System.out.print("NIK : ");
        String nik = scanner.nextLine();
        System.out.print("Nama : ");
        String nama = scanner.nextLine();
        System.out.print("No Telepon : ");
        String noTelp = scanner.nextLine();
        System.out.print("Saldo : ");
        double saldo = scanner.nextDouble();

        Member c = searchMember(nik);

        if (c == null){
            Customer customer = new Customer(nik, nama, noTelp, saldo);
            members.add(customer);
            System.out.println("\nCustomer Berhasil Ditambahkan!\n");
        }else {
            System.out.println("\nNIK Sudah terdaftar\n");
        }
    }

    private static void showMember(){
        System.out.println();
        for (Member m : members){
            m.display();
        }

    }

    private static void transaksi(String tipe){
        if (tipe.equalsIgnoreCase("topup"))
            System.out.println("\n-- Top Up Saldo --");
        else
            System.out.println("\n-- Antar Penumpang --");

        System.out.print("ID Driver : ");
        String nikDriver = scanner.nextLine();
        System.out.print("ID Customer : ");
        String nikCust = scanner.nextLine();
        if (tipe.equalsIgnoreCase("topup"))
            System.out.print("Nominal : ");
        else
            System.out.print("Biaya : ");

        double nominal = scanner.nextDouble();

        Member d = searchMember(nikDriver);
        Member c = searchMember(nikCust);

        System.out.println();
        if ((d instanceof Driver && c instanceof Customer)){
            if (tipe.equalsIgnoreCase("topup")) {
                if (d.getSaldo() < nominal){
                    System.out.println("Saldo Driver Tidak Cukup\n");
                }else {
                    c.transaksi(d, nominal);
                    System.out.println("Saldo Customer " + c.getNik() + " Ditambahkan\n");
                }

            }else {
                if (c.getSaldo() < nominal){
                    System.out.println("Saldo Customer Tidak Cukup\n");
                }else {
                    d.transaksi(c, nominal);
                    System.out.println("Saldo Driver " + d.getNik() + " Ditambahkan\n");
                }

            }
        }else if (d == null){
            System.out.println("ID Driver Tidak Ditemukan\n");
        }else {
            System.out.println("ID Customer Tidak Ditemukan\n");
        }
    }

    private static Member searchMember(String nik){
        Member member = null;
        for(Member m : members){
            if (m.getNik().equalsIgnoreCase(nik)){
                member = m;
                break;
            }
        }
        return member;
    }
}
