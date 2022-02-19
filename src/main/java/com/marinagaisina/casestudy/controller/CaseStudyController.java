package com.marinagaisina.casestudy.controller;

import com.marinagaisina.casestudy.beans.RegisterFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/case")
public class CaseStudyController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showMainWindow(HttpServletRequest request, HttpSession session) throws Exception {

//        // these 3 are the same name as on the html form
//        String username = request.getParameter("username");
//        String firstName = request.getParameter("firstName");
//        String dropdown = request.getParameter("dropdown");
//
//        System.out.println("/indexSubmit - username = " + username);
//        System.out.println("/indexSubmit - firstName = " + firstName);
//        System.out.println("/indexSubmit - dropdown = " + dropdown);
//
//        // put a value in the session
//        System.out.println("/indexSubmit - adding user to session = " + username);
//        session.setAttribute("username", username);

        // this is going to the JSP page
        ModelAndView response = new ModelAndView();
//        response.addObject("username", username);
//        response.addObject("firstName", firstName);

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

        System.out.println(form);
        if (errors.hasErrors()) {
            for ( FieldError error : errors.getFieldErrors() ) {
                // add the error message to the errorMessages list in the form bean
                form.getErrorMessages().add(error.getDefaultMessage());
                System.out.println("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

        }
        response.addObject("formBeanKey", form);
        response.setViewName("casestudy-index/register");
        /*else {
            // there are no errors on the form submission lets redirect to the login page
            // right here that you would save the new user registration to the database
            // however we will get to this later in the week when spring JPA
            User user = new User();

            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setPassword(form.getPassword());
            user.setUsername(form.getUsername());

            userDao.save(user);

            response.setViewName("redirect:/login");
        }*/

        return response;
    }

    @RequestMapping(value = "/usersupport", method = RequestMethod.GET)
    public ModelAndView usersupportPage(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("casestudy-index/usersupport");

        return response;
    }
}
