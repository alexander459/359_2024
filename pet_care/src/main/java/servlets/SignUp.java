package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
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

// This is to create a new account
@WebServlet(name = "SignUp", value = "/SignUp")
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        String queryFormatParameters = request.getReader().lines().reduce("", (accumulator, actual)
                -> accumulator + actual);
        System.out.println(queryFormatParameters);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());

        builder.setPrettyPrinting();

        Gson gson = builder.create();
        SignUpData data = gson.fromJson(queryFormatParameters, SignUpData.class);

        PetKeeper pk;
        PetOwner po;
        EditPetOwnersTable epot;
        EditPetKeepersTable epkt;

        if(data.getUser_type() == UserType.PET_KEEPER){
            pk = data.create_pet_keeper_from_sign_up_data();
            epkt = new EditPetKeepersTable();
            try {
                if(!epkt.check_data_valid(pk)){
                    response.setStatus(epkt.getStatus());
                    return;
                }
            } catch (SQLException | ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                System.out.println(e.getMessage());
                return;
            }

            try {
                epkt.addNewPetKeeper(pk);
            } catch (ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                System.out.println(e.getMessage());
                return;
            }
        }else{
            po = data.create_pet_owner_from_sign_up_data();
            epot = new EditPetOwnersTable();
            try {
                if(!epot.check_data_valid(po)){
                    response.setStatus(epot.getStatus());
                    return;
                }
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
                response.setStatus(ResponseValues.EXCEPTION);
                return;
            }

            try {
                epot.addNewPetOwner(po);
            } catch (ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                System.out.println(e.getMessage());
                return;
            }

        }
        if(data.getUser_type() == UserType.PET_OWNER){
            response.setStatus(ResponseValues.PET_OWNER_ADDED);
        }else{
            response.setStatus(ResponseValues.PET_KEEPER_ADDED);
        }

        request.getSession().setAttribute("user_type", data.getUser_type());
        request.getSession().setAttribute("username", data.getUsername());
    }

}

