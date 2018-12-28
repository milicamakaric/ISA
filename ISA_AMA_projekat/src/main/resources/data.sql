
delete from rating;
delete from korisnik;

delete from AUTHORITY;
delete from user_authority;
delete from korisnik;
delete from aviokompanija_brzi_letovi;
delete from let;
delete from filijala;
delete from rentacar_servis;

insert into rentacar_servis (id, naziv, adresa, promotivni_opis, prosecna_ocena) values (1, 'Angel', 'Vojvode Supljikca 20 Novi Sad', 'Mlada firma sa velikom vizijom. Iznajmljivanje automobila sa i bez vozaca.', 3.2);
insert into rentacar_servis (id, naziv, adresa, promotivni_opis, prosecna_ocena) values (2, 'Cartize Company', 'Novosadski put 19 Novi Sad', 'Najpovoljnije cene rentiranja automobila u Novom Sadu', 4.8);
insert into rentacar_servis (id, naziv, adresa, promotivni_opis, prosecna_ocena) values (3, 'Stars', 'Bulevar Mihajla Pupina 52 Beograd', 'U Beogradu svi iznajmljuju automobile od nas.', 4);

insert into filijala (id, adresa, rentacar_id) values (1, 'Janka Cmelika 20 Novi Sad', 1);
insert into filijala (id, adresa, rentacar_id) values (2, 'Mise Dimitrijevica 55 Novi Sad', 1);
insert into filijala (id, adresa, rentacar_id) values (3, 'Puskinova 15 Zrenjanin', 1);
insert into filijala (id, adresa, rentacar_id) values (4, 'Bulevar kralja Aleksandra 56 Beograd', 3);

delete from aviokompanija;
delete from rezervacija_hotel;
delete from soba;
delete from hotel_usluge;
delete from usluga;
delete from hotel;

insert into hotel (id, naziv, adresa, promotivni_opis, prosecna_ocena) values (1, 'Hotel Park', 'Novosadskog sajma 35 Novi Sad', 'hotel sa 4 zvezdice', 4.8);
insert into hotel (id, naziv, adresa, promotivni_opis, prosecna_ocena) values (2, 'Hotel Novi Sad', 'Jase Tomica 2 Novi Sad', 'fantastican hotel', 3.79);
insert into hotel (id, naziv, adresa, promotivni_opis, prosecna_ocena) values (3, 'Hotel Moskva', 'Futoski put bb Novi Sad', 'Svaka soba ima terasu.', 5);
insert into hotel (id, naziv, adresa, promotivni_opis, prosecna_ocena) values (4, 'Hotel Stari krovovi', 'Futoski put Novi Sad', 'Potpuno nov hotel sa svim mogucim uslugama.', 5);
insert into hotel (id, naziv, adresa, promotivni_opis, prosecna_ocena) values (5, 'Grand hotel', 'Zmaj Jovina 3', 'Hotel u srcu Novog Sada', 3.87);
insert into hotel (id, naziv, adresa, promotivni_opis, prosecna_ocena) values (6, 'Rezidencija Makarica', 'Njegoseva 46 Stepanovicevo', 'Luksuzna vila sa 4 apartmana. Svaki apartman poseduje tv, klima uredjaj, mini bar, terasu, kupatilo. Odlican Wi-Fi signal. Prostrano dvoriste i obezbedjen parking za goste. Najupecatljiviji utisak ostavljaju ljubaznost i gostoprimljivost domacina.', 4.99);
insert into hotel (id, naziv, adresa, promotivni_opis, prosecna_ocena) values (7, 'Sole mio', 'Bulevar oslobodjenja 56a', 'Hotel sa tradicijom dugom 78 godina.', 4.5);

insert into korisnik (aktiviran, bonuspoeni, email, grad, ime, lozinka, prezime, telefon) values (true, 0, 'krsmanovicc.aleksa@gmail.com', 'Savac', 'Aleksa', '12345', 'Krsmanovic', '1234567');
insert into korisnik (email, lozinka, ime, prezime, grad, telefon, bonuspoeni, aktiviran) values ('makaric.milica@gmail.com', 'makarica', 'Milica', 'Makaric', 'Stepanovicevo', '0652034133', 0, true);

insert into aviokompanija(id,adresa, info, max_kapacitet, naziv, ocena, opis) values (1, 'Tolstojeva 66, Novi Sad','Najjaci popusti najjace zezanje drugari',300, 'CoaAir', null, '20kg rucna torba, kofer 100kg drugari');

