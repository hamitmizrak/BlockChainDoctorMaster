package blockchain;



public class _001_Hashing {

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
        return input;
    }

    public static void main(String[] args) {
        //MD5
        System.out.println("SHA: " + applySha256("Merhabalar"));
    }
}
