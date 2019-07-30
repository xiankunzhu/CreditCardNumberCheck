package com.xiankunz.mypay;

public class CreditCardUtil {
    static CardType checkCCN(String cnn) {
        int len = cnn.length();
        int num = 0;
        switch (len) {
            case 14: // Diners Club and Carte Blanche
                if (cnn.charAt(0) != '3') {
                    return CardType.invalid;
                } else {
                    num = Integer.parseInt(cnn.substring(0, 6));
                    if ((300000 <= num && num <= 305999)
                        || (309500 <= num && num <= 309599)
                        || (360000 <= num && num <= 369999)
                        || (380000 <= num && num <= 399999)) {
                        return CardType.DinersClubandCarteBlanche;
                    }
                }
                break;
            case 15:
                // American Express
                if ((cnn.charAt(0) == '3') && (cnn.charAt(1) == '4' || cnn.charAt(1) == '7')) {
                    return CardType.AmericanExpress;
                }
                // enRoute
                if (cnn.substring(0, 4).equals("2014") || cnn.substring(0, 4).equals("2149")) {
                    return CardType.enRoute;
                }
                break;
            case 16:
                // discover
                num = Integer.parseInt(cnn.substring(0, 6));
                if ((601100 <= num && num <= 601109)
                    || (601120 <= num && num <= 601149)
                    || (601177 <= num && num <= 601179)
                    || (601186 <= num && num <= 601199)
                    || (644000 <= num && num <= 659999) || num == 601174) {
                    return CardType.Discover;
                }
                // MasterCard
                if ((222100 <= num && num <= 272099)
                    || (510000 <= num && num <= 559999)) {
                    return CardType.MasterCard;
                }
                // JCB
                num = Integer.parseInt(cnn.substring(0, 4));
                if (3528 <= num && num <= 3589) {
                    return CardType.JCB;
                }
                break;
            case 17:
            case 18:
                num = Integer.parseInt(cnn.substring(0, 4));
                if (3528 <= num && num <= 3589) {
                    return CardType.JCB;
                }
                break;
            case 19:
                num = Integer.parseInt(cnn.substring(0, 4));
                if (3528 <= num && num <= 3589) {
                    return CardType.JCB;
                }
                if (cnn.charAt(0) == '4') {
                    return CardType.Visa;
                }
                num = Integer.parseInt(cnn.substring(0, 2));
                if (num == 50 || (56 <= num && num <= 64) || (66 <= num && num <= 69)) {
                    return CardType.Maestro;
                }
                break;
        }
        return CardType.invalid;
    }
}
