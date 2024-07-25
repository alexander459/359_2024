package servlets;

import com.google.gson.Gson;
import database.tables.EditPetsTable;
import mainClasses.Pet;
import mainClasses.enums.ResponseValues;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "RegisterPet", value = "/RegisterPet")
public class RegisterPet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String queryFormatParameters = request.getReader().lines().reduce("", (accumulator, actual)
                -> accumulator + actual);

        Gson gson = new Gson();
        Pet p = gson.fromJson(queryFormatParameters, Pet.class);
        EditPetsTable ept = new EditPetsTable();
        try {
            if(!ept.check_pet_id_valid(p.getPet_id())){
                response.setStatus(ResponseValues.PET_ID_EXISTS);
                return;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            response.setStatus(ResponseValues.EXCEPTION);
            return;
        }

        try {
            ept.createNewPet(p);
            response.setStatus(ResponseValues.PET_ADDED);
        } catch (ClassNotFoundException e) {
            response.setStatus(ResponseValues.EXCEPTION);
        }
    }


}
