package Model.cities;

import java.awt.Point;

public enum CityType {

    PARIS, LILLE, CALAIS, NORMANDIE, NANCY, RENNES, NANTES, ORLEANS, DIJON, LYON, BORDEAUX, TOULOUSE, MONTPELLIER,
    MARSEILLE, CAEN, PAU, PERPINION, NICE, STRASBOURG, GENEVE, METZ, AMIENS, LAROCHELLE, LIMOGES, BRIVE, POTIERS, /* fin france  */
    KERKHOIANSK, PSKOV, KIEV, AZOV, ASTRAKHAN, SARAI, ORENBOURG, OMSK, TOMSK, ANADIR, OKHATSK, LAKOUTSK,
    SAINTPETERSBOURG, ARKHANGELSK, NOVGOROD, BRLANSK, MOSCOU, KHATANGA, SAMARA, TOBOLSK, /* fin russie */HELSINKI,
    VANTAA, ESPOO, TURKU, TAMPERE, RAUMA, PORI, KRISTINESTAD, VAASA, PIETARRSAARI, OULU, KAJAANI, JYVASKLYA,
    CHATEAUDEKOLOVESI, CHATEAUDOLAVINLINNA, LAHTI, KIOPIO, /* fin filande */ORAN, ALGER, ANNABA, BECHAR, TINDOUF,
    INGEZZAM, TIZIOUZOU, SOUKAHRAS, TBESSA, ELOUED, INAMENASS, DJANET, TAMENRASSET,
    CHENACHENE, POSTEMAURICECORTIER, BORDJLEPRISEUR, INZAOUDENE, BLIDA, TOUGGOURT, ELBAYADH, HASSIMESAOUD, OUERGLA,
    ADRAR, TIMIMOUN, BATNA, GHARDAYA, ELGOLEA, /* fin algerie */
    WADIHALFA, HALAIB, PORTSUDAN, SAWAKIN, ATBARA, KASSALA, ALQADARIF,
    KINYETI, NIMULE, JUBA, BOR, WAW, MALAKAL, NYALA, ALFASHIR, ALUBAYYID,
    KUSTI, WADMADANI, KHARTOUM, OMDURMAN, /* FIN SOUDAN */TAOUDENNI, TESSALIT, KIDAL, INTEBEZAS, MENAKA, ANSONGO, GOSSI,
    MOPTI, SAN, KOUTIALA, SIKASSO, BOUGOUNI, KOULIKORO,
    KITA, MAHINA, KAYES, GOUMBOU, NAMPALA, NIAFUNKE, GOUNDAM, TOMBOUCTOU, AROUANE, /* FIN MALI */CARACARAI, MACAPA,
    BELEM, SAOLUIS, TERSINA, FORTALEZA, NATAJAO, RECIFE, SALVADOR, PORTOSEGURO, RIODEJANEIRO, CURITIBA,
    PORTOALEGRE, SANTAMARIA, LONDRINA, CAMPOGRANDE, CULABA, RIOBRANCO, CRUZEIRODOSOL, TEFE, BRAZILIA, MANAUS, CARAJAS,
    /* FIN BRESIL */TIJUANA, MEXICALI, NOGALES, CIUDADJUAREZ, NUEVOLAREDO, MATAMOROS,
    CIUDADVICTORIA, TAMPICO, QUERETARO, PUEBLA, VILLAHERMOSA, MERIDA,
    CANCUN, VALLADOLID, CHETUMAL, OAXACA, ACAPULCO, MANZANILLO, GUADALAJARA, NAYARIT, SINALOA, TEPIC,
    CULIACAN, CIUDADOBREGON, LAPAZ, TBASCO, CHIAPAS, GHERRERO, PACHUCA, /* FIN MEXIQUE */SEATTLE, PORTLAND, SACRAMENTO,
    SANFRANCISCO, SANJOSE, LASVEGAS, LOSANGELES, LONGBEACH, ALBUQUERQUE, PHOENIX, TUCSON, ELPASO, AUSTIN,
    SANANTONIO, HOUSTON, LANOUVELLEORLEAN, MIAMI, JACKSONVILLE, ATLANTA, CHARLOTTE, VIRGINIABEACH, WASHINGTON,
    BALTIMORE, PHILADELPHIE, NEWYORK, BOSTON, DENVER, DALLAS, NASHVILLE, COLOMBUS, BOISE, LINCOLN, SPRINGFILED, /*FIN US */
    ALERT, POLENORDMAGNETIQUE, BANKS, INUVIK, DAWSON, TERRITOIREDEYUKON, WHITEHORSE, FORTNELSON, CLOMBIEBRITANNIQUE,
    PRINCEGEORGE, VICTORIA, KAMLOOPS, CAGARY, REGINA, WINNIPEG, THUNDERBAY, TORONTO, OTTAWA, MONREAL, QUEBEC, SEPTILES,
    CHURCHILLFAILS, NAIN, KUUJUAK,
    /* fin canada */KARAMAY, KASHI, HOTAN, LHASA, KUMNING, NANNING, HAIKOU, FOSHAN, GUANGZHOU, DONGGUAN, SHENZHEN,
    XIAMEN,
    FUZHOU, NANCHANG, HANGZHOU, SHANGHAI, BEIJING, SHENYANG, CHANGCHUN, HARBIN, HAILAR, HOHHOT, URUMQI, KOLRA, GOLMUD,
    CHENGDU, WUHAN, GUIYANG, /* fin chine */
    KARGIL, SRINAGAR, JAMMU, AMRITSAR, JAISALMER, GANDHINAGAR, OKHA, PORBANDAR, VADODARA, SURAT, DAMAN, SILVASSA,
    MUMBAI, PUNE, PANAJI, CALUCUT, COCHIN, TRIVANDRUM, TUTICORIN,
    PONDICHERRY, CHENNAI, VIJAYAWADA, KAKINADA, VISHAKHAPATNAM, BHUBANESWAR, PARADIP, HALDIA, KOLKATA, SHILONG, GANGTOK,
    GORAKHPUR, LUCKNOW, BAREILLY, DEHRADUN, LEH, SHIMLA, /* FIN INDE */ALJAWF, TABUK, ALMADINAH, JEDDAH, MECCA, ALBAHAH,
    ABHA, JIZAN, NAJRAN, SHARORAH,
    EASTERNPROVINCE, ALHASA, ALMUBARRAZ, DHAHRAN, DAMMAM, QATIF, JUBAIL, HAFARALBATIN, RAFHA, ARAR, SAKAKAH, JUBBAH,
    ALHULAYFAH, BURAIDAHÂ
    /*AUSTRALIE */, DARWIN, WYNDHAM, GREATSANDYDESERT, PORTHEDLAND, GERALDTON, PERTH, BUNBURY, ALBANY, ESPERANCE, CEDUNA,
    PORTLINCOIN, ADELAIDE, GEELONG, VICTORIAA, MELBOURNE, CANBERRA, SYDNEY, NEWCASTLE, BRISBANE, ROCKHAMPTON,
    TOWNSVILLE, CAIRNS, CAPEYORKPEN, MOUNTISA, NORTHERNTERRITORY, KIMBERLEY, PINCTADA, BARCALDINE, NORTHAM, KALGOORLIE, MTLIEBIG, ALICESPRINGS,
    ;

