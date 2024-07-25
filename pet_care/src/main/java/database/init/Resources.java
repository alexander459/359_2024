/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.init;

import mainClasses.enums.*;

/**
 *
 * @author mountant
 */
public class Resources {

    public static String adminJSON = "{\"username\":\"admin\",\"password\":\"peni\"}";

    public static String petOwnerJSON = "{\"username\":\"mountanton\",\"email\":\"mike@csd.uoc.gr\",\"password\":\"peni\","
            + "\"firstname\":\"Michalis\",\"lastname\":\"Mountanton\",\"birthdate\":\"1992-06-03\",\"gender\":\"" + Gender.MALE + "\","
            + "\"country\":\"GR\",\"city\":\"Heraklion\",\"address\":\"CSD Voutes\",\"lat\":\"35.3053121\","
            + "\"lon\":\"25.0722869\",\"telephone\":\"1234567890\","
            + "\"job\":\"Researcher\","
            + "\"personalpage\":\"www.mountanton.gr\"}";

    public static String petOwner2JSON = "{\"username\":\"tsitsip\",\"email\":\"tsitsipas@tuc.gr\",\"password\":\"peni\","
            + "\"firstname\":\"Stefanos\",\"lastname\":\"Tsitsipas\",\"birthdate\":\"1998-08-12\",\"gender\":\"" + Gender.MALE + "\","
            + "\"country\":\"GR\",\"city\":\"Heraklion\",\"address\":\"Dimokratias 99\",\"lat\":\"35.3401097\","
            + "\"lon\":\"25.1562301\",\"telephone\":\"6977777777\","
            + "\"job\":\"Twitter/Tennis\","
            + "\"personalpage\":\"www.tsitsipas.gr\"}";

    public static String petOwner3JSON = "{\"username\":\"papadaki\",\"email\":\"papadaki@hmu.gr\",\"password\":\"peni\","
            + "\"firstname\":\"Eleni\",\"lastname\":\"Papadaki\",\"birthdate\":\"2002-10-10\",\"gender\":\"" + Gender.FEMALE + "\","
            + "\"country\":\"GR\",\"city\":\"Heraklion\",\"address\":\"Evans 25\",\"lat\":\"35.3370384\","
            + "\"lon\":\"25.1340107\",\"telephone\":\"2811111111\","
            + "\"job\":\"Influencer\","
            + "\"personalpage\":\"www.papadakiH.gr\"}";

    public static String petOwner4JSON = "{\"username\":\"dish_bar\",\"email\":\"dish@hotmail.com\",\"password\":\"peni\","
            + "\"firstname\":\"Dish\",\"lastname\":\"Bar\",\"birthdate\":\"1998-05-02\",\"gender\":\"" + Gender.FEMALE + "\","
            + "\"country\":\"GR\",\"city\":\"Heraklion\",\"address\":\"Papagiamali 10\",\"lat\":\"35.340312\","
            + "\"lon\":\"25.133874\",\"telephone\":\"2810223344\","
            + "\"job\":\"BarWoman\","
            + "\"personalpage\":\"www.barwoman.gr\"}";

    public static String petKeeper1 = "{\"username\":\"mitsos\",\"email\":\"mitsos@gmail.com\",\"password\":\"peni\","
            + "\"firstname\":\"Dimitris\",\"lastname\":\"Papadopoulos\",\"birthdate\":\"1995-12-11\",\"gender\":\"" + Gender.MALE + "\","
            + "\"country\":\"GR\",\"city\":\"Heraklion\",\"address\":\"Leof. Ikarou 115\",\"lat\":\"35.3397432\","
            + "\"lon\":\"25.1499244\",\"telephone\":\"6978912345\","
            + "\"job\":\"Developer\","
            + "\"property\":\"" + PetKeeperProperty.INTERIOR + "\"," + "\"propertydescription\":\"Diamerisma se polukatoikia\","
            + "\"hosting\":\"" + Hosting.BOTH + "\","
            + "\"catprice\":\"9\"," + "\"dogprice\":\"11\","
            + "\"personalpage\":\"www.mitsos.gr\"}";

