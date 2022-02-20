package com.marinagaisina.casestudy.controller;

import com.marinagaisina.casestudy.beans.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/udemy")
public class UdemyController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView callMethod(HttpServletRequest request, HttpSession session) throws Exception {

        ModelAndView response = new ModelAndView();
        response.setViewName("udemy/udemyPractice");

//put a value in the session
        List<String> toDoListObject = (List<String>) session.getAttribute("toDoListAttribute");
        if (toDoListObject == null) {
            toDoListObject = new ArrayList<>();
            session.setAttribute("toDoListAttribute", toDoListObject);
        }
        String item = request.getParameter("itemNameForm");
        response.addObject("itemParam", item);
        boolean isItemNotEmpty = item != null && item.trim().length() > 0;
        boolean isItemNotDuplicate = item != null && !toDoListObject.contains(item.trim());
//        if ((item != null) && (!item.trim().equals(""))) {
        if (isItemNotEmpty && isItemNotDuplicate) {
            toDoListObject.add(item.trim());
        }

        Student student1 = new Student();
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setGoldCustomer(false);
        Student student2 = new Student();
        student2.setFirstName("Maxwell");
        student2.setLastName("Johnson");
        student2.setGoldCustomer(false);
        Student student3 = new Student();
        student3.setFirstName("Mary");
        student3.setLastName("Public");
        student3.setGoldCustomer(true);
        List<Student> studentsList = new ArrayList<>();
        studentsList.addAll(Arrays.asList(student1, student2, student3));

        response.addObject("studentsList", studentsList);

        return response;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public ModelAndView formSubmit(HttpServletRequest request) {
        String usernameObject = request.getParameter("formUserName");
        String fullnameObject = request.getParameter("formFullName");
        String dropdownObject = request.getParameter("formDropdown");
        String[] languages = request.getParameterValues("favoriteLanguage");

        ModelAndView response = new ModelAndView();
        response.addObject("username", usernameObject);
        response.addObject("fullname", fullnameObject);
        response.addObject("dropdown", dropdownObject);
        response.addObject("languages", languages);

        // create cookie
        Cookie newCookie = new Cookie("udemyPractice.username", usernameObject);
        // set life span ... total number of seconds
        newCookie.setMaxAge(60*60*24*365); //1 year
        response.addObject(newCookie);

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println("Cookie : " + name + " = " + value);
            }
        } else {
            System.out.println("No cookies on request");
        }


        response.setViewName("udemy/udemySubmit");

        return response;
    }
}
