package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.tables.EditAdminTable;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import database.tables.EditPetsTable;
import mainClasses.PetKeeper;
import mainClasses.PetOwner;
import mainClasses.data.PasswordData;
import mainClasses.data.SignUpData;
import mainClasses.enums.ResponseValues;
import mainClasses.enums.UserType;
import mainClasses.helpers.LocalDateAdapter;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "ChangePassword", value = "/ChangePassword")
public class ChangePassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        String queryFormatParameters = request.getReader().lines().reduce("", (accumulator, actual)
                -> accumulator + actual);

        System.out.println(queryFormatParameters);
        Gson gson = new Gson();
        PasswordData pd = gson.fromJson(queryFormatParameters, PasswordData.class);
        String new_password = pd.getPassword();
        String username = request.getSession().getAttribute("username").toString();
        UserType type = (UserType) request.getSession().getAttribute("user_type");

        if(type == UserType.PET_KEEPER){
            try {
                new EditPetKeepersTable().changePassPetKeeper(username, new_password);
                response.setStatus(200);
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
                response.setStatus(ResponseValues.EXCEPTION);
            }
        }else if(type == UserType.PET_OWNER){
            try {
                new EditPetOwnersTable().changePassPetOwner(username, new_password);
                response.setStatus(200);
            } catch (SQLException | ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                System.out.println(e.getMessage());
            }
        }else{
            try {
                new EditAdminTable().changePassAdmin(new_password);
                response.setStatus(200);
            } catch (SQLException | ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                System.out.println(e.getMessage());
            }
        }


    }

}

