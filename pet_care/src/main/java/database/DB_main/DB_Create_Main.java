package database.DB_main;

import database.init.Resources;
import database.tables.*;
import mainClasses.enums.UserType;

import java.sql.SQLException;
public class DB_Create_Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DB_Handle.create_database();
        create_tables();

        EditPetOwnersTable editPetOwnersTable = new EditPetOwnersTable();
        EditPetKeepersTable editPetKeepersTable = new EditPetKeepersTable();
        EditPetsTable editPetsTable = new EditPetsTable();
        EditBookingsTable editBookingsTable = new EditBookingsTable();

        new EditAdminTable().addAdminFromJSON(Resources.adminJSON);

        editPetOwnersTable.addPetOwnerFromJSON(Resources.petOwnerJSON);
        editPetOwnersTable.addPetOwnerFromJSON(Resources.petOwner2JSON);
        editPetOwnersTable.addPetOwnerFromJSON(Resources.petOwner3JSON);
        editPetOwnersTable.addPetOwnerFromJSON(Resources.petOwner4JSON);

        editPetKeepersTable.addPetKeeperFromJSON(Resources.petKeeper1);
        editPetKeepersTable.addPetKeeperFromJSON(Resources.petKeeper2);
        editPetKeepersTable.addPetKeeperFromJSON(Resources.petKeeper3);
        editPetKeepersTable.addPetKeeperFromJSON(Resources.petKeeper4);
        editPetKeepersTable.addPetKeeperFromJSON(Resources.petKeeper5);
        editPetKeepersTable.addPetKeeperFromJSON(Resources.petKeeper6);

        editPetsTable.addPetFromJSON(Resources.pet1);
        editPetsTable.addPetFromJSON(Resources.pet2);
        editPetsTable.addPetFromJSON(Resources.pet3);
        editPetsTable.addPetFromJSON(Resources.pet4);

        editBookingsTable.addBookingFromJSON(Resources.booking1);
        editBookingsTable.addBookingFromJSON(Resources.booking2);
        editBookingsTable.addBookingFromJSON(Resources.booking3);

    }

    private static void create_tables() throws SQLException, ClassNotFoundException {
        new EditAdminTable().createAdminTable();
        new EditPetOwnersTable().createPetOwnersTable();
        new EditPetKeepersTable().createPetKeepersTable();
        new EditPetsTable().createPetsTable();
        new EditBookingsTable().createBookingTable();
    }

}
