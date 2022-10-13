package com.springmvc.demo.controllers;


import com.springmvc.demo.Core.*;
import com.springmvc.demo.models.Account;
import com.springmvc.demo.models.Accountinfo;
import com.springmvc.demo.repositories.AccountRepository;
import com.springmvc.demo.repositories.AccountinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Controller
@RequestMapping(path = "Account")
// http://localhost:8083/Account
public class AccountController {

    @Autowired
    private AccountRepository accountre;

    public AccountRepository getAccountRepository(){
       return this.accountre;
    }
    @Autowired
    private AccountinfoRepository accountinfoRepository;

    //GO TO LOGIN JSP : http://localhost:8083/Account
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String GetIntoJSP(ModelMap modelMap
    ) {
        modelMap.addAttribute("NewAccount",new Account());
        System.out.println("Go  --> Login.jsp");
        return "Login";
    }
    //GO TO Check LOGIN Function return profile JSP : http://localhost:8083/Account/Login
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String SendRequestLogin(@ModelAttribute("NewAccount")  Account account,
                                   HttpServletRequest req,
                                   HttpServletResponse res
                                   ) throws NoSuchAlgorithmException {
        System.err.println("Go send request login --> Login.jsp");
        if(accountre.findById(account.getGmailAccount()).isPresent()){
            if(accountre.findById( account.getGmailAccount()).get().getPasswordaccount().equals(SHA_256.MySHA256(account.getPasswordaccount()))&&accountre.findById( account.getGmailAccount()).get().getActive()!=null&&accountre.findById( account.getGmailAccount()).get().getActive().equals("active")){
                System.err.println("Login Sucess");
                //Session and cookies
                Account acf = accountre.findById(account.getGmailAccount()).get();
                req.getSession().setAttribute("Account",acf);
                Cookie usernamelogin = new Cookie("gmailAccount", accountre.findById(account.getGmailAccount()).get().getGmailAccount());
                Cookie passwordlogin = new Cookie("passwordaccount", accountre.findById(account.getGmailAccount()).get().getPasswordaccount());
                usernamelogin.setMaxAge(60 * 60 * 24);
                passwordlogin.setMaxAge(60 * 60 * 24);
                res.addCookie(usernamelogin);
                res.addCookie(passwordlogin);
                //
                return"redirect:/AccountInfo";
            }else{
                System.err.println("Login Failed");
                return"Login";
            }
        }else{
            System.err.println("Login Failed");
            return"Login";
        }
    }
    //GO TO Register JSP : http://localhost:8083/Account/Register
    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String GotoRegisterJSP(ModelMap modelMap,
                                  @ModelAttribute Account account
    ) {
        modelMap.addAttribute("reac",account);
        return "Registerv2";
    }
    //GO TO ConfirmEmail JSP : http://localhost:8083/Account/ConfirmEmail
    @RequestMapping(value = "/ConfirmEmail", method = RequestMethod.GET)
    public String ViewConfirmjsp( HttpServletRequest req,
                                  HttpServletResponse res

    ) {
        return "ConfirmEmail";
    }
    //GO TO check Confirm email function : http://localhost:8083/Account/ConfirmEmail
    @RequestMapping(value = "/ConfirmEmail", method = RequestMethod.POST)
    public String Confirm( HttpServletRequest req,
                                  HttpServletResponse res

    ) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie i : cookies) {
                if (i.getName().equals("Code_register")) {
                    try {
                        if (SHA_256.MySHA256(req.getParameter("Code_register")).equals(i.getValue())) {
                            //
                            String email_register = null;
                            String password_register = null;
                            Cookie[] cook2 = req.getCookies();
                            if (cook2 != null) {
                                for (Cookie o : cook2) {
                                    if (o.getName().equals("email_register")) {
                                        email_register = o.getValue();
                                    }
                                    if (o.getName().equals("password_register")) {
                                        password_register = o.getValue();
                                    }
                                }
                            }
                            accountre.save(new Account(email_register,"Client",password_register,"active"));
//                            new AccountInfoController().GenarateDefaulAccount(email_register);
                            //
                            Accountinfo acf = new Accountinfo();
                            acf.setGmailAccount(email_register);
                            acf.setBalance(0);
                            acf.setCreatedateaccount(new CurrentDate().GetDate());
                            acf.setFname(new RandomString().RandomString(5));
                            acf.setLname(new RandomString().RandomString(5));
                            acf.setPhoneNumber(new RandomString().RandomStringN(10));
                            acf.setGender("male");
                            acf.setImg(Base64.getDecoder().decode(new ImagesString().getDefaultAvatar()));
                            System.err.println(acf.toString());
                            System.err.println(accountinfoRepository.findAll().toString());
                            accountinfoRepository.save(acf);
                            //
                            return"redirect:/Account";
                        }
                    } catch (NoSuchAlgorithmException ex) {
                    }
                }
            }
        }
        return"redirect:/Account/Register";
    }
    //GO TO check send form register function : http://localhost:8083/Account/SendForm
    @RequestMapping(value = "/SendForm", method = RequestMethod.POST)
    public String SendRequestRegister(ModelMap modelMap,
                                  @ModelAttribute Account reac,
                                      HttpServletRequest req,
                                      HttpServletResponse res
    ) {
        req.getParameter("password_register");
        //Loi register cu
        boolean pass = false;
        String notice = "";
//        OWASP Validation Regular Expression https://owasp.org/www-community/OWASP_Validation_Regex_Repository
        if (!((String) req.getParameter("password_register")).equals(((String) req.getParameter("password_register2")))) {
            notice = "Repassword wrong!\n";
        }
        if (new Inpputer().InputRegex(req.getParameter("password_register"), "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,28}$") == false) {
            notice = "Minimum eight and maximum 28 characters, at least one uppercase letter, one lowercase letter, one number and one special character\n";
        }
        if (new Inpputer().InputRegex(req.getParameter("email_register"), "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$") == false) {
            notice = "Email Format is not good format \n";
        }
        if (!notice.isEmpty()) {
            req.setAttribute("notice", notice);
            return "Registerv2";
//            req.getRequestDispatcher("view/Registerv2.jsp").forward(req, res);
        }
        else  {
            String Email = req.getParameter("email_register");
            String PassWord = "";
            try {
                PassWord = new SHA_256().MySHA256(req.getParameter("password_register"));
            } catch (NoSuchAlgorithmException ex) {
                System.err.println(ex);
            }
            String Code = RandomNumber.RandNum(5);
            //ADD COOKIES REGISTER
            Cookie email_register = new Cookie("email_register", Email);
            Cookie password_register = new Cookie("password_register", PassWord);
            Cookie Code_register = null;
            try {
                Code_register = new Cookie("Code_register", SHA_256.MySHA256(Code));
            } catch (NoSuchAlgorithmException ex) {
                System.err.println(ex);
            }
            email_register.setMaxAge(180);
            password_register.setMaxAge(180);
            Code_register.setMaxAge(180);
            res.addCookie(email_register);
            res.addCookie(password_register);
            res.addCookie(Code_register);
            EmailSend.SenForm(
                    "bsd03rd@gmail.com", "Confirm your Email",
                    new FormEmail().returnForm(Code),
                    "bachjavaweb@gmail.com", "tntkcpodwlcmddjo");
//            res.sendRedirect("ConfirmEmail");
            return "redirect:/Account/ConfirmEmail";
           //Old Core register
        }
    }


}
