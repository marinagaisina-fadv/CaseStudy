package com.marinagaisina.casestudy.controller;

import com.marinagaisina.casestudy.beans.RegisterFormBean;
import com.marinagaisina.casestudy.database.dao.LocationDAO;
import com.marinagaisina.casestudy.database.dao.ParcelDAO;
import com.marinagaisina.casestudy.database.dao.UserDAO;
import com.marinagaisina.casestudy.database.entities.Location;
import com.marinagaisina.casestudy.database.entities.Parcel;
import com.marinagaisina.casestudy.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/case")
public class CaseStudyController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ParcelDAO parcelDAO;

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showMainWindow(HttpServletRequest request, HttpSession session) throws Exception {
        //String usernameURLParam = request.getParameter("username");
        //response.addObject("username", usernameURLParam);

        // put a value in the session
        String username = (String) session.getAttribute("username");
        System.out.println("username from session: "+username);
        if (username == null) {
            username = "Guest";
        }
        System.out.println("/index - adding user to session = " + username);
        session.setAttribute("username", username);

        ModelAndView response = new ModelAndView();
        response.setViewName("casestudy-index/index");

        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage(HttpServletRequest request, HttpSession session) throws Exception {

        ModelAndView response = new ModelAndView();
        response.setViewName("casestudy-index/register");

        RegisterFormBean registerFormBean = new RegisterFormBean();
        response.addObject("formBeanKey", registerFormBean);
        return response;
    }

    @RequestMapping(value = "/registerSubmit", method = RequestMethod.GET)
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();
        response.addObject("formBeanKey", form);

        System.out.println(form);

        if (errors.hasErrors()) {
            for ( FieldError error : errors.getFieldErrors() ) {
                // Class version: add the error message to the errorMessages list in the form bean
                //form.getErrorMessages().add(error.getDefaultMessage());
                //System.out.println("error field = " + error.getField() + " message = " + error.getDefaultMessage());

                // My version: by using a Map<String, FieldError> map, and keeping error object in it, I'll have an access to entire error object
                // and will be able to get anything like error.getDefaultMessage(), error.getRejectedValue()
                form.getMap().put(error.getField(), error);
            }

            response.setViewName("casestudy-index/register");
        }
        else {
            // there are no errors on the form submission lets redirect to the login page
            // right here that you would save the new user registration to the database
            // however we will get to this later in the week when spring JPA
            User user = new User();

            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            //user.setPassword(form.getPassword());
            // encrypt password
            String encryptedPassword = passwordEncoder.encode(form.getPassword());
            // set encrypted passwords to user object
            user.setPassword(encryptedPassword);
            //user.setConfirmPassword(encryptedPassword);

            user.setUsername(form.getUsername());

            // DefaultLocation is supposed to be taken from a manager's register new user approval letter.
            // Regular users (not administrators) will be able to track pallets that were created only in their location.
            // Administrators (managers) will be able to track all the pallets.
            Location userDefaultLocation = locationDAO.findById(1);
            user.setDefaultLocation(userDefaultLocation);

            userDAO.save(user);

            response.setViewName("casestudy-index/registerCreate");
        }

        return response;
    }

    @RequestMapping(value = "/usersupport", method = RequestMethod.GET)
    public ModelAndView usersupportPage(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("casestudy-index/usersupport");

        return response;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView userList(@RequestParam(required = false) String search) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("casestudy-index/userList");

        if ( !StringUtils.isEmpty(search)) {
            List<User> users = userDAO.findByFirstNameLike(search);
            response.addObject("userListKey", users);
//            for (User user : users) {
//                System.out.println(user.getId()+" "+user.getEmail());
//            }
        }
        return response;
    }
    @RequestMapping(value = "/allitems", method = RequestMethod.GET)
    public ModelAndView allitems(@RequestParam(required = false) String searchParcelsByCustomerNameLike,
                                 @RequestParam(required = false) String searchParcelsByItemNameLike) throws Exception {
        ModelAndView response = new ModelAndView();
        if ( !StringUtils.isEmpty(searchParcelsByCustomerNameLike)) {
            List<Parcel> parcels = parcelDAO.findAllParcelsOfCustomerNameLike(searchParcelsByCustomerNameLike);
            response.addObject("parcelListKey", parcels);
        }
        if ( !StringUtils.isEmpty(searchParcelsByItemNameLike)) {
            List<Parcel> parcels2 = parcelDAO.findParcelsByItemNameLike(searchParcelsByItemNameLike);
            response.addObject("parcelsByItemNameListKey", parcels2);
        }
        response.setViewName("casestudy-index/allitems");
        return response;
    }
}
