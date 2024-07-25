package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.tables.EditPetKeepersTable;
import mainClasses.PetKeeper;
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

@WebServlet(name = "GetKeepers", value = "/GetKeepers")
public class GetKeepers extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try {
            ArrayList<PetKeeper> pk = new EditPetKeepersTable().getAllKeepers();

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            String json = gson.toJson(pk);
            response.getWriter().println(json);

        } catch (SQLException | ClassNotFoundException e) {
            response.setStatus(ResponseValues.EXCEPTION);
            throw new RuntimeException(e);
        }

    }

}