    public static String petKeeper2 = "{\"username\":\"georgia\",\"email\":\"georgia@gmail.com\",\"password\":\"peni\","
            + "\"firstname\":\"Georgia\",\"lastname\":\"Kapellaki\",\"birthdate\":\"1985-12-11\",\"gender\":\"" + Gender.FEMALE + "\","
            + "\"country\":\"GR\",\"city\":\"Heraklion\",\"address\":\"Leof. Knossou 120\",\"lat\":\"35.3226421\","
            + "\"lon\":\"25.1387469\",\"telephone\":\"6988776655\","
            + "\"job\":\"Singer\","
            + "\"property\":\"" + PetKeeperProperty.EXTERIOR + "\"," + "\"propertydescription\":\"Auli se monokatoikia\","
            + "\"hosting\":\"" + Hosting.DOG_KEEPING + "\","
            + "\"catprice\":\"0\"," + "\"dogprice\":\"10\","
            + "\"personalpage\":\"www.georgia.com\"}";

    public static String petKeeper3 = "{\"username\":\"yannis\",\"email\":\"john@gmail.com\",\"password\":\"peni\","
            + "\"firstname\":\"Yannis\",\"lastname\":\"Nikolaidis\",\"birthdate\":\"1983-03-03\",\"gender\":\"" + Gender.MALE+ "\","
            + "\"country\":\"GR\",\"city\":\"Heraklion\",\"address\":\"Naxou 10\",\"lat\":\"35.3405493\","
            + "\"lon\":\"25.1460471\",\"telephone\":\"6948000805\","
            + "\"job\":\"Delivery\","
            + "\"property\":\"" + PetKeeperProperty.BOTH + "\"," + "\"propertydescription\":\"Monokatoikia kai auli\","
            + "\"hosting\":\"" + Hosting.BOTH + "\","
            + "\"catprice\":\"8\"," + "\"dogprice\":\"12\","
            + "\"personalpage\":\"www.john.gr\"}";

    public static String petKeeper4 = "{\"username\":\"catmary\",\"email\":\"catmary@gmail.com\",\"password\":\"peni\","
            + "\"firstname\":\"Maria\",\"lastname\":\"Papaioannou\",\"birthdate\":\"2002-03-03\",\"gender\":\"" + Gender.FEMALE + "\","
            + "\"country\":\"GR\",\"city\":\"Heraklion\",\"address\":\"Leof. Ikarou 170\",\"lat\":\"35.3400419\","
            + "\"lon\":\"25.1491117\",\"telephone\":\"6991234567\","
            + "\"job\":\"Teacher\","
            + "\"property\":\"" + PetKeeperProperty.INTERIOR + "\"," + "\"propertydescription\":\"Mikro diamerisma\","
            + "\"hosting\":\"" + Hosting.CAT_KEEPING + "\","
            + "\"catprice\":\"8\"," + "\"dogprice\":\"0\","
            + "\"personalpage\":\"www.catmary.gr\"}";

    public static String petKeeper5 = "{\"username\":\"catmike\",\"email\":\"mike@gmail.com\",\"password\":\"peni\","
            + "\"firstname\":\"Mike\",\"lastname\":\"Katsoulis\",\"birthdate\":\"1994-03-03\",\"gender\":\"" + Gender.MALE + "\","
            + "\"country\":\"GR\",\"city\":\"Heraklion\",\"address\":\"Idomeneos 80\",\"lat\":\"35.3385087\","
            + "\"lon\":\"25.1334268\",\"telephone\":\"6933344455\","
            + "\"job\":\"Lawyer\","
            + "\"property\":\"" + PetKeeperProperty.INTERIOR + "\"," + "\"propertydescription\":\"Kentriko diamerisma\","
            + "\"hosting\":\"" + Hosting.CAT_KEEPING + "\","
            + "\"catprice\":\"7\"," + "\"dogprice\":\"0\","
            + "\"personalpage\":\"www.catmike.gr\"}";

    public static String petKeeper6 = "{\"username\":\"dogFriend\",\"email\":\"dogfriend@gmail.com\",\"password\":\"peni\","
            + "\"firstname\":\"Katerina\",\"lastname\":\"Nikolaou\",\"birthdate\":\"2001-03-03\",\"gender\":\"" + Gender.FEMALE + "\","
            + "\"country\":\"GR\",\"city\":\"Heraklion\",\"address\":\"Leof. Kalokerinou 16\",\"lat\":\"35.3387807\","
            + "\"lon\":\"25.1264856\",\"telephone\":\"6975559992\","
            + "\"job\":\"Doctor\","
            + "\"property\":\"" + PetKeeperProperty.BOTH + "\"," + "\"propertydescription\":\"Kentriko diamerisma stin Kalokerinou\","
            + "\"hosting\":\"" + Hosting.DOG_KEEPING + "\","
            + "\"catprice\":\"0\"," + "\"dogprice\":\"10\","
            + "\"personalpage\":\"www.nikolaou.gr\"}";

