package kodlama.io.rentacar.cammon.constants;

public class Regex {
    public final static String Plate = "^(0[1-9]|[1-7][0-9]|8[01]) [A-Z]{1,3} \\d{3,4}$";

/*
^                                       -> Başlangıç karakteri
(?:                                     -> Gruplamak için kullanılan başlangıç parantezi
  [A-Z]{2}                              -> İlk iki karakter büyük harfler olmalıdır
  \s?                                   -> Bir adet boşluk karakteri olabilir, olmayabilir de
  \d{3}                                 -> Üç adet rakam olmalıdır
  \s?                                   -> Bir adet boşluk karakteri olabilir, olmayabilir de
  [A-Z]{1,2}                            -> En az bir, en fazla iki büyük harf olmalıdır
  |                                     -> Alternatif olarak, başka bir plaka formatı tanımlanıyor
  \d{2}                                 -> İlk iki karakter rakamlardan oluşmalıdır
  \s?                                   -> Bir adet boşluk karakteri olabilir, olmayabilir de
  [A-Z]{1,3}                            -> En az bir, en fazla üç büyük harf olmalıdır
  \s?                                   -> Bir adet boşluk karakteri olabilir, olmayabilir de
  \d{2,4}                               -> İki ya da dört adet rakam olmalıdır
)                                       -> Gruplamak için kullanılan kapatma parantezi
$                                       -> Bitiş karakteri

 */



}
