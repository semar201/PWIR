package Firma_przewozowa;

import java.util.Random;

public class Fabryka extends Thread {
	static int next = 0;
	int current;
	int id_pkt_fabryki;
	static Firma wypozyczalnia;
	static Punkt_baza Pbaza;
	Trasa_baza Tbaza;

	Fabryka(Firma wypozyczalnia, Punkt_baza Pbaza, int id_pkt_fabryki,
			Trasa_baza Tbaza) {
		this.Tbaza = Tbaza;
		this.id_pkt_fabryki = id_pkt_fabryki;
		this.wypozyczalnia = wypozyczalnia;
		this.Pbaza = Pbaza;
		current = next;
		next++;
	}

	int jedz_(Auto samochod, int cel, int szukac_stacji) // return 1-braklo
															// paliwa 0-wszystko
															// ok
	{
		// jesli szukac_stacji = 1 <- zwykla jazda
		// szukac_stacji = 0 <- jedzie juz na stacje i nie szuka nastepnej
		samochod.cel = cel;
		System.out.println("Fabryka #" + id_pkt_fabryki + " Auto #"
				+ samochod.id + " [" + samochod.zrodlo + "]->[" + samochod.cel
				+ "] dystans:" + Tbaza.dystans[samochod.zrodlo][samochod.cel]
				+ " bak:" + samochod.bak_stan + "/" + samochod.bak_max);
		int postep = 0; // ktory pkt trasy mija
		int przejechane = 0;
		// tankuje gdy wie ze nie starczy benzyny do nastepnego celu
		if (szukac_stacji == 1
				&& samochod.bak_stan < samochod.bak_max
				&& 0 >= samochod.bak_stan
						- (Tbaza.dystans[samochod.zrodlo][samochod.cel] - przejechane))
		// tankuje co zmiane celu
		// if(szukac_stacji==1 && samochod.bak_stan<samochod.bak_max )
		{

			samochod.zrodlo = Tbaza.droga[samochod.zrodlo][samochod.cel][postep];
			int najb_stacja[] = new int[2]; // najblizsza stacja id+dystans
			najb_stacja[1] = -1;
			// szukaj stacji
			for (int i = 0; i < Pbaza.tab_size; i++) {
				if (Pbaza.tab[i].obiekt == 4)
					if (najb_stacja[1] == -1
							|| Tbaza.dystans[samochod.zrodlo][i] < najb_stacja[1]) {
						najb_stacja[0] = i;
						najb_stacja[1] = Tbaza.dystans[samochod.zrodlo][i];
					}
			}
			System.out.println("Fabryka #" + id_pkt_fabryki + " Auto #"
					+ samochod.id + " Jedzie zatankowac (stacja:"
					+ najb_stacja[0] + "), przejechane:" + przejechane
					+ " calosc:" + Tbaza.dystans[samochod.zrodlo][samochod.cel]
					+ " bak:" + samochod.bak_stan + "/" + samochod.bak_max
					+ " najb_stacja:" + najb_stacja[0] + " dyst:"
					+ najb_stacja[1]);

			// jedz na stacje
			int cel_poprzedni = samochod.cel; // zapamietaj gdzie jechales
			if (jedz_(samochod, najb_stacja[0], 0) == 1) // nie szukaj stacji po
															// drodze
				return 1;
			Pbaza.tab[najb_stacja[0]].stacja.zatankuj(samochod);
			System.out.println("Fabryka #" + id_pkt_fabryki + " Auto #"
					+ samochod.id + " zatankowane na stacji:" + najb_stacja[0]);
			if (jedz_(samochod, cel_poprzedni, 1) == 1)
				return 1;

		} else {
			while (samochod.czy_cel_osiagniety(Pbaza.tab[samochod.cel]) == 0) {
				Punkt mini_cel = Pbaza.tab[Tbaza.droga[samochod.zrodlo][samochod.cel][postep + 1]];
				// System.out.println("Fabryka #"+id_pkt_fabryki+" auto w punkcie "+Tbaza.droga[samochod.zrodlo][samochod.cel][postep]+" jedzie do "+samochod.cel+" przez "+Tbaza.droga[samochod.zrodlo][samochod.cel][postep+1]+"przejechane:"+przejechane+" calosc:"+Tbaza.dystans[samochod.zrodlo][samochod.cel]+" bak:"+samochod.bak_stan);

				// jedz do punktu
				while (samochod.czy_cel_osiagniety(mini_cel) == 0) {
					if (0 < samochod.bak_stan - 0.1) {
						przejechane++;
						samochod.bak_stan -= 0.1;
						samochod.x += samochod.znajdz_kierunek_x(mini_cel) * 0.1;
						samochod.y += samochod.znajdz_kierunek_y(mini_cel) * 0.1;
						try {
							Thread.sleep((long) (300 / wypozyczalnia.predkosc));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else {
						samochod.stan = 2;
						Pbaza.tab[id_pkt_fabryki].fabryka_stan = 3;
						System.out.println("Fabryka #" + id_pkt_fabryki
								+ " Auto #" + samochod.id + "Brak benzyny");
						return 1;/*
								 * while(true) { try {Thread.sleep(60000);}
								 * catch (InterruptedException e)
								 * {e.printStackTrace();} }
								 */
					}
				}
				postep++;

			}
			samochod.zrodlo = samochod.cel;
		}
		return 0;
	}

	int jedz(Auto samochod) {
		samochod.zrodlo = Pbaza.znajdz_firme();
		samochod.ustaw_firmowa_poz(Pbaza.tab[samochod.zrodlo]); // pozycja_poczatkowa

		if (jedz_(samochod, id_pkt_fabryki, 1) == 1)
			return 1;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (jedz_(samochod, Pbaza.znajdz_sklep(), 1) == 1)
			return 1;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (jedz_(samochod, Pbaza.znajdz_firme(), 1) == 1)
			return 1;
		return 0;
	}

	public void run() {
		System.out.println("Fabryka #" + id_pkt_fabryki + " otworzona watek:"
				+ current);
		Random rand = new Random();
		if (wypozyczalnia == null)
			System.out.println("Wyp   NULL");
		if (Pbaza == null)
			System.out.println("Pbaza NULL");
		while (true) {

			try {
				Thread.sleep(rand.nextInt(19000) + 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Fabryka #" + id_pkt_fabryki
					+ " potrzebuje auto.");
			Auto samochod = null;
			Pbaza.tab[id_pkt_fabryki].fabryka_stan = 1;
			try {
				samochod = wypozyczalnia.wypozycz();
			} catch (InterruptedException e1) {
				System.err.println("Fabryka #" + id_pkt_fabryki
						+ " nie dostala auta. " + samochod);
				e1.printStackTrace();
			}

			if (samochod != null) {
				Pbaza.tab[id_pkt_fabryki].fabryka_stan = 2;
				System.out.println("Fabryka #" + id_pkt_fabryki
						+ " dostala Auto #" + samochod.id + " " + samochod);
				if (jedz(samochod) == 0) // jesli nie zabraklo benzyny
				{

					try {
						Thread.sleep(rand.nextInt(1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					wypozyczalnia.oddaj(samochod);
					System.out.println("Fabryka #" + id_pkt_fabryki
							+ " oddala Auto #" + samochod.id + "");
				} else {
					System.out.println("Fabryka #" + id_pkt_fabryki
							+ " porzucila Auto #" + samochod.id + "");
				}
				Pbaza.tab[id_pkt_fabryki].fabryka_stan = 0;
			}
		}
	}
}
