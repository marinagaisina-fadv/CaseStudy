package com.marinagaisina.casestudy.controller;

import com.marinagaisina.casestudy.beans.EditUserBean;
import com.marinagaisina.casestudy.beans.RegisterFormBean;
import com.marinagaisina.casestudy.database.dao.LocationDAO;
import com.marinagaisina.casestudy.database.dao.ParcelDAO;
import com.marinagaisina.casestudy.database.dao.UserDAO;
import com.marinagaisina.casestudy.database.entities.Location;
import com.marinagaisina.casestudy.database.entities.Parcel;
import com.marinagaisina.casestudy.database.entities.User;
import com.marinagaisina.casestudy.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Locale;

@Controller
public class CaseStudyController {

    public static final Logger LOG = LoggerFactory.getLogger(CaseStudyController.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ParcelDAO parcelDAO;

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public static ItemService itemService;

    @RequestMapping(value = "/case", method = RequestMethod.GET)
    public ModelAndView showMainWindow(@RequestParam(required = false) String username, @RequestParam(required = false) String password, HttpServletRequest request, HttpSession session) throws Exception {
        // without using @RequestParam, you'll do this:
        //String usernameURLParam = request.getParameter("username");
        //response.addObject("username", usernameURLParam);

        // if we are using @RequestParam, then take the URL params from there:
        if (username == null) {
            username = "Guest";
        } else {
            System.out.println("/MainPage - adding user to session = " + username);
            session.setAttribute("username", username);
        }
        // put a username value in the session
        /*String usernameFromSession = (String) session.getAttribute("username");
        System.out.println("username from session: "+username);*/

        ModelAndView response = new ModelAndView();
        response.addObject("username", username);

        response.setViewName("casestudy-index/index");

        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage() throws Exception {

        ModelAndView response = new ModelAndView();
        //response.setViewName("casestudy-index/register");
        //RegisterFormBean form = new RegisterFormBean();

            response.setViewName("casestudy-index/register");

        //response.addObject("formBeanKey", form);
        return response;
    }

    @RequestMapping(value = "/registerSubmit", method = RequestMethod.GET)
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();
        response.addObject("formBeanKey", form);

        //System.out.println(form);

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
            // there are no errors on the form submission so this is either a create or an update

            User user;

            if (form.getId() == null) {
                // this is a creation because the incoming id is null (I set id only in case we pass an id for editing user (in /register controller)
                user = new User();
            } else {
                user = userDAO.findById(form.getId());
            }
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

    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public ModelAndView editUser(@RequestParam Integer id) throws Exception {

        ModelAndView response = new ModelAndView();
        User user = userDAO.findById(id);

        EditUserBean form = new EditUserBean();
        form.setId(user.getId());   //gets data from the hidden input in JSP
        form.setEmail(user.getEmail());
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setUsername(user.getUsername());
        form.setPassword(user.getPassword());
        form.setConfirmPassword(user.getPassword());
        form.setLocationAddress(user.getDefaultLocation().getAddress());
        response.setViewName("casestudy-index/editUser");

        response.addObject("formBeanKey", form);
        return response;
    }

    @RequestMapping(value = "/editUserSubmit", method = RequestMethod.GET)
    public ModelAndView editUserSubmit(@Valid EditUserBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();
        response.addObject("formBeanKey", form);

        if (errors.hasErrors()) {
            for ( FieldError error : errors.getFieldErrors() ) {
                // My version: by using a Map<String, FieldError> map, and keeping error object in it, I'll have an access to entire error object
                // and will be able to get anything like error.getDefaultMessage(), error.getRejectedValue()
                form.getMap().put(error.getField(), error);
            }

            response.setViewName("casestudy-index/editUser");
        }
        else {
            // there are no errors on the form submission so this is an update
            User user = userDAO.findById(form.getId());
            user.setUsername(form.getUsername());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setPassword(passwordEncoder.encode(form.getPassword()));
            user.setPhone(form.getPhone());
            userDAO.save(user);

            response.setViewName("casestudy-index/editUserSuccess");
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
            // making case-insensitive:
            search = search.toLowerCase(Locale.ROOT);
            List<User> users = userDAO.findByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(search, search);
            response.addObject("userListKey", users);
//            for (User user : users) {
//                System.out.println(user.getId()+" "+user.getEmail());
//            }
        }
        return response;
    }
    @RequestMapping(value = "/packages", method = RequestMethod.GET)
    public ModelAndView packagesService(@RequestParam(required = false) String searchParcelsByCustomerNameLike,
                                 @RequestParam(required = false) String searchParcelsByItemNameLike) throws Exception {
        ModelAndView response = new ModelAndView();
        if ( !StringUtils.isEmpty(searchParcelsByCustomerNameLike)) {
            searchParcelsByCustomerNameLike = searchParcelsByCustomerNameLike.toLowerCase(Locale.ROOT);
            List<Parcel> parcels = null;
            try {
                parcels = parcelDAO.findAllParcelsOfCustomerNameLike(searchParcelsByCustomerNameLike);
            } catch (Exception e) {
                LOG.warn(String.valueOf(e.getCause()));
            }
            response.addObject("parcelListKey", parcels);
        }
        if ( !StringUtils.isEmpty(searchParcelsByItemNameLike)) {
            searchParcelsByItemNameLike = searchParcelsByItemNameLike.toLowerCase(Locale.ROOT);
            List<Parcel> parcels2 = parcelDAO.findParcelsByItemNameLike(searchParcelsByItemNameLike);
            response.addObject("parcelsByItemNameListKey", parcels2);
        }
        response.addObject("searchParcelsByItemNameLike", searchParcelsByItemNameLike);
        response.addObject("searchParcelsByCustomerNameLike", searchParcelsByCustomerNameLike);
        response.setViewName("casestudy-index/packages");
        return response;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ModelAndView itemsService(@RequestParam(required = false) String searchParcelsByCustomerNameLike,
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
        response.setViewName("casestudy-index/items");
        return response;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(@RequestParam(defaultValue = "2") Integer id) throws Exception {

        ModelAndView response = new ModelAndView();
        response.setViewName("casestudy-index/profile");
        User user = userDAO.findById(id);

        response.addObject("user", user);
        return response;
    }

}