    public static String pet1 = "{\"pet_id\":\"1234554321\","
            + "\"owner_id\":\"1\","
            + "\"name\":\"Max\","
            + "\"type\":\"" + PetType.CAT + "\","
            + "\"breed\":\"" + BreedType.AEGEAN + "\","
            + "\"gender\":\"" + Gender.MALE + "\","
            + "\"birthyear\":\"2019\","
            + "\"weight\":\"4.1\","
            + "\"description\":\"Agrios gatos, thelei trofi 3 fores ti mera\","
            + "\"photo\":\"https://i.ytimg.com/vi/pM7RDz9BhUY/hqdefault.jpg\"}";

    public static String pet2 = "{\"pet_id\":\"1234556677\","
            + "\"owner_id\":\"2\","
            + "\"name\":\"Azor\","
            + "\"type\":\"" + PetType.DOG + "\","
            + "\"breed\":\"" + BreedType.LABRADOR + "\","
            + "\"gender\":\"" + Gender.MALE + "\","
            + "\"birthyear\":\"2020\","
            + "\"weight\":\"32.0\","
            + "\"description\":\"Volta 2 fores tin imera\","
            + "\"photo\":\"https://cf.ltkcdn.net/dogs/dog-breeds/images/orig/324775-1600x1066-labrador-retriever-breed.jpg\"}";

    public static String pet3 = "{\"pet_id\":\"1001001001\","
            + "\"owner_id\":\"3\","
            + "\"name\":\"Free\","
            + "\"type\":\"" + PetType.CAT + "\","
            + "\"breed\":\"" + BreedType.AEGEAN + "\","
            + "\"gender\":\"" + Gender.MALE + "\","
            + "\"birthyear\":\"2021\","
            + "\"weight\":\"3.8\","
            + "\"description\":\"Tha kratw trofi, psari kai solomo\","
            + "\"photo\":\"https://i.ytimg.com/vi/fSdO79eEVdY/maxresdefault.jpg\"}";

    public static String pet4 = "{\"pet_id\":\"1112223334\","
            + "\"owner_id\":\"4\","
            + "\"name\":\"Dish Cat\","
            + "\"type\":\"" + PetType.CAT + "\","
            + "\"breed\":\"" + BreedType.TURKISH + "\","
            + "\"gender\":\"" + Gender.MALE + "\","
            + "\"birthyear\":\"2018\","
            + "\"weight\":\"6\","
            + "\"description\":\"Mono gatotrofi\","
            + "\"photo\":\"https://lh3.googleusercontent.com/p/AF1QipO1VklGnUwqX7dmCxvHYPbNcJWLIj76gNnJ-80e=s680-w680-h510\"}";

    public static String booking1 = "{\"owner_id\":\"1\","
            + "\"pet_id\":\"1234554321\","
            + "\"keeper_id\":\"1\","
            + "\"fromDate\":\"2023-08-10\","
            + "\"toDate\":\"2023-08-17\","
            + "\"status\":\"finished\","
            + "\"price\":\"63\"}";

    public static String booking2 = "{\"owner_id\":\"2\","
            + "\"pet_id\":\"1234556677\","
            + "\"keeper_id\":\"2\","
            + "\"fromDate\":\"2023-12-12\","
            + "\"toDate\":\"2023-12-18\","
            + "\"status\":\"accepted\","
            + "\"price\":\"60\"}";

    public static String booking3 = "{\"owner_id\":\"3\","
            + "\"pet_id\":\"1001001001\","
            + "\"keeper_id\":\"4\","
            + "\"fromDate\":\"2023-11-11\","
            + "\"toDate\":\"2023-11-22\","
            + "\"status\":\"requested\","
            + "\"price\":\"88\"}";

    public static String message1 = "{\"booking_id\":\"1\","
            + "\"message\":\"Ti kanei o gatos?\","
            + "\"sender\":\"owner\","
            + "\"datetime\":\"2023-08-11 15:14:18\"}";


    public static String message2 = "{\"booking_id\":\"1\","
            + "\"message\":\"Einai mia xara twra koimatai\","
            + "\"sender\":\"keeper\","
            + "\"datetime\":\"2023-08-11 16:10:10\"}";


    public static String review1 = "{\"owner_id\":\"1\","
            +     "\"keeper_id\":\"1\","
            + "\"reviewText\":\"Ola teleia!!\","
            + "\"reviewScore\":\"5\"}";
}
