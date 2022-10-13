package com.springmvc.demo.controllers;

import com.springmvc.demo.Core.*;
import com.springmvc.demo.models.*;
import com.springmvc.demo.repositories.AccountinfoRepository;
import com.springmvc.demo.repositories.AdressAccountRepository;
import com.springmvc.demo.repositories.CityAddressRepository;
import com.springmvc.demo.repositories.DistrictAdressRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import java.util.Base64;
import java.util.RandomAccess;

@Controller
@RequestMapping(path = "AccountInfo")
public class AccountInfoController {

    @Autowired
    private AdressAccountRepository adressAccountRepository;
    @Autowired
    private AccountinfoRepository accountinfoRepository;
    @Autowired
    private CityAddressRepository cityAddressRepository;
    @Autowired
    private DistrictAdressRepository districtAdressRepository;


//    http://localhost:8083/AccountInfo
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String GetIntoJSP(ModelMap modelMap,
                             @ModelAttribute("AccountAdress") AdressAccount adressAccount,
                             HttpServletRequest req,
                             HttpServletResponse res
    ) {
        if (req.getSession().getAttribute("Account") == null) {
            return "redirect:/Account";
        }
        Account crac = (Account) req.getSession().getAttribute("Account");
        modelMap.addAttribute("account",accountinfoRepository.findById(crac.getGmailAccount()).get());
        System.out.println();
        return "ProfileV2";

    }

    @RequestMapping(value = "/Delete/{adid}", method = RequestMethod.GET)
    public String DeleteAd(ModelMap modelMap,
                                HttpServletRequest req,
                                HttpServletResponse res,
                                @PathVariable int adid
    ) {
        adressAccountRepository.deleteById(adid);
        return "redirect:/AccountInfo";
    }

    public String updatead(AdressAccount adressAccount, int adid) {
        adressAccountRepository.findById(adid).get().setIDCityAddress(adressAccount.getIDCityAddress());
        adressAccountRepository.findById(adid).get().setIDDistrictAddress(adressAccount.getIDDistrictAddress());
        adressAccountRepository.findById(adid).get().setStreetAddress(adressAccount.getStreetAddress());
        adressAccountRepository.save( adressAccountRepository.findById(adid).get());
        return "redirect:/AccountInfo";
    }


    @RequestMapping(value = "Update", method = RequestMethod.POST)
    public String UpdateProfile(ModelMap modelMap,
                                HttpServletRequest req,
                                HttpServletResponse res,
                                @ModelAttribute("acountinfo") Accountinfo accountinfo
    ) {

        Account account = (Account) req.getSession().getAttribute("Account");
        Accountinfo acf = accountinfoRepository.findById(account.getGmailAccount()).get();
        acf.setFname(accountinfo.getFname());
        acf.setLname(accountinfo.getLname());
        acf.setPhoneNumber(accountinfo.getPhoneNumber());
        acf.setGender(accountinfo.getGender());
        accountinfoRepository.save(acf);
        return "redirect:/AccountInfo";


    }
    public  void GenarateDefaulAccount(String email

    ) {
        Accountinfo acf = new Accountinfo();
        acf.setGmailAccount(email);
        acf.setBalance(0);
        acf.setCreatedateaccount(new CurrentDate().GetDate());
        acf.setFname(RandomString.RandomStringN(5));
        acf.setLname(RandomString.RandomStringN(5));
        acf.setPhoneNumber(RandomNumber.RandNum(10));
        acf.setGender("male");
        acf.setImg(Base64.getDecoder().decode(new ImagesString().getDefaultAvatar()));
        System.err.println(acf.toString());
        System.err.println(accountinfoRepository.findAll().toString());
        accountinfoRepository.save(acf);
    }

    //

    //
    @RequestMapping(value = "Uploadimg", method = RequestMethod.POST)
    public String UpdateIMGProfile(ModelMap modelMap,
                                HttpServletRequest req,
                                HttpServletResponse res,
                                @ModelAttribute("acountinfo") Accountinfo accountinfo
    ) throws ServletException, IOException {
        System.err.println("Vao code update anh roi");
        if (req.getParameter("uploadimg") != null) {
            Part filePart = req.getPart("imgitem");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = filePart.getInputStream();
//            byte[] img = IOUtils.readFully(fileContent, fileContent.available(), true);
            DataInputStream dataInputStr = new DataInputStream(fileContent);
            int count = dataInputStr.available();
            byte[] b = new byte[count];
            dataInputStr.readFully(b);

                if (fileName.isEmpty()) {
                    return "redirect:/AccountInfo";
                } else {
                    Account account = (Account) req.getSession().getAttribute("Account");
                    Accountinfo acf = accountinfoRepository.findById(account.getGmailAccount()).get();
                    acf.setImg(b);
                    accountinfoRepository.save(acf);
                    return "redirect:/AccountInfo";
                }

        }else{
            return "redirect:/AccountInfo";
        }
    }


    @RequestMapping(value = "uploadverify", method = RequestMethod.POST)
    public String VerifySend(ModelMap modelMap,
                                   HttpServletRequest req,
                                   HttpServletResponse res
    ) throws ServletException, IOException {

        Part filePart1 = req.getPart("img_item1");
        String fileName1 = Paths.get(filePart1.getSubmittedFileName()).getFileName().toString();
        if (fileName1.isEmpty()) {
            return "redirect:/AccountInfo";
        }
        Part filePart2 = req.getPart("img_item2");
        String fileName2 = Paths.get(filePart2.getSubmittedFileName()).getFileName().toString();
        if (fileName2.isEmpty()) {
            return "redirect:/AccountInfo";
        }

        InputStream fileContent1 = filePart1.getInputStream();
        DataInputStream dataInputStr1 = new DataInputStream(fileContent1);
        int count1 = dataInputStr1.available();
        byte[] b1 = new byte[count1];
        dataInputStr1.readFully(b1);

        InputStream fileContent2 = filePart2.getInputStream();
        DataInputStream dataInputStr2 = new DataInputStream(fileContent2);
        int count2 = dataInputStr2.available();
        byte[] b2 = new byte[count2];
        dataInputStr2.readFully(b2);

        ArrayList<byte[]> imgid = new ArrayList<>();
        imgid.add(b1);
        imgid.add(b2);

        System.err.println(imgid.toString());
        modelMap.addAttribute("notice"," window.onload = function(){\n" +
                "        document.getElementById(\"mynotice\").click();\n" +
                "    }");
        return "/ProfileV2";
    }

}
