package Firma_przewozowa;

public class Trasa_baza 
{
	Punkt_baza Pbaza;
	int dystans[][];  
	int droga[][][];
	
	public Trasa_baza(Punkt_baza Pbaza) 
	{
		this.Pbaza=Pbaza;
		dystans=new int[Pbaza.tab_size][];
		droga=new int[Pbaza.tab_size][][];
		for(int i=0;i<Pbaza.tab_size;i++)
		{
			dystans[i]=new int[Pbaza.tab_size];
			droga[i]=new int[Pbaza.tab_size][];
			for(int j=0;j<Pbaza.tab_size;j++)
			{
				System.out.print("Szukanie trasy: "+i+"->"+j);
				dystans[i][j]=0;
				droga[i][j]=new int[Pbaza.tab_size];
				if(i==j)
				{
					System.out.println();
					droga[i][j][0]=j;
				}else
				{

					//przygotowanie tablicy pomocniczej
					int tab_pom_size=Pbaza.tab_size*(Pbaza.tab_size-1);
					int tab_pom[][]=new int[tab_pom_size][];
					int tab_pom_dyst[]=new int[tab_pom_size];
					for(int k=0;k<tab_pom_size;k++)
					{
						tab_pom_dyst[k]=0;
						tab_pom[k]=new int[Pbaza.tab_size];
						for(int l=0;l<Pbaza.tab_size;l++)
							tab_pom[k][l]=-1;
					}
					tab_pom[0][0]=i; //rozpoczynamy od punktu ID=i
					
					for(int k=0;k<tab_pom_size;k++)
					{
						for(int l=0;l<Pbaza.tab_size;l++)
						{
							// jesli jest co jeszcze sprawdzac w tablicy
							if(tab_pom[k][l]!=-1 && tab_pom[k][l]!=j && l<Pbaza.tab_size-1 && tab_pom[k][l+1]==-1) 
							{
								//System.out.println(tab_pom[k][l]+"-"+k+":"+l+" "+Arrays.toString(tab_pom[k]));
								int zapisano_do_aktualnego=0; // 1-kolejny zapis idzie do nowego wiersza
								int dystans_przed_dopisaniem=0;
								
								for(int s=0;s<4;s++) // sasiedzi danego pkt
								{
									//czy sasiad istnieje
									if(Pbaza.tab[tab_pom[k][l]].tab[s][0]!=-1)// && Pbaza.tab[tab_pom[k][l]].tab[s][0]!=k)
									{
										
										int powtarza_sie=0; 
										//czy nie powtarza sie oraz czy nie ma juz celu w tablicy
										for(int m=0;m<l;m++)
										{
											if(Pbaza.tab[tab_pom[k][l]].tab[s][0]==tab_pom[k][m])
											{
												powtarza_sie=1;
												break;
											}
										}
										if(powtarza_sie==0)
										{
											if(zapisano_do_aktualnego==0)
											{
												zapisano_do_aktualnego=1;
												dystans_przed_dopisaniem=tab_pom_dyst[k];
												tab_pom[k][l+1]= Pbaza.tab[tab_pom[k][l]].tab[s][0];
												tab_pom_dyst[k]+=Pbaza.tab[tab_pom[k][l]].tab[s][1];
												//System.out.println("LEWY  "+k+" "+Arrays.toString(tab_pom[k])+" : "+Pbaza.tab[tab_pom[k][l]].tab[s][0]+" d:"+tab_pom_dyst[k]);
											}else
											{
												//znajdz wolny wiersz
												for(int m=0;m<tab_pom_size;m++)
												{
													if(tab_pom[m][0]==-1)
													{
														
														//skopiuj wiersz
														for(int n=0;n<=l;n++)
														{
															tab_pom[m][n]=tab_pom[k][n];
												
														}
														//dopisz punkt
														//System.out.println("PRZED "+m+" "+Arrays.toString(tab_pom[m])+" : "+Pbaza.tab[tab_pom[k][l]].tab[s][0]+" d:"+tab_pom_dyst[k]);
														tab_pom[m][l+1]= Pbaza.tab[tab_pom[k][l]].tab[s][0];
														tab_pom_dyst[m]=dystans_przed_dopisaniem+Pbaza.tab[tab_pom[k][l]].tab[s][1];
														//System.out.println("Nowy: "+m+" "+Arrays.toString(tab_pom[m])+" : "+Pbaza.tab[tab_pom[k][l]].tab[s][0]+" d:"+tab_pom_dyst[k]);
														break;
													}
												}
											}
										}
									}
								}
							}
						}
					}
					//koniec przygotowywania tablicy pomocznicej
					//podliczanie drogi
					int dystans_najlepszy=199990;
					int id_najlepsze=-1;
					for(int k=0;k<tab_pom_size;k++)
					{
						if(tab_pom[k][0]!=-1 && tab_pom[k][1]!=-1) 
						{
							//System.out.println(k+" "+Arrays.toString(tab_pom[k])+" d:"+tab_pom_dyst[k]);
							//czy jest cel w tym wierszu
							for(int l=1;l<Pbaza.tab_size;l++)
							{
								if(tab_pom[k][l]==j)
								{
									if(id_najlepsze==-1 ||  tab_pom_dyst[k] < dystans_najlepszy )
									{
										dystans_najlepszy=tab_pom_dyst[k];
										id_najlepsze=k;
									}
									break;
								}
							}
							//System.out.println(".");
						}
					}

					System.out.print(" najlepsza:"+id_najlepsze+" dyst:"+dystans_najlepszy+" ");
					dystans[i][j]=dystans_najlepszy;
					for(int m=0;m<tab_pom_size;m++)
					{
						if(tab_pom[id_najlepsze][m]==-1)
							break;
						droga[i][j][m]=tab_pom[id_najlepsze][m];
						System.out.print(droga[i][j][m]+",");
					}
					System.out.println();
					
				}
				
			}
		}
		System.out.println("Koniec.");
		//ile tras = trasa baza-> zaklady  + zaklady->sklepy + sklepy->baza
		//baza = wszystkie mozliwe trasy
	}
}
