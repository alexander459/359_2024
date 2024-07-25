package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import database.tables.EditPetsTable;
import mainClasses.PetKeeper;
import mainClasses.PetOwner;
import mainClasses.data.SignUpData;
import mainClasses.enums.ResponseValues;
import mainClasses.enums.UserType;
import mainClasses.helpers.LocalDateAdapter;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "ChangeInfo", value = "/ChangeInfo")
public class ChangeInfo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        String queryFormatParameters = request.getReader().lines().reduce("", (accumulator, actual)
                -> accumulator + actual);


        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());

        builder.setPrettyPrinting();
        Gson gson = builder.create();


        if(request.getSession().getAttribute("user_type") == UserType.PET_KEEPER){
            PetKeeper pk = gson.fromJson(queryFormatParameters, PetKeeper.class);
            try {
                new EditPetKeepersTable().updatePetKeeper(pk);
                response.setStatus(200);
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
                response.setStatus(ResponseValues.EXCEPTION);
            }
        }else{
            PetOwner po = gson.fromJson(queryFormatParameters, PetOwner.class);
            try {
                new EditPetOwnersTable().updatePetOwner(po);
                response.setStatus(200);
            } catch (SQLException | ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                System.out.println(e.getMessage());
            }
        }



    }

}

