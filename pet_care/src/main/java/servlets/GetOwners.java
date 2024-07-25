package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.tables.EditPetOwnersTable;
import database.tables.EditPetsTable;
import mainClasses.Pet;
import mainClasses.PetOwner;
import mainClasses.enums.ResponseValues;
import mainClasses.helpers.LocalDateAdapter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "GetOwners", value = "/GetOwners")
public class GetOwners extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try {
            ArrayList<PetOwner> po = new EditPetOwnersTable().getAllOwners();

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            String json = gson.toJson(po);
            response.getWriter().println(json);

        } catch (SQLException | ClassNotFoundException e) {
            response.setStatus(ResponseValues.EXCEPTION);
            throw new RuntimeException(e);
        }

    }

}
