package blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _002_BlockChain {

    //Applies Sha256 to a string and returns the result.
    public static String applySha256(String input) {
        /*
        Message Digest 5 (MD5) algoritması, verilen dosyanın veya mesajın (şifre vb.) kendine has “parmak izi” nin oluşturulmasını "hash" fonksiyonlarına dayalı olarak sağlayan bir algoritmadır.
        Bir veritabanı yönetimi (database management) tekniğidir. 1991 yılında MIT (Massachusetts Institute of Technology)’de görev yapan Profesör Ron Rivest tarafından geliştirilmiştir.
        Profesör Rivest MD5’i MD4’ün bir üst sürümü olarak tasarlamıştır.
        Özellikleri

        MD5 algoritması tek yönlü çalışır. Şifreleme yapılır, ancak şifre çözüm işlemi yapılamaz.
        MD5 algoritması, üzerinde işlem yapılan dosyada (aktarma vb.) herhangi bir değişiklik olup olmadığını tespit eder.
        Eğer bir değişiklik yapılmışsa, yeni dosyanın MD5 algoritmasından geçilmesinden çıkan sonuç ile ilk dosyanın MD5 sonucu birbirinden farklı olacaktır.
        MD5 algoritması bir alt sürümü olan MD4’e göre yavaş çalışır, ancak şifrelendirme sistemi çok daha karışık ve çözülmesi güçtür.
        Genel olarak 4 farklı aşamalı bir sisteme sahiptir. Her aşama birbirinden farklı işleyişe sahip olup 16’şar basamaktan oluşmuştur.
        Bir MD5 şifreleme işleminde aşağıdaki resimdeki sistemden 64 tane gerçekleşmektedir.

        Kullanıldığı Yerler
        İnternet trafiğinde. "SSL (Secure Sockets Layer - Güvenli Yuva Katmanı)" gibi.
        Özel bilgisayar ağlarında. "VPN (Virtual Private Network - Sanal Özel Ağ)" gibi.
        Güvenli uzaktan ulaşım uygulamalarında. "SSH (Secure Shell - Güvenli Kabuk)" gibi.
        Kimlik belirleme uygulamalarında.

        */
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Applies sha256 to our input,
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

        //MD5
        System.out.println("SHA: " + applySha256("Merhabalar"));

        //hash function
        String data = "ı am writing first my blockchain code";
        //hashcode: -614247938
        int hashValue = data.hashCode();
        System.out.println("first original data: " + data + " hashcode: " + hashValue);

        //String array same array
        System.out.println("\nsame copy array");
        String[] arrayData1 = {"mvc", "data", "security"};
        String[] arrayData2 = {"mvc", "data", "security"};
        int hash1 = Arrays.hashCode(arrayData1);
        int hash2 = Arrays.hashCode(arrayData2);
        System.out.println("Hash1: " + hash1 + "\nHash2: " + hash2);

        //String array just a letter array
        System.out.println("\nOther array");
        String[] arrayData3 = {"mvc", "data", "security"};
        String[] arrayData4 = {"Mvc", "data", "security"};
        int hash3 = Arrays.hashCode(arrayData3);
        int hash4 = Arrays.hashCode(arrayData4);
        System.out.println("Hash3: " + hash3 + "\nHash4: " + hash4);


        //Blok zinciri listesi oluşturdum
        ArrayList<Block> blockChain = new ArrayList<Block>();
        List<Integer> hashList = new ArrayList<>();

        /////////////////////////////////////////////////////////
        //Blok zinciri ilkini oluşturdum
        String[] initialValues = {"javase44", "javame44"};

        //ilk blok zinciri
        Block firstBlock = new Block(initialValues, 0);

        //blok zincirine ilk kaydı ekledim
        blockChain.add(firstBlock);
        //ilk zincir
        System.out.println("\nFirst block is " + firstBlock.toString());
        //1.blockHash=-641758054
        //bütün zincir
        System.out.println("Block Chain is " + blockChain.toString());
        //listeye eklemek
        hashList.add(firstBlock.getBlockHash());

        /////////////////////////////////////////////////////////
        //ikinci blok zinciri data
        String[] secondValues = {"javase23", "javame23"};

        //ikinci  blok zinciri, öncekinin devamıdır
        Block secondBlock = new Block(secondValues, firstBlock.getBlockHash());

        //blok zincirine ikinci kaydı ekledim
        blockChain.add(secondBlock);
        //ikinci zincir
        System.out.println("\nSecond block is " + secondBlock.toString());
        //2.blockHash=-1283578604
        //bütün zincir
        System.out.println("Block Chain is " + blockChain.toString());
        //listeye eklemek
        hashList.add(secondBlock.getBlockHash());

        /////////////////////////////////////////////////////////
        //üçüncü blok zinciri data
        String[] thirdValues = {"javase10", "javame10"};
        //üçüncü  blok zinciri, öncekinin devamıdır
        Block thirdBlock = new Block(thirdValues, secondBlock.getBlockHash());
        //blok zincirine üçüncü kaydı ekledim
        blockChain.add(thirdBlock);
        //üçüncü zincir
        System.out.println("\nthird block is " + thirdBlock.toString());
        //3.blockHash=-1925432882
        //bütün zincir
        System.out.println("Block Chain is " + blockChain.toString());
        //listeye eklemek
        hashList.add(thirdBlock.getBlockHash());

        /////////////////////////////////////////////////////////
        //Ekranda Göster
        hashList.forEach((temp) -> System.out.println(temp + " "));

        //Not: eğer bir veriyi değiştirdiğimde hashcode değişcektir.
        //Not: hangi alanda değişiklik yaparsam bundan sonraki hashcode değişcektir.
        /////////////////////////////////////////////////////////
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            //Applies sha256 to our input,
            //byte[] hash = sha.digest(input.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
        }
    }
}
