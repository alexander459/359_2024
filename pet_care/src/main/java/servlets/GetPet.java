package servlets;


import com.google.gson.Gson;
import database.tables.EditPetOwnersTable;
import database.tables.EditPetsTable;
import mainClasses.Pet;
import mainClasses.enums.ResponseValues;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "GetPet", value = "/GetPet")
public class GetPet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String logged_in_user = request.getSession().getAttribute("username").toString();

        try {
            Pet p = new EditPetsTable().get_pet(logged_in_user);
            if (p == null) {
                response.setStatus(ResponseValues.PET_NOT_FOUND);
            }else{
                String json = new Gson().toJson(p);
                response.setStatus(ResponseValues.PET_RETRIEVED);
                response.getWriter().println(json);
            }

        } catch (SQLException | ClassNotFoundException e) {
            response.setStatus(ResponseValues.EXCEPTION);
            System.out.println(e.getMessage());
        }

    }

}
