package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import database.tables.EditPetsTable;
import mainClasses.Pet;
import mainClasses.PetKeeper;
import mainClasses.PetOwner;
import mainClasses.data.LogInData;
import mainClasses.enums.ResponseValues;
import mainClasses.enums.UserType;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeletePet", value = "/DeletePet")
public class DeletePet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        String queryFormatParameters = request.getReader().lines().reduce("", (accumulator, actual)
                -> accumulator + actual);

        Gson gson = new Gson();
        Pet p = gson.fromJson(queryFormatParameters, Pet.class);

        try {
            new EditPetsTable().deletePet(Long.toString(p.getPet_id()));
            response.setStatus(ResponseValues.PET_DELETED);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            response.setStatus(ResponseValues.EXCEPTION);
        }

    }

}