    private static final Point[] coordinates = {
            new Point(15, 6), new Point(17, 2), new Point(15, 1), new Point(20, 5),
            new Point(21, 8),
            new Point(7, 8), new Point(10, 10), new Point(10, 4), new Point(20, 7), new Point(14, 16), new Point(8, 14),
            new Point(14, 13),
            new Point(12, 14), new Point(21, 13), new Point(10, 4), new Point(11, 15), new Point(17, 16),
            new Point(25, 13), new Point(25, 7), new Point(22, 10), new Point(22, 4), new Point(15, 3),
            new Point(8, 12), new Point(16, 10), new Point(18, 11), new Point(11, 6), /* fin france */ new Point(22, 3),
            new Point(3, 2), new Point(2, 8), new Point(3, 12), new Point(8, 16),
            new Point(8, 12), new Point(13, 12), new Point(18, 13), new Point(22, 15), new Point(24, 1),
            new Point(26, 5), new Point(23, 8), new Point(10, 1), new Point(17, 2), new Point(8, 4), new Point(6, 6),
            new Point(10, 8), new Point(18, 6), new Point(12, 5), new Point(19, 9), /* fin russi */new Point(15, 15),
            new Point(19, 14), new Point(12, 14), new Point(10, 13), new Point(16, 12), new Point(9, 11),
            new Point(9, 8), new Point(11, 6), new Point(14, 4), new Point(14, 2), new Point(16, 0),
            new Point(19, 2), new Point(18, 5), new Point(19, 8), new Point(19, 11), new Point(13, 8),
            new Point(15, 6), /* fin finlande */
            new Point(8, 1), new Point(14, 1), new Point(20, 1), new Point(9, 6), new Point(2, 9), new Point(17, 16),
            new Point(16, 2), new Point(20, 5), new Point(21, 7), new Point(22, 10), new Point(22, 14),
            new Point(19, 15), new Point(17, 14),
            new Point(5, 11), new Point(7, 13), new Point(10, 15), new Point(13, 16), new Point(12, 3),
            new Point(17, 6), new Point(11, 8), new Point(18, 10), new Point(20, 12), new Point(12, 12),
            new Point(8, 11), new Point(18, 4), new Point(14, 6), new Point(14, 9), /* FIN algerie */new Point(17, 1),
            new Point(24, 1), new Point(25, 4), new Point(25, 7), new Point(22, 9), new Point(25, 11),
            new Point(23, 13), new Point(19, 16), new Point(15, 16), new Point(15, 14), new Point(16, 12),
            new Point(13, 10), new Point(17, 8),
            new Point(10, 9), new Point(8, 7), new Point(14, 7), new Point(17, 6), new Point(21, 5), new Point(17, 4),
            new Point(20, 3),
            /* FIN SOUDAN */new Point(12, 1), new Point(18, 3), new Point(22, 5), new Point(22, 7), new Point(23, 9),
            new Point(21, 9), new Point(18, 9), new Point(15, 9), new Point(13, 11), new Point(11, 13),
            new Point(9, 16), new Point(5, 16), new Point(5, 13), new Point(3, 11), new Point(1, 9), new Point(4, 9),
            new Point(7, 9), new Point(12, 9), new Point(12, 7), new Point(12, 4), new Point(17, 6), new Point(8, 12),
            /* FIN MALI */new Point(9, 1), new Point(12, 2), new Point(15, 3), new Point(18, 5), new Point(21, 6),
            new Point(25, 8), new Point(21, 11), new Point(19, 12), new Point(17, 14), new Point(15, 16),
            new Point(10, 16),
            new Point(8, 14), new Point(5, 11), new Point(3, 9), new Point(3, 7), new Point(5, 5), new Point(7, 4),
            new Point(9, 4), new Point(8, 8), new Point(12, 13), new Point(15, 11), new Point(18, 9), new Point(13, 6),
            /* FIN BRESIL */
            new Point(2, 1), new Point(5, 1), new Point(11, 2), new Point(16, 3), new Point(18, 8),
            new Point(19, 10), new Point(21, 12), new Point(23, 12), new Point(23, 10), new Point(23, 8),
            new Point(25, 6), new Point(26, 8), new Point(26, 10), new Point(26, 12), new Point(25, 16),
            new Point(22, 16), new Point(19, 16), new Point(15, 16), new Point(13, 15), new Point(11, 13),
            new Point(9, 8), new Point(5, 4),
            new Point(3, 11), new Point(1, 9), new Point(1, 5), new Point(23, 14), new Point(17, 13), new Point(14, 8),
            new Point(12, 4), /* fin mexique */new Point(3, 1), new Point(2, 3), new Point(1, 5), new Point(1, 7),
            new Point(1, 9), new Point(3, 10), new Point(1, 11), new Point(5, 13), new Point(7, 15), new Point(11, 16),
            new Point(13, 14), new Point(15, 14), new Point(18, 14), new Point(20, 16), new Point(22, 14),
            new Point(24, 12), new Point(26, 10), new Point(26, 7), new Point(26, 3),
            new Point(24, 2), new Point(20, 2), new Point(18, 4), new Point(14, 4), new Point(13, 1), new Point(9, 1),
            new Point(7, 5), new Point(8, 11),
            new Point(15, 11), new Point(20, 9), new Point(22, 4), new Point(5, 4), new Point(12, 8),
            new Point(15, 8), /* FIN US */
            new Point(14, 1), new Point(11, 3), new Point(9, 5), new Point(7, 7), new Point(5, 10),
            new Point(5, 12), new Point(7, 14), new Point(10, 15), new Point(13, 16),
            new Point(17, 16), new Point(20, 16), new Point(23, 14), new Point(25, 12),
            new Point(25, 9), new Point(25, 7), new Point(25, 5), new Point(23, 3),
            new Point(21, 2), new Point(18, 2), new Point(14, 6), new Point(14, 10),
            new Point(20, 6), new Point(20, 12), new Point(10, 9), /* FIN CANADA */new Point(5, 1), new Point(2, 5),
            new Point(4, 8), new Point(7, 10), new Point(9, 10), new Point(11, 12), new Point(13, 13),
            new Point(15, 15), new Point(17, 16), new Point(19, 14), new Point(21, 12), new Point(23, 10),
            new Point(25, 9), new Point(27, 8), new Point(27, 6), new Point(27, 4), new Point(27, 1), new Point(25, 2),
            new Point(23, 3), new Point(19, 4), new Point(15, 4), new Point(13, 3), new Point(9, 1),
            new Point(7, 5), new Point(13, 7), new Point(17, 9), new Point(23, 7), new Point(17, 12),
            /* fin chine */new Point(7, 0), new Point(5, 2), new Point(6, 4), new Point(5, 6), new Point(3, 9),
            new Point(5, 10), new Point(5, 12),
            new Point(7, 13), new Point(9, 14), new Point(11, 15), new Point(13, 16), new Point(14, 14),
            new Point(16, 12), new Point(18, 10), new Point(20, 8), new Point(20, 6), new Point(20, 3),
            new Point(22, 5), new Point(23, 7), new Point(25, 7), new Point(27, 5),
            new Point(27, 2), new Point(26, 0), new Point(24, 0), new Point(21, 0), new Point(19, 1), new Point(17, 2),
            new Point(14, 2), new Point(11, 2), new Point(9, 4), new Point(9, 8), new Point(13, 4), new Point(17, 5),
            new Point(15, 8), new Point(12, 11), new Point(24, 3), /* FIN INDE */
            new Point(8, 1), new Point(2, 3), new Point(2, 5), new Point(2, 8),
            new Point(4, 10), new Point(6, 12), new Point(8, 14), new Point(10, 16), new Point(14, 16),
            new Point(18, 16),
            new Point(21, 12), new Point(23, 9),
            new Point(23, 6), new Point(21, 4), new Point(19, 2), new Point(15, 2), new Point(12, 2), new Point(6, 4),
            new Point(8, 8), new Point(16, 4), new Point(20, 7), new Point(20, 10), new Point(14, 12), new Point(13, 7),/*AUSTRALIE */
            new Point(6, 1), new Point(3, 3), new Point(2, 5), new Point(1, 7), new Point(1, 10), new Point(4, 11), new Point(8, 12), new Point(10, 12), new Point(12, 10), new Point(14, 10),
            new Point(16, 10), new Point(18, 12), new Point(19, 14), new Point(21, 15), new Point(23, 15), new Point(25, 14), new Point(26, 11), new Point(27, 9), new Point(27, 7), new Point(27, 5),
            new Point(27, 1), new Point(22, 2), new Point(20, 4), new Point(16, 4), new Point(14, 1), new Point(10, 1), new Point(7, 5), new Point(23, 5),
            new Point(7, 8), new Point(12, 6), new Point(16, 7), new Point(22, 10),

    };

    /*********************************************************************
     * Getters and setters
     *********************************************************************/

    public Point getCoordinates() {
        return coordinates[this.ordinal()];
    }

    public void setCoordinates(int x, int y) {
        coordinates[this.ordinal()] = new Point(x, y);
    }
}
