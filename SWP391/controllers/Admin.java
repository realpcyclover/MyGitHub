package com.springmvc.demo.controllers;

import com.springmvc.demo.Core.CurrentDate;
import com.springmvc.demo.models.Account;
import com.springmvc.demo.models.Accountinfo;
import com.springmvc.demo.models.InsuranceCard;
import com.springmvc.demo.models.Request;
import com.springmvc.demo.repositories.AccountRepository;
import com.springmvc.demo.repositories.AccountinfoRepository;
import com.springmvc.demo.repositories.InsuranceCardRepository;
import com.springmvc.demo.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(path = "AdminAccount")
//    http://localhost:8083/AdminAccount
public class Admin {

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountinfoRepository accountinfoRepository;

    @Autowired
    InsuranceCardRepository insuranceCardRepository;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String GetIntoJSP(ModelMap modelMap,
                             HttpServletResponse res,
                             HttpServletRequest req
    ) throws IOException {
//        res.sendRedirect("Admin/table.jsp");
        if(req.getSession().getAttribute("Account")==null){
            return "redirect:/Account";
        }
        modelMap.addAttribute("reqlist",requestRepository.findAll());
        return "table";
    }
//    http://localhost:8083/AdminAccount/accountmanager
    @RequestMapping(value = "accountmanager", method = RequestMethod.GET)
    public String AccountManagerJSP(ModelMap modelMap,
                             HttpServletResponse res,
                             HttpServletRequest req
    ) throws IOException {
        ArrayList<Accountinfo>  ac = (ArrayList<Accountinfo>) accountinfoRepository.findAll();
        modelMap.addAttribute("aclist",ac);
        return "AccountManager";
    }
//    http://localhost:8083/AdminAccount/insurancecardmanager
    @RequestMapping(value = "insurancecardmanager", method = RequestMethod.GET)
    public String InsuranceCardManagerJSP(ModelMap modelMap,
                                    HttpServletResponse res,
                                    HttpServletRequest req
    ) throws IOException {
        modelMap.addAttribute("iclist",insuranceCardRepository.findAll());
        return "InsuranceCardManager";
    }
//    http://localhost:8083/AdminAccount/ActiveAccount
    @RequestMapping(value = "ActiveAccount/{gmail}", method = RequestMethod.GET)
    public String ActiveAccount(ModelMap modelMap,
                                    HttpServletResponse res,
                                    HttpServletRequest req,
                                    @PathVariable String gmail
    ) throws IOException {
        Account ac = accountRepository.findById(gmail).get();
        if(ac.getActive()!=null&&ac.getActive().equals("active")){
            ac.setActive("deactive");
           accountRepository.save(ac) ;
        }else{
            ac.setActive("active");
            accountRepository.save(ac) ;
        }
        return "redirect:/AdminAccount/accountmanager";
    }
    //    http://localhost:8083/AdminAccount/ActiveAccount
    @RequestMapping(value = "ActiveCard/{id}", method = RequestMethod.GET)
    public String ActiveCard(ModelMap modelMap,
                                HttpServletResponse res,
                                HttpServletRequest req,
                                @PathVariable String id
    ) throws IOException {
        InsuranceCard ic = insuranceCardRepository.findById(id).get();
        if(ic.getActive()!=null&&ic.getActive().equals("Enable")){
            ic.setActive("Disable");
            insuranceCardRepository.save(ic) ;
        }else{
            ic.setActive("Enable");
            insuranceCardRepository.save(ic) ;
        }
        return "redirect:/AdminAccount/insurancecardmanager";
    }


//    http://localhost:8083/AdminAccount/addreq
    @RequestMapping(value = "addreq", method = RequestMethod.POST)
    public String NewRequest(ModelMap modelMap,
                             HttpServletResponse res,
                             HttpServletRequest req
    ) throws IOException, ServletException {
        // Get Data File to byte[]
        Part filePart = req.getPart("docitem");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        DataInputStream dataInputStr = new DataInputStream(fileContent);
        int count = dataInputStr.available();
        byte[] docx = new byte[count];
        dataInputStr.readFully(docx);
        req.getSession().setAttribute("doc",docx);
        System.out.println("Du lieu file docx:"+docx.toString());
        //Get input
        System.out.println("Du lieu Title :"+req.getParameter("title"));
        System.out.println("Du lieu Title :"+req.getParameter("content"));
        Account curentAccount=(Account)req.getSession().getAttribute("Account");
        Request newreq = new Request(UUID.randomUUID().toString(),curentAccount.getGmailAccount(),
                accountRepository.findById(curentAccount.getGmailAccount()).get().getTypeaccount(),
                Integer.parseInt(req.getParameter("level"))  ,
                req.getParameter("typereq"),
                req.getParameter("title"),
                req.getParameter("content"),
                docx,
                "SENT",
                new CurrentDate().GetDate());
        requestRepository.save(newreq);
        return "redirect:/AdminAccount";
    }
    //    http://localhost:8083/AdminAccount/addcard
    @RequestMapping(value = "addcard", method = RequestMethod.POST)
    public String NewCard(ModelMap modelMap,
                             HttpServletResponse res,
                             HttpServletRequest req
    ) throws IOException, ServletException {
        // Get Data File to byte[]
        Part filePart = req.getPart("docitem");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        DataInputStream dataInputStr = new DataInputStream(fileContent);
        int count = dataInputStr.available();
        byte[] docx = new byte[count];
        dataInputStr.readFully(docx);
        req.getSession().setAttribute("doc",docx);
        System.out.println("Du lieu file docx:"+docx.toString());
        InsuranceCard newcard = new InsuranceCard(UUID.randomUUID().toString(), req.getParameter("namecard"),
                req.getParameter("descriptioncard"), req.getParameter("typecard"),
                docx, Double.parseDouble(req.getParameter("price")),
                "Enable");

//        InsuranceCard(UUID.randomUUID().toString(), req.getParameter("namecard"),
//                req.getParameter("descriptioncard"), req.getParameter("typecard"),
//                docx, Double.parseDouble(req.getParameter("price")),
//                        "Enable");

        insuranceCardRepository.save(newcard);
        return "redirect:/AdminAccount/insurancecardmanager";
    }

    @RequestMapping(value = "editcard", method = RequestMethod.POST)
    public String EditCard(ModelMap modelMap,
                          HttpServletResponse res,
                          HttpServletRequest req
    ) throws IOException, ServletException {
        // Get Data File to byte[]
        byte[] docx = null;
        Part filePart = req.getPart("docitem");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InsuranceCard oldcard = insuranceCardRepository.findById(req.getParameter("ID")).get();
        oldcard.setNameCard(req.getParameter("namecard").trim());
        oldcard.setDescriptionCard(req.getParameter("descriptioncard").trim());
        oldcard.setTypeCard(req.getParameter("typecard"));
        oldcard.setPriceByYear(Double.parseDouble(req.getParameter("price")));
        if(fileName.isEmpty()){
        }else{
            InputStream fileContent = filePart.getInputStream();
            DataInputStream dataInputStr = new DataInputStream(fileContent);
            int count = dataInputStr.available();
            docx = new byte[count];
            dataInputStr.readFully(docx);
            oldcard.setImg(docx);
        }
        insuranceCardRepository.save(oldcard);
        return "redirect:/AdminAccount/insurancecardmanager";
    }


}
