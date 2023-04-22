package fr.benjamindanlos.laptimes.F1.F12022.Enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Team
 */
public interface Team {

    static Team valueOf(int season, int value) {
        switch (season) {
            case 2020:
                return Team2020.valueOf(value);
            case 2021:
                return Team2021.valueOf(value);
            case 2022:
                return Team2022.valueOf(value);
            default:
                throw new IllegalArgumentException("Season season " + season + " not supported");
        }
    }

    int getValue();

    String name();

    /**
     * Team 2020
     */
    enum Team2020 implements Team {
        MERCEDES_2020(0),
        FERRARI_2020(1),
        RED_BULL_RACING_2020(2),
        WILLIAMS_2020(3),
        RACING_POINT_2020(4),
        RENAULT_2020(5),
        ALPHA_TAURI_2020(6),
        HAAS_2020(7),
        MCLAREN_2020(8),
        ALFA_ROMEO_2020(9),
        MCLAREN_1988(10),
        MCLAREN_1991(11),
        WILLIAM_1992(12),
        FERRARI_1995(13),
        WILLIAMS_1996(14),
        MCLAREN_1998(15),
        FERRARI_2002(16),
        FERRARI_2004(17),
        RENAULT_2006(18),
        FERRARI_2007(19),
        MCLAREN_2008(20),
        RED_BULL_2010(21),
        FERRARI_1976(22),
        ART_GRAND_PRIX_2020(23),
        CAMPOS_VEXATEC_RACING_2020(24),
        CARLIN_2020(25),
        CHAROUZ_RACING_SYSTEM_2020(26),
        DAMS_2020(27),
        RUSSIAN_TIME_2020(28),
        MP_MOTORSPORT_2020(29),
        PERTAMINA_2020(30),
        MCLAREN_1990(31),
        TRIDENT_2020(32),
        BWT_ARDEN_2020(33),
        MCLAREN_1976(34),
        LOTUS_1972(35),
        FERRARI_1979(36),
        MCLAREN_1982(37),
        WILLIAMS_2003(38),
        BRAWN_2009(39),
        LOTUS_1978(40),
        F1_GENERIC_CAR_2020(41),
        ART_GP_2019(42),
        CAMPOS_2019(43),
        CARLIN_2019(44),
        SAUBER_JUNIOR_CHAROUZ_2019(45),
        DAMS_2019(46),
        UNI_VIRTUOSI_2019(47),
        MP_MOTORSPORT_2019(48),
        PREMA_2019(49),
        TRIDENT_2019(50),
        ARDEN_2019(51),
        BENETTON_1994(53),
        BENETTON_1995(54),
        FERRARI_2000(55),
        JORDAN_1991(56);

        private static Map<Integer, Team2020> map = new HashMap<>();

        static {
            for (Team2020 team : Team2020.values()) {
                map.put(team.value, team);
            }
        }

        private int value;

        Team2020(int value) {
            this.value = value;
        }

        public static Team2020 valueOf(int value) {
            return map.get(value);
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Team 2021
     */
    enum Team2021 implements Team {
        MERCEDES_2021(0),
        FERRARI_2021(1),
        RED_BULL_RACING_2021(2),
        WILLIAMS_2021(3),
        ASTON_MARTIN_2021(4),
        ALPINE_2021(5),
        ALPHA_TAURI_2021(6),
        HAAS_2021(7),
        MCLAREN_2021(8),
        ALFA_ROMEO_2021(9),
        ART_GP_2019(42),
        CAMPOS_2019(43),
        CARLIN_2019(44),
        SAUBER_JUNIOR_CHAROUZ_2019(45),
        DAMS_2019(46),
        UNI_VIRTUOSI_2019(47),
        MP_MOTORSPORT_2019(48),
        PREMA_2019(49),
        TRIDENT_2019(50),
        ARDEN_2019(51),
        ART_GP_2020(70),
        CAMPOS_2020(71),
        CARLIN_2020(72),
        CHAROUZ_2020(73),
        DAMS_2020(74),
        UNI_VIRTUOSI_2020(75),
        MP_MOTORSPORT_2020(76),
        PREMA_2020(77),
        TRIDENT_2020(78),
        BWT_2020(79),
        HITECH_2020(80),
        MERCEDES_2020(85),
        FERRARI_2020(86),
        RED_BULL_2020(87),
        WILLIAMS_2020(88),
        RACING_POINT_2020(89),
        RENAULT_2020(90),
        ALPHA_TAURI_2020(91),
        HAAS_2020(92),
        MCLAREN_2020(93),
        ALFA_ROMEO_2020(94),
        PREMA_2021(106),
        UNI_VIRTUOSI_2021(107),
        CARLIN_2021(108),
        HITECH_2021(109),
        ART_GP_2021(110),
        MP_MOTORSPORT_2021(111),
        CHAROUZ_2021(112),
        DAMS_2021(113),
        CAMPOS_2021(114),
        BWT_2021(115),
        TRIDENT_2021(116);

        private static Map<Integer, Team2021> map = new HashMap<>();

        static {
            for (Team2021 team : Team2021.values()) {
                map.put(team.value, team);
            }
        }

        private int value;

        Team2021(int value) {
            this.value = value;
        }

        public static Team2021 valueOf(int value) {
            return map.get(value);
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Team 2022
     */
    enum Team2022 implements Team {
        MERCEDES_2022(0),
        FERRARI_2022(1),
        RED_BULL_RACING_2022(2),
        WILLIAMS_2022(3),
        ASTON_MARTIN_2022(4),
        ALPINE_2022(5),
        ALPHA_TAURI_2022(6),
        HAAS_2022(7),
        MCLAREN_2022(8),
        ALFA_ROMEO_2022(9),
        MERCEDES_2020(85),
        FERRARI_2020(86),
        RED_BULL_2020(87),
        WILLIAMS_2020(88),
        RACING_POINT_2020(89),
        RENAULT_2020(90),
        ALPHA_TAURI_2020(91),
        HAAS_2020(92),
        MCLAREN_2020(93),
        ALFA_ROMEO_2020(94),
        ASTON_MARTIN_DB11_V12(95),
        ASTON_MARTIN_VANTAGE_F1_EDITION(96),
        ASTON_MARTIN_VANTAGE_SAFETY_CAR(97),
        FERRARI_F8_TRIBUTO(98),
        FERRARI_ROMA(99),
        MCLAREN_720S(100),
        MCLAREN_ARTURA(101),
        MERCEDES_AMG_GT_BLACK_SERIES_SAFETY_CAR(102),
        MERCEDES_AMG_GTR_PRO(103),
        F1_CUSTOM_TEAM(104),
        PREMA_2021(106),
        UNI_VIRTUOSI_2021(107),
        CARLIN_2021(108),
        HITECH_2021(109),
        ART_GP_2021(110),
        MP_MOTORSPORT_2021(111),
        CHAROUZ_2021(112),
        DAMS_2021(113),
        CAMPOS_2021(114),
        BWT_2021(115),
        TRIDENT_2021(116),
        MERCEDES_AMG_GT_BLACK_SERIES(117);

        private static Map<Integer, Team2022> map = new HashMap<>();

        static {
            for (Team2022 team : Team2022.values()) {
                map.put(team.value, team);
            }
        }

        private int value;

        Team2022(int value) {
            this.value = value;
        }

        public static Team2022 valueOf(int value) {
            return map.get(value);
        }

        public int getValue() {
            return value;
        }
    }
}