insert into let(id,cena,dokle,odakle,popust,trajanje,udaljenost,vreme_poletanja,vreme_sletanja, aviokompanija_id) values (6,750,'Tokyo - JPN', 'Belgrade - SRB', 10, 15, 15000,'2018-12-30 11:59:59.999999','2018-12-31 23:59:59.999999',1);
insert into let(id,cena,dokle,odakle,popust,trajanje,udaljenost,vreme_poletanja,vreme_sletanja, aviokompanija_id) values (1,500,'Tokyo - JPN', 'Belgrade - SRB', 10, 15, 15000,'2018-12-30 11:59:59.999999','2018-12-31 23:59:59.999999',1);
insert into let(id,cena,dokle,odakle,popust,trajanje,udaljenost,vreme_poletanja,vreme_sletanja, aviokompanija_id) values (5,5000,'Tokyo - JPN', 'Belgrade - SRB', 10, 15, 15000,'2018-12-30 11:59:59.999999','2018-12-31 23:59:59.999999',1);
insert into let(id,cena,dokle,odakle,popust,trajanje,udaljenost,vreme_poletanja,vreme_sletanja, aviokompanija_id) values (2,250,'Milano - ITA', 'Belgrade - SRB', 10, 15, 15000,'2018-12-30 11:59:59.999999','2018-12-31 23:59:59.999999',1);
insert into let(id,cena,dokle,odakle,popust,trajanje,udaljenost,vreme_poletanja,vreme_sletanja, aviokompanija_id) values (3,500,'Washington DC - USA', ' Nis - SRB', 0, 15, 15000,'2019-1-15 02:59:59.999999','2019-1-15 23:59:59.999999',1);
insert into let(id,cena,dokle,odakle,popust,trajanje,udaljenost,vreme_poletanja,vreme_sletanja, aviokompanija_id) values (4,500,'Madrid - SPN', 'Podgorica - MNG', 0, 15, 15000,'2018-12-25 3:00:00.999999','2018-12-25 6:00:00.999999',1);

insert into aviokompanija_brzi_letovi(aviokompanija_id, brzi_letovi_id) values (1,1);
insert into aviokompanija_brzi_letovi(aviokompanija_id, brzi_letovi_id) values (1,2);

insert into soba (id, prosecna_ocena, cena_nocenja, broj_kreveta, brza_soba, popust, hotel_id, zauzeta) values (1, 4.8, 120.5, 3, false, 20.5, 6, false);
insert into soba (id, prosecna_ocena, cena_nocenja, broj_kreveta, brza_soba, popust, hotel_id, zauzeta) values (2, 3.77, 53, 2, false, 0, 6, false);
insert into soba (id, prosecna_ocena, cena_nocenja, broj_kreveta, brza_soba, popust, hotel_id, zauzeta) values (3, 4.8, 120.5, 3, false, 20.5, 1, false);

insert into usluga (id, naziv, cena) values (1, 'air-conditioner', 5);
insert into usluga (id, naziv, cena) values (2, 'Wi-Fi', 2);
insert into usluga (id, naziv, cena) values (3, 'swimming pool', 3);
insert into usluga (id, naziv, cena) values (4, 'gym', 2);
insert into usluga (id, naziv, cena) values (5, 'sauna', 4);

insert into hotel_usluge (hotel_id, usluge_id) values (6, 1);
insert into hotel_usluge (hotel_id, usluge_id) values (6, 2);
insert into hotel_usluge (hotel_id, usluge_id) values (6, 3);

insert into rezervacija_hotel (id, datum_dolaska, datum_odlaska, soba_id, broj_nocenja, brza, ukupna_cena) values (1, '2018-12-25', '2018-12-26', 1, 1, false, 10);
insert into rezervacija_hotel (id, datum_dolaska, datum_odlaska, soba_id, broj_nocenja, brza, ukupna_cena) values (2, '2018-12-25', '2018-12-27', 3, 2, false, 10);
insert into rezervacija_hotel (id, datum_dolaska, datum_odlaska, soba_id, broj_nocenja, brza, ukupna_cena) values (3, '2018-12-20', '2018-12-23', 3, 3, false, 10);

insert into korisnik (id, email, lozinka, ime, prezime, grad, telefon, bonuspoeni, aktiviran) values (1, 'andrijana.jeremi@gmail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Andrijana', 'Jeremic', 'Ruma', '062522006', 0, true);
insert into AUTHORITY (id, name) values (1, 'ROLE_USER');
insert into user_authority (user_id, authority_id) values (1, 1);
